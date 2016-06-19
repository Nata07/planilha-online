package br.com.caelum.vraptor.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.unoesc.dao.MovimentacaoFinanceiraDAO;
import br.edu.unoesc.dao.PessoaDAO;
import br.edu.unoesc.exception.DAOException;
import br.edu.unoesc.model.*;
import br.edu.unoesc.util.ConversorData;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by Luiz Fachin on 15/06/2016.
 */
@Controller
@ApplicationScoped
@NoArgsConstructor
public class MovimentacaoFinanceiraController {
    private Pessoa pessoa;
    private Despesas despesas;
    private Receitas receitas;
    @Inject
    private MovimentacaoFinanceiraDAO movimentacaoFinanceiraDAO;
    @Inject
    private PessoaDAO pessoaDAO;

    private MovimentacaoFinanceira movimentacaoFinanceira = new MovimentacaoFinanceira();
    @Inject
    private InicialController inicialController;
    @Inject
    private Result result;
    @Inject
    private Validator validator;


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
    public void goToInicial() {
        result.redirectTo(InicialController.class).inicial(pessoa);
    }

    @Post("/adicionarDespesa")
    public void adicionarDespesa(Despesas despesas) {
        movimentacaoFinanceira.addDespesa(despesas);
        try {
            movimentacaoFinanceiraDAO.salvar(movimentacaoFinanceira);
            pessoaDAO.salvar(pessoa);
            result.forwardTo(this).cadastrarDespesas(this.pessoa);
        } catch (DAOException e) {
            e.printStackTrace();
            validator.onErrorForwardTo(this).goToInicial();
        }
    }

    @Post("/adicionarReceita")
    public void adicionarReceita(Receitas r) {
        movimentacaoFinanceira.addReceita(r);
        movimentacaoFinanceira.setSaldo(movimentacaoFinanceira.calcularSaldo());
        try {
            movimentacaoFinanceiraDAO.salvar(movimentacaoFinanceira);
            pessoaDAO.salvar(pessoa);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
