package com.luminor.dc.testautomation;

import com.luminor.dc.testautomation.api.GatewayApi;
import com.luminor.dc.testautomation.enums.Runner;
import com.luminor.dc.testautomation.enums.Users;
import com.luminor.dc.testautomation.helper.ConfigurationReader;
import com.luminor.dc.testautomation.helper.EmailHelper;
import com.luminor.dc.testautomation.helper.configuration.TestConfiguration;

import java.util.ResourceBundle;

public final class Environment
{
    private static TestConfiguration configuration;
    private static ResourceBundle translation;
    private static GatewayApi gatewayApi;
    private static EmailHelper emailHelper;

    private Environment() {}

    /**
     * Get configuration parameters
     *
     * @return {@link TestConfiguration} configuration information
     */
    public static TestConfiguration getConfiguration() {
        if (null == configuration) {
            ConfigurationReader configurationReader = new ConfigurationReader();
            configuration = configurationReader.getConfiguration();
            String currentRunner = System.getProperty("currentRunner");
            if (currentRunner!= null && !currentRunner.isEmpty()) {
                configuration.currentRunner = Runner.getEnum(currentRunner);
            }
        }
        return configuration;
    }

    /**
     * Get API gateway instance
     *
     * @return {@link GatewayApi} instance
     */
    public static GatewayApi getGatewayApi() {
        if (gatewayApi == null) {
            gatewayApi = new GatewayApi(
                    getConfiguration().environment,
                    getConfiguration().apiAuth.login,
                    getConfiguration().apiAuth.password,
                    "000002",
                    "reach",
                    "lt");
        }
        return gatewayApi;
    }

    /**
     * Get API gateway instance
     *
     * @return {@link GatewayApi} instance
     */
    public static GatewayApi getGatewayApiWithUserProvided(Users user) {
        if (gatewayApi == null) {
            gatewayApi = new GatewayApi(
                    getConfiguration().environment,
                    getConfiguration().apiAuth.login,
                    getConfiguration().apiAuth.password,
                    user);
        }
        return gatewayApi;
    }
    
    /**
     * Get POP3 email client helper
     *
     * @return {@link EmailHelper} instance
     */
    public static EmailHelper getEmailHelper() {
        if (emailHelper == null) {
        	emailHelper = new EmailHelper(
        			getConfiguration().pop3.host,
        			getConfiguration().pop3.port,
        			getConfiguration().pop3.username,
        			getConfiguration().pop3.password);
        }
        return emailHelper;
    }

}
