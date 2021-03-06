package com.wind.banking.app.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wind.banking.app.models.entity.account.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
