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

import com.bankapi.vo.Currency;
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

    private Currency currency;
    private BigDecimal amount;

    public Payment(Account debitorAccount, Account creditorAccount, BigDecimal amount) {
        this.debitorAccount = debitorAccount;
        this.creditorAccount = creditorAccount;
        this.currency = debitorAccount.getCurrency();
        this.amount = amount;
    }

    public String getDebitorAccountNumber() {
        return debitorAccount.getNumber();
    }

    public String getCreditorAccountNumber() {
        return creditorAccount.getNumber();
    }
}
