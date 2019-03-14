package com.luminor.dc.testautomation.api.model;

import com.luminor.dc.testautomation.api.model.child.PageContainer;

import java.util.List;

public class PageResponse {
    public String id;
    public String title;
    public String locale;
    public String layout;
    public List<PageContainer> containers;
}
