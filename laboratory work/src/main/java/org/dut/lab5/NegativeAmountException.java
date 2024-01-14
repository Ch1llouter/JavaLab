package org.dut.lab5;

public class NegativeAmountException extends RuntimeException {
    public NegativeAmountException(String message){
        super(message);
    }
    public NegativeAmountException(){

    }
}
