package br.senai.sp.info.pweb.keeper.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class MapUtils {

	public static Map<String, String> mapa(BindingResult bindingResult){
		Map<String, String> mapaErros = new HashMap<>();
		
		for (FieldError erro : bindingResult.getFieldErrors()) {
			mapaErros.put(erro.getField(), erro.getDefaultMessage());
		}
		
		return mapaErros;
	}
	
}
