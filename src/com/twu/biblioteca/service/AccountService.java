package com.twu.biblioteca.service;

import com.twu.biblioteca.repo.AccountRepo;

public class AccountService {
    private final AccountRepo accountRepo = new AccountRepo();

    public boolean userLogin(int number, String password) {
        return accountRepo.userLogin(number, password);
    }
}
