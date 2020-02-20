package com.thoughtworks.ifoodcompetition.configuration;

import com.thoughtworks.ifoodcompetition.infraestructure.CnpjValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    private String cnpjHostname;

    public Configuration(@Value("${cnpj.hostname}") String cnpjHostname) {
        this.cnpjHostname = cnpjHostname;
    }

    @Bean
    public CnpjValidator getCnpjValidator() {
        return new CnpjValidator(cnpjHostname);
    }
}
