package ru.mts.demofintech.exceptions;

public class AnimalListOutOfBoundException extends ArrayIndexOutOfBoundsException{
    public AnimalListOutOfBoundException(String message) {
        super(message);
    }
}
