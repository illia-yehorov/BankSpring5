/*
 * © EVRY. All rights reserved.
 * This code/information is protected by national and international law and may not be used, copied, amended, compiled,
 * decompiled, transferred etc. without the explicitly written prior consent from EVRY.
 * Any use in violation of the said will be prosecuted and compensation will be claimed.
 */
package com.bankapi.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="account")
public class Account {
    @Id
    private String number;
    private String currency;
    private BigDecimal amount;

    public Account(String number, String currency, BigDecimal amount) {
        this.number = number;
        this.currency = currency;
        this.amount = amount;
    }
}
