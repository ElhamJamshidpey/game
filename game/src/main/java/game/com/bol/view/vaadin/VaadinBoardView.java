package game.com.bol.view.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import game.com.bol.Game;
import game.com.bol.component.Board;
import game.com.bol.component.Pit;
import game.com.bol.exception.InvalidMoveException;

@SpringUI(path = "game")
public class VaadinBoardView extends UI implements ViewDisplay{

	@Autowired
	private Game game;
	
	private Panel springViewDisplay;
		
	@Override
	public void showView(View view) {
        springViewDisplay.setContent((Component) view);
		
	}

	@Override
	protected void init(VaadinRequest request) {

		final VerticalLayout layout = new VerticalLayout();


		if(game.getCurrentPlayer()==null) {
//			Link loginLink = new Link("Click here for login",new ExternalResource("http://localhost:8080/login"));
	        TextField ff = new TextField("Source");

//			layout.addComponent(loginLink);
			layout.addComponent(ff);

		}else {	
        setContent(layout);
        
        showBoard(layout);
        
        TextField src = new TextField("Source");
        TextField des = new TextField("Destination");

        Label winnerName = new Label();
        
        layout.addComponent(src);
        layout.addComponent(des);

        Button playButton = new Button("PLAY");
        Button reloadGame = new Button("RELOAD");
        
        playButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
        		try {
					game.play(src.getValue(),des.getValue());
					showBoard(layout);
	        		if(game.gameIsFinish()) {
	            	   winnerName.setCaption("Game Is Finish , Winner "+ game.findWinner());
	            	   layout.removeComponent(playButton);
	            	   layout.addComponents(reloadGame,winnerName);
	        		}
				} catch (InvalidMoveException e) {
					Notification.show("Moving not allowed!",
			                  e.getMessage(),
			                  Notification.Type.HUMANIZED_MESSAGE);
				}
        		
            }
        });
        layout.addComponent(playButton);
        
        reloadGame.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	game.reload();
				getPage().setLocation("login");
            }
        });
		}

	}

	private void showBoard(Layout layout) {
		Board board = game.getBoard();
		layout.addComponent(new Label("Current Player: " + game.getCurrentPlayer().getName()));
		layout.addComponent(new Label(game.getFirstPlayer().getName()+" pits:"));
		HorizontalLayout firstPlayerRow = new HorizontalLayout();
		firstPlayerRow.addComponent(new Button("L: " + board.getSecondPlayerLargerPit().getStoneNumber()));
		layout.addComponent(firstPlayerRow);

		for (int i = 0; i < board.getFirstPlayerAPits().size(); ++i) {
						Pit p = board.getFirstPlayerAPits().get(i);
						Button b = new Button(p.getStoneNumber().toString());
//						b.addClickListener(event -> {
							// TODO: play for first play from this player
			
							// if current player != first player
							// return (no play)
			
							// game.play();
							// input params:
							// 		- index of current pit (source pit) -> i
							// 		- move the stones to the next pits as much as possible
			
							// XXX: this is now an infinite recursion, must fix above
//							showBoard(layout);
//						});
						firstPlayerRow.addComponent(b);
					}
					// TODO repeat the same button click lister logic for larger pit?
			
			
					layout.addComponent(new Label(game.getSecondPlayer().getName()+" pits:"));
					HorizontalLayout secondPlayerRow = new HorizontalLayout();
					// TODO repeat the same logic from above for second player
					board.getSecondPlayerBPits().forEach(p -> secondPlayerRow.addComponent(new Button(p.getStoneNumber().toString())));
					secondPlayerRow.addComponent(new Button("L: " + board.getSecondPlayerLargerPit().getStoneNumber()));
					layout.addComponent(secondPlayerRow);
	}

    
}
