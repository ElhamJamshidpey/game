package game.com.bol;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.com.bol.component.Board;
import game.com.bol.component.Pit;
import game.com.bol.component.Player;
import game.com.bol.strategy.GameStrategy;

@Component
public class GameRunner {

	@Autowired
	private GameStrategy gameStrategy;
	
	public void run() {

		Board board = initialBoard();
		gameStrategy.play(board);
		
	}
	
	public static Board initialBoard() {
		
		List<Pit> pitsA = new ArrayList<Pit>();
		Pit pa1 = new Pit("a",6);
		Pit pa2 = new Pit("b",6);
		Pit pa3 = new Pit("c",6);
		Pit pa4 = new Pit("d",6);
		Pit pa5 = new Pit("e",6);
		Pit pa6 = new Pit("f",6);
		pitsA.add(pa1);
		pitsA.add(pa2);
		pitsA.add(pa3);
		pitsA.add(pa4);
		pitsA.add(pa5);
		pitsA.add(pa6);
		
		List<Pit> pitsB = new ArrayList<Pit>();
		Pit pb1 = new Pit("a",6);
		Pit pb2 = new Pit("b",6);
		Pit pb3 = new Pit("c",6);
		Pit pb4 = new Pit("d",6);
		Pit pb5 = new Pit("e",6);
		Pit pb6 = new Pit("f",6);
		pitsB.add(pb1);
		pitsB.add(pb2);
		pitsB.add(pb3);
		pitsB.add(pb4);
		pitsB.add(pb5);
		pitsB.add(pb6);
		
		Board board = new Board(pitsA,pitsB);
		return board;
	}
}
