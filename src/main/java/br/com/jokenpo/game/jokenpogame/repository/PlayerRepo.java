package br.com.jokenpo.game.jokenpogame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jokenpo.game.jokenpogame.model.Player;


@Repository
public interface PlayerRepo  extends JpaRepository<Player, Long> {

}
