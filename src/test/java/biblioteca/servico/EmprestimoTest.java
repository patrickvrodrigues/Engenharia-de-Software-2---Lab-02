package biblioteca.servico;
import biblioteca.builder.EmprestimoBuilder;
import biblioteca.builder.LivroBuilder;
import biblioteca.builder.UsuarioBuilder;
import biblioteca.modelo.Emprestimo;
import biblioteca.modelo.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;


public class EmprestimoTest {
    private EmprestimoService emprestimoService;
    private Usuario user1;
    @BeforeEach
    public void inicio() {
        emprestimoService = new EmprestimoService();
        user1 = UsuarioBuilder.umUsuario().constroi();

    }
    @Test
    void deveRealizarEmprestimoQueNaoEstejaReservado() {
        Emprestimo emprestimo = emprestimoService.realizarEmprestimos(user1, LivroBuilder.umLivro().constroi());

        Assertions.assertFalse(emprestimo.getLivro().isReservado());

    }

    @Test
    void naoDeveRealizarEmprestimoQueJaEstejaReservado() {

        IllegalArgumentException exception =

                assertThrows(IllegalArgumentException.class,
                        () -> emprestimoService.realizarEmprestimos(user1,LivroBuilder.umLivro().reservado().constroi()),
                        "Deveria ter lançado um IllegalArgumentException");
            assertTrue(exception.getMessage().contains("Livro ja está Reservado"));
    }

    @Test
    void deveRetornarDataPrevistaDaquiSeteDias() {

        Emprestimo emprestimo = EmprestimoBuilder
                .umEmprestimo().constroi();
        Assertions.assertEquals(emprestimo.getDataPrevista(), emprestimo.getDataEmprestimo().plusDays(7));

    }

    @Test
    void usuarioNaoDeveTerEmprestimo() {

        int quantidade = emprestimoService.
                quantidadeDeEmprestimoDoUsuario(UsuarioBuilder.umUsuario().comNome("João").
                        comMatricula("54321").constroi(),
                        EmprestimoBuilder.umEmprestimo().constroi(),
                        EmprestimoBuilder.umEmprestimo().constroi(),
                        EmprestimoBuilder.umEmprestimo().constroi());
        Assertions.assertEquals(0,quantidade);

    }
    @Test
    void usuarioDeveTerUmEmprestimos() {

        int quantidade = emprestimoService.
                quantidadeDeEmprestimoDoUsuario(UsuarioBuilder.umUsuario().comNome("João").
                                comMatricula("54321").constroi(),
                        EmprestimoBuilder.umEmprestimo().comNomeDeUsuario("João").constroi(),
                        EmprestimoBuilder.umEmprestimo().constroi(),
                        EmprestimoBuilder.umEmprestimo().constroi());
        Assertions.assertEquals(1,quantidade);

    }
    @Test
    void usuarioDeveTerDoisEmprestimos() {

        int quantidade = emprestimoService.
                quantidadeDeEmprestimoDoUsuario(UsuarioBuilder.umUsuario().comNome("João").
                                comMatricula("54321").constroi(),
                        EmprestimoBuilder.umEmprestimo().comNomeDeUsuario("João").constroi(),
                        EmprestimoBuilder.umEmprestimo().comNomeDeUsuario("João").constroi(),
                        EmprestimoBuilder.umEmprestimo().constroi());
        Assertions.assertEquals(2,quantidade);

    }

    @Test
    void naoDeveEmprestarTresVezesAoMesmoUsuario() {


        IllegalArgumentException exception =

                assertThrows(IllegalArgumentException.class,
                        () -> emprestimoService.realizarEmprestimos(user1,
                                LivroBuilder.umLivro().reservado().constroi(),
                                EmprestimoBuilder.umEmprestimo().constroi(),EmprestimoBuilder.umEmprestimo().constroi()),
                        "Deveria ter lançado um IllegalArgumentException");
        assertTrue(exception.getMessage().contains("Usuario ja tem 2 filmes alugados"));
    }

    @Test
    void devolverLivroAntesDaDataPrevista() {
        assertDoesNotThrow(() -> emprestimoService.realizarDevolucao(EmprestimoBuilder.umEmprestimo().
                comDataDevolucao(LocalDate.now()).constroi()));
    }

    @Test
    void devolverLivroNaDataPrevista() {
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().comDevolucaoIgualDataPrevista().constroi();
        Assertions.assertEquals(true, emprestimo.getLivro().isEmprestado());
        emprestimo = emprestimoService.realizarDevolucao(emprestimo);
        Assertions.assertEquals(5.0, emprestimo.getValorPago());
        Assertions.assertEquals(false,emprestimo.getLivro().isEmprestado());

    }

    @Test
    void devolverLivroUmDiaDepoisDaDataPrevista() {
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().emAtraso(1).constroi();
        Assertions.assertEquals(true, emprestimo.getLivro().isEmprestado());
        emprestimo = emprestimoService.realizarDevolucao(emprestimo);
        Assertions.assertEquals(5.4, emprestimo.getValorPago());
        Assertions.assertEquals(false,emprestimo.getLivro().isEmprestado());

    }

    @Test
    void devolverLivroTrintaDiasDepoisDaDataPrevista() {
        Emprestimo emprestimo = EmprestimoBuilder.umEmprestimo().emAtraso(30).constroi();
        Assertions.assertEquals(true, emprestimo.getLivro().isEmprestado());
        emprestimo = emprestimoService.realizarDevolucao(emprestimo);
        Assertions.assertEquals(17.0, emprestimo.getValorPago());
        Assertions.assertEquals(false,emprestimo.getLivro().isEmprestado());

    }
}
