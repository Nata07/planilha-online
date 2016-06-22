package br.com.caelum.vraptor.controller;

import br.com.caelum.vraptor.*;
import br.edu.unoesc.model.MovimentacaoFinanceira;
import br.edu.unoesc.model.Pessoa;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by Luiz Fachin on 11/06/2016.
 */
@Controller @ApplicationScoped
public class InicialController {

    private Pessoa pessoa;

    private MovimentacaoFinanceiraController movimentacaoFinanceiraController = new MovimentacaoFinanceiraController();

    @Inject
    private Result result;

    @Get("/inicial/{Pessoa pessoa}")
    public void inicial(Pessoa pessoa){
        result.include("nomeUsuario", pessoa.getNome());
        this.pessoa = pessoa;
    }

    @Get("/despesas")
    public void goToDespesas(){
        result.forwardTo(MovimentacaoFinanceiraController.class).cadastrarDespesas(pessoa);
    }

    @Get("/receitas")
    public void goToReceitas() {
        result.redirectTo(MovimentacaoFinanceiraController.class).cadastrarReceitas(pessoa);
    }

}
