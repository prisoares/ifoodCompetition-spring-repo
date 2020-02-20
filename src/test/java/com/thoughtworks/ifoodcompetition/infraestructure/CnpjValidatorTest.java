package com.thoughtworks.ifoodcompetition.infraestructure;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
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
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CnpjValidatorTest {

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

        service.stubFor(get(urlEqualTo("/v1/cnpj/23403106000131"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(response)));
        //when
        String resultado = cnpj.validaCnpj("23403106000131");
        //then
        assertThat(resultado, is("VALID"));
    }

    @Test
    public void deveRetornarInvalidEmCasoDeCnpjInvalido() throws IOException, URISyntaxException {
        String response = fileGetContents("deveValidarCnpjInvalidoResponse.json");

        service.stubFor(get(urlEqualTo("/v1/cnpj/23403106000130"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(response)));
        //when
        String resultado = cnpj.validaCnpj("23403106000130");
        //then
        assertThat(resultado, is("INVALID"));
    }

    @Test
    public void deveRetornarFailEmCasoDeErro() {
        String response = "Too many requests, please try again later.";

        service.stubFor(get(urlEqualTo("/v1/cnpj/23403106000131"))
                .willReturn(aResponse()
                        .withStatus(429)
                        .withBody(response)));
        //when
        String resultado = cnpj.validaCnpj("23403106000131");
        //then
        assertThat(resultado, is("FAIL"));
    }

    private String fileGetContents(String filename) throws URISyntaxException, IOException {
        URI uri = getClass().getClassLoader().getResource(filename).toURI();

        try (Stream<String> stream = Files.lines(Paths.get(uri))) {
            return stream.parallel().collect(Collectors.joining());
        }
    }
}