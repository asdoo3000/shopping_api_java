package com.example.demo3.filter;

public class XssSanitizerUtil {
    public static String sanitize(String input) {
        if (input == null) return null;
        return input
                .replaceAll("(?i)<script.*?>.*?</script>", "")  // remove script tags
                .replaceAll("(?i)<.*?javascript:.*?>", "")       // remove javascript: attributes
                .replaceAll("<.*?>", "")                         // remove all HTML tags
                .replaceAll("[\"']", "");                        // remove quotes
    }
}