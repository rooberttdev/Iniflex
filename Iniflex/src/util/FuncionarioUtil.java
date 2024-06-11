package util;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Funcionario;

public class FuncionarioUtil {
    public static List<Funcionario> aumentarSalario(List<Funcionario> funcionarios) {
        for (Funcionario funcionario : funcionarios) {
            funcionario.setSalario(funcionario.getSalario().multiply(BigDecimal.valueOf(1.1))); // Aumento de 10%
        }
        return funcionarios;
    }
    

    public static Funcionario encontrarMaisVelho(List<Funcionario> funcionarios) {
        return Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
    }
}
