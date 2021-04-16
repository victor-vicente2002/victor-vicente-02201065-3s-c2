package br.com.bandtec.avaliacaocontinuada;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LutadorRepository extends JpaRepository<Lutador, Integer> {

    //    @Query("select vida from Lutador ORDER BY vida desc")
//    List<Lutador> findAllByVida();
}
