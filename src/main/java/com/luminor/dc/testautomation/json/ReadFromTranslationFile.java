package com.luminor.dc.testautomation.json;

import com.luminor.dc.testautomation.enums.Country;
import com.luminor.dc.testautomation.helper.TranslationFileGenerator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Map;


public class ReadFromTranslationFile {

    public static String getTranslation(String elementText, String language, Country country) {
        Object object = new Object();
        String fileLocation = TranslationFileGenerator.getFilePath(country);

        try {
            object = new JSONParser().parse(new FileReader(fileLocation));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject) object;

        Map translations = ((Map) jsonObject.get(elementText));

        if (translations == null) {
            throw new Error("\"" + elementText + "\" not found in translations file");
        }

        String localeAndLanguage = language + "-" + country;
        for (Object o : translations.entrySet()) {
            Map.Entry record = (Map.Entry) o;
            if (record.getKey().equals(localeAndLanguage.toLowerCase())) {
                return (String) record.getValue();
            }
        }
        throw new Error("\"" + elementText + "\" translation not found for language-country combination: " + localeAndLanguage.toLowerCase());
    }
}
