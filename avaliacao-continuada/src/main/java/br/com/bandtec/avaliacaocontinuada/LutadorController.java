package br.com.bandtec.avaliacaocontinuada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lutadores")
public class LutadorController {

    @Autowired
    private LutadorRepository repository;

    @PostMapping
    public ResponseEntity addLutadores(@RequestBody @Valid Lutador lutador){
        repository.save(lutador);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity getLutadores(){
        List<Lutador> lutadores = repository.findAll();
        if (lutadores.isEmpty()){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.status(200).body(lutadores);
        }
    }
}
