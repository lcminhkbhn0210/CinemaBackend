package com.example.cinema.management.services;

import com.example.cinema.management.dto.BillRequestDTO;
import com.example.cinema.management.dto.BillResponseDTO;
import com.example.cinema.management.model.Bill;
import com.example.cinema.management.model.Message;
import com.example.cinema.management.paypal.dto.BillPayPalRequestDTO;

import java.util.List;

public interface BillService {
    Message deletebill(long id);
    BillResponseDTO getBillById(long id);
    List<BillResponseDTO> getAllBill();
    BillResponseDTO createBillOffLine(BillRequestDTO billRequestDTO);
    BillResponseDTO checkExpireBill(BillRequestDTO billRequestDTO);
    double caculatedPrice(Bill bill);
    BillResponseDTO getBillByCode(String code);
    Bill createBillPayPal(BillPayPalRequestDTO billPayPalRequestDTO);
    Bill updateBill(Bill bill);
}
