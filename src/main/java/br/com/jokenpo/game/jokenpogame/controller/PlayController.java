package br.com.jokenpo.game.jokenpogame.controller;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import br.com.jokenpo.game.jokenpogame.model.Play;
import br.com.jokenpo.game.jokenpogame.repository.PlayRepo;

@RestController
@RequestMapping("/play")
public class PlayController {
	
	private PlayRepo playRepo;
	
	PlayController(PlayRepo playRepo) {
		this.playRepo = playRepo;
	}
	
	//Adiciona uma jogada
	@PostMapping("/create")
	public 	Play create(@RequestBody Play play){
	   return playRepo.save(play);
	}
	
	// Recupera Lista de jogadas
	@GetMapping("/all")
	public List<Play> findAll(){
		List<Play>list = playRepo.findAll();
			if (list.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NO_CONTENT);
			}
	   
		return list;
	}
	
	//Exclui uma jogada
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable Long id) {
	   return playRepo.findById(id)
	           .map(record -> {
	               playRepo.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.noContent().build());
	}
}
