package modelo;

import java.util.HashSet;
import java.util.Set;

public abstract class Atividade {

    // Atributos privados obrigatórios (Encapsulamento)
    private String codigo;
    private String titulo;
    private int cargaHoraria;
    private int capacidadeMaxima;
    private Palestrante palestrante; // Associação com a classe Palestrante

    // Coleção do tipo SET para garantir que não haja participantes duplicados
    private Set<Participante> participantesInscritos = new HashSet<>();

    // Construtor com as validações solicitadas
    public Atividade(String codigo, String titulo, int cargaHoraria, int capacidadeMaxima, Palestrante palestrante) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Código inválido");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título inválido");
        }
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser maior que zero");
        }
        if (capacidadeMaxima <= 0) {
            throw new IllegalArgumentException("Capacidade máxima deve ser maior que zero");
        }
        if (palestrante == null) {
            throw new IllegalArgumentException("Palestrante obrigatório");
        }

        this.codigo = codigo;
        this.titulo = titulo;
        this.cargaHoraria = cargaHoraria;
        this.capacidadeMaxima = capacidadeMaxima;
        this.palestrante = palestrante;
    }

    // ==========================================
    // METODOS ABSTRATOS (Obrigatórios para as subclasses)
    // ==========================================

    // Cada tipo de atividade (Palestra, Minicurso...) vai calcular seu preço de um jeito
    public abstract double calcularCusto();

    // Cada subclasse vai dizer qual é o seu tipo em formato de texto
    public abstract String obterTipoAtividade();

    // ==========================================
    // MÉTODOS CONCRETOS (Já têm comportamento padrão)
    // ==========================================

    // Inscreve o participante se houver vagas. O Set.add() já barra duplicatas sozinho!
    public boolean inscreverParticipante(Participante participante) {
        if (participantesInscritos.size() >= capacidadeMaxima) {
            return false; // Lotado!
        }
        return participantesInscritos.add(participante);
    }

    // Retorna quantas vagas ainda restam
    public int obterVagasDisponiveis() {
        return capacidadeMaxima - participantesInscritos.size();
    }

    // Calcula a porcentagem de ocupação da atividade
    public double obterTaxaOcupacao() {
        return ((double) participantesInscritos.size() / capacidadeMaxima) * 100.0;
    }

    // ==========================================
    // GETTERS E SETTERS (Encapsulamento)
    // ==========================================

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getCargaHoraria() {
        return cargaHoraria; // É ESSE MÉTODO que o Participante chama no loop de horas!
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public Palestrante getPalestrante() {
        return palestrante;
    }

    // Encapsulamento de Coleções: Retorna uma CÓPIA do Set para proteção dos dados
    public Set<Participante> getParticipantesInscritos() {
        return new HashSet<>(participantesInscritos);
    }
}
