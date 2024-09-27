package com.pheonix.paymentservicefirstproject.services;

import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("StripePaymentGateway")
public class StripePaymentGateway implements PaymentService{

    @Value("${STRIPE_API_KEY}")
    private String stripeApiKey;

    @Override
    public String generatePaymentLink(Long orderId) throws StripeException {

//        Stripe.apiKey = "sk_test_tR3PYbcVNZZ796tH88S4VQ2u";
        Stripe.apiKey = stripeApiKey;

        // Get it from : https://docs.stripe.com/api/prices/create

        PriceCreateParams priceCreateParamsarams =
                PriceCreateParams.builder()
                        .setCurrency("usd")
                        .setUnitAmount(1000L)
                        .setRecurring(
                                PriceCreateParams.Recurring.builder()
                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                        .build()
                        )
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Price to view my first Project").build()
                        )
                        .build();

        Price price = Price.create(priceCreateParamsarams);

        //Get it from : https://docs.stripe.com/api/payment-link/create

        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(paymentLinkCreateParams);

        return paymentLink.toString();

    }
}
