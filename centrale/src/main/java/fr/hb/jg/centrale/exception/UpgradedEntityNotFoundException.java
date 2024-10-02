package fr.hb.jg.centrale.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpgradedEntityNotFoundException extends EntityNotFoundException {

    private String field;

    private Object value;

    private String entity;


    @Override
    public String toString(){

        return "An error happened with field : "+field+", value : "+value+", from entity : "+entity;
    }

}

