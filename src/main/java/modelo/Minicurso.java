package modelo;

public class Minicurso extends Atividade{

    private double custoMaterial;
    // Aqui é onde dizemos o tamnho do vetor
    private static final int  TAMANHO_MAXIMO = 5;
    // aqui declaramos o vetor como um atributo
    private double [] avaliacoes = new double [TAMANHO_MAXIMO];
    private int numAvaliacoes = 0; // contador

    public Minicurso(String codigo, String titulo, int cargaHoraria, int capacidadeMaxima, Palestrante palestrante, double custoMaterial) {
        super(codigo, titulo, cargaHoraria, capacidadeMaxima, palestrante);
        this.custoMaterial = custoMaterial;

    }

    @Override
    public double calcularCusto() {
        return custoMaterial;
    }
    @Override
    public String obterTipoAtividade() {
        return "Minicurso";
    }
}
