package myproject.main;

import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to tic tac toe game");
		System.out.println("Enter the name of player 1");
		//int i = 0;
		// while(Players.PLAYER_COUNT>i) {
		String name = sc.next();
		Players player1 = Players.getInstance(name);
		player1.setRole("Player 1");
		System.out.println("Player 1 created");
		System.out.println("Enter the name of player 2");
		name = sc.next();
		Players player2 = Players.getInstance(name);
		player2.setRole("Player 2");
		System.out.println("Player 2 created");

		/*
		 * i++; }
		 */

		System.out.println("Player 1 choose the toss (H/T)");
		char toss = sc.next().toUpperCase().charAt(0);
		if (toss == 'H' || toss == 'T') {
			player1.setToss(toss);
			player2.setToss(getOpposit(toss));
		} 
		System.out.println(player1);
		System.out.println(player2);
	}

	private static char getOpposit(char toss) {
		return (toss == 'H') ? 'T' : 'H';
	}
}
