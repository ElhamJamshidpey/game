package game.com.bol.view.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import game.com.bol.component.Board;
import game.com.bol.exception.MovingException;
import game.com.bol.presenter.BoardPresenter;

@SpringUI(path = "game")
@SpringViewDisplay
public class VaadinBoardView extends UI implements ViewDisplay{

	@Autowired
	private BoardPresenter presenter;
	
	private Panel springViewDisplay;
		
	@Override
	public void showView(View view) {
        springViewDisplay.setContent((Component) view);
		
	}

	@Override
	protected void init(VaadinRequest request) {
		
		final VerticalLayout layout = new VerticalLayout();
        setContent(layout);
        
        showBoard(presenter.loadBoard());
        
        TextField src = new TextField("Source");
        TextField des = new TextField("Destination");

        Label winnerName = new Label();
        
        layout.addComponent(src);
        layout.addComponent(des);

        Button playButton = new Button("PLAY");
        
        playButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
        		try {
					presenter.play(src.getValue(),des.getValue());
					showBoard(presenter.loadBoard());
	        		if(presenter.gameIsFinish()) {
	            	   winnerName.setCaption("Game Is Finish , Winner "+ presenter.findWinner());
	            	   layout.removeComponent(playButton);
	            	   layout.addComponent(winnerName);
	        		}
				} catch (MovingException e) {
					Notification.show("Moving not alowd!",
			                  e.getMessage(),
			                  Notification.Type.HUMANIZED_MESSAGE);
				}
        		
            }
        });
        layout.addComponent(playButton);
        
        

	}

	private void showBoard(Board loadBoard) {
		//TODO!
	}

    
}
