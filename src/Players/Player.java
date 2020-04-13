package Players;

import Game.Card;
import Game.Color;

public interface Player {
    Card playCard();

    void addCard(Card newCard);

    void addCards(Card[] newCards);

    int cardsLeft();

    boolean canPlayCard(Card topCard);

    Color chooseColor();
}
