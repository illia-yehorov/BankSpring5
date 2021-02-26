package com.bankapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@Entity
@Table(name="account_owner")
public class AccountOwner {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = User.class)
    private User user;

    @OneToOne(targetEntity = Account.class)
    private Account account;

    public AccountOwner(User user, Account account) {
        this.user = user;
        this.account = account;
    }
}
