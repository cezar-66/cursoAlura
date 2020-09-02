package br.com.alura.cursoAlura.controller;

import br.com.alura.cursoAlura.dto.DetalhesTopicoDTO;
import br.com.alura.cursoAlura.dto.TopicoDTO;
import br.com.alura.cursoAlura.form.AtualizacaoFormTopico;
import br.com.alura.cursoAlura.form.TopicoForm;
import br.com.alura.cursoAlura.model.Topico;
import br.com.alura.cursoAlura.repository.CursoRepository;
import br.com.alura.cursoAlura.repository.TopicoRepository;
import org.omg.CORBA.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDTO> listaTopicos(String nomeCurso){
        if(nomeCurso == null){
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDTO.converter(topicos);
        }else {
            List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso);
            return TopicoDTO.converter(topicos);
        }
    }

    @PostMapping
    @Transactional // "Avisa Ao Spring que deve comitar a transação ao final do metodo"
    public ResponseEntity<TopicoDTO> cadastarTopico(@RequestBody @Valid
                                                    TopicoForm topicoForm,
                                                    UriComponentsBuilder uriBuilder){
        Topico topico =  topicoForm.converter(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }


    @GetMapping("/{id}")
    public ResponseEntity<DetalhesTopicoDTO> detalhar(@PathVariable Long id) {
        if(topicoRepository.findById(id).isPresent()){
            Optional<Topico> topico = topicoRepository.findById(id);
            return ResponseEntity.ok(new DetalhesTopicoDTO(topico.get()));
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    @Transactional // "Avisa Ao Spring que deve comitar a transação ao final do metodo"
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid
                                               AtualizacaoFormTopico formTopico ){
        if(topicoRepository.findById(id).isPresent()){
            Topico topico = formTopico.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDTO(topico));
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    @Transactional // "Avisa Ao Spring que deve comitar a transação ao final do metodo"
    public ResponseEntity<Any> remover(@PathVariable Long id){
        if (topicoRepository.findById(id).isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
