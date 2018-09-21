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
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import game.com.bol.component.Player;
import game.com.bol.exception.LoginException;
import game.com.bol.presenter.BoardPresenter;

@SpringUI(path = "login")
@SpringViewDisplay
public class LoginView extends UI implements ViewDisplay{

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
        
        
        TextField playerName = new TextField("Enter Your Name:");

        layout.addComponent(playerName);

        Button playButton = new Button("Login");
        
        playButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	try {
					presenter.loginPlayer(new Player(playerName.getValue()));
					getPage().setLocation("game");
				} catch (LoginException e) {
					Notification.show("Login Failed!",
			                  e.getMessage(),
			                  Notification.Type.HUMANIZED_MESSAGE);
				}
            }
        });
        layout.addComponent(playButton);

	}

}
