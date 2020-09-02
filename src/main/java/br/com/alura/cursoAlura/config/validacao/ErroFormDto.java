package br.com.alura.cursoAlura.config.validacao;

public class ErroFormDto {
    private String campoErro;
    private String mensagemErro;

    public ErroFormDto(String campoErro, String mensagemErro) {
        this.campoErro = campoErro;
        this.mensagemErro = mensagemErro;
    }

    public String getCampoErro() {
        return campoErro;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }
}
