package biblioteca.builder;

import biblioteca.modelo.Emprestimo;

public class Teste {
    public static void main(String[] args){
        EmprestimoBuilder builder;
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().constroi();

        System.out.println(emprestimo);
    }
}
