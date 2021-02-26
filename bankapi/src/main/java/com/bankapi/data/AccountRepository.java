package com.bankapi.data;

import java.util.List;

import com.bankapi.entity.Account;
import com.bankapi.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {

}
