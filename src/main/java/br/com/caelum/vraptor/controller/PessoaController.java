package br.com.caelum.vraptor.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.unoesc.dao.PessoaDAO;
import br.edu.unoesc.exception.DAOException;
import br.edu.unoesc.model.Pessoa;

@Controller
public class PessoaController {

	@Inject
	private PessoaDAO pessoaDao;

	@Inject
	private Result result;
	
	@Inject
	private Validator validator;

	private InicialController inicialController = new InicialController();
	
	private Pessoa pessoa;

	@Post("/validar")
	public void validar(Pessoa pessoa) {
		Pessoa p = pessoaDao.validarUsuario(pessoa);
		if (p != null) {
			result.forwardTo(InicialController.class).inicial(p);
		}
		else {

		}
	}

	@Post("/cadastrar")
	public void cadastrar(Pessoa pessoa) {
		if (pessoa != null) {
			try {
				pessoaDao.salvar(pessoa);
				inicialController.inicial(pessoa);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		result.forwardTo(InicialController.class).inicial(pessoa);
	}
}