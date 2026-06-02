package modelo;

import enums.TipoParticipante;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Participante extends Pessoa {

    private String matricula;
    private TipoParticipante tipo;
    //Lista criada para armazenar as atividades
    private List<Atividade> atividadesFrequentadas = new ArrayList<>();

    //construtor com validações
    public Participante (String cpf, String nome, String email, LocalDate dataNascimento, String matricula, TipoParticipante tipo){
        // O super estar chamando ocontrutor da class pessoa ja que participante estende pessoa
        super(cpf, nome, email, dataNascimento);
        if (matricula == null || matricula.trim().isEmpty()){
            throw new IllegalArgumentException("Matrícula inválida");
        }
        else{
            this.matricula = matricula;
        }
        if (tipo == null){
            throw new IllegalArgumentException("Tipo inválido");
        }
        else {
            this.tipo = tipo;
        }
    }

    //metodo sobrescrito para mostrar na tela
    @Override
    public String obterTipoPessoa() {
        return "Participante (" + tipo + ")";
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {

        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Matrícula inválida");
        } else {
            this.matricula = matricula;
        }
    }

    public TipoParticipante getTipo() {
        return tipo;
    }

    public void setTipo(TipoParticipante tipo) {
        if (tipo == null){
            throw new IllegalArgumentException("Tipo inválido");
        }
        else {
            this.tipo = tipo;
        }
    }
    //essa etapa ser utilizada para futuramente ser usada aqui tem a verificação da lista
    public boolean adicionarAtividadeFrequentada(Atividade atividade){
        if (!atividadesFrequentadas.contains(atividade)){
            atividadesFrequentadas.add(atividade);
            return  true;
        }
            return false;
    }

    public int calcularTotalHoras(){
        int totalHoras = 0;

        // O 'for' vai pegar cada Atividade da lista, uma por uma
        for (Atividade atividade : atividadesFrequentadas){

            // Não importa se é Palestra ou Minicurso, o Java vai chamar o getCargaHoraria() dela
            totalHoras += atividade.getCargaHoraria();
        }
        return totalHoras;
    }

    public double calcularCustoTotal(){
        double custoTotal = 0;

        // O 'for' vai pegar cada Atividade da lista, uma por uma
        for (Atividade atividade : atividadesFrequentadas){

            // Se for Palestra, o Java executa o método da Palestra (retorna 0.0)
            // Se for Mesa Redonda, executa o da Mesa Redonda (retorna 25.0)
            custoTotal += atividade.calcularCusto();
        }
            return custoTotal;

        }



    public List<Atividade> getAtividadesFrequentadas(){

        /*Aqui é um metodo de segurança visto que casso não crie esse new ArrayList o sistema vai retornar o lista original. Criando esse ArrayList
        Criando esse new ArrayList o sistema retonar uma lista nova e não a original então se um usiuario mal intencionado quiser apagar apaga a lista nova
        e não a lista verdadeira
         */
        return new ArrayList<>(atividadesFrequentadas);
    }
}
