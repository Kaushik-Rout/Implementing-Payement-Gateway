package com.pheonix.paymentservicefirstproject.configs;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayClientConfig {

    // Found under Usage - Using Private Auth
    // Initialize client
    //RazorpayClient instance = new RazorpayClient("key_id", "key_secret");
    // keeping the key and value openly is not safe do - add it to the application file / environment variable

    //@Value annpotation: used to read value from application.properties file.
    @Value("${razorpay.key.id}")
    private String razorpayKeyId;
    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    @Bean
    public RazorpayClient createRazorpayClient() throws RazorpayException {
        return new RazorpayClient("rzp_test_PWfqOFvQKAd59y", "XA9Q29EmcW3zs8jUWR5NGNqt");
    }
}
