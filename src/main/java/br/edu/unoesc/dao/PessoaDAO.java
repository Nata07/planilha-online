package br.edu.unoesc.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.unoesc.model.MovimentacaoFinanceira;
import br.edu.unoesc.model.Pessoa;


@RequestScoped
public class PessoaDAO extends HibernateDAO<Pessoa> {

    @Inject
    private PessoaDAO pessoaDAO;

    public Pessoa buscarPorUsuario(String usuario) {
        this.conectar();
        try {
            TypedQuery<Pessoa> query = em.createNamedQuery(Pessoa.FILTRA_POR_USUARIO, Pessoa.class);
            query.setParameter(1, usuario);
            Pessoa p = query.getSingleResult();
            return p;
        } catch (NoResultException nre) {
            return null;
        } finally {
            this.finalizar();
        }
    }

    public Pessoa validarUsuario(Pessoa pessoa) {
        Pessoa p = pessoaDAO.buscarPorUsuario(pessoa.getCpf());
        if (p != null) {
            return p;
        } else {
            return null;
        }
    }

    public MovimentacaoFinanceira retornaMF(Long id) {
        this.conectar();
        try {
            TypedQuery<MovimentacaoFinanceira> query = em.createNamedQuery(Pessoa.RETORNA_MF, MovimentacaoFinanceira.class);
            query.setParameter(1, id);
            MovimentacaoFinanceira mf = query.getSingleResult();
            return mf;
        } catch (NoResultException nre) {
            return null;
        } finally {
            this.finalizar();
        }
    }
}
