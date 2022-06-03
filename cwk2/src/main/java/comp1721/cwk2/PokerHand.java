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

}
