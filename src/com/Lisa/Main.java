package com.Lisa;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Initialize deck
        Deck newDeck = new Deck();

        // Initialize hands
        Player humanPlayer = new Player();
        Player computerAiPlayer = new Player();

        // Opening deal
        newDeck.dealCards(10, humanPlayer.getHandCards());
        newDeck.dealCards(10, computerAiPlayer.getHandCards());
        newDeck.dealCards(1, newDeck.getDiscardPile());

        outputGameStatus(humanPlayer.getHandCards(), newDeck);
        draw(humanPlayer.getHandCards(), newDeck);
        discard(humanPlayer.getHandCards(), newDeck);

        // While whatever startRound()
        // When whatever calculateScore()
        // Display score
        // Ask if player wants to play again
    }

//    public static void startRound() {
//        // check if discard pile is empty
//        // interactWithHuman()
//        // aiStrategize()
//        // check if any player's hand is empty (they have won)
//    }

    public static void outputGameStatus(LinkedList<Card> hand, Deck newDeck) {

        // Output player cards
        System.out.println("\nYOUR HAND:");
        for (Card card : hand) {
            outputCardToTerminalInColor(card);
            if (card != hand.getLast()) {
                System.out.print(", ");
            }
        }

        // Display top card in discard pile
        System.out.println("\n\nDISCARD PILE:");
        if (newDeck.getDiscardPile().isEmpty()) {
            newDeck.dealCards(1, newDeck.getDiscardPile());
        }
        outputCardToTerminalInColor(newDeck.getDiscardPile().peek());

        // Display all runs and books on table
        System.out.println("\n");
    }

    public static void outputCardToTerminalInColor(Card card) {

        // Set colors for terminal output
        String ANSI_red = "\u001B[31m";
        String ANSI_reset_color = "\u001B[0m";

        if (card.getSuit() > 9828) {
            System.out.print(ANSI_red + card.getName() + ANSI_reset_color);
        } else {
            System.out.print(card.getName());
        }
    }

    public static void draw(LinkedList<Card> hand, Deck newDeck) {
        System.out.println("DRAW:\n");
        int drawFromPile = 0;

        // Get valid response from user
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press 1 to draw from the stock pile, or 2 to draw from the discard pile.");

            try {
                drawFromPile = scanner.nextInt();

            } catch (InputMismatchException ime) {
                // User did not input an integer
                continue;
            }

            if (drawFromPile != 1 && drawFromPile != 2) {
                // User input an invalid integer
                continue;
            }

            break;
        }

        // Draw card from selected pile
        switch (drawFromPile) {
            case 1:
                newDeck.drawFromStockPile(hand);
                break;
            case 2:
                newDeck.drawFromDiscardPile(hand);
                break;
        }
        outputGameStatus(hand, newDeck);
    }

    public static void discard(LinkedList<Card> hand, Deck newDeck) {
        System.out.println("DISCARD:\nWhich card would you like to discard to end your turn?");
        int cardToDiscard = 0;

        // Get valid response from user
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please use your number keypad to enter which card, as counted from the left.");

            try {
                cardToDiscard = scanner.nextInt();
                cardToDiscard --;

            } catch (InputMismatchException ime) {
                // User did not input an integer
                continue;
            }

            if (cardToDiscard < 0 || cardToDiscard > hand.size()) {
                // User input an invalid integer
                continue;
            }

            break;
        }

        // Discard selected card
//        switch (cardToDiscard) {
//            case 1:
//                newDeck.drawFromStockPile(player.getHandGroup());
//                break;
//            case 2:
//                newDeck.drawFromDiscardPile(player.getHandGroup());
//                break;
//        }
        outputGameStatus(hand, newDeck);
    }
}

//    public static void getPlayerAction() {
//        // Display options: draw from deck, draw from discard, meld (create new run or book), lay off (add to existing run or book)
//        // Receive player input
////        System.out.println("");
//    }

//    public static void aiStrategize() {
//        // if whatever, do whatever
//    }
//
//    public static void calculateScore() {
//        // TODO
//    }
//}
