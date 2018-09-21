package game.com.bol.presenter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import game.com.bol.strategy.BolDotComStrategy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardPresenterTest {

	@MockBean
	private BolDotComStrategy strategyMock;
	
	@Autowired
	private BoardPresenter presenter;
	
	@Test
	public void testFindWinner() {
		Mockito.when(strategyMock.gameIsFinish()).thenReturn(false);
		Assert.assertNull(presenter.findWinner());
	}
	
	@Test
	public void testgameIsFinish() {
		Mockito.when(strategyMock.gameIsFinish()).thenReturn(true);
		Assert.assertTrue(presenter.gameIsFinish());
		
	}
	
	@Test
	public void testgameIsNotFinish() {
		Mockito.when(strategyMock.gameIsFinish()).thenReturn(false);
		Assert.assertFalse(presenter.gameIsFinish());
		
	}
	
}
