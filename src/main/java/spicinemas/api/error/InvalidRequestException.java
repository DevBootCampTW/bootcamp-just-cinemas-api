package spicinemas.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid parameters in url")
public class InvalidRequestException extends RuntimeException {



}
