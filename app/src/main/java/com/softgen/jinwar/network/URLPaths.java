package com.softgen.jinwar.network;

public enum URLPaths {
    USERAUTHENTICATION("/userauthenticate"),
    CHECKAPPVERSION("/checkAppVersion");

    private final String urlPath;
    URLPaths(String urlPath){this.urlPath = urlPath;}

    public String getUrlPath(){
        return this.urlPath;
    }
}
