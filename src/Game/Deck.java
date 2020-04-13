package Game;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards;

    public Deck() {
        cards = new Stack<>();
        for (int i = 0; i < 10; i++) {
            cards.add(new Card(Color.RED, String.valueOf(i)));
            cards.add(new Card(Color.GREEN, String.valueOf(i)));
            cards.add(new Card(Color.BLUE, String.valueOf(i)));
            cards.add(new Card(Color.YELLOW, String.valueOf(i)));
        }
        for (int i = 1; i < 10; i++) {
            cards.add(new Card(Color.RED, String.valueOf(i)));
            cards.add(new Card(Color.GREEN, String.valueOf(i)));
            cards.add(new Card(Color.BLUE, String.valueOf(i)));
            cards.add(new Card(Color.YELLOW, String.valueOf(i)));
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new Card(Color.BLUE, "+2"));
            cards.add(new Card(Color.YELLOW, "+2"));
            cards.add(new Card(Color.RED, "+2"));
            cards.add(new Card(Color.GREEN, "+2"));

            cards.add(new Card(Color.BLUE, "skip"));
            cards.add(new Card(Color.YELLOW, "skip"));
            cards.add(new Card(Color.RED, "skip"));
            cards.add(new Card(Color.GREEN, "skip"));

            cards.add(new Card(Color.BLUE, "swap"));
            cards.add(new Card(Color.YELLOW, "swap"));
            cards.add(new Card(Color.RED, "swap"));
            cards.add(new Card(Color.GREEN, "swap"));
        }
        for (int i = 0; i < 4; i++) {
            cards.add(new Card(Color.BLACK, "choose color"));
            cards.add(new Card(Color.BLACK, "+4"));
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.pop();
    }

    public Card[] draw(int n) {
        Card[] drawnCards = new Card[n];
        for (int i = 0; i < n; i++) {
            drawnCards[i] = cards.pop();
        }
        return drawnCards;
    }

    public void add(List<Card> newCards) {
        cards.addAll(newCards);
    }
}
