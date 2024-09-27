package com.pheonix.paymentservicefirstproject.services;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {
    public String generatePaymentLink(Long orderId) throws RazorpayException, StripeException;
}
