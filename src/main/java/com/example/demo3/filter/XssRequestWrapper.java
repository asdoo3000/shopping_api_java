package com.example.demo3.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class XssRequestWrapper extends HttpServletRequestWrapper {

    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        return XssSanitizerUtil.sanitize(super.getParameter(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) return null;

        return Arrays.stream(values)
                .map(XssSanitizerUtil::sanitize)
                .toArray(String[]::new);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = super.getParameterMap();
        Map<String, String[]> sanitized = new HashMap<>();
        map.forEach((key, value) ->
                sanitized.put(key, Arrays.stream(value).map(XssSanitizerUtil::sanitize).toArray(String[]::new)));
        return sanitized;
    }
}