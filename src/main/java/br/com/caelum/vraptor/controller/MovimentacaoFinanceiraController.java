package br.com.caelum.vraptor.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.edu.unoesc.model.*;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by Luiz Fachin on 15/06/2016.
 */
@Controller @ApplicationScoped @NoArgsConstructor
public class MovimentacaoFinanceiraController {
    private Pessoa pessoa;
    private Despesas despesas;
    private Receitas receitas;
    private MovimentacaoFinanceira movimentacaoFinanceira = new MovimentacaoFinanceira();
    @Inject
    private InicialController inicialController;
    @Inject
    private Result result;
    @Inject
    public MovimentacaoFinanceiraController(Result result) {
        this.result = result;
        result.include("tpDespesas", TipoDespesa.values());
        result.include("tpReceitas", TipoReceita.values());
    }
    public void cadastrarReceitas(Pessoa p) {
        this.pessoa = p;
        result.include("nomePessoa", p.getNome());
    }
    public void cadastrarDespesas(Pessoa p) {
        this.pessoa = p;
        result.include("nomePessoa", p.getNome());
    }
    @Get("/demonstrativo")
    public void goToInicial(){
        result.redirectTo(InicialController.class).inicial(pessoa);
    }
    @Post("/adicionarDespesa")
    public void adicionarDespesa(Despesas d) {
        movimentacaoFinanceira.addDespesa(d);
    }
    @Post("/adicionarReceita")
    public void adicionarReceita(Receitas r) {
        movimentacaoFinanceira.addReceita(r);
    }
}
