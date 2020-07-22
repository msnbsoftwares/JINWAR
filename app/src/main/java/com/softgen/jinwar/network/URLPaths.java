package com.softgen.jinwar.network;

public enum URLPaths {
    USERAUTHENTICATION("/userauthenticate"),
    CHECKAPPVERSION("/checkAppVersion"),
    AVAILABLE_DHARMASHALAS("/getAvailableDharmashalas");

    private final String urlPath;
    URLPaths(String urlPath){this.urlPath = urlPath;}

    public String getUrlPath(){
        return this.urlPath;
    }
}
