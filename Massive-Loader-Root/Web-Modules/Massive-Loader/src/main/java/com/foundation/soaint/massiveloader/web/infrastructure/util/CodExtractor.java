package com.foundation.soaint.massiveloader.web.infrastructure.util;

/**
 * Created by g2o on 31-Jul-17.
 */
public class CodExtractor {

    public static String process(String value){
        return value.substring(value.indexOf("/"));
    }
}
