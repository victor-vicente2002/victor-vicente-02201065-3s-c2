package br.com.bandtec.avaliacaocontinuada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

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
        List<Lutador> lutadores = repository.findAllByVida();
        if (lutadores.isEmpty()){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.status(200).body(lutadores);
        }
    }

    @GetMapping("/contagem-vivos")
    public ResponseEntity getLutadoresVivos() {
        List<Lutador> lutadoresVivos = repository.findAllByVivo(true);
        if (lutadoresVivos.isEmpty()){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.status(200).body(lutadoresVivos.size());
        }
    }

    @PostMapping("/{id}/concentrar")
    public ResponseEntity addConcentracao(@PathVariable int id){
        Lutador lutador = repository.findLutadorById(id);
        if (lutador.getConcentracoesRealizadas() >= 3){
            return ResponseEntity.status(400).body("Lutador já se concentrou 3 vezes!");
        }else{
            lutador.setConcentracoesRealizadas(lutador.getConcentracoesRealizadas()+1);
            lutador.setVida(lutador.getVida()*1.15);
            repository.save(lutador);
            return ResponseEntity.status(201).build();
        }
    }

    @PostMapping("/golpe")
    public ResponseEntity addGolpe(@RequestBody Golpe golpe){
        Lutador lutadorbate = repository.findLutadorById(golpe.getIdLutadorBate());
        Lutador lutadorApanha = repository.findLutadorById(golpe.getIdLutadorApanha());

        lutadorApanha.setVida(lutadorApanha.getVida()-lutadorbate.getForcaGolpe());
        repository.save(lutadorApanha);
        return ResponseEntity.status(201).body("deu certo");
    }
}
