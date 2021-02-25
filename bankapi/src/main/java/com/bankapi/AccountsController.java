/*
 * © EVRY. All rights reserved.
 * This code/information is protected by national and international law and may not be used, copied, amended, compiled,
 * decompiled, transferred etc. without the explicitly written prior consent from EVRY.
 * Any use in violation of the said will be prosecuted and compensation will be claimed.
 */
package com.bankapi;

import com.bankapi.data.PaymentRepository;
import com.bankapi.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/accounts", produces="application/json")
public class AccountsController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping(path="/{accountId}/payments", produces="application/json")
    public Iterable<Payment> getPayments(@PathVariable("accountId") String accountNumber) {
        return paymentRepository.findPaymentsByDebitorAccount_Number(accountNumber);
    }

    @GetMapping(path="/{paymentId}", produces="application/json")
    public Payment byId(@PathVariable("paymentId") Long paymentId) {
        return paymentRepository.findById(paymentId).get();
    }


}
