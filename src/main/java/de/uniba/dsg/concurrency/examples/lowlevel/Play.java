package de.uniba.dsg.concurrency.examples.lowlevel;

import java.util.ArrayList;
import java.util.List;


public class Play {

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
        for (int i = 0; i < Play.noOfPlayers; i++) {
            this.playerList.add(new Player(ball, "" + (char) ('A' + i)));
        }

    }

    public void play() throws InterruptedException {
        for (Player p : playerList) {
            p.start();
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
        this.play();
    }

    public void play() {
        synchronized (ball) {
            // business logic condition here
            while (this.noOfTurns != 0) {
                // guarded wait condition
                while (!this.itsMyTurn()) {
                    try {
                        ball.wait();
                    } catch (InterruptedException e) {
                        // we do not care for the moment
                    }
                }
                // critical section
                this.makeMyTurn();
                // as in our running example, if you only notify a single player,
                // the program hangs
                ball.notifyAll();
            }
        }
    }

    private void makeMyTurn() {
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
