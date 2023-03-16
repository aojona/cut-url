package ru.kirill.cuturl.util;

import lombok.experimental.UtilityClass;
import org.springframework.validation.BindingResult;

@UtilityClass
public class BindingResultUtil {

    public static String getErrorMessage(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();
        bindingResult
                .getFieldErrors()
                .forEach(er -> {
                    errorMessage
                            .append(er.getField())
                            .append(": ")
                            .append(er.getDefaultMessage())
                            .append(";");
                });
        return errorMessage.toString();
    }
}