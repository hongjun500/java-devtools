package com.hongjun.web.util.convert;

import lombok.extern.log4j.Log4j2;
import org.springframework.cglib.core.Converter;

import java.io.IOException;
import java.io.Reader;
import java.sql.Blob;
import java.sql.NClob;
import java.sql.SQLException;

/**
 * @author hongjun500
 * @date 2023/12/9 19:58
 * @tool ThinkPadX1隐士
 * Created with 2023.2.2.IntelliJ IDEA
 * Description: Blob, Clob 转 String
 */

@Log4j2
public class BClobToStringConverter implements Converter {

    /**
     * Clob、Blob 转换器
     *
     * @param value  值
     * @param target String
     * @return obj
     */
    @Override
    public Object convert(Object value, Class target, Object context) {
        if (value instanceof NClob && target == String.class) {
            try {
                NClob source = (NClob) value;
                long length = source.length();
                int bufferSize = 1024;
                StringBuilder sb = new StringBuilder((int) length);
                Reader reader = source.getCharacterStream();
                char[] buffer = new char[bufferSize];
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    sb.append(buffer, 0, n);
                    if (sb.length() > length) {
                        break;
                    }
                }
                return source.getSubString(1, (int) source.length());
            } catch (SQLException e) {
                log.error("NClobToStringConverter SQLException: ", e);
                return null;
            } catch (IOException e) {
                return null;
            }
        }
        if (value instanceof Blob && target == String.class) {
            try {
                Blob source = (Blob) value;
                return new String(source.getBytes(1, (int) source.length()));
            } catch (SQLException e) {
                log.error("NClobToStringConverter SQLException: ", e);
                return null;
            }
        }
        return value;
    }
}
