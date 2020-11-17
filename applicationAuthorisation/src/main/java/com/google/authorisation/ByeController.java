package com.google.authorisation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ByeController {

    @RequestMapping("/bye")
    public String hello() {
        StringBuilder message = new StringBuilder("This is for testing purpose!");
        List l = new ArrayList();
        l.add(message);
        try {
            InetAddress ip = InetAddress.getLocalHost();
            message.append("\n printing arraylist object." + l);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return message.toString();
    }

}
