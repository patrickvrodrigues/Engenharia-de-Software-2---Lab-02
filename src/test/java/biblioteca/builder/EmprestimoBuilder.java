package biblioteca.builder;

import biblioteca.modelo.Emprestimo;

import java.time.LocalDate;

public class EmprestimoBuilder {

    private Emprestimo emprestimo;

    private EmprestimoBuilder() { }

    public static EmprestimoBuilder umEmprestimo() {

        EmprestimoBuilder builder = new EmprestimoBuilder();

        builder.emprestimo = new Emprestimo(LivroBuilder.umLivro().constroi());
        builder.emprestimo.setUsuario(UsuarioBuilder.umUsuario().constroi());
        builder.emprestimo.setDataEmprestimo(LocalDate.now());
        builder.emprestimo.setDataDevolucao(LocalDate.now().plusDays(5));
        builder.emprestimo.setValorPago(5.00);
        return builder;
    }

    public EmprestimoBuilder comDataDevolucao(LocalDate localDate) {
        this.emprestimo.setDataDevolucao(localDate);
        return this;
    }


    public EmprestimoBuilder comNomeDeUsuario(String nome) {
        this.emprestimo.setUsuario(UsuarioBuilder.umUsuario().comNome(nome).constroi());
        return this;
    }

    public EmprestimoBuilder comQuantidadeDeHistoricoEmLivro(int quantidade){
        this.emprestimo.setLivro(LivroBuilder.umLivro().comHistorico(quantidade).constroi());
        return this;
    }
    public EmprestimoBuilder comLivroReservado(){
        this.emprestimo.getLivro().setReservado(true);
        return this;
    }

    public EmprestimoBuilder emAtraso(int dias){
        this.emprestimo.setDataDevolucao(emprestimo.getDataPrevista().plusDays(dias));
        return this;
    }

    public EmprestimoBuilder comDevolucaoIgualDataPrevista(){
        this.emprestimo.setDataEmprestimo(LocalDate.now());
        this.emprestimo.setDataDevolucao(emprestimo.getDataPrevista());
        return this;
    }

    public Emprestimo constroi() {
        return this.emprestimo;
    }
}
