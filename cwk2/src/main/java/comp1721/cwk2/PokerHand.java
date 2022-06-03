package comp1721.cwk2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Class representing the poker hand.
 *
 * <p> The class is used to represent a poker hand</p>
 */

public class PokerHand extends CardCollection {


    /**
     * Define a hashmap
     */
    private final Map<Character, Integer> map = new HashMap<>();

    /**
     * Creates a new poker hand.
     */
    public PokerHand() {
        super();
    }

    /**
     * Creates a new poker hand from a list of cards.
     *
     * @param cards List of cards.
     * @throws CardException if the list of cards is invalid.
     */
    public PokerHand(String cards) {
        super();
        String[] cardArray = cards.split(" ");
        for (String card : cardArray) {
            this.add(new Card(card));
        }
    }

    /**
     * Override the toString method to return a string represent the cards in hand.
     *
     * @return A string represent the cards in hand.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.toString()).append(" ");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * Add a card to the hand.
     *
     * @param card Card to be added
     * @throws CardException if the card is repeated or there are already five cards
     */
    public void add(Card card) throws CardException {
        if (cards.size() < 5 && !cards.contains(card)) {
            cards.add(card);
        } else if (cards.contains(card) || cards.size() == 5) {
            throw new CardException("Duplicate");
        }
    }

    /**
     * Get the size of the poker hand.
     *
     * @return the size of the poker hand.
     */
    public int size() {
        return cards.size();
    }

    /**
     * Discard the poker hand.
     *
     * @throws CardException if the hand is empty.
     */
    public void discard() {
        if (cards.size() > 0) {
            cards.clear();
        } else {
            throw new CardException("cannotDiscardEmptyHand");
        }
    }

    /**
     * Discard a card to the hand.
     *
     * @param deck Used to discard the card.
     * @throws CardException the hand is empty.
     */
    public void discardTo(Deck deck) throws CardException {
        if (cards.size() > 0) {
            for (Card card : cards) {
                deck.add(card);
            }
            cards.clear();
        } else {
            throw new CardException("cannotDiscardEmptyHand");
        }
    }

}
