package br.com.alura.cursoAlura.form;


import br.com.alura.cursoAlura.model.Curso;
import br.com.alura.cursoAlura.model.Topico;
import br.com.alura.cursoAlura.repository.CursoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TopicoForm {

    @NotNull
    @NotEmpty
    @Size(max = 50, min = 3)
    private String titulo;

    @NotNull
    @NotEmpty
    @Size(max = 50, min = 3)
    private String mensagem;

    @NotNull
    @NotEmpty
    @Size(max = 50, min = 3)
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepository repository) {
        Curso curso = repository.findByNome(nomeCurso);
        return new Topico(titulo, mensagem, curso);
    }
}
