package com.bankapi.data;

import java.util.List;

import com.bankapi.entity.Account;
import com.bankapi.entity.AccountOwner;
import org.springframework.data.repository.CrudRepository;

public interface AccountOwnerRepository extends CrudRepository<AccountOwner, String> {

    List<AccountOwner> findAccountOwnerByUserSsn(String ssn);

}
