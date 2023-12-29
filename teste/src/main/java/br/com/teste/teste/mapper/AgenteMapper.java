package br.com.teste.teste.mapper;

import br.com.teste.teste.dto.ValoresDTO;
import br.com.teste.teste.entity.AgenteEntity;
import br.com.teste.teste.entity.RegiaoEntity;
import br.com.teste.teste.entity.ValorEntity;
import br.com.teste.teste.model.Agente;
import br.com.teste.teste.model.Agentes;
import br.com.teste.teste.model.Regiao;
import br.com.teste.teste.response.ValoresResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Mapper
public abstract class AgenteMapper {

    public static final AgenteMapper INSTANCE = Mappers.getMapper(AgenteMapper.class);


    public Page<ValoresResponse> mapToPageResponse(Page<ValoresDTO> page) {
        return page.map(this::mapToValoresResponse);
    }

    public abstract ValoresResponse mapToValoresResponse(ValoresDTO dto);

    @Mapping(target = "valores", source = "regiao")
    public abstract RegiaoEntity mapRegiaoToRegiaoEntity(Regiao regiao);

    public abstract AgenteEntity mapAgenteToAgenteEntity(Agente agente, String versao);

    public List<AgenteEntity> mapToListaAgentes(Agentes agentes) {
        return agentes.getAgente().stream()
                .map(agente -> mapAgenteToAgenteEntity(agente, agentes.getVersao()))
                .toList();
    }

    public List<RegiaoEntity> mapRegioesToRegiaoEntity(List<Regiao> regioes) {
        return regioes.stream()
                .map(this::mapRegiaoToRegiaoEntity)
                .toList();
    }

    public List<ValorEntity> mapRegiaoToValores(Regiao regiao) {
        List<ValorEntity> valores = new ArrayList<>();

        if (regiao.getGeracao().size() == regiao.getCompra().size() && regiao.getCompra().size() == regiao.getPrecoMedio().size()) {
            for (int i = 0; i < regiao.getGeracao().size(); i++) {
                ValorEntity valorEntity = ValorEntity.builder()
                        .geracao(regiao.getGeracao().get(i))
                        .compra(regiao.getCompra().get(i))
                        .precoMedio(regiao.getPrecoMedio().get(i))
                        .build();
                valores.add(valorEntity);
            }
        }
        return valores;
    }
}
