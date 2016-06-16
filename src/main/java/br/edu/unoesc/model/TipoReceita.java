package br.edu.unoesc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Luiz Fachin on 14/06/2016.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum TipoReceita {

    SALARIO("Salário"),
    BENEFICIO("Benefício"),
    INVESTIMENTOS("Investimentos"),
    OUTROS("Outros");

    private String descricao;
}
