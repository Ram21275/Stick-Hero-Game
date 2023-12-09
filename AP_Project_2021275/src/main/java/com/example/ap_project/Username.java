package com.example.ap_project;

public class Username {
    private static Username instance = null;
    private String name;
    private Username(){}

    public static Username getInstance(){
        if(instance==null) instance = new Username();
        return instance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
