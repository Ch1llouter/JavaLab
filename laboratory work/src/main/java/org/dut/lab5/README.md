# Лабораторна робота №5 (Супрун Євгеній, ПД-33)
---

## Мета:
- Зрозуміти базові принципи обробки виняткових ситуацій в Java.
- Створити спеціалізовані класи винятків для обробки конкретних помилкових сценаріїв.
- Використовувати пропагацію винятків.
---

## Звіт
**[AccountNotFoundException.java](AccountNotFoundException.java)[Decoder.java](Decoder.java)**  знаходиться в папці main/java/org.dut/lab5
```java
package org.dut.lab5;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message){
        super(message);
    }
    public AccountNotFoundException(){
    }
}
```
Це - спеціально створене виключення під банк, яке при активації означає, що акаунт не був знайдений.

---

**[Bank.java](Bank.java)**  знаходиться в папці main/java/org.dut/lab5
```java
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
```
Це - головний клас який відтворює банк.

---

**[BankAccount.java](BankAccount.java)**  знаходиться в папці main/java/org.dut/lab5
```java
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

```
Цей клас відтворює роботу акаунту в банці. Має потрібні дял його існування методи та поля.

---

**[InsufficientFundsException.java](InsufficientFundsException.java)**  знаходиться в папці main/java/org.dut/lab5
```java
package org.dut.lab5;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message){
        super(message);
    }
    public InsufficientFundsException(){

    }
}

```
Це - виключення, помилка, яка означає, що в акаунті не висиачає грошей за суму яку намагаються зняти.

---

**[NegativeAmountException.java](NegativeAmountException.java)**  знаходиться в папці main/java/org.dut/lab5
```java
package org.dut.lab5;

public class NegativeAmountException extends RuntimeException {
    public NegativeAmountException(String message){
        super(message);
    }
    public NegativeAmountException(){

    }
}
```
Це - виключення, помилка, яка означає, що до депозиту чи в акаунт намагаються додати негативне значення, що не є правильно.

---

Тести створені в класі **[BankTests.java](..%2F..%2F..%2F..%2F..%2Ftest%2Fjava%2Forg%2Fdut%2Flab5%2FBankTests.java)** (.java) і знаходиться в папці test/java/org.dut/lab5
```java
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

```
