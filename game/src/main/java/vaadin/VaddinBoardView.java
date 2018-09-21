package vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
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
public class VaddinBoardView extends UI implements ViewDisplay{

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

        layout.addComponent(src);
        layout.addComponent(des);

        Button playButton = new Button("PLAY");
        
        playButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
        		try {
					presenter.play(src.getValue(),des.getValue());
				} catch (MovingException e) {
					Notification.show("Moving not alowd!",
			                  e.getMessage(),
			                  Notification.Type.HUMANIZED_MESSAGE);
				}
        		showBoard(presenter.loadBoard());
            	playButton.setCaption("board reloaded");
            }
        });
        layout.addComponent(playButton);
        
        

	}

	private void showBoard(Board loadBoard) {
		//TODO!
	}

    
}
