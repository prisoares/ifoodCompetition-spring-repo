package com.thoughtworks.ifoodcompetition.infraestructure;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.thoughtworks.ifoodcompetition.model.CnpjStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.thoughtworks.ifoodcompetition.model.CnpjStatus.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CnpjValidatorTest {

    private static final String VALID_CNPJ = "23403106000131";
    private static final String URL_WITH_VALID_CNPJ = "/v1/cnpj/" + VALID_CNPJ;
    private static final String INVALID_CNPJ = "23403106000130";
    private static final String URL_WITH_INVALID_CNPJ = "/v1/cnpj/" + INVALID_CNPJ;
    private static final String FAILED_CNPJ = "23403106000131";
    private static final String URL_WITH_FAILED_CNPJ = "/v1/cnpj/" + FAILED_CNPJ;

    @Rule
    public WireMockRule service = new WireMockRule(8090);

    CnpjValidator cnpj;
    public static final String HOSTNAME = "http://localhost:8090";

    @Before
    public void setup(){
        cnpj = new CnpjValidator(HOSTNAME);
    }

    @Test
    public void deveRetornarValidEmCasoDeCnpjValido() throws IOException, URISyntaxException {
        String response = fileGetContents("deveValidarCnpjValidoResponse.json");

        service.stubFor(get(urlEqualTo(URL_WITH_VALID_CNPJ))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(response)));
        //when
        CnpjStatus resultado = cnpj.validaCnpj(VALID_CNPJ);
        //then
        assertThat(resultado, is(VALID));
    }

    @Test
    public void deveRetornarInvalidEmCasoDeCnpjInvalido() throws IOException, URISyntaxException {
        String response = fileGetContents("deveValidarCnpjInvalidoResponse.json");

        service.stubFor(get(urlEqualTo(URL_WITH_INVALID_CNPJ))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(response)));
        //when
        CnpjStatus resultado = cnpj.validaCnpj(INVALID_CNPJ);
        //then
        assertThat(resultado, is(INVALID));
    }

    @Test
    public void deveRetornarFailEmCasoDeErro() {
        String response = "Too many requests, please try again later.";

        service.stubFor(get(urlEqualTo(URL_WITH_FAILED_CNPJ))
                .willReturn(aResponse()
                        .withStatus(429)
                        .withBody(response)));
        //when
        CnpjStatus resultado = cnpj.validaCnpj(FAILED_CNPJ);
        //then
        assertThat(resultado, is(FAIL));
    }

    private String fileGetContents(String filename) throws URISyntaxException, IOException {
        URI uri = getClass().getClassLoader().getResource(filename).toURI();

        try (Stream<String> stream = Files.lines(Paths.get(uri))) {
            return stream.parallel().collect(Collectors.joining());
        }
    }
}