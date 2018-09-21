package game.com.bol.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.com.bol.GameContext;
import game.com.bol.component.Board;
import game.com.bol.component.Pit;
import game.com.bol.component.Player;
import game.com.bol.exception.MovingException;

@Component
public class BolDotComStrategy implements GameStrategy {

	@Autowired
	private GameContext context;
	
	
	public void play(String src, String des) throws MovingException{
		
		Board board = context.getBoard(); 
		movingValidation(src,des);
		
		//play and land the stones into source pit to destination pit(in each pit one stone)
		context.reloadBoard(board);
			
		//switch player
		if(!playerHasAnotherTurn()) {
			if(context.getCurrentPlayer().getName().equals(context.getFirstPlayer())) 
				context.setCurrentPlayer(context.getSecondPlayer());
			else
				context.setCurrentPlayer(context.getFirstPlayer());
		}
		
		
//		if(gameIsFinish()) 
//			findWinner();
	}
	
	public boolean movingValidation(String src, String des) throws MovingException{
		/* game rules:
		   moving just to right
		   no stone land in opponent's big pit
		*/
		return false;
	}
	
	private boolean playerHasAnotherTurn() {
		//if last stone land in player big pit return true
		return false;
	}
	
	public boolean gameIsFinish() {
		Board board = context.getBoard();
		int playerApits = board.getFirstPlayerAPits().stream().map(Pit::getStoneNumber).mapToInt(Integer::intValue).sum();
		int playerBpits = board.getSecondPlayerBPits().stream().map(Pit::getStoneNumber).mapToInt(Integer::intValue).sum();
		
		if(playerApits > 0 && playerBpits > 0)
			return false;
		
		board.getFirstPlayerLargerPit().addStone(playerApits);
		board.getFirstPlayerAPits().clear();
		board.getSecondPlayerLargerPit().addStone(playerBpits);
		board.getSecondPlayerBPits().clear();
	
		return true;
	}

	public Player findWinner() {
		if(context.getBoard().getFirstPlayerLargerPit().getStoneNumber() > context.getBoard().getSecondPlayerLargerPit().getStoneNumber())
			return context.getFirstPlayer();
		return context.getSecondPlayer();

	}
	
}
