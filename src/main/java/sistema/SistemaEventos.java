package sistema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.Atividade;
import modelo.Palestrante;
import modelo.Participante;

public class SistemaEventos {

    /* ATRIBUTOS: As 4 estruturas de dados reunidas conforme exigido.
       Maps garantem busca ultra rápida O(1) por Matrícula ou CPF. */
    private Map<String, Participante> participantesPorMatricula = new HashMap<>();
    private Map<String, Palestrante> palestrantesPorCpf = new HashMap<>();
    private List<Atividade> atividades = new ArrayList<>();
    private String[] diasFuncionamento = new String[7];

    /* CONSTRUTOR: Inicializa o cérebro do projeto preenchendo o vetor fixo de dias. */
    public SistemaEventos() {
        diasFuncionamento[0] = "Segunda-feira";
        diasFuncionamento[1] = "Terça-feira";
        diasFuncionamento[2] = "Quarta-feira";
        diasFuncionamento[3] = "Quinta-feira";
        diasFuncionamento[4] = "Sexta-feira";
        diasFuncionamento[5] = "Sábado";
        diasFuncionamento[6] = "Domingo";
    }

    /* MÉTODOS OBRIGATÓRIOS */

    /* MÉTODOS DE CADASTRO */

    /* MÉTODO: Verifica se a matrícula já existe no Map. Se não existir, faz o .put() */
    public boolean cadastrarParticipante(Participante p) {
        if (p == null || p.getMatricula() == null) {
            return false;
        }
        if (participantesPorMatricula.containsKey(p.getMatricula())) {
            return false; /* Matrícula já cadastrada no sistema! */
        }
        participantesPorMatricula.put(p.getMatricula(), p);
        return true;
    }

    /* MÉTODO: Verifica se o CPF já existe no Map. Se não existir, faz o .put() */
    public boolean cadastrarPalestrante(Palestrante p) {
        if (p == null || p.getCpf() == null) {
            return false;
        }
        if (palestrantesPorCpf.containsKey(p.getCpf())) {
            return false; /* CPF já cadastrado no sistema! */
        }
        palestrantesPorCpf.put(p.getCpf(), p);
        return true;
    }

    /* MÉTODO: Percorre a List e verifica se o código é único. Se for, faz o .add() */
    public boolean cadastrarAtividade(Atividade a) {
        if (a == null || a.getCodigo() == null) {
            return false;
        }
        for (Atividade existente : atividades) {
            if (existente.getCodigo().equalsIgnoreCase(a.getCodigo())) {
                return false; /* Código duplicado! */
            }
        }
        atividades.add(a);
        return true;
    }

    /* OPERAÇÃO DE INSCRIÇÃO */

    /* MÉTODO: Busca o participante e a atividade e chama o inscreverParticipante() */
    public boolean inscreverParticipanteEmAtividade(String matricula, String codigoAtividade) {
        Participante p = buscarParticipantePorMatricula(matricula);
        if (p == null) {
            return false;
        }

        Atividade a = null;
        for (Atividade existente : atividades) {
            if (existente.getCodigo().equalsIgnoreCase(codigoAtividade)) {
                a = existente;
                break;
            }
        }

        if (a == null) {
            return false; /* Atividade não localizada */
        }

        /* Delegação polimórfica da inscrição baseada na capacidade da atividade */
        return a.inscreverParticipante(p);
    }

    /* MÉTODOS DE BUSCA O(1) */

    /* MÉTODO: Busca rápida por matrícula utilizando o Map */
    public Participante buscarParticipantePorMatricula(String m) {
        return participantesPorMatricula.get(m);
    }

    /* MÉTODO: Busca rápida por CPF utilizando o Map */
    public Palestrante buscarPalestrantePorCpf(String cpf) {
        return palestrantesPorCpf.get(cpf);
    }

    /* MÉTODO DE POLIMORFISMO */

    /* MÉTODO: Percorre atividades e calcula o faturamento total com polimorfismo dinâmico */
    public double calcularFaturamentoTotal() {
        double total = 0;
        for (Atividade a : atividades) {
            /* De acordo com a sua classe Atividade, usamos getParticipantesInscritos().size() */
            total += a.calcularCusto() * a.getParticipantesInscritos().size();
        }
        return total;
    }

    /* MÉTODOS DE LISTAGEM */

    /* MÉTODO: Retorna uma cópia da lista de atividades para que o código externo possa
       percorrê-la sem ter acesso direto à lista interna (encapsulamento de coleções) */
    public List<Atividade> getAtividades() {
        return new ArrayList<>(atividades);
    }

    public void listarAtividades() {
        System.out.println("--- Lista de Atividades Cadastradas ---");
        for (Atividade a : atividades) {
            System.out.println("[" + a.obterTipoAtividade() + "] Código: " + a.getCodigo() + " | Título: " + a.getTitulo());
        }
    }

    public void listarParticipantes() {
        System.out.println("--- Lista de Participantes Cadastradas ---");
        for (Participante p : participantesPorMatricula.values()) {
            System.out.println("Matrícula: " + p.getMatricula() + " | Nome: " + p.getNome());
        }
    }

    public void listarPalestrantes() {
        System.out.println("--- Lista de Palestrantes Cadastrados ---");
        for (Palestrante p : palestrantesPorCpf.values()) {
            System.out.println("CPF: " + p.getCpf() + " | Nome: " + p.getNome());
        }
    }

    /* MÉTODOS DE ANÁLISE E INDICADORES */

    /* MÉTODO: Calcula a média aritmética das taxas de ocupação das atividades */
    public double calcularTaxaMediaOcupacao() {
        if (atividades.isEmpty()) {
            return 0.0;
        }
        double somaTaxas = 0;
        for (Atividade a : atividades) {
            somaTaxas += a.obterTaxaOcupacao();
        }
        return somaTaxas / atividades.size();
    }

    /* MÉTODO: Retorna a atividade que atingiu o maior índice de ocupação */
    public Atividade identificarAtividadeMaiorOcupacao() {
        if (atividades.isEmpty()) {
            return null;
        }
        Atividade maior = atividades.get(0);
        for (int i = 1; i < atividades.size(); i++) {
            if (atividades.get(i).obterTaxaOcupacao() > maior.obterTaxaOcupacao()) {
                maior = atividades.get(i);
            }
        }
        return maior;
    }

    /* MÉTODO: Retorna a atividade que atingiu o menor índice de ocupação */
    /* Faz o oposto do método anterior: assume que a primeira atividade é a menor
       e percorre a lista substituindo sempre que encontrar uma taxa ainda menor */
    public Atividade identificarAtividadeMenorOcupacao() {
        if (atividades.isEmpty()) {
            return null;
        }
        Atividade menor = atividades.get(0); // Considera a primeira como menor inicialmente
        for (int i = 1; i < atividades.size(); i++) {
            /* Se a taxa da atividade atual for menor que a menor registrada, atualiza */
            if (atividades.get(i).obterTaxaOcupacao() < menor.obterTaxaOcupacao()) {
                menor = atividades.get(i);
            }
        }
        return menor;
    }
}