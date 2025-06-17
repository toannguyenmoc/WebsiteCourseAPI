package com.course.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    // JWT có 3 phần: header , payload (nơi chứ claims), signature (chứa secretkey)
    public static final String SECRET = "5367566859703373367639792F423F452848284D6251655468576D5A71347437";

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email) // Định dang người dùng
                .setIssuedAt(new Date()) // thời điểm tạo token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 30 phút, thời điểm hết hạn
                .signWith(getSignKey(), SignatureAlgorithm.HS256) // sử dụng secret key và thuật toán SHA256 để bảo mật
                .compact(); // Tạo chuỗi JWT (header, payload, signature)

    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET); // sử dụng mảng byte để chứa chuỗi base64 đã giải mã
        return Keys.hmacShaKeyFor(keyBytes); // tạo key dạng HMAC-SHA256
    }

    private Claims extractAllClaims(String token) { // dùng để giải mã JWT và lấy phần payload (claims)
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey()) // lấy signature
                .build() // xây dựng parser
                .parseClaimsJws(token) // phân tích token
                .getBody(); // lấy payload
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) { // hàm generic để lấy thông tin linh
                                                                                 // hoạt dựa vào function
        final Claims claims = extractAllClaims(token); // toàn bộ payload của JWT
        return claimResolver.apply(claims); // lấy ra claim cần
    }

    public String extractUsername(String token) { // dùng để get username/email từ JWT
        return extractClaim(token, Claims::getSubject); // Claims::getSubject là một "method reference"
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) { // xem một token có hết hạn sử dụng hay chưa
        return extractExpiration(token).before(new Date()); // nếu ngày hết hạn (expiration) trước ngày hôm nay thì trả
                                                            // về true
    }

    public Boolean validateToken(String token, UserDetails userDetails) { // UserDetail được lấy từ
                                                                          // UserInfoDetailService
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
