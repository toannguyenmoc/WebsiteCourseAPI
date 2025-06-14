package com.course.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Util {

    // Encode chuỗi thành Base64
    public static String encode(String input) {
        if (input == null) return null;
        return Base64.getEncoder()
                     .encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    // Decode chuỗi Base64 về chuỗi gốc
    public static String decode(String base64) {
        if (base64 == null) return null;
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }

    // Encode byte[] thành Base64 string
    public static String encode(byte[] bytes) {
        if (bytes == null) return null;
        return Base64.getEncoder().encodeToString(bytes);
    }

    // Decode Base64 string thành byte[]
    public static byte[] decodeToBytes(String base64) {
        if (base64 == null) return null;
        return Base64.getDecoder().decode(base64);
    }

    // Kiểm tra định dạng có phải Base64 không
    public static boolean isValidBase64(String base64) {
        if (base64 == null) return false;
        try {
            Base64.getDecoder().decode(base64);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
