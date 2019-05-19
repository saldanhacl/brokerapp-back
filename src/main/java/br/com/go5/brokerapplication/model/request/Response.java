package br.com.go5.brokerapplication.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Response {

    private String errorMessage;

    private Object body;

}
