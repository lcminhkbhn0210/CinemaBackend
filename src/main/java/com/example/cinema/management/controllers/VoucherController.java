package com.example.cinema.management.controllers;

import com.example.cinema.management.model.Voucher;
import com.example.cinema.management.model.VoucherCustomer;
import com.example.cinema.management.services.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voucher")
@RequiredArgsConstructor
public class VoucherController {
    private final VoucherService voucherService;

    @PostMapping
    public ResponseEntity<Voucher> addVoucher(@RequestBody  Voucher voucher){
        return new ResponseEntity<>(voucherService.addVoucher(voucher), HttpStatus.CREATED);
    }

    @PostMapping("/voucherCustomer/{voucherId}/{customerId}")
    public ResponseEntity<VoucherCustomer> addVoucherCustomer(@PathVariable long voucherId, @PathVariable long customerId) {
        return new ResponseEntity<>(voucherService.addVoucherCustomer(customerId, voucherId), HttpStatus.CREATED);
    }

    @GetMapping("/voucherCustomer")
    public ResponseEntity<List<VoucherCustomer>> getListVoucherCustomerByUserName(@RequestParam("username") String username){
        return new ResponseEntity<>(voucherService.getListVoucherCustomerByUserName(username), HttpStatus.OK);
    }

}
