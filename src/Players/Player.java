package Players;

import Game.Card;

public interface Player {
    Card playCard();

    void addCard();

    void addCards(Card[] newCards);

    int cardsLeft();
}
