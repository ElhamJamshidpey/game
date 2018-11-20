package com.github.elhamjamshidpey.africanBoardGame.view;

import org.springframework.stereotype.Component;

import com.github.elhamjamshidpey.africanBoardGame.component.Board;

@Component
public class SimpleBoardView implements BoardView {

	@Override
	public void showBoard(Board board) {
		System.out.print("  ");
		board.getFirstPlayerAPits().stream().forEach(System.out::print);
		System.out.println("");
		System.out.println(board.getFirstPlayerLargerPit().toString() + "a b c d e f "
				+ board.getSecondPlayerLargerPit().toString());
		System.out.print("  ");
		board.getSecondPlayerBPits().stream().forEach(System.out::print);
		System.out.println("");
	}

}
