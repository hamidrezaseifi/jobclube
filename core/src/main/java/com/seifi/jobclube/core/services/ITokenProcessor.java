package com.seifi.jobclube.core.services;

public interface ITokenProcessor {

    void validateAppToken(String appToken);

    void validateAuthenticationToken(String authToken);


}
