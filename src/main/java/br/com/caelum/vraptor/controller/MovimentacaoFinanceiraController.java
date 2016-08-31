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
    private MovimentacaoFinanceira movimentacaoFinanceira;
    @Inject
    private InicialController inicialController;
    @Inject
    private Result result;
    @Inject
    private Validator validator;

    @ApplicationScoped
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
        if (sucesso == -1) {
            incluirTiposReceitas();
            this.pessoa = p;
            setMF(p);
        }
        else if (sucesso == 2) {
            incluirTiposReceitas();
            result.include("alerta", "<div class=\"alert alert-success\" role=\"alert\">Receita salva com sucesso</div>");
        }
        else if (sucesso == 0) {
            incluirTiposReceitas();
            result.include("alerta", "<div class=\"alert alert-danger\" role=\"alert\">Erro ao salvar receita</div>");
        }
        else if (sucesso == -2) {
            incluirTiposReceitas();
            result.include("alerta", "<div class=\"alert alert-danger\" role=\"alert\">Erro no banco</div>");
        }
        else if (sucesso == 3) {
            incluirTiposReceitas();
        }
    }

    private void setMF(Pessoa p) {
        if (pessoa.getMovimentacoesFinanceiras() == null) {
            this.movimentacaoFinanceira = new MovimentacaoFinanceira();
        } else {
            this.movimentacaoFinanceira = p.getMovimentacoesFinanceiras();
        }
    }

    public void cadastrarDespesas(Pessoa p) {
        if (sucesso == -1) {
            incluirTiposDespesas();
            this.pessoa = p;
            setMF(p);
        }
        else if (sucesso == 3) {
            incluirTiposDespesas();
            result.include("alerta", "<div class=\"alert alert-success\" role=\"alert\">Despesa salva com sucesso</div>");
        }
        else if (sucesso == 0) {
            incluirTiposDespesas();
            result.include("alerta", "<div class=\"alert alert-danger\" role=\"alert\">Erro ao salvar despesa</div>");
        }
        else if (sucesso == -2) {
            incluirTiposDespesas();
            result.include("alerta", "<div class=\"alert alert-danger\" role=\"alert\">Erro no banco</div>");
        }
        else if (sucesso == 2) {
            incluirTiposDespesas();
        }
    }

    @Get("/demonstrativo")
    public void goToInicial() {
        result.redirectTo(InicialController.class).inicial(pessoa, 1);
    }

    @Post("/salvarDespesa")
    public void salvarDespesa(Despesas despesas) {
        try {
            movimentacaoFinanceira.addDespesa(despesas);
            sucesso = 3;
            result.redirectTo(MovimentacaoFinanceiraController.class).cadastrarDespesas(pessoa);
        } catch (Exception e) {
            e.printStackTrace();
            sucesso = 0;
            retornaMensagemOnErrorDespesas();
        }
    }

    @Get("/gravarDespesa")
    public void gravarDespesa() {
        try {
            MovimentacaoFinanceiraDAO mfdao = new MovimentacaoFinanceiraDAO();
            movimentacaoFinanceira.setSaldo(movimentacaoFinanceira.calcularSaldo());
            movimentacaoFinanceira.setData(ConversorData.toDate(LocalDate.now()));
            mfdao.salvar(movimentacaoFinanceira);
            Pessoa p = pessoaDAO.buscarPorUsuario(pessoa.getCpf());
            p.setMovimentacoesFinanceiras(movimentacaoFinanceira);
            pessoaDAO.salvar(p);
            this.pessoa = p;
            movimentacaoFinanceira.limparDados();
            result.redirectTo(MovimentacaoFinanceiraController.class).goToInicial();
        } catch (DAOException e) {
            e.printStackTrace();
            sucesso = -2;
            retornaMensagemOnErrorDespesas();
        }
    }

    private void retornaMensagemOnErrorDespesas(){
        validator.onErrorUse(page()).of(MovimentacaoFinanceiraController.class).cadastrarDespesas(pessoa);
    }

    private void retornaMensagemOnErrorReceitas(){
        validator.onErrorUse(page()).of(MovimentacaoFinanceiraController.class).cadastrarReceitas(pessoa);
    }

    @Post("/salvarReceita")
    public void salvarReceita(Receitas receitas) {
        try {
            movimentacaoFinanceira.addReceita(receitas);
            sucesso = 2;
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
            Pessoa p = pessoaDAO.buscarPorUsuario(pessoa.getCpf());
            p.setMovimentacoesFinanceiras(movimentacaoFinanceira);
            pessoaDAO.salvar(p);
            movimentacaoFinanceira.limparDados();
            result.redirectTo(MovimentacaoFinanceiraController.class).goToInicial();
        } catch (DAOException e) {
            e.printStackTrace();
            sucesso = -2;
            retornaMensagemOnErrorReceitas();
        }
    }
}
