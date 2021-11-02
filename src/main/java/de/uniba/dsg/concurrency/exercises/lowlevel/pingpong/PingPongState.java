package de.uniba.dsg.concurrency.exercises.lowlevel.pingpong;

/**
 * Holds the state of the ping-pong game.
 *
 */
public class PingPongState {

	public int remaining;
	public int next;

	/**
	 * @param remaining
	 *            The number of remaining turns, must be positive.
	 * @param next
	 *            The id of the next player.
	 */
	public PingPongState(int remaining, int next) {
		if (remaining < 1) {
			throw new IllegalArgumentException("Remaining turns must be positive!");
		}

		this.remaining = remaining;
		this.next = next;
	}

}
