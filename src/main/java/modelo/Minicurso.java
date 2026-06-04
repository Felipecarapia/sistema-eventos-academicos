package modelo;

public class Minicurso extends Atividade {

    private double custoMaterial;
    // Aqui é onde dizemos o tamnho do vetor
    private static final int TAMANHO_MAXIMO = 5;
    // aqui declaramos o vetor como um atributo
    private double[] avaliacoes = new double[TAMANHO_MAXIMO];
    private int numAvaliacoes = 0; // contador

    public Minicurso(String codigo, String titulo, int cargaHoraria, int capacidadeMaxima, Palestrante palestrante, double custoMaterial) {
        super(codigo, titulo, cargaHoraria, capacidadeMaxima, palestrante);
        if (custoMaterial < 0) {
            throw new IllegalArgumentException("Custo do material inválido");
        }
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

    public boolean adicionarAvaliacao(double nota) {

        /* METODO DE LÓGICA: Recebe uma nota dada por um participante.
        1º: Valida se a nota está entre 0 e 10 (se não estiver, dá erro).
        2º: Verifica se o vetor ainda tem espaço (limite de 5).
        Se couber, guarda a nota na posição atual do contador, avança o contador (numAvaliacoes++) e retorna true.
        Se o vetor já estiver cheio (5 notas), ignora e retorna false.*/

        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("Nota inválida");
        }
        if (numAvaliacoes < TAMANHO_MAXIMO) {
            avaliacoes[numAvaliacoes] = nota;
            numAvaliacoes++;
            return true;
        }
        return false;
    }

    public double calcularMediaAvaliacoes(){

        /* METODO DE LÓGICA: Calcula a média de todas as notas dadas até o momento.
       Primeiro verifica se o contador está em zero para evitar um erro matemático de divisão por zero.
       Depois, usa um laço (for) para somar apenas as caixinhas do vetor que já receberam notas reais.
       No final, divide o total somado pela quantidade de notas inseridas. */

        if (numAvaliacoes == 0){
            return 0.0;
        }
        double soma = 0;
        for (int i = 0; i < numAvaliacoes; i++){
            soma+=avaliacoes[i];
        }
        return soma/numAvaliacoes;
    }

    public double obterAvaliacaoMaxima(){

        /* METODO DE LÓGICA: Percorre o vetor para encontrar a maior nota registrada.
       Ele começa fingindo que a primeira nota (posição 0) é a maior de todas.
       Depois, compara essa nota com as outras posições. Se encontrar qualquer nota maior,
       atualiza a variável até o fim do laço e retorna o maior valor encontrado. */

        if (numAvaliacoes==0){
            return 0.0;
        }
        double maxima = avaliacoes[0];
        for (int i = 1; i < numAvaliacoes; i++){ // CORRIGIDO: sinal mudado de '>' para '<' para percorrer o vetor
            if (avaliacoes[i] > maxima) {        // CORRIGIDO: sinal mudado de '<' para '>' para achar a MAIOR nota
                maxima = avaliacoes[i];
            }
        }
        return maxima;
    }

    public double obterAvaliacaoMinima() {

        /* METODO DE LÓGICA: Faz o oposto do método anterior.
       Ele assume que a primeira nota do vetor é a menor de todas.
       Percorre o laço comparando as notas e, se encontrar uma nota ainda menor,
       atualiza a variável, devolvendo a nota mais baixa registrada no fim. */

        if (numAvaliacoes == 0) {
            return 0.0;
        }
        double minima = avaliacoes[0];
        for (int i = 1; i < numAvaliacoes; i++) {
            if (avaliacoes[i] < minima) {
                minima = avaliacoes[i];
            }
        }
        return minima;
    }
}