package Players;

import Game.Card;
import Game.Color;

import java.util.List;

public class HumanPlayer implements Player {
    private List<Card> hand;

    @Override
    public Card playCard() {
        return null;
    }

    @Override
    public void addCard(Card newCard) {

    }

    @Override
    public void addCards(Card[] newCards) {

    }

    @Override
    public int cardsLeft() {
        return 0;
    }

    @Override
    public boolean canPlayCard(Card topCard) {
        return false;
    }

    @Override
    public Color chooseColor() {
        return null;
    }
}
