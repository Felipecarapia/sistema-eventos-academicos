package modelo;

import java.time.LocalDate;

public class Palestrante extends Pessoa{
    private String titularizacao;
    private String areaEspecializacao;

    public Palestrante(String cpf, String nome, String email, LocalDate dataNascimento, String titularizacao, String areaEspecializacao) {
        super(cpf, nome, email, dataNascimento);
        if (titularizacao == null || titularizacao.trim().isEmpty()){
            throw new IllegalArgumentException("Titulação inválida");
        }
        if (areaEspecializacao == null || areaEspecializacao.trim().isEmpty()){
            throw new IllegalArgumentException("Área de especialização inválida");
        }
        this.titularizacao = titularizacao;
        this.areaEspecializacao = areaEspecializacao;
    }
    // sobrescreve para dizer que é paplestrante
    @Override
    public String obterTipoPessoa() {
        return "Palestrante";
    }

    public String getTitularizacao() {
        return titularizacao;
    }

    public void setTitularizacao(String titularizacao) {
        if (titularizacao == null || titularizacao.trim().isEmpty()){
            throw new IllegalArgumentException("Titulação inválida");
        }
        else {
            this.titularizacao = titularizacao;
        }
    }

    public String getAreaEspecializacao() {
        return areaEspecializacao;
    }

    public void setAreaEspecializacao(String areaEspecializacao) {
        if (areaEspecializacao == null || areaEspecializacao.trim().isEmpty()){
            throw new IllegalArgumentException("Área de especialização inválida");
        }
        else {
            this.areaEspecializacao = areaEspecializacao;
        }
    }
}
