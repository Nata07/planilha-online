package br.edu.unoesc.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Luiz Fachin on 12/06/2016.
 */
@Entity
@Getter
@Setter
public class Receitas implements Serializable, MinhaEntidade{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String descricao;
    private Float valor;
    private String tipoReceitas;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @ManyToOne(optional = false, targetEntity = MovimentacaoFinanceira.class)
    private MovimentacaoFinanceira movimentacaoFinanceira;
}
