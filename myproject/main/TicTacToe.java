package myproject.main;

import java.util.Scanner;

/**
 * Todo: Need to work on some corner edge case
 * 
 *
 */
public class TicTacToe {
	public static Players currentPlayer;
	public static final char CROSS = 'X', CIRCLE = 'O';
	private static char box[] = new char[9];
	static Scanner sc = new Scanner(System.in);
	static {
		System.out.println("Welcome to tic tac toe game");
	}

	public static void main(String[] args) {

		// Create a while loop to handle the user creation
		int p = 1;
		Players player1 = null, player2 = null;
		while (p <= Players.PLAYER_COUNT) {
			System.out.println("Enter the name of player " + p);
			String name = sc.next();
			if (p == 1) {
				player1 = Players.getInstance(name);
				player1.setRole("Player 1");
			} else {
				player2 = Players.getInstance(name);
				player2.setRole("Player 2");
			}
			System.out.println("Player " + p + " created");

			p++;
		}
		char toss;
		System.out.println("Let's check whose going to get the opportunity to choose the toss");
		if (userTossChecking()) {
			System.out.println("Player 1 choose the toss (H/T)");
			toss = sc.next().toUpperCase().charAt(0);
			player1.setToss(toss);
			player2.setToss(getOpposit(toss));
		} else {
			System.out.println("Player 2 choose the toss (H/T)");

			toss = sc.next().toUpperCase().charAt(0);
			player2.setToss(toss);
			player1.setToss(getOpposit(toss));
		}

		if (player1.getToss() == spinTheToss())
			currentPlayer = player1;
		else
			currentPlayer = player2;

		System.out.println(currentPlayer.getName() + " won the toss");

		int i = 1, c = 0;
		System.out.println(player1.getName() + " - [X] " + player2.getName() + " - [O]");
		while (i <= box.length) {
			System.out.println(currentPlayer.getName() + " choose the number in (1 to 9)");

			int n = sc.nextInt();
			if (!(n <= 9 && n >= 1)) // use regex
				n = vaildNumberCheck(n);

			setStatus(n, currentPlayer.getRole());
			currentPlayer.setMoves((i % 2 == 0) ? i / 2 : i - c++);
			printStatus();
			if (i > 3 && winningPossibility()) {
				currentPlayer.setStatus(Players.WINNER);
				System.out.println(currentPlayer.getName() + " has won the game");
				currentPlayer = (currentPlayer == player1) ? player2 : player1;
				currentPlayer.setStatus(Players.LOSER);
				break;
			}
			currentPlayer = (currentPlayer == player1) ? player2 : player1;
			i++;
		}
		if (!(player1.getStatus() == Players.WINNER || player2.getStatus() == Players.WINNER)) {
			player1.setStatus(Players.DRAW);
			player2.setStatus(Players.DRAW);
		}
		System.out.println(player1);
		System.out.println(player2);

	}

	private static boolean userTossChecking() {
		return Math.random() > 0.5 ? false : true;

	}

	private static char spinTheToss() {
		return Math.random() > 0.5 ? CROSS : CIRCLE;

	}

	private static int vaildNumberCheck(int n) {
		while (true) {
			System.out.println("Enter the valid number in range[1-9]");
			n = sc.nextInt();
			if (n >= 1 && n <= 9)
				return n;
		}

	}

	private static boolean winningPossibility() {
		if (horizontal1() || horizontal2() || horizontal3() || vertical1() || vertical2() || vertical3() || diagonal1()
				|| diagonal2()) {
			return true;
		}
		return false;
	}

	private static boolean diagonal1() {

		return ((box[0] == CROSS && box[4] == CROSS && box[8] == CROSS)
				|| (box[0] == CIRCLE && box[4] == CIRCLE && box[8] == CIRCLE)) ? true : false;
	}

	private static boolean diagonal2() {
		return ((box[2] == CROSS && box[4] == CROSS && box[6] == CROSS)
				|| (box[2] == CIRCLE && box[4] == CIRCLE && box[6] == CIRCLE)) ? true : false;

	}

	private static boolean vertical3() {
		return ((box[2] == CROSS && box[5] == CROSS && box[8] == CROSS)
				|| (box[2] == CIRCLE && box[5] == CIRCLE && box[8] == CIRCLE)) ? true : false;
	}

	private static boolean vertical2() {

		return ((box[1] == CROSS && box[4] == CROSS && box[7] == CROSS)
				|| (box[1] == CIRCLE && box[4] == CIRCLE && box[7] == CIRCLE)) ? true : false;
	}

	private static boolean vertical1() {

		return ((box[0] == CROSS && box[3] == CROSS && box[6] == CROSS)
				|| (box[0] == CIRCLE && box[3] == CIRCLE && box[6] == CIRCLE)) ? true : false;
	}

	private static boolean horizontal3() {

		return ((box[6] == CROSS && box[7] == CROSS && box[8] == CROSS)
				|| (box[6] == CIRCLE && box[7] == CIRCLE && box[8] == CIRCLE)) ? true : false;
	}

	private static boolean horizontal2() {

		return ((box[3] == CROSS && box[4] == CROSS && box[5] == CROSS)
				|| (box[3] == CIRCLE && box[4] == CIRCLE && box[5] == CIRCLE)) ? true : false;
	}

	private static boolean horizontal1() {

		return ((box[0] == CROSS && box[1] == CROSS && box[2] == CROSS)
				|| (box[0] == CIRCLE && box[1] == CIRCLE && box[2] == CIRCLE)) ? true : false;
	}

	private static void setStatus(int n, String role) {

		while (checkStatus(n)) {
			System.out.println("The given number is taken please choose the correct number");
			printStatus();
			n = sc.nextInt();
		}

		if (role == "Player 1")
			box[n - 1] = CROSS;
		else
			box[n - 1] = CIRCLE;
	}

	private static boolean checkStatus(int n) {
		return box[n - 1] == CROSS || box[n - 1] == CIRCLE;
	}

	private static void printStatus() {
		System.out.println(box[0] + "|" + box[1] + "|" + box[2]);
		System.out.println("-+-+-");
		System.out.println(box[3] + "|" + box[4] + "|" + box[5]);
		System.out.println("-+-+-");
		System.out.println(box[6] + "|" + box[7] + "|" + box[8]);
	}

	private static char getOpposit(char toss) {
		return (toss == 'H') ? 'T' : 'H';
	}
}
