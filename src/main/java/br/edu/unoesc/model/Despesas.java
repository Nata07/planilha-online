package br.edu.unoesc.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Luiz Fachin on 12/06/2016.
 */
@Entity
@Data
public class Despesas implements Serializable, MinhaEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String descricao;
    private Float valor;
    private TipoDespesa tipoDespesa;
    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date data;
}
