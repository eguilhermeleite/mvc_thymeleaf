package com.edvaldo.leite.controller.conversor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToInteger implements Converter<String, Integer> {

    @Override
    public Integer convert(String text) {
	text = text.trim();

	// Se o campo conter apenas digitos de 0 - 9 prossiga a execução
	if (text.matches("[0-9]+")) {
	    return Integer.valueOf(text);
	}
	// senão retorne nulo e o Bean Validation entra em execução
	return null;
    }
}
