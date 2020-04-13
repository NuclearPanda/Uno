package Game;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void remove(Card card) {
        cards.remove(card);
    }

    public List<Card> getMatchingCards(Card cardToMatch) {
        List<Card> matchingCards = new ArrayList<>();
        for (Card card : cards) {
            if (card.matches(cardToMatch)) {
                matchingCards.add(card);
            }
        }
        return matchingCards;
    }
}
