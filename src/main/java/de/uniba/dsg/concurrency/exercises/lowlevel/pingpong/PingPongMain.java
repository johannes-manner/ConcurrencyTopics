package de.uniba.dsg.concurrency.exercises.lowlevel.pingpong;

public class PingPongMain {

	public static void main(String[] args) {
		PingPongState state = new PingPongState(100, 1);
		Thread ping = new PingPongPlayer(state, 1, 2);
		Thread pong = new PingPongPlayer(state, 2, 1);

		// Overall 30 minutes time for the implementation

		// Task 1
		// Currently the players make active/spin waiting
		// Implement a solution with wait/notify based on guarded wait

		// Task 2 - Extension to more players
		// Alter player pong:
		// Thread pong = new PingPongPlayer(state, 2, 3);
		// Thread pang = new PingPongPlayer(state, 3, 1);
		// Where are the differences in your implementation?

		ping.start();
		pong.start();
		// pang.start();
	}

}
