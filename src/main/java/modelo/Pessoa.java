package modelo;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pessoa {
    private String cpf;
    private String nome;
    private String email;
    private LocalDate dataNascimento;

    //Contrutor com verificações
    public Pessoa (String cpf, String nome, String email, LocalDate dataNascimento){
        if (cpf == null || cpf.trim().isEmpty()){
            throw new IllegalArgumentException("CPF inválido");
        }
        else{
            this.cpf = cpf;
        }
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("Nome iválido");
        }
        else {
            this.nome = nome;
        }
        // O professor não pediu porem é uma maneira de segurança
        if (dataNascimento == null){
            throw new IllegalArgumentException("Data de nascimeto iválida");
        }
        else {

            this.dataNascimento = dataNascimento;
        }
        this.email = email;
    }
    //Getters para todos os atributos
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    // Set apenas para o atributo nome

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("Nome iválido");
        }
        else {
            this.nome = nome;
        }
    }

    // Métodos

    public abstract String obterTipoPessoa();

    public int calcularIdade (){
        /* O period porque ele representa o intevalo entre datas
            O  between() é um metodo do period onde calcula a diferença entre duas datas
            O getYers é usado para formatar a saida do between porque ele tem a saida
            na seguinte formatação ex: P22Y0M21D o getYears retorna apenas o 22
        */
            return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
    //metodo tostring sobrescrito para exibir a mensagem
    @Override
    public String toString() {
        return nome + " (" + cpf + ") - " + obterTipoPessoa();
    }
}
