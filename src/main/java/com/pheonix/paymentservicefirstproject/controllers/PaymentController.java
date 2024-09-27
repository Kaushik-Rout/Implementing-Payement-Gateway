package com.pheonix.paymentservicefirstproject.controllers;

import com.pheonix.paymentservicefirstproject.dtos.GenerateaymentLinkRequestDto;
import com.pheonix.paymentservicefirstproject.services.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(@Qualifier("RazorpayPaymentGateway") PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // POST -> http://localhost:8080/payments
    @PostMapping// no need of additional "/generatePaymentLink" , cause using "/create" , "/show" etc is not a good practice - can be cracked by a hacker.
    public String generatePaymentLink(@RequestBody GenerateaymentLinkRequestDto requestDto) throws RazorpayException, StripeException {
        ///generally we should handle our exception instead of throwing it from the controller
        return paymentService.generatePaymentLink(requestDto.getOrderId());
    }

//    @PostMapping
//    public void handleWebhookEvent(@RequestBody Object object){
//
//        System.out .println("Webhook Triggered");
//
//    }
}
