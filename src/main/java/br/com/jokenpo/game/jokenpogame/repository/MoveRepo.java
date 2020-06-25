package br.com.jokenpo.game.jokenpogame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jokenpo.game.jokenpogame.model.Move;

@Repository
public interface MoveRepo extends JpaRepository<Move, Long> {
	
	Move findByPower(String power);
	

}
