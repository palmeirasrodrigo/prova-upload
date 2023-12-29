package br.com.teste.teste.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@ToString
@Table(name = "agente")
public class AgenteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String versao;
    private Integer codigo;
    private OffsetDateTime data;

    @OneToMany(mappedBy = "agente", cascade = CascadeType.ALL)
    @Builder.Default
    private List<RegiaoEntity> regioes = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        regioes.forEach(p -> p.setAgente(this));
    }
}
