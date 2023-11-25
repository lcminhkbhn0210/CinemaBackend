package com.example.cinema.management.services;


import com.example.cinema.management.model.Voucher;
import com.example.cinema.management.model.VoucherCustomer;

import java.util.List;

public interface VoucherService {
    Voucher addVoucher(Voucher voucher);
    VoucherCustomer addVoucherCustomer(long customerId, long voucherId);
    List<VoucherCustomer> getListVoucherCustomerByUserName(String username);

}
