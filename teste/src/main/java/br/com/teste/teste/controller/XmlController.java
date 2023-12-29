package br.com.teste.teste.controller;

import br.com.teste.teste.response.ValoresResponse;
import br.com.teste.teste.service.XmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class XmlController {

    @Autowired
    private XmlService service;

    @PostMapping(value = "/v1/carregar")
    public ResponseEntity<Void> carregarXML(@RequestBody MultipartFile file) {
        service.salvarXml(file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(path = "/v1/agentes/{regiao}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ValoresResponse>> buscarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable String regiao) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ValoresResponse> resultPage = service.buscarPaginado(regiao, pageRequest);

        return ResponseEntity.ok().body(resultPage);
    }
}
