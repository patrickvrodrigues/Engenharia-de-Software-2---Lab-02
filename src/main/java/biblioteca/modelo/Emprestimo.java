package biblioteca.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Emprestimo {
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevista;
    private LocalDate dataDevolucao;
    private Livro livro;
    private double valorPago;

    //Não Sei se deve ter esse construtor
    /*public Emprestimo (Usuario usuario){
        this.usuario = usuario;
        this.dataEmprestimo = new Date(System.currentTimeMillis());
        this.dataPrevista.setDate(dataEmprestimo.getDate()+7);
        this.valorPago = 5.00;
    }
    */

    public  Emprestimo(Livro livro){
        this.livro = livro;
    }

    public void setUsuario(Usuario usuario){this.usuario = usuario;}

    public Usuario getUsuario(){return this.usuario;}

    public void setDataEmprestimo(LocalDate data){
        this.dataEmprestimo = data;
        this.dataPrevista = data.plusDays(7);
    }

    public LocalDate getDataEmprestimo(){return this.dataEmprestimo;}

    public LocalDate getDataPrevista(){return this.dataPrevista;}

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public LocalDate getDataDevolucao(){
        return this.dataDevolucao;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Livro getLivro() {
        return this.livro;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double getValorPago() {
        return valorPago;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "usuario=" + usuario +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataPrevista=" + dataPrevista +
                ", dataDevolucao=" + dataDevolucao +
                ", livro=" + livro +
                ", valorPago=" + valorPago +
                '}' + '\n';
    }
}
