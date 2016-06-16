package br.edu.unoesc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Luiz Fachin on 12/06/2016.
 */
@Entity
public class MovimentacaoFinanceira implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @OneToMany(mappedBy = "movimentacaoFinanceira", targetEntity = Despesas.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Despesas> despesas;

    @OneToMany(mappedBy = "movimentacaoFinanceira", targetEntity = Receitas.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Receitas> receitas;

    @Temporal(TemporalType.TIMESTAMP)
    private Date mes;

    private Float saldo = 0f;
    private Float totalDespesas = 0f;
    private Float totalReceitas = 0f;

    public Float totalDespesas() {
        despesas.forEach(despesa -> totalDespesas = totalDespesas + despesa.getValor());
        return totalDespesas;
    }

    public Float totalReceitas() {
        receitas.forEach(receita -> totalReceitas = totalReceitas + receita.getValor());
        return totalReceitas;
    }

    public Float calcularSaldo() {
        saldo = totalReceitas() - totalDespesas();
        return saldo;
    }

    public void addReceita(Receitas receita) {
        if (receitas == null) {
            receitas = new ArrayList<>();
            receitas.add(receita);
        } else {
            receitas.add(receita);
        }
    }

    public void addDespesa(Despesas despesa) {
        if (despesas == null) {
            despesas = new ArrayList<>();
            despesas.add(despesa);
        } else {
            despesas.add(despesa);
        }
    }

    public void removerReceita(Receitas receita) {
        receitas.forEach(r -> {
            String descricao = r.getDescricao();
            Float valor = r.getValor();
            Date data = r.getData();
            if (descricao.equals(receita.getDescricao()) && valor == receita.getValor() && data == receita.getData()) {
                receitas.remove(receita);
            }
        });
    }

    public void removerDespesa(Despesas despesa) {
        despesas.forEach(d -> {
            String descricao = d.getDescricao();
            Float valor = d.getValor();
            Date data = d.getData();
            if (descricao.equals(despesa.getDescricao()) && valor == despesa.getValor() && data == despesa.getData()) {
                despesas.remove(despesa);
            }
        });
    }
}
