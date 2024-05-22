package ru.mts.demofintech.exception_handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.mts.demofintech.exceptions.AnimalListOutOfBoundException;
import ru.mts.demofintech.exceptions.WrongYearException;

@ControllerAdvice
public class AnimalExceptionHandler {
    @ExceptionHandler(AnimalListOutOfBoundException.class)
    public ResponseEntity<String> handleAnimalListOutOfBoundException(AnimalListOutOfBoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongYearException.class)
    public ResponseEntity<String> handleWrongYearException(WrongYearException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
