package br.com.jokenpo.game.jokenpogame.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jokenpo.game.jokenpogame.model.Input;
import br.com.jokenpo.game.jokenpogame.model.Play;
import br.com.jokenpo.game.jokenpogame.repository.MoveRepo;
import br.com.jokenpo.game.jokenpogame.repository.PlayRepo;
import br.com.jokenpo.game.jokenpogame.repository.PlayerRepo;

@RequestMapping("/match")
@RestController
public class MatchController {
	
	PlayerRepo playerRepo;
	MoveRepo moveRepo;
	PlayRepo playRepo;
	
@PostMapping("/result")
public String result(@RequestBody List<Input> input){
	int win = 0;
	
	List<Play> list = new ArrayList<>();
	System.out.println("FDP");
	if(input.isEmpty()) {
		System.out.println("ta vazio");
	}
	System.out.println(input.toString());
	input.forEach(entry -> {
		Play play = new Play();
		System.out.println(entry);
		play.setPlayer_id(playerRepo.findByNickname(entry.getNickname()).getPlayer_id());
		System.out.println("FDP3");
		play.setMove_id(moveRepo.findByPower(entry.getPower()).getId());
		System.out.println("FDP4");
		playRepo.save(play);
		System.out.println("FDP5");
		list.add(play);
		System.out.println("FDP6");
	}); 
	
	for (int i = 0; i < list.size()-1; i++) {
		for (int j=1; j < list.size(); j++) {
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
				return list.get(i).getPlayer_id().toString();
			} 

		}
	}
	return ("Tie!");
}
	

}
