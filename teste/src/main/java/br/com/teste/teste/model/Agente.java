package br.com.teste.teste.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "agente")
public class Agente implements Serializable {

    private Integer codigo;
    private OffsetDateTime data;

    @Builder.Default
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "regiao")
    private List<Regiao> regioes = new ArrayList<>();
}
