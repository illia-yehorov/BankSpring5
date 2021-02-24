/*
 * © EVRY. All rights reserved.
 * This code/information is protected by national and international law and may not be used, copied, amended, compiled,
 * decompiled, transferred etc. without the explicitly written prior consent from EVRY.
 * Any use in violation of the said will be prosecuted and compensation will be claimed.
 */
package com.bankapi.entity;


import static javax.persistence.FetchType.LAZY;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@Entity
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = Account.class)
    @JsonIgnore
    private Account debitorAccount;

    @OneToOne(targetEntity = Account.class)
    @JsonIgnore
    private Account creditorAccount;

    private String currency;
    private BigDecimal amount;

    public Payment(Account debitorAccount, Account creditorAccount, String currency, BigDecimal amount) {
        this.debitorAccount = debitorAccount;
        this.creditorAccount = creditorAccount;
        this.currency = currency;
        this.amount = amount;
    }

    public String getDebitorAccountNumber() {
        return debitorAccount.getNumber();
    }

    public String getCreditorAccountNumber() {
        return creditorAccount.getNumber();
    }
}
