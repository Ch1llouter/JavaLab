package org.dut.lab5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class BankTests {
    static Bank bank;

    @BeforeAll
    static void setUp(){
        bank = new Bank();
        bank.createAccount("Andrii", 2000);
    }
    @Test
    void Bank_void_createAccountNegative(){
        bank.createAccount("negativeValue", -2000);
    }
    @Test
    void Bank_void_createAccountPositive(){
        bank.createAccount("positiveValue", 2000);
    }
    @Test
    void Bank_void_findAccount(){
        bank.findAccount("3f534");
    }
    @Test
    void Bank_void_transferMoney(){
        bank.transferMoney("3f534", "32424", 324d);
    }
}