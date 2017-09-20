package hr.corvus.krizickruzic.game.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.corvus.krizickruzic.game.database.DatabaseClass;
import hr.corvus.krizickruzic.game.resource.GameStats;

@RestController
@RequestMapping("/game")
public class GameStatsController {
	
	@RequestMapping("/stats")
	public GameStats getGameStats() {
		
		return DatabaseClass.getGameStats();
	}
	
}
