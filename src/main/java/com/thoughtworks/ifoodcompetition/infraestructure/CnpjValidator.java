package com.thoughtworks.ifoodcompetition.infraestructure;

import com.thoughtworks.ifoodcompetition.model.CnpjStatus;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.thoughtworks.ifoodcompetition.model.CnpjStatus.*;

public class CnpjValidator {

    public static final String URL = "/v1/cnpj/";
    private final String hostname;

    public CnpjValidator(String hostname) {
        this.hostname = hostname;
    }

    public CnpjStatus validaCnpj(String cnpj) {
        try {
            Content response = Request.Get(hostname + URL + cnpj)
                    .execute().returnContent();
            if (response.asString().contains("OK")) {
                return VALID;
            } else {
                return INVALID;
            }
        } catch (IOException e) {
            e.getMessage();
            return FAIL;
        }
    }
}
