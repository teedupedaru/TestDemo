package com.luminor.dc.testautomation.helper;

import com.luminor.dc.testautomation.api.methods.Gateway;
import com.luminor.dc.testautomation.enums.Country;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static com.luminor.dc.testautomation.globals.Constants.*;

public class TranslationFileGenerator {

    public void createTranslationFile() {
        List<Country> countries = Arrays.asList(Country.values());
        prepareFiles(countries);
    }

    private static void prepareFiles(List<Country> countries) {
        for (Country country : countries) {
            Gateway gateway = new Gateway("dev", "", "");
            String fileLocation = getFilePath(country);

            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileLocation), StandardCharsets.UTF_8))) {
                String words = gateway.getTranslationsFromApi(country.name());
                writer.write(words);
            } catch (UnirestException | IOException e1) {
                e1.printStackTrace();
            }

            try {
                arrangeFileContent(fileLocation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void arrangeFileContent(String fileLocation) throws IOException {

        File file = new File(fileLocation);
        String fileContext = FileUtils.readFileToString(file);
        fileContext = fileContext.replaceAll("\\{\"", "{\n\"");
        fileContext = fileContext.replaceAll("},", "\n}\n");
        fileContext = fileContext.replaceAll("\",\"", "\",\n\"");
        fileContext = fileContext.replaceAll(":\\{", ":\n\\{");
        fileContext = fileContext.replaceAll("\"}", "\"\n}");
        FileUtils.write(file, fileContext);
    }

    public static String getFilePath(Country country) {
        switch (country.toString().toLowerCase()) {
            case "lt":
                return TRANSLATION_FILE_LT;
            case "lv":
                return TRANSLATION_FILE_LV;
            case "ee":
                return TRANSLATION_FILE_EE;
            default:
                throw new NoSuchElementException("Country: " + country + " for translation file output not defined");
        }

    }
}