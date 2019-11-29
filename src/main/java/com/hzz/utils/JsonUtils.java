package com.hzz.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: hezz
 */
public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonUtils() {
    }

    public static String toJsonString(Object data) {
        try {
            return null == data ? "" : mapper.writeValueAsString(data);
        } catch (IOException var2) {
            return null;
        }
    }

    public static <T> T parseObject(String content, Class<T> clazz) {
        try {
            return mapper.readValue(content, clazz);
        } catch (IOException var3) {
            return null;
        }
    }

    public static <T> T parseObject(String json, TypeReference<T> valueType) {
        try {
            return mapper.readValue(json, valueType);
        } catch (Exception e) {
            return null;
        }
    }

    public static void writeStringAsJson(HttpServletResponse response, Object result) {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.write(mapper.writeValueAsString(result));
        } catch (IOException e) {
        } finally {
            if (null != printWriter) {
                printWriter.close();
                printWriter = null;
            }
        }
    }

    static {
        mapper.disable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
        mapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }
}
