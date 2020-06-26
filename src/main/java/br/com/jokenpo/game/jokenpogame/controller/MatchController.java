package br.com.jokenpo.game.jokenpogame.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.jokenpo.game.jokenpogame.model.Input;
import br.com.jokenpo.game.jokenpogame.model.Play;
import br.com.jokenpo.game.jokenpogame.model.Player;
import br.com.jokenpo.game.jokenpogame.repository.MoveRepo;
import br.com.jokenpo.game.jokenpogame.repository.PlayRepo;
import br.com.jokenpo.game.jokenpogame.repository.PlayerRepo;

@RequestMapping("/match")
@RestController
public class MatchController {
	
	@Autowired
	private PlayerRepo playerRepo;
	private MoveRepo moveRepo;
	private PlayRepo playRepo;
	
	MatchController(MoveRepo moveRepo, PlayerRepo playerRepo, PlayRepo playRepo) {
		this.moveRepo = moveRepo;
		this.playerRepo = playerRepo;
		this.playRepo = playRepo;
	}

@PostMapping(value="/result", consumes=(MediaType.APPLICATION_JSON_VALUE))
public String result(@RequestBody List<Input> input){
	int win = 0;

	List<Play> list = new ArrayList<>();
	input.forEach(entry -> {
		Play play = new Play();
		play.setPlayer_id(playerRepo.findByNickname(entry.getNickname()).getId());
		play.setMove_id(moveRepo.findByPower(entry.getPower()).getId());
		playRepo.save(play);
		list.add(play);
	}); 
	
	for (int i = 0; i < list.size(); i++) {
		for (int j=0; j < list.size(); j++) {
			if (list.get(i).getMove_id() == 1 && list.get(j).getMove_id() == 2) {
				win++;
			}
			if (list.get(i).getMove_id() == 2 && list.get(j).getMove_id() == 3) {
				win++;
			}
			if (list.get(i).getMove_id() == 3 && list.get(j).getMove_id() == 1) {
				win++;
			}
			if (win == list.size()-1) {
				 Optional<Player> winner = playerRepo.findById(list.get(i).getPlayer_id());
				 
				 return ("The Winner Is:" +winner.get().getNickname().toString());
			} 

		}
	}
	return ("Tie!");
}
	

}
