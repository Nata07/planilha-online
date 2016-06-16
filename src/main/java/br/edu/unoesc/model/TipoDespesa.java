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
public enum TipoDespesa {

    MORADIA("Moradia"),
    ALIMENTACAO("Alimentação"),
    TRANSPORTE("Transporte"),
    SAUDE("Saúde"),
    ENTRETENIMENTO("Entretenimento"),
    CARTAOCREDITO("Cartão de crédito"),
    OUTROS("Outros");

    private String descricao;
}
