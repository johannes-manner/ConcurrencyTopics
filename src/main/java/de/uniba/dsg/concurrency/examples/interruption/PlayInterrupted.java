package de.uniba.dsg.concurrency.examples.interruption;


import java.util.ArrayList;
import java.util.List;

import static de.uniba.dsg.concurrency.examples.interruption.PlayInterrupted.noOfPlayers;

public class PlayInterrupted {

    public static final int noOfPlayers = 4;

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        game.play();
    }
}

/**
 * Makes literally no sense that a ball knows its players but for now the
 * reference is necessary here to print some stuff :)
 * Otherwise we can't print the thread states
 */
class Ball {
    public int index = 0;
    public final List<Player> participatingPlayers;

    public Ball(List<Player> participatingPlayers) {
        this.participatingPlayers = participatingPlayers;
    }

    public void next() {
        // round robin
        index = (index + 1) % participatingPlayers.size();
    }
}

class Game {
    private final Ball ball;

    private List<Player> playerList = new ArrayList();

    public Game() {
        // instantiate players
        ball = new Ball(playerList);
        for (int i = 0; i < noOfPlayers; i++) {
            this.playerList.add(new Player(ball, "" + (char) ('A' + i)));
        }

    }

    public void play() throws InterruptedException {
        for (Player p : playerList) {
            p.start();
        }

        // interrupt the game
        Thread.sleep(noOfPlayers * 1_000);
        for (Player p : playerList) {
            p.interrupt();
        }

        for (Player p : playerList) {
            p.join();
        }
    }
}

class Player extends Thread {
    private final Ball ball;
    private int noOfTurns = 2;    // exits when turns == 0

    public Player(Ball ball, String name) {
        super(name);
        this.ball = ball;
    }

    public void run() {
        try {
            this.play();
        } catch (InterruptedException e) {
            System.out.println(this.getName() + " was interrupted and exits the game!");
        }
    }

    /**
     * So the play method has both interruption mechanisms implemented.
     * Propagation and Preservation of the interruption status.
     * <p>
     * Since the developer knows that after wait another blocking call (Thread#sleep)
     * in makeMyTurn will happen, it preserves the status when the thread is interrupted
     * while waiting and exists the while loop.
     * <p>
     * Since makeMyTurn is implemented that is propagates the InterruptedException back
     * to the caller, the play() method here throws the exception up the stack to
     * the run() method.
     *
     * @throws InterruptedException
     */
    public void play() throws InterruptedException {
        synchronized (ball) {
            // business logic condition here
            while (this.noOfTurns != 0) {
                // guarded wait condition
                while (!this.itsMyTurn()) {
                    try {
                        ball.wait();
                    } catch (InterruptedException e) {
                        // preserve status (makeMyTurn) also has a blocking method
                        System.out.println(this.getName() + " interrupted while waiting for the ball");
                        Thread.currentThread().interrupt();
                        // leave loop
                        break;
                    }
                }
                // critical section
                // in case of interruption, throws the InterruptedException up the stack (to run method)
                this.makeMyTurn();
                // as in our running example, if you only notify a single player,
                // the program hangs
                ball.notifyAll();
            }
        }
    }

    private void makeMyTurn() throws InterruptedException {
        // When thread interruption status is true, sleep immediately throws InterruptedException.
        Thread.sleep(1000);
        this.noOfTurns--;
        ball.next();
    }

    private boolean itsMyTurn() {
        printCurrentThreadStatus();
        return this.ball.participatingPlayers.get(this.ball.index).equals(this);
    }

    private void printCurrentThreadStatus() {
        System.out.println("New player update");
        System.out.println(Thread.currentThread().getName() + " - " + this.ball.participatingPlayers.get(this.ball.index).equals(this));
        System.out.println(" - - - - - - - - - ");
        for (Player p : ball.participatingPlayers) {
            System.out.println("Player: " + p.getName() + " - Current State: " + p.getState().name());
        }
        System.out.println();
    }
}

