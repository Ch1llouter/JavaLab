package org.dut.lab5;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class Bank {
    final private ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
    private boolean accountExists(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }
    public void createAccount(String accountName, BigDecimal initialDeposit){
        double initialDepositString = initialDeposit.doubleValue();
        try{
            if(BigDecimal.valueOf(initialDepositString).compareTo(BigDecimal.ZERO) < 0){
                throw new NegativeAmountException("Initial deposit cannot be negative");
            }
            BankAccount newAccount = new BankAccount(accountName, initialDeposit, generateAccountNumber().toString());
            accounts.add(newAccount);
        }
        catch(NegativeAmountException exception){
            System.out.println(exception.getMessage());
        }
    }
    public void createAccount(String accountName, double initialDeposit){
        try{
            if(BigDecimal.valueOf(initialDeposit).compareTo(BigDecimal.ZERO) < 0){
                throw new NegativeAmountException("Initial deposit cannot be negative");
            }
            BankAccount newAccount = new BankAccount(accountName, initialDeposit, generateAccountNumber().toString());
            accounts.add(newAccount);
        }
        catch(NegativeAmountException exception){
            System.out.println(exception.getMessage());
        }
    }
    public BankAccount findAccount(String accountNumber){
        try{
            for(BankAccount account : accounts){
                if(account.getAccountNumber().equals(accountNumber)){
                    return account;
                }
                throw new AccountNotFoundException("Account " + accountNumber + " not found");
            }
        }
        catch(AccountNotFoundException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }
    public boolean transferMoney(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        BankAccount fromAccount = null;
        BankAccount toAccount = null;

        for(BankAccount account : accounts){
            if(fromAccountNumber.equals(account.getAccountNumber()) && fromAccount == null){
                fromAccount = account;
            }
            if(toAccountNumber.equals(account.getAccountNumber()) && fromAccount == null){
                toAccount = account;
            }
        }
        try {
            if(fromAccount == null || toAccount == null){
                throw new AccountNotFoundException("There is a problem to find an account/accounts");
            }
            if(amount.compareTo(fromAccount.getBalance()) > 0){
                throw new InsufficientFundsException("Not enough funds to transfer");
            }
            return true;
        }
        catch(AccountNotFoundException | InsufficientFundsException exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }
    public boolean transferMoney(String fromAccountNumber, String toAccountNumber, double amount) {
        BankAccount fromAccount = null;
        BankAccount toAccount = null;

        for(BankAccount account : accounts){
            if(fromAccountNumber.equals(account.getAccountNumber()) && fromAccount == null){
                fromAccount = account;
            }
            if(toAccountNumber.equals(account.getAccountNumber()) && fromAccount == null){
                toAccount = account;
            }
        }
        try {
            if(fromAccount == null || toAccount == null){
                throw new AccountNotFoundException("There is a problem to find an account/accounts");
            }
            if(fromAccount.getBalance().doubleValue() < amount){
                throw new InsufficientFundsException("Not enough funds to transfer");
            }
            return true;
        }
        catch(AccountNotFoundException | InsufficientFundsException exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }
    private String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        Random random = new Random();
        accountNumber.setLength(5);

        while (accountExists(accountNumber.toString())) {
            for (int i = 0; i < accountNumber.capacity(); i++) {
                accountNumber.setCharAt(i, (char) (random.nextInt(94) + 33));
            }
        }

        return accountNumber.toString();
    }
}
