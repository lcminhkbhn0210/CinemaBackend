package com.example.cinema.management.paypal.controllers;

import com.example.cinema.management.dto.BillResponseDTO;
import com.example.cinema.management.paypal.dto.BillDTO;
import com.example.cinema.management.paypal.dto.BillResponsePayPalDTO;
import com.example.cinema.management.paypal.services.PayPalService;
import com.example.cinema.management.repositories.BillRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/paypal")
@RequiredArgsConstructor
public class PayPalController {

    private final PayPalService payPalService;
    private final BillRepository billRepository;

    @PostMapping("/payment")
    public ResponseEntity<BillResponsePayPalDTO> paymentByPayPal(@RequestBody BillDTO billDTO) throws IOException, InterruptedException {
        return new ResponseEntity<>(payPalService.createBill(billDTO), HttpStatus.CREATED);
    }

    @GetMapping("/success")
    public ResponseEntity<BillResponseDTO> paymentSuccess(HttpServletRequest request){
        System.out.println(request.getParameter("token"));
        return null;
    }
}
