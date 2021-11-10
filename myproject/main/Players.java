package myproject.main;

public class Players {
	public static int PLAYER_COUNT = 2;
	public static String WINNER = "Winner";
	public static String LOSER = "Loser";
	public static String DRAW = "Draw";
	private static Players player;
	private String name;
	private int moves;
	private char toss;
	private String role;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public char getToss() {
		return toss;
	}

	public void setToss(char toss) {
		this.toss = toss;
	}

	public static Players getPlayer() {
		return player;
	}

	public static void setPlayer(Players player) {
		Players.player = player;
	}

	private Players(String name) {
		this.name = name;
		this.moves = 0;
	}

	public static Players getInstance(String name) {

		player = new Players(name);
		return player;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Players [name=" + name + ", moves=" + moves + ", toss=" + toss + ", role=" + role + ", status=" + status
				+ "]";
	}

}
