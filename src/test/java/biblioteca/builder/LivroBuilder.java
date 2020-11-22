package biblioteca.builder;

import biblioteca.modelo.Emprestimo;
import biblioteca.modelo.Livro;

import java.util.ArrayList;

public class LivroBuilder {

    private Livro livro;

    private LivroBuilder() { }


    public static LivroBuilder umLivro() {

        LivroBuilder builder = new LivroBuilder();

        builder.livro = new Livro();
        builder.livro.setAutor("Autor 1");
        builder.livro.setEmprestado(true);
        builder.livro.setReservado(false);
        builder.livro.setTitulo("Titulo 1");
        return builder;
    }

    public LivroBuilder emprestado() {
        this.livro.setEmprestado(true);
        return this;
    }

    public LivroBuilder reservado() {
        this.livro.setReservado(true);
        return this;
    }

    public LivroBuilder adicionaNoHistorico(Emprestimo emprestimo){
        livro.adicionaEmprestimoEmHistorico(emprestimo);
        return this;
    }
    public LivroBuilder comHistorico(int quantidade) {
        int i=0;
        for(i=0;i<=quantidade;i++){
            this.livro.adicionaEmprestimoEmHistorico(EmprestimoBuilder.umEmprestimo().
                    comNomeDeUsuario("Usuario"+ String.valueOf(i)).constroi());
            System.out.println("Ok");
        }

        return this;
    }

    public Livro constroi() {
        return this.livro;
    }


}