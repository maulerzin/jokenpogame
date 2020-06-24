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
import br.com.jokenpo.game.jokenpogame.model.Player;
import br.com.jokenpo.game.jokenpogame.repository.PlayerRepo;

@RestController
@RequestMapping("/player")
public class PlayerController {
	
	private PlayerRepo playerRepo;
	
	PlayerController(PlayerRepo playerRepo) {
		this.playerRepo = playerRepo;
	}
	
	//Adiciona um Jogador
	@PostMapping("/create")
	public Player create(@RequestBody Player player){
	   return playerRepo.save(player);
	}
	
	// Recupera Lista de Players
	@GetMapping("/all")
	public List<Player> findAll(){
		List<Player>list = playerRepo.findAll();
			if (list.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NO_CONTENT);
			}
	   
		return list;
	}
	
	//Recupera Player por ID
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<?>findById(@PathVariable Long id){
	   return playerRepo.findById(id)
	           .map(record -> ResponseEntity.ok().body(record))
	           .orElse(ResponseEntity.noContent().build());
	   
	}	

	//Exclui um Player
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable Long id) {
	   return playerRepo.findById(id)
	           .map(record -> {
	               playerRepo.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.noContent().build());
	}
}
