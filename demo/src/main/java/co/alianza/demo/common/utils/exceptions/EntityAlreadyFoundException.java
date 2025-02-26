package co.alianza.demo.common.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityAlreadyFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EntityAlreadyFoundException(String message) {
        super(message);
    }

    public EntityAlreadyFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
