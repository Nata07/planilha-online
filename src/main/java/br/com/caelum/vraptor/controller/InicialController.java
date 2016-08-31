package br.com.caelum.vraptor.controller;

import br.com.caelum.vraptor.*;
import br.edu.unoesc.dao.PessoaDAO;
import br.edu.unoesc.model.Despesas;
import br.edu.unoesc.model.MovimentacaoFinanceira;
import br.edu.unoesc.model.Pessoa;
import br.edu.unoesc.model.Receitas;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Luiz Fachin on 11/06/2016.
 */
@Controller
@ApplicationScoped
public class InicialController {

    private Pessoa pessoa;

    @Inject
    PessoaDAO pessoaDAO;

    private MovimentacaoFinanceiraController movimentacaoFinanceiraController;

    @Inject
    private Result result;

    private int var = 0;

    @Get("/inicial/{Pessoa pessoa}")
    public void inicial(Pessoa pessoa, int var) {
        result.include("nomeUsuario", pessoa.getNome());
        if (var == 0) {
            if (pessoa.getMovimentacoesFinanceiras() == null) {
                result.include("saldo", 0);
            } else {
                Double saldo = pessoa.getMovimentacoesFinanceiras().getSaldo();
                if (saldo != null) {
                    if (saldo <= 0) {
                        result.include("saldo", "<p style=\"color: red\">" + saldo + "</p>");
                    } else if (saldo > 1000) {
                        result.include("saldo", "<p style=\"color: green;\">" + saldo + "</p>");
                    }
                    result.include("saldo", "<p style=\"color: #f18801;\">" + saldo + "</p>");
                    if (!pessoa.getMovimentacoesFinanceiras().getReceitas().isEmpty()) {
                        List<Receitas> receitas = pessoa.getMovimentacoesFinanceiras().getReceitas();
                        List<Receitas> receitasSemRepetidos = new ArrayList<>(new HashSet<>(receitas));
                        result.include("listaReceitas", receitasSemRepetidos);
                    }
                    if (!pessoa.getMovimentacoesFinanceiras().getDespesas().isEmpty()) {
                        List<Despesas> despesas = pessoa.getMovimentacoesFinanceiras().getDespesas();
                        List<Despesas> despesasSemRepetidos = new ArrayList<>(new HashSet<>(despesas));
                        result.include("listaDespesas", despesasSemRepetidos);
                    }
                } else {
                    result.include("saldo", 0);
                }
            }
        }
        if (var == 1) {
            if (pessoa.getMovimentacoesFinanceiras() == null) {
                result.include("saldo", 0);
            } else {
                Pessoa p = pessoaDAO.buscarPorUsuario(pessoa.getCpf());
                Double saldo = p.getMovimentacoesFinanceiras().getSaldo();
                if (saldo <= 0) {
                    result.include("saldo", "<p style=\"color: red\">" + saldo + "</p>");
                } else if (saldo > 1000) {
                    result.include("saldo", "<p style=\"color: green;\">" + saldo + "</p>");
                } else if (saldo > 0 && saldo <= 1000) {
                    result.include("saldo", "<p style=\"color: #f18801;\">" + saldo + "</p>");
                }
                if (!p.getMovimentacoesFinanceiras().getReceitas().isEmpty()) {
                    result.include("listaReceitas", p.getMovimentacoesFinanceiras().getReceitas());
                }
                if (!p.getMovimentacoesFinanceiras().getDespesas().isEmpty()) {
                    result.include("listaDespesas", p.getMovimentacoesFinanceiras().getDespesas());
                }
            }
        }
        this.pessoa = pessoa;
    }

    @Get("/despesas")
    public void goToDespesas() {
        result.forwardTo(MovimentacaoFinanceiraController.class).cadastrarDespesas(pessoa);
    }

    @Get("/receitas")
    public void goToReceitas() {
        result.redirectTo(MovimentacaoFinanceiraController.class).cadastrarReceitas(pessoa);
    }

}
