package com.example.cinema.management.controllers;

import com.example.cinema.management.dto.BillRequestDTO;
import com.example.cinema.management.dto.BillResponseDTO;
import com.example.cinema.management.model.Message;
import com.example.cinema.management.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping("")
    public ResponseEntity<BillResponseDTO> createBillOffLine(@RequestBody BillRequestDTO billRequestDTO){
        return new ResponseEntity<>(billService.createBillOffLine(billRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<BillResponseDTO> getBillById(@PathVariable("id") long id){
        return new ResponseEntity<>(billService.getBillById(id),HttpStatus.OK);
    }

    @GetMapping("/bills")
    public List<BillResponseDTO> getAllBill(){
        return billService.getAllBill();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Message> deleteBill(@PathVariable("id") long id){
        return new ResponseEntity<>(billService.deletebill(id),HttpStatus.OK);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public BillResponseDTO getBillByCode(@RequestParam("code") String code){
        return billService.getBillByCode(code);
    }
}
