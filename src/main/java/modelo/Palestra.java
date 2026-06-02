package modelo;

public class Palestra extends Atividade{


    public Palestra(String codigo, String titulo, int cargaHoraria, int capacidadeMaxima, Palestrante palestrante) {
        super(codigo, titulo, cargaHoraria, capacidadeMaxima, palestrante);


    }

    @Override
    public double calcularCusto() {
        return 0.0;
    }

    @Override
    public String obterTipoAtividade() {
        return "Palestra";
    }
}
