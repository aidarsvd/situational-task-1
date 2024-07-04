package kg.aidar.fin_tech_innovators.services.payment;

import kg.aidar.fin_tech_innovators.dto.ProceedPaymentDto;

public interface PaymentService {

    boolean makePayment(ProceedPaymentDto proceedPaymentDto);

}
