package com.thoughtworks.ifoodcompetition.infraestructure;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CnpjValidatorTest {

    @Test
    public void deveValidarCnpjValido() {
        //given
        CnpjValidator cnpj = new CnpjValidator();

        //when
        String resultado = cnpj.validaCnpj("23403106000131");
        //then
        assertThat(resultado, is("OK"));
    }
}