package fr.hb.jg.centrale.custom_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomMessageResponse {

    private int status;
    private String message;
}
