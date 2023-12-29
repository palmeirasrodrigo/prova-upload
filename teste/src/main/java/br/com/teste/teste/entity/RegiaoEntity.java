package br.com.teste.teste.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@ToString
@Table(name = "regiao")
public class RegiaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agente_id")
    private AgenteEntity agente;

    private String sigla;

    @OneToMany(mappedBy = "regiao", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ValorEntity> valores = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        valores.forEach(p -> p.setRegiao(this));
    }
}
