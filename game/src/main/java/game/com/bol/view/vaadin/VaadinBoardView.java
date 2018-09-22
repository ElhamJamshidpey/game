package game.com.bol.view.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import game.com.bol.Game;
import game.com.bol.component.Board;
import game.com.bol.exception.InvalidMoveException;

@SpringUI(path = "game")
public class VaadinBoardView extends UI implements ViewDisplay {

	@Autowired
	private Game game;

	private Panel springViewDisplay;
	private VerticalLayout boardLayout = new VerticalLayout();
	private final VerticalLayout layout = new VerticalLayout();

	@Override
	public void showView(View view) {
		springViewDisplay.setContent((Component) view);

	}

	@Override
	protected void init(VaadinRequest request) {
		setContent(layout);

		if (game.getCurrentPlayer() == null) {
			Link loginLink = new Link("Click here for login", new ExternalResource("/login"));
			layout.addComponent(loginLink);

		} else {
			showBoard();

			TextField src = new TextField("Source");
			TextField des = new TextField("Destination");

			Label winnerName = new Label();

			layout.addComponent(src);
			layout.addComponent(des);

			Button playButton = new Button("PLAY");
			Button reloadGameButton = new Button("RELOAD");

			playButton.addClickListener(new Button.ClickListener() {
				public void buttonClick(ClickEvent event) {
					try {
						game.play(src.getValue(), des.getValue());
						showBoard();
						if (game.gameIsFinish()) {
							winnerName.setCaption("Game Is Finish , Winner " + game.findWinner());
							layout.removeComponent(playButton);
							layout.addComponents(winnerName);
						}
					} catch (InvalidMoveException e) {
						Notification.show("Moving not allowed!", e.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
					}

				}
			});
			HorizontalLayout playButtons = new HorizontalLayout();
			playButtons.addComponent(playButton);
			playButtons.addComponent(reloadGameButton);
			layout.addComponent(playButtons);

			reloadGameButton.addClickListener(new Button.ClickListener() {
				public void buttonClick(ClickEvent event) {
					game.reload();
					getPage().setLocation("login");
				}
			});
		}

	}

	private void showBoard() {
		boardLayout.removeAllComponents();
		Board board = game.getBoard();
		boardLayout.addComponent(new Label("Current Player: " + game.getCurrentPlayer().getName()));
		boardLayout.addComponent(new Label(game.getFirstPlayer().getName() + " pits:"));
		HorizontalLayout firstPlayerRow = new HorizontalLayout();
		firstPlayerRow.addComponent(new Button("L: " + board.getSecondPlayerLargerPit().getStoneNumber()));
		boardLayout.addComponent(firstPlayerRow);
		board.getFirstPlayerAPits()
				.forEach(p -> firstPlayerRow.addComponent(new Button(p.getStoneNumber().toString())));

		boardLayout.addComponent(new Label(game.getSecondPlayer().getName() + " pits:"));
		HorizontalLayout secondPlayerRow = new HorizontalLayout();
		board.getSecondPlayerBPits()
				.forEach(p -> secondPlayerRow.addComponent(new Button(p.getStoneNumber().toString())));
		secondPlayerRow.addComponent(new Button("L: " + board.getSecondPlayerLargerPit().getStoneNumber()));
		boardLayout.addComponent(secondPlayerRow);
		layout.replaceComponent(boardLayout, boardLayout);
	}

}
