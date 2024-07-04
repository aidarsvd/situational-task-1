package kg.aidar.fin_tech_innovators.controllers;

import kg.aidar.fin_tech_innovators.dto.ProceedPaymentDto;
import kg.aidar.fin_tech_innovators.dto.SuccessDto;
import kg.aidar.fin_tech_innovators.services.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/proceed-payment")
    public ResponseEntity<SuccessDto> makePayment(@RequestBody ProceedPaymentDto proceedPaymentDto) {
        return ResponseEntity.ok(new SuccessDto(paymentService.makePayment(proceedPaymentDto)));
    }

}
