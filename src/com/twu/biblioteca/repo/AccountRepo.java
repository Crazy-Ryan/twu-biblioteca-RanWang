package com.twu.biblioteca.repo;

import com.twu.biblioteca.entity.Account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountRepo {

    private final List<Account> accounts;

    public AccountRepo() {
        accounts = new ArrayList(Arrays.asList(
                new Account(1234567, "Jack", "password"),
                new Account(2345678, "Ross", "passcode")
        ));
    }

    public boolean userLogin(int number, String password) {
        for (Account account : accounts) {
            if (number == account.getNumber() && password.equals(account.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
