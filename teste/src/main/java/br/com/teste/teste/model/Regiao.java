package br.com.teste.teste.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "regiao")
public class Regiao implements Serializable {

    @JacksonXmlProperty(isAttribute = true)
    private String sigla;

    @Builder.Default
    @JacksonXmlElementWrapper(localName = "geracao")
    private List<Double> geracao = new ArrayList<>();

    @Builder.Default
    @JacksonXmlElementWrapper(localName = "compra")
    private List<Double> compra = new ArrayList<>();

    @Builder.Default
    @JacksonXmlElementWrapper(localName = "precoMedio")
    private List<Double> precoMedio = new ArrayList<>();

}
