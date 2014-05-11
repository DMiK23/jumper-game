package jumper.model;

public enum BonusTypeEnumerator {
	BETTER_JUMP,
	ONE_UP,
	ADD_TIME,
	ADD_POINTS;	
	
	public static BonusTypeEnumerator create (int i) {
		switch (i) {
		case 1: return BETTER_JUMP;
		case 2: return ONE_UP;
		case 3: return ADD_TIME;
		case 4: return ADD_POINTS;
		default: throw new IllegalArgumentException("Taki bonus nie istnieje.");
		}
	}
}
