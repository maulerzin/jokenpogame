package br.com.jokenpo.game.jokenpogame.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Play {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long move_id;
	
	private Long player_id;
	
	public Long getMove_id() {
		return move_id;
	}

	public void setMove_id(Long move_id) {
		this.move_id = move_id;
	}

	public Long getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(Long player_id) {
		this.player_id = player_id;
	}

}