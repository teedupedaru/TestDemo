package com.luminor.dc.testautomation.helper;

import com.luminor.dc.testautomation.helper.configuration.TestConfiguration;
import com.luminor.dc.testautomation.helper.yaml.LocalResource;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;


public class ConfigurationReader {

    private TestConfiguration configuration;

    public ConfigurationReader () {
        readConfiguration("config/config.yaml");
    }

    public ConfigurationReader (String configFile) {
        readConfiguration(configFile);
    }

    public TestConfiguration getConfiguration() {
        return configuration;
    }

    private void readConfiguration(String configFile) {
        Yaml yaml = new Yaml(new Constructor(TestConfiguration.class));
        configuration = yaml.load(LocalResource.getLocalResource(configFile));
    }
}