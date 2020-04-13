package Game;

import Players.Player;

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
    }

    public void nextTurn() {
        incrementCurrentPlayerIndex();
        Player currentPlayer = players.get(currentPlayerIndex);
        if (currentPlayer.canPlayCard(getTopCard())) {
            playCard(currentPlayer.playCard());
            if (currentPlayer.cardsLeft() == 0) {
                winner = currentPlayer;
            }
        } else {
            Card newCard = draw();
            if (newCard.matches(getTopCard())) {
                playCard(newCard);
            } else {
                currentPlayer.addCard(newCard);
            }
        }
    }

    private void playCard(Card card) {
        if (!card.matches(getTopCard())) {
            throw new RuntimeException("The played card does not match the top card"); // should never be thrown but just in case
        }
        resolveCardEffects(card);
        cardStack.add(card);
    }

    private int getNextPlayerIndex() {
        if (direction) {
            if (currentPlayerIndex == players.size()) {
                return 0;
            }
            return currentPlayerIndex + 1;
        } else {
            if (currentPlayerIndex == 0) {
                return players.size();
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

    private void resolveCardEffects(Card card) {
        switch (card.getValue()) {
            case "+2":
                getNextPlayer().addCards(draw(2));
                break;
            case "+4":
                getNextPlayer().addCards(draw(4));
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
}
