package jumper.model;

public class Score {
	
	private final int points;
	private final String playerNick;
	
	public Score (int points, String playerNick) {
		this.points = points;
		this.playerNick = playerNick;		
	}

	public int getPoints() {
		return points;
	}

	public String getNick() {
		return playerNick;
	}

}
