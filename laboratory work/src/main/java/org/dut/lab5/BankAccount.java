package org.dut.lab5;

import java.math.BigDecimal;

public class BankAccount {
    final private String accountNumber;
    final private String accountName;
    private BigDecimal balance;
    public BankAccount(String accountName, BigDecimal initialBalance, String accountNumber) {
        this.accountName = accountName;
        this.balance = initialBalance;
        this.accountNumber = accountNumber;
    }
    public BankAccount(String accountName, double initialBalance, String accountNumber) {
        this.accountName = accountName;
        this.balance = BigDecimal.valueOf(initialBalance);
        this.accountNumber = accountNumber;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getAccountName() {
        return accountName;
    }
    public BigDecimal getBalance(){
        return balance;
    }
    public void deposit(BigDecimal amount){
        balance = balance.add(amount);
    }
    public BigDecimal withdraw(BigDecimal amount){
        balance = balance.subtract(amount);
        return amount;
    }
    public String getAccountSummary(){
        return String.format("Account number: %s, account name: %s, balance: %s",
                                        accountNumber,      accountName,   balance);
    }
}
