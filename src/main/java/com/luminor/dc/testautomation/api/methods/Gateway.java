package com.luminor.dc.testautomation.api.methods;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luminor.dc.testautomation.api.model.Account;
import com.luminor.dc.testautomation.api.model.AuthResponse;
import com.luminor.dc.testautomation.api.model.Balance;
import com.luminor.dc.testautomation.api.model.ClientProfileResponse;
import com.luminor.dc.testautomation.api.model.PageResponse;
import com.luminor.dc.testautomation.api.model.PaymentsCountResponse;
import com.luminor.dc.testautomation.api.model.PaymentsResponse;
import com.luminor.dc.testautomation.enums.Page;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.lang.reflect.Type;
import java.util.List;

public class Gateway {

  private String serviceUrl;
  private String environment;
  private String apiLogin;
  private String apiPassword;
  private Gson gson;

  public Gateway(String environment, String login, String password) {
    this.environment = environment;
    this.apiLogin = login;
    this.apiPassword = password;
    setServiceUrl();
    gson = new Gson();
  }

  /**
   * Get Authorization tokens
   *
   * @param login user login
   * @param password user password
   * @return {@link AuthResponse} object with authorization tokens
   * @throws UnirestException throws on request exception
   */
  public AuthResponse getOauthToken(String login, String password, String country)
      throws UnirestException {

    HttpResponse<String> response =
        Unirest.post(serviceUrl + "/oauth/token")
            .basicAuth(apiLogin, apiPassword)
            .header("cache-control", "no-cache")
            .header("content-type", "application/x-www-form-urlencoded")
            .body(
                "grant_type=password&username="
                    + login
                    + "&password="
                    + password
                    + "&country="
                    + country)
            .asString();
    String responseBody = response.getBody();
    int statusCode = response.getStatus();
    if (statusCode != 200) {
      throw new IllegalStateException(
          "API. Invalid status code. Expected 200, but was: " + statusCode + "\n" + responseBody);
    }
    return gson.fromJson(responseBody, AuthResponse.class);
  }

  /**
   * Get page information
   *
   * @param page page enum value
   * @param token access token
   * @return {@link PageResponse} page object
   * @throws UnirestException throws on request exception
   */
  public PageResponse getPage(Page page, String token) throws UnirestException {
    String responseBody = makeGetRequest(token, serviceUrl + "/render/v1/page/" + page.getValue());
    return gson.fromJson(responseBody, PageResponse.class);
  }

  /**
   * Get client profile
   *
   * @param token access token
   * @return {@link ClientProfileResponse} client profile object
   * @throws UnirestException throws on request exception
   */
  public ClientProfileResponse getClientProfile(String token) throws UnirestException {
    String responseBody = makeGetRequest(token, serviceUrl + "/api/v1/client/profile");
    return gson.fromJson(responseBody, ClientProfileResponse.class);
  }

  /**
   * Get client's money balance
   *
   * @param token access token
   * @return {@link List<Balance>} get client balance list
   * @throws UnirestException throws on request exception
   */
  public List<Balance> getBalances(String token) throws UnirestException {

    String responseBody = makeGetRequest(token, serviceUrl + "/api/v1/accounts/balances");
    Type listType = new TypeToken<List<Balance>>() {}.getType();
    return gson.fromJson(responseBody, listType);
  }

  /**
   * Get client's payment information
   *
   * @param token access token
   * @return {@link PaymentsResponse} get payments list grouped by account and type
   * @throws UnirestException throws on request exception
   */
  public PaymentsResponse getPayments(String token) throws UnirestException {
    String responseBody = makeGetRequest(token, serviceUrl + "/api/v1/payments");
    return gson.fromJson(responseBody, PaymentsResponse.class);
  }

  /**
   * Get client's confirmed payments count
   *
   * @param token access token
   * @return {@link PaymentsCountResponse} payments count
   * @throws UnirestException throws on request exception
   */
  public PaymentsCountResponse getPaymentsToConfirmCount(String token) throws UnirestException {
    String responseBody = makeGetRequest(token, serviceUrl + "/api/v1/payments/pending/count");
    return gson.fromJson(responseBody, PaymentsCountResponse.class);
  }

  /**
   * Get client's processing payments count
   *
   * @param token access token
   * @return {@link PaymentsCountResponse} payments count
   * @throws UnirestException throws on request exception
   */
  public PaymentsCountResponse getPaymentsProcessingCount(String token) throws UnirestException {
    String responseBody = makeGetRequest(token, serviceUrl + "/api/v1/payments/processing/count");
    return gson.fromJson(responseBody, PaymentsCountResponse.class);
  }

  /**
   * Get client's unpaid payments count
   *
   * @param token access token
   * @return {@link PaymentsCountResponse} payments count
   * @throws UnirestException throws on request exception
   */
  public PaymentsCountResponse getPaymentsUnpaidCount(String token) throws UnirestException {
    String responseBody = makeGetRequest(token, serviceUrl + "/api/v1/payments/rejected/count");
    return gson.fromJson(responseBody, PaymentsCountResponse.class);
  }

  /**
   * Get account information
   *
   * @param token access token
   * @param accountId account id
   * @return {@link Account} account information
   * @throws UnirestException throws on request exception
   */
  public Account getAccountInformation(String token, String accountId) throws UnirestException {
    String responseBody = makeGetRequest(token, serviceUrl + "/api/v1/accounts/" + accountId);
    return gson.fromJson(responseBody, Account.class);
  }

  /**
   * Get all translations
   *
   * @return translation objects
   * @throws UnirestException throws on request exception
   */
  public String getTranslationsFromApi(String country) throws UnirestException {

    HttpResponse<String> response =
        Unirest.get(serviceUrl + "/render/v1/" + country + "/vocabulary")
            .basicAuth(apiLogin, apiPassword)
            .header("cache-control", "no-cache")
            .header("content-type", "application/json; charset=utf-8")
            .asString();

    String responseBody = response.getBody();
    int statusCode = response.getStatus();
    if (statusCode != 200) {
      throw new IllegalStateException(
          "API. Invalid status code. Expected 200, but was: " + statusCode + "\n" + responseBody);
    }
    return responseBody;
  }

  private String makeGetRequest(String token, String url) throws UnirestException {
    HttpResponse<String> response =
        Unirest.get(url).header("Authorization", "bearer " + token).asString();
    String responseBody = response.getBody();
    int statusCode = response.getStatus();
    if (statusCode != 200) {
      throw new IllegalStateException(
          "API. Invalid status code. Expected 200, but was: " + statusCode + "\n" + responseBody);
    }
    return responseBody;
  }

  private void setServiceUrl() {
    if ("dev".equals(environment)) {
      serviceUrl = "https://java-dev.baltic-amadeus.lt:8080";

    } else {
      throw new IndexOutOfBoundsException("Unknown environment provided: " + environment);
    }
  }
}
