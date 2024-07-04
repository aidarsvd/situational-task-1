package kg.aidar.fin_tech_innovators.services.payment.impl;

import kg.aidar.fin_tech_innovators.dto.PortfolioDto;
import kg.aidar.fin_tech_innovators.dto.ProceedPaymentDto;
import kg.aidar.fin_tech_innovators.entities.InvoiceEntity;
import kg.aidar.fin_tech_innovators.entities.PortfolioEntity;
import kg.aidar.fin_tech_innovators.enums.InvoiceStatus;
import kg.aidar.fin_tech_innovators.repositories.InvoiceRepository;
import kg.aidar.fin_tech_innovators.repositories.PortfolioRepository;
import kg.aidar.fin_tech_innovators.services.payment.PaymentService;
import kg.aidar.fin_tech_innovators.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PortfolioRepository portfolioRepository;
    private final InvoiceRepository invoiceRepository;
    private final UserService userService;

    @Override
    public boolean makePayment(ProceedPaymentDto proceedPaymentDto) {
        // todo check if requisite exists
        // todo create invoice
        InvoiceEntity invoiceEntity = new InvoiceEntity(proceedPaymentDto.getAmount());
        // Check if user owns account
        boolean isOwnedAccount = userService.getProfile().getPortfolio().stream().map(PortfolioDto::getId).toList().contains(proceedPaymentDto.getAccountId());
        if (isOwnedAccount) {
            PortfolioEntity owner = portfolioRepository.findById(proceedPaymentDto.getAccountId()).get();
            invoiceEntity.setSender(owner);
            // Initialize invoice
            if (portfolioRepository.existsByRequisite(proceedPaymentDto.getRecipientRequisite())) {
                // Find recipient
                PortfolioEntity recipient = portfolioRepository.findByRequisite(proceedPaymentDto.getRecipientRequisite());
                invoiceEntity.setRecipient(recipient);
                // Validating transaction
                if (owner.getBalance().compareTo(proceedPaymentDto.getAmount()) < 0) {
                    // Sender has low balance
                    log.info("Account: {} has insufficient balance to make transaction: ", owner.getId());
                    invoiceEntity.setStatus(InvoiceStatus.INSUFFICIENT_BALANCE);
                } else {
                    // Making transaction
                    owner.setBalance(owner.getBalance().subtract(proceedPaymentDto.getAmount()));
                    recipient.setBalance(recipient.getBalance().add(proceedPaymentDto.getAmount()));
                    invoiceEntity.setStatus(InvoiceStatus.PAYED);
                    portfolioRepository.save(recipient);
                    portfolioRepository.save(owner);
                }
            } else {
                log.info("Recipient: {} was not found", proceedPaymentDto.getRecipientRequisite());
                invoiceEntity.setStatus(InvoiceStatus.RECIPIENT_NOT_EXIST);
            }
            // Closing transaction
            invoiceRepository.save(invoiceEntity);
            return true;
        } else {
            log.info("User: {} does not own account: {}. Canceling invoice", userService.getProfile().getUsername(), proceedPaymentDto.getAccountId());
            invoiceEntity.setStatus(InvoiceStatus.CANCELED);
            invoiceRepository.save(invoiceEntity);
            return false;
        }
    }
}
