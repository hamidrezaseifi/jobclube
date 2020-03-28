package com.seifi.jobclube.services;

public interface ITokenProcessor {

    void validateAppToken(String appToken);

    void validateAuthenticationToken(String authToken);


}
