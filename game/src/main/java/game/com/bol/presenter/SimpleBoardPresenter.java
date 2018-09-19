package game.com.bol.presenter;

import org.springframework.stereotype.Component;

import game.com.bol.component.Board;

@Component
public class SimpleBoardPresenter implements BoardPresenter{

	@Override
	public void showBoard(Board board) {
		System.out.print("  ");
		board.getPlayerAPits().stream().forEach(System.out::print);
		System.out.println("");
		System.out.println(board.getPlayerALargerPit().toString() + "a b c d e f " + board.getPlayerBLargerPit().toString());
		System.out.print("  ");
		board.getPlayerBPits().stream().forEach(System.out::print);
		System.out.println("");		
	}

}
