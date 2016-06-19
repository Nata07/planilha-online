package br.com.caelum.vraptor.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.unoesc.dao.DespesasDAO;
import br.edu.unoesc.dao.MovimentacaoFinanceiraDAO;
import br.edu.unoesc.dao.PessoaDAO;
import br.edu.unoesc.exception.DAOException;
import br.edu.unoesc.model.*;
import br.edu.unoesc.util.ConversorData;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Transient;

import java.time.LocalDate;

import static br.com.caelum.vraptor.view.Results.page;

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
    private MovimentacaoFinanceiraDAO movimentacaoFinanceiraDAO;
    @Inject
    private PessoaDAO pessoaDAO;
    @Inject
    private DespesasDAO despesasDAO;
    private MovimentacaoFinanceira movimentacaoFinanceira = new MovimentacaoFinanceira();
    @Inject
    private InicialController inicialController;
    @Inject
    private Result result;
    @Inject
    private Validator validator;

    @Transient
    private int sucesso = -1;

    @Inject
    public MovimentacaoFinanceiraController(Result result) {
        this.result = result;
        incluirTiposDespesas();
        incluirTiposReceitas();
    }

    private void incluirTiposReceitas() {
        result.include("tpReceitas", TipoReceita.values());
    }

    private void incluirTiposDespesas() {
        result.include("tpDespesas", TipoDespesa.values());
    }

    public void cadastrarReceitas(Pessoa p) {
        this.pessoa = p;
        result.include("nomePessoa", p.getNome());
    }

    public void cadastrarDespesas(Pessoa p) {
        if (sucesso == 1) {
            result.include("alerta", "<div class=\"alert alert-success\" role=\"alert\">Despesa salva com sucesso</div>");
            incluirTiposDespesas();
        }
        else if (sucesso == 2) {
            result.include("alerta", "<div class=\"alert alert-success\" role=\"alert\">Despesas gravadas com sucesso</div>");
            incluirTiposDespesas();
        }
        else if (sucesso == 0) {
            result.include("alerta", "<div class=\"alert alert-danger\" role=\"alert\">Erro ao salvar despesa</div>");
            incluirTiposDespesas();
        }
        else if (sucesso == -2) {
            result.include("alerta", "<div class=\"alert alert-danger\" role=\"alert\">Erro no banco</div>");
            incluirTiposDespesas();
        }
        this.pessoa = p;
    }

    @Get("/demonstrativo")
    public void goToInicial() {
        result.redirectTo(InicialController.class).inicial(pessoa);
    }

    @Post("/salvarDespesa")
    public void salvarDespesa(Despesas despesas) {
        try {
            movimentacaoFinanceira.addDespesa(despesas);
            sucesso = 1;
            result.redirectTo(MovimentacaoFinanceiraController.class).cadastrarReceitas(pessoa);
        } catch (Exception e) {
            e.printStackTrace();
            sucesso = 0;
            retornaMensagemOnErrorDespesas();
        }
    }

    private void retornaMensagemOnErrorDespesas(){
        validator.onErrorUse(page()).of(MovimentacaoFinanceiraController.class).cadastrarDespesas(pessoa);
    }

    private void retornaMensagemOnErrorReceitas(){
        validator.onErrorUse(page()).of(MovimentacaoFinanceiraController.class).cadastrarReceitas(pessoa);
    }

    @Get("/gravarDespesa")
    public void gravarDespesa() {
        try {
            MovimentacaoFinanceiraDAO mfdao = new MovimentacaoFinanceiraDAO();
            movimentacaoFinanceira.setSaldo(movimentacaoFinanceira.calcularSaldo());
            movimentacaoFinanceira.setData(ConversorData.toDate(LocalDate.now()));
            mfdao.salvar(movimentacaoFinanceira);
            sucesso = 2;
            result.redirectTo(MovimentacaoFinanceiraController.class).cadastrarDespesas(pessoa);
        } catch (DAOException e) {
            e.printStackTrace();
            sucesso = -2;
            retornaMensagemOnErrorDespesas();
        }
    }

    @Post("/salvarReceita")
    public void salvarReceita(Receitas receitas) {
        try {
            movimentacaoFinanceira.addReceita(receitas);
            sucesso = 1;
            result.redirectTo(MovimentacaoFinanceiraController.class).cadastrarReceitas(pessoa);
        } catch (Exception e) {
            e.printStackTrace();
            sucesso = 0;
            retornaMensagemOnErrorReceitas();
        }
    }

    @Get("/gravarReceita")
    public void gravarReceita() {
        try {
            MovimentacaoFinanceiraDAO mfdao = new MovimentacaoFinanceiraDAO();
            movimentacaoFinanceira.setSaldo(movimentacaoFinanceira.calcularSaldo());
            movimentacaoFinanceira.setData(ConversorData.toDate(LocalDate.now()));
            mfdao.salvar(movimentacaoFinanceira);
            sucesso = 2;
            result.redirectTo(MovimentacaoFinanceiraController.class).goToInicial();
        } catch (DAOException e) {
            e.printStackTrace();
            sucesso = -2;
            retornaMensagemOnErrorReceitas();
        }
    }
}
