package com.alexis.domain.model;

import java.util.ArrayList;

public class ResponseModel {
    public String site_id;
    public String country_default_time_zone;
    public String query;
    public Paging paging;
    public ArrayList<Result> results;
    public Sort sort;
    public ArrayList<AvailableSort> available_sorts;
    public ArrayList<Filter> filters;
    public ArrayList<AvailableFilter> available_filters;
}
