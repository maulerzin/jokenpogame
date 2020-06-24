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
import br.com.jokenpo.game.jokenpogame.model.Move;
import br.com.jokenpo.game.jokenpogame.repository.MoveRepo;

@RestController
@RequestMapping("/move")
public class MoveController {
	
	private MoveRepo moveRepo;
	
	MoveController(MoveRepo moveRepo) {
		this.moveRepo = moveRepo;
	}
	
	//Adiciona um Poder
	@PostMapping("/create")
	public 	Move create(@RequestBody Move move){
	   return moveRepo.save(move);
	}
	
	// Recupera Lista de poderes disponíveis
	@GetMapping("/all")
	public List<Move> findAll(){
		List<Move>list = moveRepo.findAll();
			if (list.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NO_CONTENT);
			}
	   
		return list;
	}
	
	//Exclui um poder
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable Long id) {
	   return moveRepo.findById(id)
	           .map(record -> {
	               moveRepo.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.noContent().build());
	}
}
