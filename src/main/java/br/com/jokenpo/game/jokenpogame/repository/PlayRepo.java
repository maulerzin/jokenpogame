package br.com.jokenpo.game.jokenpogame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.jokenpo.game.jokenpogame.model.Play;

@Repository
public interface PlayRepo extends JpaRepository<Play, Long> {

}
