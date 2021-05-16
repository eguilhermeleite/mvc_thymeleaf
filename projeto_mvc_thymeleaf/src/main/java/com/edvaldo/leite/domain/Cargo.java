package com.edvaldo.leite.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long> {

    @NotBlank(message = "O nome do Cargo é obrigatório")
    @Size(max = 40, message = "O nome do Cargo deve ter no máximo 40 caracteres.")
    @Column(nullable = false, unique = true, length = 30)
    private String nome;

    @NotNull(message = "Por favor, selecione o Departamento associado ao Cargo...")
    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> funcionarios;

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public Departamento getDepartamento() {
	return departamento;
    }

    public void setDepartamento(Departamento departamento) {
	this.departamento = departamento;
    }

    public List<Funcionario> getFuncionarios() {
	return funcionarios;
    }

}
