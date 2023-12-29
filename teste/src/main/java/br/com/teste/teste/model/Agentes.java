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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "agentes")
public class Agentes implements Serializable {

    @JacksonXmlProperty(isAttribute = true)
    private String versao;

    @Builder.Default
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "agente")
    private List<Agente> agente = new ArrayList<>();
}
