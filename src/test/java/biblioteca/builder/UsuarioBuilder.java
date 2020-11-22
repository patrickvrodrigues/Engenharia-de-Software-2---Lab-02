package biblioteca.builder;
import biblioteca.modelo.Usuario;
public class UsuarioBuilder {
    private Usuario usuario;

    public UsuarioBuilder() { }


    public static UsuarioBuilder umUsuario() {

        UsuarioBuilder builder = new UsuarioBuilder();

        builder.usuario = new Usuario();
        builder.usuario.setNome("Patrick");
        builder.usuario.setMatricula("123456");

        return builder;
    }
    public UsuarioBuilder comNome(String nome) {
        this.usuario.setNome(nome);
        return this;
    }

    public UsuarioBuilder comMatricula(String matricula) {
        this.usuario.setMatricula(matricula);
        return this;
    }

    public Usuario constroi() {
        return this.usuario;
    }
}
