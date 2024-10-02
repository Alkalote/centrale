package fr.hb.jg.centrale.advisor;

import fr.hb.jg.centrale.custom_response.CustomMessageResponse;
import fr.hb.jg.centrale.exception.AlreadyActiveException;
import fr.hb.jg.centrale.exception.ExpiredCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RuntimeResponse {

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    CustomMessageResponse entityNotFoundHandler(RuntimeException exception) {


        if(exception instanceof AlreadyActiveException){
            CustomMessageResponse response = new CustomMessageResponse();

            response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
            response.setMessage(exception.getMessage());
            return response;

        } else if (exception instanceof ExpiredCodeException) {
            CustomMessageResponse response = new CustomMessageResponse();

            response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
            response.setMessage(exception.getMessage());
            return response;
        }

        CustomMessageResponse response = new CustomMessageResponse();

        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        response.setMessage(exception.getMessage());
        return response;

    }
}
