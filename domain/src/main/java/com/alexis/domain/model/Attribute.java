package com.alexis.domain.model;

import java.util.ArrayList;

public class Attribute {
    public String id;
    public String name;
    public String value_id;
    public String value_name;
    public String attribute_group_id;
    public String attribute_group_name;
    public ValueStruct value_struct;
    public ArrayList<Value> values;
    public Object source;
    public String value_type;
}
