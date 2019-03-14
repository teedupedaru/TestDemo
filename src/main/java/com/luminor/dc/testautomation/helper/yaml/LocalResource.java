package com.luminor.dc.testautomation.helper.yaml;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LocalResource {

    public static String getLocalResource(String theName) {
        try {
            InputStream input;
            input = YamlDocument.class.getClassLoader().getResourceAsStream(theName);
            if (input == null) {
                throw new RuntimeException("Can not find " + theName);
            }
            StringBuilder buf = new StringBuilder(3000);
            int i;
            try (BufferedInputStream is = new BufferedInputStream(input)) {
                while ((i = is.read()) != -1) {
                    buf.append((char) i);
                }
            }
            String resource = buf.toString();
            // convert EOLs
            String[] lines = resource.split("\\r?\\n");
            StringBuilder buffer = new StringBuilder();
            for (String line : lines) {
                buffer.append(line);
                buffer.append("\n");
            }
            return buffer.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
