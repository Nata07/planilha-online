package br.edu.unoesc.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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
            return query.getSingleResult();
        } finally {
            this.finalizar();
        }
    }

    public Pessoa validarUsuario(Pessoa pessoa) {
        Pessoa p = pessoaDAO.buscarPorUsuario(pessoa.getUsuario());
        if (p != null) {
            if (p.getSenha().equals(pessoa.getSenha())) {
                return p;
            } else {
                return null;
            }
        }
        return null;
    }

    public Pessoa buscarPorEmail(String email){
        this.conectar();
        try {
            TypedQuery<Pessoa> query = em.createNamedQuery(Pessoa.FILTRA_POR_EMAIL, Pessoa.class);
            query.setParameter(1, email);
            return query.getSingleResult();

        } catch (NoResultException nre) {
            return null;
        }
        finally
         {
            this.finalizar();
        }
    }
}
