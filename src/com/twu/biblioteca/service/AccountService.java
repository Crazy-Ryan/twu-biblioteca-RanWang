package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Account;
import com.twu.biblioteca.repo.AccountRepo;

public class AccountService {
    private final AccountRepo accountRepo = new AccountRepo();

    public Account userLogin(int number, String password) {
        return accountRepo.userLogin(number, password);
    }
}
