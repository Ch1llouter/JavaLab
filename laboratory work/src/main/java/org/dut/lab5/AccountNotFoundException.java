package org.dut.lab5;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message){
        super(message);
    }
    public AccountNotFoundException(){
    }
}
