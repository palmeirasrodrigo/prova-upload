package br.com.teste.teste.service;

import br.com.teste.teste.response.ValoresResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

public interface XmlService {

    void salvarXml(MultipartFile file);

    Page<ValoresResponse> buscarPaginado(String regiao, PageRequest pageRequest);
}
