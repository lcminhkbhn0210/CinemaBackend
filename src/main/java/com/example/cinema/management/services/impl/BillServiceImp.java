package com.example.cinema.management.services.impl;

import com.example.cinema.management.dto.BillRequestDTO;
import com.example.cinema.management.dto.BillResponseDTO;
import com.example.cinema.management.model.Bill;
import com.example.cinema.management.model.BuyProduct;
import com.example.cinema.management.model.Message;
import com.example.cinema.management.model.Ticket;
import com.example.cinema.management.paypal.dto.BillPayPalRequestDTO;
import com.example.cinema.management.services.TicketService;
import com.example.cinema.management.repositories.BillRepository;
import com.example.cinema.management.repositories.BuyProductRepository;
import com.example.cinema.management.repositories.UserRepository;
import com.example.cinema.management.services.BillService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImp implements BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private BuyProductRepository buyProductRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Message deletebill(long id) {
        if(billRepository.findById(id).isPresent()){
            billRepository.deleteById(id);
            return new Message("200: OK","Da xoa bill thanh cong");
        }
        return new Message("200: OK","Xoa bill that bai, Bill khong ton tai");
    }

    @Override
    public BillResponseDTO getBillById(long id) {
        Optional<Bill> billDb = billRepository.findById(id);
        if(billDb.isPresent()){
            Bill bill = billDb.get();
            BillResponseDTO billResponseDTO = BillResponseDTO.toBillResponseDTO(bill);
            billResponseDTO.setMessage("Lay bill thanh cong");
            billResponseDTO.setStatus("200: OK");
            return billResponseDTO;
        }
        return new BillResponseDTO("Khong ton tai bill can tim","404: Not Found");
    }

    @Override
    public List<BillResponseDTO> getAllBill() {
        Page<Bill> billPage = billRepository.findAll(PageRequest.of(0,10));
        List<Bill> bills = billPage.stream().toList();
        List<BillResponseDTO> billResponseDTOS = new ArrayList<>();
        for(Bill bill:bills){
            billResponseDTOS.add(BillResponseDTO.toBillResponseDTO(bill));
        }
        return billResponseDTOS;
    }

    @Override
    public BillResponseDTO createBillOffLine(BillRequestDTO billRequestDTO) {
        String verification_code = RandomStringUtils.randomAlphabetic(16);
        while (billRepository.findByVerification_code(verification_code, new Date()) != null){
            verification_code = RandomStringUtils.randomAlphabetic(16);
        }
        Bill bill = new Bill();
        List<Ticket> tickets = new ArrayList<>();
        List<BuyProduct> buyProducts = new ArrayList<>();

        for(Long ticket_id:billRequestDTO.getListTicketId()){
            Ticket ticket = ticketService.updateTicket(ticket_id);
            ticket.setBill(bill);
            tickets.add(ticket);
        }
        for (Long buyProduct_id:billRequestDTO.getListBuyProductId()){
            BuyProduct buyProduct = buyProductRepository.findById(buyProduct_id).get();
            buyProduct.setBill(bill);
            buyProducts.add(buyProduct);
        }

        bill.setTicketList(tickets);
        bill.setBuyProducts(buyProducts);
        bill.setTotal(caculatedPrice(bill));
        bill.setSellDate(new Date());
        if(bill.getTicketList().size()!=0) bill.setExpire(bill.getTicketList().get(0).getShowTimes().getTimeend());
        bill.setEmployee(userRepository.findEmployeeById(billRequestDTO.getEmployee_id()).get());
        if(billRequestDTO.getCustomer_id()!=null) bill.setCustomer(userRepository.findCustomerById(billRequestDTO.getCustomer_id()).get());
        bill.setVerification_code(verification_code);
        BillResponseDTO billResponseDTO = BillResponseDTO.toBillResponseDTO(bill);
        billResponseDTO.setMessage("Tao Bill thanh cong");
        billResponseDTO.setStatus("202: Created");
        billRepository.save(bill);
        return billResponseDTO;
    }

    @Override
    public BillResponseDTO checkExpireBill(BillRequestDTO billRequestDTO) {
        return null;
    }

    @Override
    public double caculatedPrice(Bill bill) {
        double total = 0;
        for(Ticket ticket:bill.getTicketList()){
            total = total + ticket.getPrice();
        }
        for (BuyProduct buyProduct:bill.getBuyProducts()){
            total = total + buyProduct.getAmount()*buyProduct.getSellProduct().getPrice();
        }
        return total;
    }

    @Override
    public BillResponseDTO getBillByCode(String code) {
        Bill bill = billRepository.findByVerification_code(code,new Date());
        if(bill!=null){
            BillResponseDTO billResponseDTO = BillResponseDTO.toBillResponseDTO(bill);
            billResponseDTO.setMessage("Thanh cong");
            billResponseDTO.setStatus("200: Ok");
            return billResponseDTO;
        }
        return new BillResponseDTO("Khong ton tai Bill","404: Not Found");
    }

    @Override
    public Bill createBillPayPal(BillPayPalRequestDTO billPayPalRequestDTO) {
        String verification_code = RandomStringUtils.randomAlphabetic(16);
        while (billRepository.findByVerification_code(verification_code, new Date()) != null){
            verification_code = RandomStringUtils.randomAlphabetic(16);
        }
        Bill bill = new Bill();
        List<Ticket> tickets = new ArrayList<>();
        List<BuyProduct> buyProducts = new ArrayList<>();

        for(Long ticket_id:billPayPalRequestDTO.getListTicketId()){
            Ticket ticket = ticketService.updateTicket(ticket_id);
            ticket.setBill(bill);
            tickets.add(ticket);
        }
        for (Long buyProduct_id:billPayPalRequestDTO.getListBuyProductId()){
            BuyProduct buyProduct = buyProductRepository.findById(buyProduct_id).get();
            buyProduct.setBill(bill);
            buyProducts.add(buyProduct);
        }

        bill.setTicketList(tickets);
        bill.setBuyProducts(buyProducts);
        bill.setTotal(caculatedPrice(bill));
        bill.setSellDate(new Date());
        if(bill.getTicketList().size()!=0) bill.setExpire(bill.getTicketList().get(0).getShowTimes().getTimeend());
        if(billPayPalRequestDTO.getUserAccount()!=null) bill.setCustomer(userRepository.findCustomerByUsername(billPayPalRequestDTO.getUserAccount()).get());
        bill.setVerification_code(verification_code);
        return billRepository.save(bill);
    }

    @Override
    public Bill updateBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill getBillByPaypalToken(String token) {
        return billRepository.findByPaypalToken(token).orElseThrow();
    }
}
