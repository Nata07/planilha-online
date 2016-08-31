package br.edu.unoesc.model;

import br.edu.unoesc.dao.MovimentacaoFinanceiraDAO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Luiz Fachin on 12/06/2016.
 */
@Entity @Data
@NamedQueries({
        @NamedQuery(name = MovimentacaoFinanceira.BUSCAR_SALDO, query = "SELECT SUM(mf.saldo) FROM MovimentacaoFinanceira mf")
})
public class MovimentacaoFinanceira implements Serializable, MinhaEntidade {

    public static final String BUSCAR_SALDO = "BUSCAR_SALDO";
    public static final String BUSCAR_TODOS = "BUSCAR_TODOS";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name="MOVIMENTACAO_DESPESAS", joinColumns={@JoinColumn(name="MOVIMENTACAO_CODIGO", referencedColumnName="codigo")}, inverseJoinColumns={@JoinColumn(name="DESPESAS_CODIGO", referencedColumnName="codigo")})
    private List<Despesas> despesas;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name="MOVIMENTACAO_RECEITAS", joinColumns={@JoinColumn(name="MOVIMENTACAO_CODIGO", referencedColumnName="codigo")}, inverseJoinColumns={@JoinColumn(name="RECEITAS_CODIGO", referencedColumnName="codigo")})
    private List<Receitas> receitas;

    @Temporal(TemporalType.DATE)
    private Date data;

    private Double saldo = 0.0;
    @Transient
    private Double totalDespesas = 0.0;
    @Transient
    private Double totalReceitas = 0.0;
    @Transient
    private MovimentacaoFinanceiraDAO mfdao;



    private Double totalDespesas() {
        if (despesas != null) {
            despesas.forEach(despesa -> totalDespesas = totalDespesas + despesa.getValor());
            return totalDespesas;
        } else {
            return 0.0;
        }
    }

    private Double totalReceitas() {
        if (receitas != null) {
            receitas.forEach(receita -> totalReceitas = totalReceitas + receita.getValor());
            return totalReceitas;
        }else {
            return 0.0;
        }
    }

    public Double calcularSaldo() {
        mfdao = new MovimentacaoFinanceiraDAO();
        saldo = mfdao.buscarSaldo();
        saldo = saldo + (totalReceitas() - totalDespesas());
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

    private void limparListas(){
        if (despesas != null) {
            despesas.clear();
        }
        if (receitas != null) {
            receitas.clear();
        }
    }

    private void limparVariaveisDouble(){
        saldo = 0.0;
        totalDespesas = 0.0;
        totalReceitas = 0.0;
    }

    public void limparDados(){
        limparListas();
        limparVariaveisDouble();
    }
}
