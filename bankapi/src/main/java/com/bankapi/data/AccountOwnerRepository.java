/*
 * © EVRY. All rights reserved.
 * This code/information is protected by national and international law and may not be used, copied, amended, compiled,
 * decompiled, transferred etc. without the explicitly written prior consent from EVRY.
 * Any use in violation of the said will be prosecuted and compensation will be claimed.
 */
package com.bankapi.data;

import java.util.List;

import com.bankapi.entity.Account;
import com.bankapi.entity.AccountOwner;
import org.springframework.data.repository.CrudRepository;

public interface AccountOwnerRepository extends CrudRepository<AccountOwner, String> {

    List<AccountOwner> findAccountOwnerByUserSsn(String ssn);

}
