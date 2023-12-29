package br.com.teste.teste.repository;

import br.com.teste.teste.dto.ValoresDTO;
import br.com.teste.teste.entity.AgenteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenteRepository extends JpaRepository<AgenteEntity, Long> {

    @Query(value = "SELECT v.compra as compra, v.geracao as geracao, v.preco_medio as precoMedio FROM valor v INNER JOIN regiao r on v.regiao_id = r.id WHERE r.sigla = :sigla", nativeQuery = true)
    Page<ValoresDTO> findByRegiao(@Param("sigla") String sigla, Pageable pageable);
}
