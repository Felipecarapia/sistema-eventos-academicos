package modelo;

public class MesaRedonda extends Atividade {

    private String tema;
    private String moderador;

    /* CONSTRUTOR: Cria o objeto MesaRedonda.
       Repassa os dados comuns para a classe mãe (Atividade) através do super()
       e valida se o tema e o moderador não estão vazios ou nulos. */
    public MesaRedonda(String codigo, String titulo, int cargaHoraria, int capacidadeMaxima, Palestrante palestrante, String tema, String moderador) {
        super(codigo, titulo, cargaHoraria, capacidadeMaxima, palestrante);

        if (tema == null || tema.trim().isEmpty()) {
            throw new IllegalArgumentException("Tema inválido");
        }
        if (moderador == null || moderador.trim().isEmpty()) {
            throw new IllegalArgumentException("Moderador inválido");
        }

        this.tema = tema;
        this.moderador = moderador;
    }

    /* SOBRESCRITA: Cumpre o contrato da classe mãe.
       Define que o preço final de qualquer Mesa Redonda é um valor fixo de R$ 25.00. */
    @Override
    public double calcularCusto() {
        return 25.0;
    }

    /* SOBRESCRITA: Cumpre o contrato da classe mãe.
       Retorna o texto correspondente a este tipo específico de atividade. */
    @Override
    public String obterTipoAtividade() {
        return "Mesa Redonda";
    }

    /* GETTERS E SETTERS: Garantem o encapsulamento dos atributos próprios. */

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        if (tema == null || tema.trim().isEmpty()) {
            throw new IllegalArgumentException("Tema inválido");
        }
        this.tema = tema;
    }

    public String getModerador() {
        return moderador;
    }

    public void setModerador(String moderador) {
        if (moderador == null || moderador.trim().isEmpty()) {
            throw new IllegalArgumentException("Moderador inválido");
        }
        this.moderador = moderador;
    }
}