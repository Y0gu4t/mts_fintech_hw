package ru.mts.demofintech.exceptions;

public class WrongYearException extends IllegalArgumentException {

    public WrongYearException(String message) {
        super(message);
    }
}
