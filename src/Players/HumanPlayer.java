package Players;

import Game.Card;
import Game.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HumanPlayer implements Player {
    private String name;
    private List<Card> hand = new ArrayList<>();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String getName() {
        return name;
    }

    public HumanPlayer(String name) {
        this.name = name;
    }

    public List<Card> playableCards(Card topCard) {
        ArrayList<Card> cards = new ArrayList<>();
        for (Card card : hand) {
            if (card.matches(topCard)) {
                cards.add(card);
            }
        }
        return cards;
    }

    @Override
    public Card playCard(Card topCard) throws IOException {
        List<Card> playableCards = playableCards(topCard);
        System.out.println("Your hand is --> " + hand);
        System.out.println("The top card is " + topCard);
        System.out.println("Pick a card to play by typing the index of the card");
        while (true) {
            String line = reader.readLine();
            try {
                int choice = Integer.parseInt(line.strip());
                if (choice >= cardsLeft()) {
                    System.out.println("Index out of range, try again");
                    continue;
                }
                if (playableCards.contains(hand.get(choice))) {
                    return hand.remove(choice);
                } else {
                    System.out.println("That card doesn't match the top card, choose another");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input was not a number, try again");
            }
        }
    }

    @Override
    public void addCard(Card newCard) {
        hand.add(newCard);
    }

    @Override
    public void addCards(Card[] newCards) {
        hand.addAll(Arrays.asList(newCards));
    }

    @Override
    public int cardsLeft() {
        return hand.size();
    }

    @Override
    public boolean canPlayCard(Card topCard) {
        return playableCards(topCard).size() > 0;
    }

    @Override
    public Color chooseColor() throws IOException {
        System.out.println("Your hand is --> " + hand);
        System.out.println("You need to choose a color. Type r for red, b for blue, y for yellow and g for green.");
        while (true) {
            String line = reader.readLine().strip().toLowerCase();
            switch (line) {
                case "r":
                    return Color.RED;
                case "b":
                    return Color.BLUE;
                case "g":
                    return Color.GREEN;
                case "y":
                    return Color.YELLOW;
                default:
                    System.out.println("Color not recognized, try again");
            }
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
