package com.pheonix.paymentservicefirstproject.services;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("RazorpayPaymentGateway")
public class RazorpayPaymentGateway implements PaymentService{

    private RazorpayClient razorpayClient;

    public RazorpayPaymentGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }


    @Override
    public String generatePaymentLink(Long orderId) throws RazorpayException {
        //make a call to Razorpay to generate the payment Link
        //You anre not expected to remeber the implementation.
        // find the implementation in : https://github.com/razorpay/razorpay-java
        //maven user need to add the required dependency

        // Found under Usage - Using Private Auth
        // Initialize client
        //RazorpayClient instance = new RazorpayClient("rzp_test_PWfqOFvQKAd59y", "XA9Q29EmcW3zs8jUWR5NGNqt");

        //found in the link of 'Payment Link'
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000); // this is actually Rs.10.00 -//- every ammount such as 98.75 --stored as -> 98.75x100 = 9875 // the last two digits are always considered as number after decimal.
        paymentLinkRequest.put("currency","INR");
//        paymentLinkRequest.put("accept_partial",true);
//        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",System.currentTimeMillis() + 10 * 60 * 1000);//Suppose we wnat the expiry time to be 10 min in mili-sec= 10 min * 60sec * 1000 mili sec //paymentLinkRequest.put("expire_by",1691097057);
        paymentLinkRequest.put("reference_id",orderId.toString()); // reff_id is the order id //paymentLinkRequest.put("reference_id","TS1989");
        paymentLinkRequest.put("description","Payment for viewing my first project"); // add your own description
        JSONObject customer = new JSONObject();

        //instead of name, contact, email ---from OrderService---> order.name , order.contact, order.email
        //Call the OrderService to get details
        //OrderService order = restTemplate.getForObject("orderService URL", Order,class);

        customer.put("name","Kaushik Rout");
        customer.put("contact","+919692107969");
        customer.put("email","kaushikrout.official@gmail.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
//        notes.put("policy_name","Jeevan Bima");
//        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://scaler.com/"); //Callback
        paymentLinkRequest.put("callback_method","get");

        // this 'instance' won't work untill we create the Razorpay client object
        // to create a client object we need the "key_id", "key_secret"
        //i.e. // Initialize client
        //RazorpayClient instance = new RazorpayClient("key_id", "key_secret");
        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);

        return payment.get("short_url");
    }
}
