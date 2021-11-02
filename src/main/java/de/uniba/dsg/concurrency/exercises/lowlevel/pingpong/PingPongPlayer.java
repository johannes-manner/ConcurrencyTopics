package de.uniba.dsg.concurrency.exercises.lowlevel.pingpong;

public class PingPongPlayer extends Thread {

	private final PingPongState state;
	private final int myId;
	private final int nextId;

	public PingPongPlayer(PingPongState state, int myId, int nextId) {
		super();
		this.state = state;
		this.myId = myId;
		this.nextId = nextId;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (state) {
				if (isGameFinished()) {
					return;
				}
				// => game must be in progress

				if (isNotMyTurn()) {
					continue;
				}
				// => game must be in progress and it is my turn

				performMyTurn();
				passToNextPlayer();
			}
		}
	}

	private boolean isGameFinished() {
		return state.remaining <= 0;
	}

	private void passToNextPlayer() {
		state.next = nextId;
	}

	private void performMyTurn() {
		state.remaining--;
		System.out.printf("Player #%d played his turn, remaining turns %d.%n", myId, state.remaining);
	}

	private boolean isNotMyTurn() {
		return myId != state.next;
	}

}
