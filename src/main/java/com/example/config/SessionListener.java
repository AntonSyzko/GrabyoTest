package com.example.config;

import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Admin on 23.09.2016.
 */
@Configuration
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("==== Session is created ==== " );
        event.getSession().setMaxInactiveInterval(30*60);
        System.out.println(" max inactive  interval " + event.getSession().getMaxInactiveInterval());

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("==== Session is destroyed ====");

    }
}
