package game.com.bol.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.com.bol.GameContext;
import game.com.bol.component.Board;
import game.com.bol.component.Player;
import game.com.bol.exception.LoginException;
import game.com.bol.exception.MovingException;
import game.com.bol.strategy.GameStrategy;

@Component
public class BoardPresenter {
	
	@Autowired
	private GameContext context;
	@Autowired
	private GameStrategy game;
	
	public Board loadBoard() {
		return context.getBoard();
	}
	
	public void play(String src,String des) throws MovingException{
		game.play(src,des);
	}
	
	public void loginPlayer(Player player) throws LoginException{
		context.addPlayer(player);
	}
	
	public boolean gameIsFinish() {
		return game.gameIsFinish();
	}
	
	public Player findWinner() {
		if(game.gameIsFinish())
			return game.findWinner();
		return null;
	}
	
}
