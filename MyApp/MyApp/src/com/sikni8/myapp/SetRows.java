package com.sikni8.myapp;

public class SetRows {
    
    String id;
    String type;
    String data;
    
    public String getData () {
        return data;
    }

    public void setData (String data) {
        this.data = data;
    }
    
    public String getID () {
    	return id;
    }
    
    public void setID (String id) {
    	this.id = id;
    }
    public String getType () {
    	return type;
    }
    
    public void setType (String type) {
    	this.type = type;
    }

    public SetRows(String id, String type, String data) {
        super();
        this.id = "ID: \t" + id;
        this.type = "TYPE: \t" + type;
        this.data = "DATA: \t" + data;
    }
}