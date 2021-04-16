package br.com.bandtec.avaliacaocontinuada;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LutadorRepository extends JpaRepository<Lutador, Integer> {

    List<Lutador> findAllByVivo(boolean vivo);

    @Query("from Lutador ORDER BY forcaGolpe desc")
    List<Lutador> findAllByVida();

    Lutador findLutadorById(int id);

    @Query("from Lutador where vida <= 0")
    List<Lutador> getMortos();
//    @Query("from Lutador where ")
//    List<Lutador> getDoisLutadores
}
