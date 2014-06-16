package jumper.model;

public class Score {
	
	private final int scorePoints;
	private final String name;
	
	public Score (int scorePoints, String name) {
		this.scorePoints = scorePoints;
		this.name = name;		
	}

	public int getScorePoints() {
		return scorePoints;
	}

	public String getName() {
		return name;
	}

}
