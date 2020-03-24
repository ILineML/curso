package br.com.cursospring.curso.controllers.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static String decodeParametro(String s) {

        try{
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException ex){
            return "";
        }

    }

    public static List<Integer> decodeIds(String s){
        return Arrays.asList(s.split(",")).stream().map(current -> Integer.parseInt(current)).collect(Collectors.toList());
    }

}
