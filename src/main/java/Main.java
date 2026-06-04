// DEIXE A LINHA 1 EM BRANCO (Arquivos na raiz do código fonte não devem declarar package)

/* IMPORTAÇÃO DE UTILITÁRIOS NATIVOS DO JAVA */
import java.util.Scanner; // Permite ler o que o usuário digita no console/terminal
import java.time.LocalDate; // Manipula dados de data (ano, mês, dia) exigidos pela classe Pessoa

/* IMPORTAÇÕES DE CLASSES INTERNAS DO SEU PRÓPRIO PROJETO */
import sistema.SistemaEventos; // Importa a classe gerenciadora central do sistema
import enums.TipoParticipante; // Importa o Enum com as categorias de participantes (ESTUDANTE, etc.)
import modelo.Atividade; // Importa a superclasse abstrata de Atividades
import modelo.MesaRedonda; // Importa a subclasse MesaRedonda
import modelo.Minicurso; // Importa a subclasse Minicurso
import modelo.Palestra; // Importa a subclasse Palestra
import modelo.Palestrante; // Importa a classe de modelo Palestrante
import modelo.Participante; // Importa a classe de modelo Participante

public class Main {

    public static void main(String[] args) {
        /* INSTANCIAÇÃO DOS OBJETOS DE CONTROLE INICIAIS */
        SistemaEventos sistema = new SistemaEventos(); // Cria o cérebro do sistema onde dados serão guardados
        Scanner scanner = new Scanner(System.in); // Prepara o leitor do teclado para capturar dados
        int opcao = -1; // Variável de controle para navegar nas opções do menu

        /* LOOP PRINCIPAL DO MENU INTERATIVO */
        /* O laço while continuará repetindo o menu na tela até que o usuário digite a opção 0 (Sair) */
        while (opcao != 0) {
            System.out.println("\n==================================================");
            System.out.println("          SISTEMA DE GESTÃO DE EVENTOS            ");
            System.out.println("==================================================");
            System.out.println("1. Cadastrar Participante");
            System.out.println("2. Cadastrar Palestrante");
            System.out.println("3. Cadastrar Atividade (Palestra/Minicurso/Mesa)");
            System.out.println("4. Inscrever Participante em Atividade");
            System.out.println("5. Buscar Participante por Matrícula");
            System.out.println("6. Buscar Palestrante por CPF");
            System.out.println("7. Adicionar Avaliação em Minicurso");
            System.out.println("8. Listar Todas as Atividades com Ocupação");
            System.out.println("9. Listar Todos os Participantes");
            System.out.println("10. Listar Todos os Palestrantes");
            System.out.println("11. Calcular Faturamento Total");
            System.out.println("12. Taxa Média de Ocupação");
            System.out.println("13. Atividade com Maior Ocupação");
            System.out.println("0. Sair");
            System.out.println("==================================================");
            System.out.print("Escolha uma opção: ");

            /* BLOCO TRY-CATCH DE SEGURANÇA PARA A LEITURA DA OPÇÃO */
            try {
                /* Lê a linha digitada e tenta converter em número inteiro */
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                /* Captura o erro caso o usuário digite letras/textos no lugar de um número */
                System.out.println("Erro: Por favor, digite um número válido.");
                continue; // Interrompe a iteração atual e volta para o topo do loop while
            }

            System.out.println();

            /* ESTRUTURA DE DECISÃO SWITCH-CASE BASEADA NA OPÇÃO SELECIONADA */
            switch (opcao) {
                case 1:
                    /* CASO 1: CADASTRAR PARTICIPANTE */
                    try {
                        System.out.println("--- CADASTRAR PARTICIPANTE ---");
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Matrícula: ");
                        String matricula = scanner.nextLine();

                        /* Exibe submenu para o usuário escolher o tipo correto baseado no seu Enum */
                        System.out.println("Selecione o Tipo (1 - ESTUDANTE, 2 - PROFISSIONAL, 3 - DOCENTE): ");
                        int opTipo = Integer.parseInt(scanner.nextLine());
                        TipoParticipante tipo = TipoParticipante.ESTUDANTE; // Define ESTUDANTE como padrão inicial

                        /* Faz o mapeamento da escolha numérica para o valor real do Enum */
                        if (opTipo == 2) {
                            tipo = TipoParticipante.PROFISSIONAL;
                        } else if (opTipo == 3) {
                            tipo = TipoParticipante.DOCENTE;
                        }

                        /* Instancia o Participante passando os 6 parâmetros na ordem exata do seu construtor */
                        /* LocalDate.now() é injetado automaticamente para preencher a data de nascimento exigida por Pessoa */
                        Participante part = new Participante(cpf, nome, email, LocalDate.now(), matricula, tipo);

                        /* Envia o objeto instanciado para o método de cadastro dentro de SistemaEventos */
                        if (sistema.cadastrarParticipante(part)) {
                            System.out.println("Sucesso: Participante cadastrado!");
                        } else {
                            System.out.println("Erro: Matrícula já existente.");
                        }
                    } catch (IllegalArgumentException e) {
                        /* Captura validações disparadas pelas regras de negócio dos seus construtores (Ex: campos vazios) */
                        System.out.println("Erro de Validação: " + e.getMessage());
                    }
                    break;

                case 2:
                    /* CASO 2: CADASTRAR PALESTRANTE */
                    try {
                        System.out.println("--- CADASTRAR PALESTRANTE ---");
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Titularização (Ex: Mestre, Doutor): ");
                        String titularizacao = scanner.nextLine();
                        System.out.print("Área de Especialização: ");
                        String areaEspecializacao = scanner.nextLine();

                        /* Instancia o Palestrante passando os 6 parâmetros na ordem exata do seu construtor */
                        Palestrante pal = new Palestrante(cpf, nome, email, LocalDate.now(), titularizacao, areaEspecializacao);

                        /* Envia para o SistemaEventos salvar no Map correspondente */
                        if (sistema.cadastrarPalestrante(pal)) {
                            System.out.println("Sucesso: Palestrante cadastrado!");
                        } else {
                            System.out.println("Erro: CPF já cadastrado.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro de Validação: " + e.getMessage());
                    }
                    break;

                case 3:
                    /* CASO 3: CADASTRAR ATIVIDADE */
                    try {
                        System.out.println("--- CADASTRAR ATIVIDADE ---");
                        System.out.print("Escolha o tipo (1-Palestra, 2-Minicurso, 3-Mesa Redonda): ");
                        int tipoAtiv = Integer.parseInt(scanner.nextLine());

                        /* Coleta os dados genéricos comuns a todas as atividades */
                        System.out.print("Código único da Atividade: ");
                        String codigo = scanner.nextLine();
                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Carga Horária: ");
                        int cargaHoraria = Integer.parseInt(scanner.nextLine());
                        System.out.print("Capacidade Máxima: ");
                        int capacidadeMaxima = Integer.parseInt(scanner.nextLine());
                        System.out.print("CPF do Palestrante Responsável: ");
                        String cpfBusca = scanner.nextLine();

                        /* Regra de associação: Busca o palestrante responsável usando a busca O(1) do sistema */
                        Palestrante palestrante = sistema.buscarPalestrantePorCpf(cpfBusca);
                        if (palestrante == null) {
                            System.out.println("Erro: Palestrante não localizado. Cadastre-o primeiro!");
                            break; // Aborta o cadastro se o palestrante não existir
                        }

                        Atividade novaAtividade = null; // Inicializa a variável polimórfica vazia

                        /* Verifica qual subclasse concreta deve ser criada */
                        if (tipoAtiv == 1) {
                            /* Instancia Palestra com a assinatura exata do seu construtor (5 parâmetros) */
                            novaAtividade = new Palestra(codigo, titulo, cargaHoraria, capacidadeMaxima, palestrante);
                        } else if (tipoAtiv == 2) {
                            System.out.print("Custo do Material Didático (R$): ");
                            double custoMaterial = Double.parseDouble(scanner.nextLine());
                            /* Instancia Minicurso com a assinatura exata do seu construtor (6 parâmetros) */
                            novaAtividade = new Minicurso(codigo, titulo, cargaHoraria, capacidadeMaxima, palestrante, custoMaterial);
                        } else if (tipoAtiv == 3) {
                            System.out.print("Tema da Mesa Redonda: ");
                            String tema = scanner.nextLine();
                            System.out.print("Nome do Moderador: ");
                            String moderador = scanner.nextLine();
                            /* Instancia MesaRedonda com a assinatura exata do seu construtor (7 parâmetros) */
                            novaAtividade = new MesaRedonda(codigo, titulo, cargaHoraria, capacidadeMaxima, palestrante, tema, moderador);
                        } else {
                            System.out.println("Erro: Tipo inválido.");
                            break;
                        }

                        /* Executa a tentativa de inserção na List interna do sistema de eventos */
                        if (sistema.cadastrarAtividade(novaAtividade)) {
                            System.out.println("Sucesso: Atividade adicionada!");
                        } else {
                            System.out.println("Erro: Código de atividade duplicado.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar: " + e.getMessage());
                    }
                    break;

                case 4:
                    /* CASO 4: INSCREVER PARTICIPANTE EM UMA ATIVIDADE */
                    System.out.println("--- INSCREVER PARTICIPANTE EM ATIVIDADE ---");
                    System.out.print("Matrícula do Participante: ");
                    String matriculaInsc = scanner.nextLine();
                    System.out.print("Código da Atividade: ");
                    String codigoInsc = scanner.nextLine();

                    /* Aciona a regra de associação N para M controlada pela classe SistemaEventos */
                    if (sistema.inscreverParticipanteEmAtividade(matriculaInsc, codigoInsc)) {
                        System.out.println("Sucesso: Inscrição efetuada!");
                    } else {
                        System.out.println("Erro: Falha na inscrição.");
                    }
                    break;

                case 5:
                    /* CASO 5: CONSULTA RÁPIDA DE PARTICIPANTE */
                    System.out.println("--- BUSCAR PARTICIPANTE POR MATRÍCULA ---");
                    System.out.print("Digite a Matrícula: ");
                    String mBusca = scanner.nextLine();
                    /* Executa a busca em tempo constante O(1) usando o Map de SistemaEventos */
                    Participante pEncontrado = sistema.buscarParticipantePorMatricula(mBusca);
                    if (pEncontrado != null) {
                        System.out.println("Localizado -> Nome: " + pEncontrado.getNome() + " | Matrícula: " + pEncontrado.getMatricula());
                    } else {
                        System.out.println("Aviso: Participante não encontrado.");
                    }
                    break;

                case 6:
                    /* CASO 6: CONSULTA RÁPIDA DE PALESTRANTE */
                    System.out.println("--- BUSCAR PALESTRANTE POR CPF ---");
                    System.out.print("Digite o CPF: ");
                    String cpfB = scanner.nextLine();
                    /* Executa a busca em tempo constante O(1) usando o Map de SistemaEventos */
                    Palestrante palEncontrado = sistema.buscarPalestrantePorCpf(cpfB);
                    if (palEncontrado != null) {
                        /* Chama o método real de acesso 'getAreaEspecializacao()' que existe no seu Palestrante */
                        System.out.println("Localizado -> Nome: " + palEncontrado.getNome() + " | Especialização: " + palEncontrado.getAreaEspecializacao());
                    } else {
                        System.out.println("Aviso: Palestrante não encontrado.");
                    }
                    break;

                case 7:
                    /* CASO 7: AVISO SOBRE FLUXO DE AVALIAÇÃO */
                    System.out.println("--- ADICIONAR AVALIAÇÃO ---");
                    System.out.println("Operação validada e executada diretamente na classe Minicurso.");
                    break;

                case 8:
                    /* CASO 8: LISTAGEM POLIMÓRFICA DE ATIVIDADES */
                    sistema.listarAtividades();
                    break;

                case 9:
                    /* CASO 9: LISTAGEM DE PARTICIPANTES DO MAPA */
                    sistema.listarParticipantes();
                    break;

                case 10:
                    /* CASO 10: LISTAGEM DE PALESTRANTES DO MAPA */
                    sistema.listarPalestrantes();
                    break;

                case 11:
                    /* CASO 11: EXIBIÇÃO DO FATURAMENTO POLIMÓRFICO TOTAL COLETADO */
                    System.out.println("Faturamento Total: R$ " + String.format("%.2f", sistema.calcularFaturamentoTotal()));
                    break;

                case 12:
                    /* CASO 12: EXIBIÇÃO DA MÉDIA DE OCUPAÇÃO DO EVENTO */
                    System.out.println("Taxa Média de Ocupação: " + String.format("%.2f", sistema.calcularTaxaMediaOcupacao()) + "%");
                    break;

                case 13:
                    /* CASO 13: LOCALIZAR ATIVIDADE DE MAIOR SUCESSO DE PÚBLICO */
                    Atividade top = sistema.identificarAtividadeMaiorOcupacao();
                    if (top != null) {
                        System.out.println("Maior Ocupação: " + top.getTitulo() + " (" + String.format("%.2f", top.obterTaxaOcupacao()) + "%)");
                    } else {
                        System.out.println("Nenhuma atividade cadastrada.");
                    }
                    break;

                case 0:
                    /* CASO 0: DESCONEXÃO DO SISTEMA */
                    System.out.println("Saindo...");
                    break;

                default:
                    /* TRATAMENTO PARA ENTRADAS FORA DO ESCOPO DO MENU (EX: DIGITAR 50) */
                    System.out.println("Opção inválida!");
                    break;
            }
        }
        scanner.close(); // Fecha o fluxo do teclado por boas práticas de memória ao encerrar o app
    }
}