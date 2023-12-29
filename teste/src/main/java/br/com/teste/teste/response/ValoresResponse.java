package br.com.teste.teste.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ValoresResponse implements Serializable {

    private Double geracao;

    private Double compra;

    private Double precoMedio;
}
