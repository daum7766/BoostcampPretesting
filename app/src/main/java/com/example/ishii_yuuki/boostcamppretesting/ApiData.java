package com.example.ishii_yuuki.boostcamppretesting;


public class ApiData {
    private String clientId;
    private String clientSecret;
    private String url;

    ApiData()
    {
        this.clientId = "ZMMLgdqke9N6xZtLoq_h";
        this.clientSecret = "LAGrmLMizL";
        this.url = "https://openapi.naver.com/v1/search/movie.json";
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getUrl() {
        return url;
    }
}
