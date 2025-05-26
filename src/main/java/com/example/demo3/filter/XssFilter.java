package com.example.demo3.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class XssFilter implements Filter  {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        XssRequestWrapper sanitizedRequest = new XssRequestWrapper(httpRequest);

        filterChain.doFilter(sanitizedRequest, servletResponse);
    }
}
