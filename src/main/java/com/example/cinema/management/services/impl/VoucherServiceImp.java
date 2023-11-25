package com.example.cinema.management.services.impl;

import com.example.cinema.management.model.Customer;
import com.example.cinema.management.model.Voucher;
import com.example.cinema.management.model.VoucherCustomer;
import com.example.cinema.management.repositories.UserRepository;
import com.example.cinema.management.repositories.VoucherCustomerRepository;
import com.example.cinema.management.repositories.VoucherRepository;
import com.example.cinema.management.services.UserService;
import com.example.cinema.management.services.VoucherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VoucherServiceImp implements VoucherService {

    private final VoucherRepository voucherRepository;
    private final UserRepository userRepository;
    private final VoucherCustomerRepository voucherCustomerRepository;
    @Override
    public Voucher addVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public VoucherCustomer addVoucherCustomer(long customerId, long voucherId) {
        VoucherCustomer voucherCustomer = new VoucherCustomer();
        Customer customer = userRepository.findCustomerById(customerId).get();
        Voucher voucher = voucherRepository.findById(voucherId).get();
        voucherCustomer.setCustomer(customer);
        voucherCustomer.setVoucher(voucher);
        return voucherCustomerRepository.save(voucherCustomer);
    }

    @Override
    public List<VoucherCustomer> getListVoucherCustomerByUserName(String username) {
        List<VoucherCustomer> voucherCustomers = voucherCustomerRepository.findAll();
        return voucherCustomers.stream().filter(voucherCustomer -> voucherCustomer.getCustomer().getUsername().equals(username)).collect(Collectors.toList());
    }
}
