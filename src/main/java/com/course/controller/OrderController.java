package com.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.service.PaymentService;
import com.course.service.VNPayService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    VNPayService vnPayService;
    @Autowired
    PaymentService paymentService;

    @GetMapping("/vnpay-payment")
    public ResponseEntity<?> getUrl(@RequestParam("amount") int orderTotal, @RequestParam("orderInfo") String orderInfo) {
        String baseUrl = "http://localhost:5173";
        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
        return ResponseEntity.ok().body(vnpayUrl);
    }
    @GetMapping("/submitOrder")
    public ResponseEntity<?> paymentCompleted(HttpServletRequest request){
        int paymentStatus = vnPayService.orderReturn(request);
        String transactionCode = request.getParameter("vnp_OrderInfo");
        return ResponseEntity.ok().body(paymentStatus == 1 && !paymentService.existsByTransaction(transactionCode) ? request.getQueryString() : false);
    }
}
