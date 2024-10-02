package fr.hb.jg.centrale.advisor;

import fr.hb.jg.centrale.custom_response.CustomResponse;
import fr.hb.jg.centrale.exception.UpgradedEntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UpgradedEntityNotFoundResponse {

    @ResponseBody
    @ExceptionHandler(UpgradedEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    CustomResponse entityNotFoundHandler(UpgradedEntityNotFoundException exception){

        CustomResponse response = new CustomResponse();

        response.setEntity(exception.getEntity());
        response.setField(exception.getField());
        response.setValue(exception.getValue());
        response.setStatus(HttpStatus.BAD_GATEWAY.value());
        return response;

    }
}
