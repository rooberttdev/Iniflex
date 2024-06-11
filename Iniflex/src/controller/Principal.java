package controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Funcionario;
import util.FuncionarioUtil;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5 ), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        
        List<Funcionario> funcionariosExcluindoJoao = new ArrayList<>(funcionarios);
        funcionariosExcluindoJoao.removeIf(funcionario -> funcionario.getNome().equals("João"));
        imprimirFuncionarios(funcionariosExcluindoJoao);
        System.out.println("\n");

        System.out.println("Valores com aumento de 10%:\n");
        List<Funcionario> funcionariosComAumento = FuncionarioUtil.aumentarSalario(new ArrayList<>(funcionariosExcluindoJoao));
        imprimirSalariosAtuais(funcionariosExcluindoJoao, funcionariosComAumento);
        System.out.println("\n");

        Map<String, List<Funcionario>> funcionariosAgrupadosPorFuncao = funcionariosExcluindoJoao.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        for (Map.Entry<String, List<Funcionario>> entry : funcionariosAgrupadosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario funcionario : entry.getValue()) {
                System.out.printf("Nome: %s, Salário: %s\n", funcionario.getNome(), FuncionarioUtil.formatarNumero(funcionario.getSalario()));
            }
            System.out.println("-----------------------------------------------------------------------------\n");
        }
        System.out.println("\n");

        System.out.println("Funcionários que fazem aniversário no mês 10 e 12:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Funcionario funcionario : funcionariosExcluindoJoao) {
            if (funcionario.getDataNascimento().getMonthValue() == 10 ||
                    funcionario.getDataNascimento().getMonthValue() == 12) {
                System.out.printf("Nome: %s, Data de Nascimento: %s\n", funcionario.getNome(),
                        funcionario.getDataNascimento().format(formatter));
            }
        }
        System.out.println("-----------------------------------------------------------------------------\n");
        System.out.println("\n");

        Funcionario maisVelho = FuncionarioUtil.encontrarMaisVelho(funcionariosExcluindoJoao);
        int idade = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
        System.out.printf("Funcionário mais velho: %s, Idade: %d\n", maisVelho.getNome(), idade);
        System.out.println("-----------------------------------------------------------------------------\n");

        funcionariosExcluindoJoao.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("Funcionários por ordem alfabética:");
        for (Funcionario funcionario : funcionariosExcluindoJoao) {
            System.out.println(funcionario.getNome());
        }
        System.out.println("-----------------------------------------------------------------------------\n");

        BigDecimal totalSalarios = funcionariosExcluindoJoao.stream().map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("Total dos salários dos funcionários: %s\n", FuncionarioUtil.formatarNumero(totalSalarios));
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------\n");
        
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("Salários mínimos ganhos por cada funcionário:");
        for (Funcionario funcionario : funcionariosExcluindoJoao) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.DOWN);
            System.out.printf("%s: %s\n", funcionario.getNome(), FuncionarioUtil.formatarNumero(salariosMinimos));
        }
    }

    private static void imprimirFuncionarios(List<Funcionario> funcionariosExcluindoJoao) {
        System.out.println("Funcionários:");
        System.out.printf("%-20s %-20s %-15s %-20s%n", "Nome", "Data de Nascimento", "Salário", "Função");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Funcionario funcionario : funcionariosExcluindoJoao) {
            System.out.printf("%-20s %-20s %-15s %-20s%n",
                    funcionario.getNome(),
                    funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    FuncionarioUtil.formatarNumero(funcionario.getSalario()),
                    funcionario.getFuncao());
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private static void imprimirSalariosAtuais(List<Funcionario> funcionariosAntigos, List<Funcionario> funcionariosAtuais) {
        System.out.println("Nome            Salário");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < funcionariosAntigos.size(); i++) {
            Funcionario atual = funcionariosAtuais.get(i);
            System.out.printf("%-15s %-20s%n", atual.getNome(), FuncionarioUtil.formatarNumero(atual.getSalario()));
        }
        System.out.println("------------------------------------------------- \n");
    }
}