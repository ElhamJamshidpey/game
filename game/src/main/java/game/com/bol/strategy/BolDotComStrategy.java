package game.com.bol.strategy;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.com.bol.component.Board;
import game.com.bol.component.Pit;
import game.com.bol.component.Player;
import game.com.bol.presenter.BoardPresenter;

@Component
public class BolDotComStrategy implements GameStrategy {

	@Autowired
	private BoardPresenter presenter;
	
	private Player playerA;
	private Player playerB;
	
	public void play(Board board){
		
		playerA = new Player("Player A");
		playerB = new Player("Player B");
		boolean played = false;
		
		board.setCurrentPlayer(playerA);
		
		
		while(!gameIsFinish(board)) {
			
			
			presenter.showBoard(board);
			String sourcePit;
			String destinationPit;
			
			Scanner scan = new Scanner(System.in);

			System.out.println(board.getCurrentPlayer().getName() +" Please enter source pit (a-b-c-d-e-f):");
			sourcePit = scan.nextLine();
			
			System.out.println("And also please enter destination pit /n"
					+ "Ma-Mb-Mc-Md-Me-Mf (your pits) /n "
					+ "Big (your big pit) /n "
					+ "Oa-Ob-Oc-Od-Oe-Of (opponent's pits):");
			
		    destinationPit = scan.nextLine();
			
			if(movingValidation(board,sourcePit,destinationPit)) {
				move(board,sourcePit,destinationPit);
				played = true;
			}
			if(!playerHasAnotherTurn(board) && played) {
			//change player
			  if(board.getCurrentPlayer().getName().equals("Player A")) 
			   	 board.setCurrentPlayer(playerB);
			  else
				 board.setCurrentPlayer(playerA);
			}
			
		}

		System.out.println("WINNER IS : " + findWinner(board).getName());
	}
	
	private boolean movingValidation(Board board, String src, String des) {
		/* game rules:
		   moving just to right
		   no stone land in opponent's big pit
		*/
		return false;
	}

	private void move(Board board, String src, String des) {
		//play and land the stones into source pit to destination pit(in each pit one stone)
	}
	
	private boolean playerHasAnotherTurn(Board board) {
		//if last stone land in player big pit return true
		return false;
	}
	public boolean gameIsFinish(Board board) {
		
		int playerApits = board.getPlayerAPits().stream().map(Pit::getStoneNumber).mapToInt(Integer::intValue).sum();
		int playerBpits = board.getPlayerBPits().stream().map(Pit::getStoneNumber).mapToInt(Integer::intValue).sum();
		
		if(playerApits > 0 && playerBpits > 0)
			return false;
		
		board.getPlayerALargerPit().addStone(playerApits);
		board.getPlayerAPits().clear();
		board.getPlayerBLargerPit().addStone(playerBpits);
		board.getPlayerBPits().clear();
	
		return true;
	}

	public Player findWinner(Board board) {
		if(board.getPlayerALargerPit().getStoneNumber() > board.getPlayerBLargerPit().getStoneNumber())
			return playerA;
		return playerB;

	}
	
}
