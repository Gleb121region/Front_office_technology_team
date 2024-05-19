package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static int currentTurn = 0;

    public static void main(String[] args) {

        String[] scene = {
                "Joey: Hey, hey.",
                "Chandler: Hey.",
                "Phoebe: Hey.",
                "Chandler: And this from the cry-for-help department. Are you wearing makeup?",
                "Joey: Yes, I am. As of today, I am officially Joey Tribbiani, actor slash model.",
                "Chandler: That's so funny, 'cause I was thinking you look more like Joey Tribbiani, man slash woman.",
                "Phoebe: What were you modeling for?",
                "Joey: You know those posters for the City Free Clinic?",
                "Monica: Oh, wow, so you're gonna be one of those \"healthy, healthy, healthy guys\"?",
                "Phoebe: You know, the asthma guy was really cute.",
                "Chandler: Do you know which one you're gonna be?",
                "Joey: No, but I hear lyme disease is open, so... (crosses fingers)",
                "Chandler: Good luck, man. I hope you get it.",
                "Joey: Thanks."
        };

        for (int i = 0; i < scene.length; i++) {
            new Thread(new Character(i, scene[i], scene.length)).start();
        }
    }

    static class Character implements Runnable {
        private final int turn;
        private final String line;
        private final int totalTurns;

        Character(int turn, String line, int totalTurns) {
            this.turn = turn;
            this.line = line;
            this.totalTurns = totalTurns;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                while (currentTurn != turn) {
                    condition.await();
                }
                System.out.println(line);
                currentTurn++;
                if (currentTurn < totalTurns) {
                    condition.signalAll();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}
