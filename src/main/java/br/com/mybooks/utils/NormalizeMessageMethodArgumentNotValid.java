package br.com.mybooks.utils;

import java.util.List;

import org.springframework.validation.ObjectError;

public class NormalizeMessageMethodArgumentNotValid {

    public static String normalize(List<ObjectError> allErrors) {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < allErrors.size(); i++) {
            message.append(allErrors.get(i).getDefaultMessage());
            if (i < allErrors.size()-1) {
                message.append("; ");
            }
        }
        return message.toString();
    }

}
