package biblioteca.servico;

import biblioteca.modelo.Emprestimo;
import biblioteca.modelo.Livro;
import biblioteca.modelo.Usuario;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;

public class EmprestimoService {
    int contador = 0;
    public Emprestimo realizarEmprestimos(Usuario usuario, Livro livro, Emprestimo... emprestimos) {
        contador=0;
        Arrays.stream(emprestimos).
                forEach(emprestimo_bd -> {
                    if (emprestimo_bd.getUsuario().getNome()==usuario.getNome()){
                        contador = contador+1;}
                    if(contador>=2)
                        throw new IllegalArgumentException("Usuario ja tem 2 filmes alugados");
                });


        if (livro.isReservado())
            throw new IllegalArgumentException("Livro ja está Reservado");

        Emprestimo emprestimo = new Emprestimo(livro);
        emprestimo.setUsuario(usuario);
        emprestimo.setDataEmprestimo(LocalDate.now());

        //Salvando a locacao...
        //TODO adicionar método para salvar
        //daoLocacao.salva(locacao);

        return emprestimo;
    }
    public Emprestimo realizarDevolucao(Emprestimo emprestimo){
        if (emprestimo.getDataDevolucao().isBefore(emprestimo.getDataPrevista())==false){
            if((emprestimo.getDataDevolucao().isEqual(emprestimo.getDataPrevista()))==false) {
                long diasAtrasados = emprestimo.getDataPrevista().until(emprestimo.getDataDevolucao(), ChronoUnit.DAYS);
                emprestimo.setValorPago(emprestimo.getValorPago() + diasAtrasados * 0.40);
            }
        }
        emprestimo.getLivro().setEmprestado(false);
        return emprestimo;
    }

    public int quantidadeDeEmprestimoDoUsuario(Usuario usuario, Emprestimo... emprestimos){
        contador = 0;
        Arrays.stream(emprestimos).
                forEach(emprestimo_bd -> {
                    if (emprestimo_bd.getUsuario().getNome()==usuario.getNome()){
                        contador = contador+1;}
                });
        return contador;
    }
}
