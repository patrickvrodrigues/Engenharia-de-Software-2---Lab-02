package biblioteca.modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Livro {
    private String autor;
    private String titulo;
    private boolean isEmprestado;
    private boolean isReservado;
    private ArrayList<Emprestimo> historico = new ArrayList<>();

    public Livro() {}

    public Livro(String autor, String titulo, boolean isEmprestado, boolean isReservado){
        this.autor = autor;
        this.titulo = titulo;
        this.isEmprestado = isEmprestado;
        this.isReservado = isReservado;
    }

    public void adicionaEmprestimoEmHistorico(Emprestimo emprestimo){
        this.historico.add(emprestimo);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isEmprestado() {
        return isEmprestado;
    }

    public void setEmprestado(boolean emprestado) {
        isEmprestado = emprestado;
    }

    public boolean isReservado() {
        return isReservado;
    }

    public void setReservado(boolean reservado) {
        isReservado = reservado;
    }

    public ArrayList<Emprestimo> getHistorico() {
        return historico;
    }

    public void setHistorico(ArrayList<Emprestimo> historico) {
        this.historico = historico;
    }

    public void adicionaNoHistorico(Emprestimo emprestimo){
        this.historico.add(emprestimo);
    }
    public void imprimeHistorico(){
        Iterator<Emprestimo> iterator = this.historico.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.toString());
        }
    }

    @Override
    public String toString() {
        return "Livro{" +
                "autor='" + autor + '\'' +
                ", titulo='" + titulo + '\'' +
                ", isEmprestado=" + isEmprestado +
                ", isReservado=" + isReservado +
                ", historico=" + historico +
                '}' + '\n';
    }
}
