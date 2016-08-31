package br.edu.unoesc.dao;

import br.edu.unoesc.model.MovimentacaoFinanceira;

import javax.persistence.TypedQuery;

/**
 * Created by Luiz Fachin on 18/06/2016.
 */
public class MovimentacaoFinanceiraDAO extends HibernateDAO<MovimentacaoFinanceira> {

    public Double buscarSaldo(){
        this.conectar();
        try {
            TypedQuery<Double> query = em.createNamedQuery(MovimentacaoFinanceira.BUSCAR_SALDO, Double.class);
            Double d = query.getSingleResult();
            if (d == null) {
                return 0.0;
            }else {
                return d;
            }
        } finally {
            this.finalizar();
        }
    }
}