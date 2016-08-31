package br.com.caelum.vraptor.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.unoesc.dao.MovimentacaoFinanceiraDAO;
import br.edu.unoesc.dao.PessoaDAO;
import br.edu.unoesc.exception.DAOException;
import br.edu.unoesc.model.Pessoa;

@Controller
public class PessoaController {

	//TODO: CRIAR UMA TABELA MOSTRANDO AS INFORMAÇÕES REFERENTE AO MÊS ATUAL

	@Inject
	private PessoaDAO pessoaDao;

	@Inject
	private MovimentacaoFinanceiraDAO mfdao;

	@Inject
	private Result result;

	@Inject
	private Validator validator;

	@Inject
	private InicialController inicialController;

	private Pessoa pessoa;

	@Post("/validar")
	public void validar(Pessoa pessoa) {
		Pessoa p = pessoaDao.validarUsuario(pessoa);
		if (p != null) {
			result.forwardTo(InicialController.class).inicial(p, 0);
		} else {
			result.redirectTo(IndexController.class).index();
		}
	}

	@Post("/cadastrar")
	public void cadastrar(Pessoa pessoa) {
		if (pessoa != null) {
			try {
				Pessoa p = new Pessoa();
				p = pessoaDao.buscarPorUsuario(pessoa.getCpf());
				if (p != null) {
					result.redirectTo(IndexController.class).index();
				} else {
					pessoaDao.salvar(pessoa);
					result.forwardTo(InicialController.class).inicial(pessoa, 0);
				}
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else {
			result.redirectTo(IndexController.class).index();
		}
	}

}