package bg.softuni.errors.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class ObjectNotFoundRestException extends RuntimeException {


    public ObjectNotFoundRestException(String message) {
        super(message);
    }
}
