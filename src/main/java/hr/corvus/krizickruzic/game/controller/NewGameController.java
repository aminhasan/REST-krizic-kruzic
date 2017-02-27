package hr.corvus.krizickruzic.game.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.corvus.krizickruzic.game.resource.NewGame;

@RestController
@RequestMapping("/game")
public class NewGameController {

	private final AtomicLong gameId = new AtomicLong();
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public NewGame newGame() {
		return new NewGame(gameId.incrementAndGet());
	}
}
