package br.com.teste.teste.service;

import br.com.teste.teste.dto.ValoresDTO;
import br.com.teste.teste.entity.AgenteEntity;
import br.com.teste.teste.mapper.AgenteMapper;
import br.com.teste.teste.model.Agentes;
import br.com.teste.teste.repository.AgenteRepository;
import br.com.teste.teste.response.ValoresResponse;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class XmlServiceImpl implements XmlService {
    private final XmlMapper xmlMapper;
    private final AgenteRepository repository;

    @Override
    @Transactional
    public void salvarXml(MultipartFile file) {
        try {
            Agentes agentes = xmlMapper.readValue(file.getInputStream(), Agentes.class);
            List<AgenteEntity> agenteEntity = AgenteMapper.INSTANCE.mapToListaAgentes(agentes);
            repository.saveAll(agenteEntity);
            agenteEntity.forEach(a -> System.out.println("O código do agente é: " + a.getCodigo()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Page<ValoresResponse> buscarPaginado(String regiao, PageRequest pageRequest) {
        Page<ValoresDTO> byRegiao = repository.findByRegiao(regiao, pageRequest);
        return AgenteMapper.INSTANCE.mapToPageResponse(byRegiao);
    }
}
