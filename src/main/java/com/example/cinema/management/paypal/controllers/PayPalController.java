package com.example.cinema.management.paypal.controllers;

import com.example.cinema.management.dto.BillResponseDTO;
import com.example.cinema.management.model.Bill;
import com.example.cinema.management.paypal.dto.BillDTO;
import com.example.cinema.management.paypal.dto.BillPayPalRequestDTO;
import com.example.cinema.management.paypal.dto.BillResponsePayPalDTO;
import com.example.cinema.management.paypal.model.QRCodeGenerator;
import com.example.cinema.management.paypal.services.PayPalService;
import com.example.cinema.management.repositories.BillRepository;
import com.example.cinema.management.services.BillService;
import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/paypal")
@RequiredArgsConstructor
public class PayPalController {

    private final PayPalService payPalService;


    @PostMapping("/payment")
    public ResponseEntity<BillResponsePayPalDTO> paymentByPayPal(@RequestBody BillPayPalRequestDTO billPayPalRequestDTO) throws IOException, InterruptedException {
        return new ResponseEntity<>(payPalService.createBill(billPayPalRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/success")
    public ResponseEntity<BillResponseDTO> paymentSuccess(@RequestParam("token") String token) throws IOException, InterruptedException {
        return new ResponseEntity<>(payPalService.successPaymentBill(token),HttpStatus.OK);
    }

    @GetMapping("/QR/{text}")
    public ResponseEntity<BufferedImage> generateQRCode(@PathVariable("text") String text) throws WriterException {
        return new ResponseEntity<>(QRCodeGenerator.generateQRCodeImage(text,200,200),HttpStatus.OK);
    }
}
