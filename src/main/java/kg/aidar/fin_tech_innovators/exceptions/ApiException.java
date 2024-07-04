package kg.aidar.fin_tech_innovators.exceptions;

import lombok.Getter;


@Getter
public class ApiException extends RuntimeException {


    public ApiException(String message) {

    }

    public ApiException(Throwable cause, String message) {
        super(cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

}
