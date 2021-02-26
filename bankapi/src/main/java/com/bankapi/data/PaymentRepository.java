package com.bankapi.data;

import java.util.List;

import com.bankapi.entity.Account;
import com.bankapi.entity.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

    List<Payment> findPaymentsByDebitorAccount_Number(String number);

}
