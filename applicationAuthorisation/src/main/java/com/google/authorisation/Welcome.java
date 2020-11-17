package com.google.authorisation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class Welcome {

    @RequestMapping("/wel")
    public String hello() {
        StringBuilder message = new StringBuilder("This is for testing purpose!");
        Object o = new Object();
        int k = o.hashCode();
        try {
            InetAddress ip = InetAddress.getLocalHost();
            message.append(" printing Object value "+k+" Internate address.."+ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return message.toString();
    }

}
