package Players;

import Game.Card;
import Game.Color;

import java.io.IOException;

public interface Player {

    String getName();

    Card playCard(Card topCard) throws IOException;

    void addCard(Card newCard);

    void addCards(Card[] newCards);

    int cardsLeft();

    boolean canPlayCard(Card topCard);

    Color chooseColor() throws IOException;
}
