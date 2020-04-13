package Game;

import Players.HumanPlayer;
import Players.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game {
    private List<Player> players;
    private Deck deck = new Deck();
    private int currentPlayerIndex = 0;
    private boolean direction = true;
    private Stack<Card> cardStack = new Stack<>();
    private Player winner;

    public Game(List<Player> players) {
        this.players = players;
        cardStack.add(draw());
        for (Player player : players) {
            player.addCards(draw(7));
        }
    }

    public void nextTurn() throws IOException { // ioexception for human player reading input from standard input
        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println("The current player is " + currentPlayer.getName());
        if (currentPlayer.canPlayCard(getTopCard())) {
            playCard(currentPlayer.playCard(getTopCard()));
            if (currentPlayer.cardsLeft() == 0) {
                winner = currentPlayer;
            }
        } else {
            System.out.println(currentPlayer + " is unable to play a card, drawing a card");
            Card newCard = draw();
            System.out.println("Drew " + newCard);
            if (newCard.matches(getTopCard())) {
                System.out.println("Playing " + newCard);
                playCard(newCard);
            } else {
                System.out.println("New card is also unplayable, skipping turn");
                currentPlayer.addCard(newCard);
            }
        }
        incrementCurrentPlayerIndex();
    }

    private void playCard(Card card) throws IOException {
        if (!card.matches(getTopCard())) {
            throw new RuntimeException("The played card does not match the top card"); // should never be thrown but just in case
        }
        resolveCardEffects(card);
        cardStack.add(card);
    }

    private int getNextPlayerIndex() {
        if (direction) {
            if (currentPlayerIndex + 1 == players.size()) {
                return 0;
            }
            return currentPlayerIndex + 1;
        } else {
            if (currentPlayerIndex == 0) {
                return players.size() - 1;
            } else return currentPlayerIndex - 1;
        }
    }

    private void incrementCurrentPlayerIndex() {
        currentPlayerIndex = getNextPlayerIndex();
    }

    private Card draw() {
        if (deck.cardsLeft() == 0) {
            Card topCard = cardStack.pop();
            deck.add(cardStack);
            deck.shuffle();
            cardStack.clear();
            cardStack.add(topCard);
        }
        return deck.draw();
    }

    private Card[] draw(int n) {
        Card[] drawnCards = new Card[n];
        for (int i = 0; i < n; i++) {
            drawnCards[i] = draw();
        }
        return drawnCards;
    }

    private Player getNextPlayer() {
        return players.get(getNextPlayerIndex());
    }

    private Card getTopCard() {
        return cardStack.peek();
    }

    private void resolveCardEffects(Card card) throws IOException {
        switch (card.getValue()) {
            case "+2":
                getNextPlayer().addCards(draw(2));
                incrementCurrentPlayerIndex();
                break;
            case "+4":
                getNextPlayer().addCards(draw(4));
                card.setColor(players.get(currentPlayerIndex).chooseColor());
                incrementCurrentPlayerIndex();
                break;
            case "swap":
                direction = !direction;
                break;
            case "skip":
                incrementCurrentPlayerIndex();
                break;
            case "choose color":
                card.setColor(players.get(currentPlayerIndex).chooseColor());
                break;
        }
    }

    public Player getWinner() {
        return winner;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new HumanPlayer("player 1"));
        players.add(new HumanPlayer("player 2"));
        Game game = new Game(players);
        while (game.winner == null) {
            game.nextTurn();
        }
        System.out.println(game.winner + " won the game.");
    }
}
