package at.fhhagenberg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class InvalidRequestBodyException extends RuntimeException {
    public InvalidRequestBodyException(String message) {
        super(message);
    }
}