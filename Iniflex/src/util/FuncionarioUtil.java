package util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    
    public static class FuncionarioService {
    	public static List<Funcionario> removerFuncionario(List<Funcionario> funcionarios, String nome) {
            return funcionarios.stream()
                              .filter(funcionario -> !funcionario.getNome().equals("João"))
                              .collect(Collectors.toList());
        }

     public static List<Object> aumentarSalario(List<Funcionario> funcionarios, BigDecimal percentual) {
        return funcionarios.stream()
                   .map(funcionario -> {
                    BigDecimal aumento = funcionario.getSalario().multiply(percentual);
                    return funcionario.aumentarSalario(aumento);
                         })
                    .collect(Collectors.toList());
        }
    }
    public static String formatarNumero(BigDecimal numero) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(numero);
    }

}