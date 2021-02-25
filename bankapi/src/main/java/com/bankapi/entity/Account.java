/*
 * © EVRY. All rights reserved.
 * This code/information is protected by national and international law and may not be used, copied, amended, compiled,
 * decompiled, transferred etc. without the explicitly written prior consent from EVRY.
 * Any use in violation of the said will be prosecuted and compensation will be claimed.
 */
package com.bankapi.entity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bankapi.AccountsController;
import com.bankapi.UserController;
import com.bankapi.vo.Currency;
import org.apache.commons.lang3.RandomStringUtils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@Entity
@Table(name="account")
public class Account extends RepresentationModel<Account> {
    @Id
    private String number;
    private Currency currency;
    private BigDecimal balance;

    public Account(BigDecimal balance, Currency currency) {
        this.number = generateAccountNumber();
        this.currency = currency;
        this.balance = balance;

    }

    public Account addLink() {
        Link selfLink = linkTo(methodOn(AccountsController.class)
                .getPayments(number)).withSelfRel();
        add(selfLink);
        return this;
    }

    public String generateAccountNumber() {
        return RandomStringUtils.random(11, "0123456789");
    }
}
