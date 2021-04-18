package com.edvaldo.leite.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@SuppressWarnings("serial")
@Entity
@Table(name = "FUNCIONARIOS")
public class Funcionario extends AbstractEntity<Long> {

	@Column(nullable = false, unique = true)
	private String nome;

	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal salario;

	@Column(nullable = false, columnDefinition = "DATE")
	private LocalDate data_entrada;

	@Column(columnDefinition = "DATE")
	private LocalDate data_saida;

	@OneToOne
	@Column(nullable = false)
	@JoinColumn(name = "id_endereco")
	@Cascade(CascadeType.ALL)
	private Endereco endereco;

	@ManyToOne
	@Column(nullable = false)
	@JoinColumn(name = "id_cargo")
	private Cargo cargo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getData_entrada() {
		return data_entrada;
	}

	public void setData_entrada(LocalDate data_entrada) {
		this.data_entrada = data_entrada;
	}

	public LocalDate getData_saida() {
		return data_saida;
	}

	public void setData_saida(LocalDate data_saida) {
		this.data_saida = data_saida;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
