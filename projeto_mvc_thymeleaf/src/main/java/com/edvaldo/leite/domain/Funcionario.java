package com.edvaldo.leite.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
@Entity
@Table(name = "FUNCIONARIOS")
public class Funcionario extends AbstractEntity<Long> {

   
    @NotBlank(message = "O nome precisa ser preenchido")
    @Size(max = 255, min = 3)
    @Column(nullable = false, unique = true)
    private String nome;

    @NotNull// mensagem padrão de ValidationMessages
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    @Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
    private BigDecimal salario;

    @NotNull// mensagem padrão de ValidationMessages
    @PastOrPresent(message = "A data de entrada deve ser anterior ou igual a data atual")
    @DateTimeFormat(iso = ISO.DATE)
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate dataEntrada;

    @FutureOrPresent(message = "A data de saída não pode ser anterior a data de entrada")
    @DateTimeFormat(iso = ISO.DATE)
    @Column(columnDefinition = "DATE")
    private LocalDate dataSaida;

    @Valid
    @OneToOne()
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @NotNull(message = "{NotNull.funcionario.cargo}")
    @ManyToOne
    @JoinColumn(name = "id_cargo")
    private Cargo cargo;

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public LocalDate getDataEntrada() {
	return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
	this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
	return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
	this.dataSaida = dataSaida;
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
