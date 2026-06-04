# Sistema de Gestão de Eventos Acadêmicos

Projeto da disciplina de **Programação Orientada a Objetos** — sistema de gerenciamento de eventos acadêmicos desenvolvido em Java puro, demonstrando os pilares da POO: **Herança**, **Encapsulamento**, **Polimorfismo** e **Abstração**.

---

## 📁 Estrutura do Projeto

```
trabalhoPOO/
└── src/main/java/
    ├── enums/
    │   └── TipoParticipante.java   ← enum (ESTUDANTE, PROFISSIONAL, DOCENTE)
    ├── modelo/
    │   ├── Pessoa.java             ← classe abstrata base (Herança)
    │   ├── Participante.java       ← subclasse de Pessoa
    │   ├── Palestrante.java        ← subclasse de Pessoa
    │   ├── Atividade.java          ← classe abstrata base (Herança)
    │   ├── Palestra.java           ← subclasse de Atividade (custo: R$ 0)
    │   ├── Minicurso.java          ← subclasse de Atividade (custo: material)
    │   └── MesaRedonda.java        ← subclasse de Atividade (custo: R$ 25)
    ├── sistema/
    │   └── SistemaEventos.java     ← gerencia tudo (Map, List, vetor)
    └── Main.java                   ← ponto de entrada, menu interativo
```

---

## ⚙️ Pré-requisitos

- **Java JDK 11 ou superior** instalado
- Verificar instalação:
  ```bash
  java -version
  javac -version
  ```

---

## 🔨 Compilação

A partir da **raiz do projeto** (`trabalhoPOO/`), execute:

```bash
javac -cp src/main/java -d out ^
  src/main/java/enums/TipoParticipante.java ^
  src/main/java/modelo/Pessoa.java ^
  src/main/java/modelo/Atividade.java ^
  src/main/java/modelo/Palestrante.java ^
  src/main/java/modelo/Participante.java ^
  src/main/java/modelo/Palestra.java ^
  src/main/java/modelo/Minicurso.java ^
  src/main/java/modelo/MesaRedonda.java ^
  src/main/java/sistema/SistemaEventos.java ^
  src/main/java/Main.java
```

> O parâmetro `-d out` salva os arquivos `.class` compilados na pasta `out/`.  
> Se a pasta `out/` não existir, crie com: `mkdir out`

**No Linux/Mac**, substitua `^` por `\`:

```bash
javac -cp src/main/java -d out \
  src/main/java/enums/TipoParticipante.java \
  src/main/java/modelo/Pessoa.java \
  src/main/java/modelo/Atividade.java \
  src/main/java/modelo/Palestrante.java \
  src/main/java/modelo/Participante.java \
  src/main/java/modelo/Palestra.java \
  src/main/java/modelo/Minicurso.java \
  src/main/java/modelo/MesaRedonda.java \
  src/main/java/sistema/SistemaEventos.java \
  src/main/java/Main.java
```

---

## ▶️ Execução

Após compilar, execute o programa:

```bash
java -cp out Main
```

---

## 📋 Opções do Menu

| # | Opção | Conceito demonstrado |
|---|-------|----------------------|
| 1 | Cadastrar Participante | Encapsulamento (validações) + Map |
| 2 | Cadastrar Palestrante | Encapsulamento + Map |
| 3 | Cadastrar Atividade (Palestra / Minicurso / Mesa) | Herança + List |
| 4 | Inscrever Participante em Atividade | Set (unicidade) + polimorfismo |
| 5 | Buscar Participante por Matrícula | Map O(1) |
| 6 | Buscar Palestrante por CPF | Map O(1) |
| 7 | Adicionar Avaliação em Minicurso | Vetor (array) |
| 8 | Listar Todas as Atividades com Ocupação | Relatório + polimorfismo |
| 9 | Listar Todos os Participantes | Relatório |
| 10 | Listar Todos os Palestrantes | Relatório |
| 11 | Calcular Faturamento Total | Polimorfismo (`calcularCusto()`) |
| 12 | Taxa Média de Ocupação | Relatório |
| 13 | Atividade com Maior/Menor Ocupação | Relatório |
| 0 | Sair | — |

---

## 🗂️ Estruturas de Dados Utilizadas

| Tipo | Atributo | Classe | Finalidade |
|------|----------|--------|------------|
| `String[]` | `diasFuncionamento` | `SistemaEventos` | Vetor fixo com os 7 dias da semana |
| `double[]` | `avaliacoes` | `Minicurso` | Vetor fixo com até 5 avaliações |
| `List<Atividade>` | `atividadesFrequentadas` | `Participante` | Histórico de atividades do participante |
| `List<Atividade>` | `atividades` | `SistemaEventos` | Lista de atividades cadastradas |
| `Set<Participante>` | `participantesInscritos` | `Atividade` | Garante inscrição única por atividade |
| `Map<String, Participante>` | `participantesPorMatricula` | `SistemaEventos` | Busca O(1) por matrícula |
| `Map<String, Palestrante>` | `palestrantesPorCpf` | `SistemaEventos` | Busca O(1) por CPF |

---

## 🧱 Conceitos de POO Aplicados

- **Herança**: `Participante` e `Palestrante` estendem `Pessoa`; `Palestra`, `Minicurso` e `MesaRedonda` estendem `Atividade`
- **Encapsulamento**: todos os atributos são `private`; validações via `IllegalArgumentException` em construtores e setters; getters retornam cópias defensivas de coleções
- **Polimorfismo**: `calcularCusto()` é chamado polimorficamente no loop de faturamento
- **Abstração**: `Pessoa` e `Atividade` são classes abstratas com métodos abstratos obrigatórios (`obterTipoPessoa()`, `calcularCusto()`, `obterTipoAtividade()`)
