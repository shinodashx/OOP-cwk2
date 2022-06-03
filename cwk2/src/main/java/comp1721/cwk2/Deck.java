package comp1721.cwk2;

import java.util.Collections;

/**
 * Class representing the deck of cards
 *
 * <p>The class is used to represent a deck of cards</p>
 */

public class Deck extends CardCollection {

    /**
     * Creates a new deck of cards
     */
    public Deck() {
        super();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                this.add(new Card(rank, suit));
            }
        }
    }

    /**
     * Return  the number of cards in the deck
     *
     * @return t the number of cards in the deck
     */
    public int size() {
        return cards.size();
    }

    /**
     * Check if the deck is empty or not
     *
     * @return true if the deck is empty of cards, false otherwise
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Check if the  specified card contains in the deck.
     *
     * @param card Card is looked for
     * @return true if the deck contains the specified card, false otherwise.
     */
    public boolean contains(Card card) {
        return cards.contains(card);
    }

    /**
     * Discard the deck.
     */
    public void discard() {
        cards.clear();
    }

    /**
     * Deal the deck, remove first card in the deck
     *
     * @return the card dealt from deck.
     * @throws CardException if the deck is empty
     */
    public Card deal() throws CardException {
        if (cards.size() > 0) {
            Card card = cards.get(0);
            cards.remove(0);
            return card;
        } else {
            throw new CardException("Empty deal");
        }
    }

    /**
     * Shuffle the deck.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

}
