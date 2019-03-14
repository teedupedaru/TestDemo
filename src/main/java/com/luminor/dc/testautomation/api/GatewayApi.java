package com.luminor.dc.testautomation.api;

import com.luminor.dc.testautomation.api.methods.Gateway;
import com.luminor.dc.testautomation.api.model.Account;
import com.luminor.dc.testautomation.api.model.Balance;
import com.luminor.dc.testautomation.api.model.PageResponse;
import com.luminor.dc.testautomation.api.model.PaymentsGroup;
import com.luminor.dc.testautomation.api.model.WidgetContent;
import com.luminor.dc.testautomation.api.model.child.PageContainer;
import com.luminor.dc.testautomation.api.model.child.PageWidget;
import com.luminor.dc.testautomation.api.model.child.Payment;
import com.luminor.dc.testautomation.enums.Page;
import com.luminor.dc.testautomation.enums.Users;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GatewayApi {

  private Gateway gateway;
  private String token;
  Logger log = Logger.getLogger(GatewayApi.class.getName());

  public GatewayApi(String environment, String apiLogin, String apiPassword) {
    gateway = new Gateway(environment, apiLogin, apiPassword);
  }

  public GatewayApi(
      String environment,
      String apiLogin,
      String apiPassword,
      String userLogin,
      String userPassword,
      String country) {
    gateway = new Gateway(environment, apiLogin, apiPassword);
    try {
      token = gateway.getOauthToken(userLogin, userPassword, country).access_token;
    } catch (UnirestException e) {
      log.log(Level.SEVERE, "Error getting authorization token", e);
    }
  }

  public GatewayApi(String environment, String apiLogin, String apiPassword, Users user) {
    gateway = new Gateway(environment, apiLogin, apiPassword);
    try {
      token =
          gateway.getOauthToken(user.getUsername(), user.getPassword(), user.getCountry())
              .access_token;
    } catch (UnirestException e) {
      log.log(Level.SEVERE, "Error getting authorization token", e);
    }
  }

  public Map<String, WidgetContent> getFrontPageWidgetsInformation() {
    Map<String, WidgetContent> widgets = new HashMap<>();
    try {
      PageResponse pageResponse = gateway.getPage(Page.FRONT_PAGE, token);
      for (PageContainer container : pageResponse.containers) {
        for (PageWidget pageWidget : container.widgets) {
          WidgetContent widget = new WidgetContent();
          widget.uuid = pageWidget.uuid;
          if (pageWidget.config != null) {
            if (pageWidget.config.action != null) {
              widget.actionName = pageWidget.config.action.name;
            }
            widget.label = pageWidget.config.fields.label;
          }
          if (!pageWidget.fields.isEmpty()) {
            widget.content = pageWidget.fields.get(0).content;
            widget.contentTitle = pageWidget.fields.get(0).content_title;
          }
          widget.name = pageWidget.name;
          widget.title = pageWidget.title;
          widgets.put(widget.name, widget);
        }
      }
    } catch (UnirestException e) {
      log.log(Level.SEVERE, "Error getting page information for " + Page.FRONT_PAGE.getValue(), e);
    }
    return widgets;
  }

  public WidgetContent getLegalPageInformation() {
    WidgetContent widget = new WidgetContent();
    try {
      PageWidget pageWidget = gateway.getPage(Page.LEGAL, token).containers.get(0).widgets.get(0);
      widget.uuid = pageWidget.uuid;
      if (pageWidget.config != null) {
        if (pageWidget.config.action != null) {
          widget.actionName = pageWidget.config.action.name;
        }
        widget.label = pageWidget.config.fields.label;
      }
      if (!pageWidget.fields.isEmpty()) {
        widget.content = pageWidget.fields.get(0).content;
        widget.contentTitle = pageWidget.fields.get(0).content_title;
      }
      widget.name = pageWidget.name;
      widget.title = pageWidget.title;
    } catch (UnirestException e) {
      log.log(Level.SEVERE, "Error getting page information for " + Page.FRONT_PAGE.getValue(), e);
    }
    return widget;
  }

  public String getProcessingPaymentsCount() {
    String count = "";
    try {
      count = String.valueOf(gateway.getPaymentsProcessingCount(token).count);
    } catch (UnirestException e) {
      log.log(Level.SEVERE, "Error getting processing payments count", e);
    }
    return count;
  }

  public String getUnpaidPaymentsCount() {
    String count = "";
    try {
      count = String.valueOf(gateway.getPaymentsUnpaidCount(token).count);
    } catch (UnirestException e) {
      log.log(Level.SEVERE, "Error getting unpaid payments count", e);
    }
    return count;
  }

  public String getPaymentsToConfirmCount() {
    String count = "";
    try {
      count = String.valueOf(gateway.getPaymentsToConfirmCount(token).count);
    } catch (UnirestException e) {
      log.log(Level.SEVERE, "Error getting payments to confirm count", e);
    }
    return count;
  }

  public String getTotalAmountOfPayments() {
    double total = 0;
    try {
      List<PaymentsGroup> paymentsResponses = gateway.getPayments(token).processing;
      for (PaymentsGroup paymentsResponse : paymentsResponses) {
        for (Payment payment : paymentsResponse.payments) {
          total += payment.amount;
        }
      }
    } catch (UnirestException e) {
      log.log(Level.SEVERE, "Error getting payments information", e);
    }
    return String.format("%.2f", total);
  }

  public List<Balance> getAccountsBalancesList() {
    List<Balance> balances = new ArrayList<>();
    try {
      balances = gateway.getBalances(token);
    } catch (UnirestException e) {
      log.log(Level.SEVERE, "Error getting payments information", e);
    }
    return balances;
  }

  public Account getAccountDetails(String accountName) {
    Account account = new Account();
    try {
      account = gateway.getAccountInformation(token, accountName);
    } catch (UnirestException e) {
      log.log(Level.SEVERE, "Error getting account details", e);
    }
    return account;
  }
}
