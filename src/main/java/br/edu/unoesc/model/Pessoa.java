package br.edu.unoesc.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;



@Entity
@NamedQueries({
		@NamedQuery(name = Pessoa.FILTRA_POR_NOME, query = "SELECT p FROM Pessoa p WHERE p.nome like ?1 "),
		@NamedQuery(name = Pessoa.FILTRA_POR_USUARIO, query = "SELECT p FROM Pessoa p WHERE p.cpf = ?1"),
		@NamedQuery(name = Pessoa.RETORNA_MF, query = "SELECT DISTINCT p.movimentacoesFinanceiras FROM Pessoa p WHERE p.movimentacoesFinanceiras = ?1")
})
public @Data class Pessoa implements MinhaEntidade, Serializable {

	public static final String FILTRA_POR_NOME = "FILTRA_POR_NOME";
	public static final String FILTRA_POR_USUARIO = "FILTRA_POR_USUARIO";
	public static final String RETORNA_MF = "RETORNA_MF";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false, unique = true)
	private String cpf;
	@Column(nullable = false)
	private String senha;
	@Column
	private String sexo;
	@Column
	private String telefone;


	@ManyToOne(targetEntity = MovimentacaoFinanceira.class, fetch = FetchType.EAGER)
	private MovimentacaoFinanceira movimentacoesFinanceiras;
}
