package game.com.bol.component;

public class Pit {

	private String id;

	private Integer stoneNumber;

	public Pit(String id, Integer stoneNumber) {
		super();
		this.id = id;
		this.stoneNumber = stoneNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getStoneNumber() {
		return stoneNumber;
	}

	public void setStoneNumber(Integer stoneNumber) {
		this.stoneNumber = stoneNumber;
	}

	public void addStone(Integer stones) {
		this.stoneNumber += stones;
	}

	@Override
	public String toString() {
		return stoneNumber + " ";
	}

}
