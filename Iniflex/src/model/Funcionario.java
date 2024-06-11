package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private BigDecimal salarioAtual;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.salarioAtual = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getSalarioAtual() {
        return salarioAtual;
    }

    public void setSalarioAtual(BigDecimal salarioAtual) {
        this.salarioAtual = salarioAtual;
    }

    public String getFuncao() {
        return funcao;
    }

    public List<Funcionario> aumentarSalario(BigDecimal aumento2) {
        BigDecimal aumento = salario.multiply(BigDecimal.valueOf(0.1));
        salario = salario.add(aumento);
        salarioAtual = salario;
		return null;
    }
}