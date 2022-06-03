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

    /**
     * Used to init the map of ranks and occurrence.
     */
    public void initMap() {
        map.clear();
        for (Card card : cards) {
            if (map.containsKey(card.getRank().getSymbol())) {
                map.put(card.getRank().getSymbol(), map.get(card.getRank().getSymbol()) + 1);
            } else {
                map.put(card.getRank().getSymbol(), 1);
            }
        }
    }

    /**
     * Used to check if the hand cards is Pair.
     *
     * @return true if the hand cards is Pair, false otherwise.
     */
    public boolean isPair() {
        initMap();
        int countPairs = 0;
        int countOne = 0;
        for (Character key : map.keySet()) {
            if (map.get(key) == 2) {
                countPairs++;
            } else if (map.get(key) == 1) {
                countOne++;
            }
        }
        return countPairs == 1 && countOne == 3 && cards.size() == 5;
    }

    /**
     * Used to check if the hand cards is TwoPairs.
     *
     * @return true if the hand cards is TwoPairs, false otherwise.
     */
    public boolean isTwoPairs() {
        initMap();
        int pairCount = 0;
        for (Character key : map.keySet()) {
            if (map.get(key) == 2) {
                pairCount++;
            }
        }
        return pairCount == 2 && cards.size() == 5;
    }

    /**
     * Used to check if the hand cards is Three of a Kind.
     *
     * @return true if the hand cards is Three of a Kind, false otherwise.
     */
    public boolean isThreeOfAKind() {
        initMap();
        int countThree = 0;
        int countOne = 0;
        for (Character key : map.keySet()) {
            if (map.get(key) == 3) {
                countThree++;
            } else if (map.get(key) == 1) {
                countOne++;
            }
        }
        return countThree == 1 && countOne == 2 && cards.size() == 5;
    }

    /**
     * Used to check if the hand cards is Four of a Kind.
     *
     * @return true if the hand cards is Four of a Kind, false otherwise.
     */
    public boolean isFourOfAKind() {
        initMap();
        for (Character key : map.keySet()) {
            if (map.get(key) == 4 && cards.size() == 5) {
                return true;
            }
        }
        return false;
    }

    /**
     * Used to check if the hand cards is Full House
     *
     * @return true if the hand cards is Full House, false otherwise.
     */
    public boolean isFullHouse() {
        initMap();
        int pairCount = 0;
        int threeOfAKindCount = 0;
        for (Character key : map.keySet()) {
            if (map.get(key) == 2) {
                pairCount++;
            } else if (map.get(key) == 3) {
                threeOfAKindCount++;
            }
        }
        return pairCount == 1 && threeOfAKindCount == 1 && cards.size() == 5;
    }

    /**
     * Used to check if the hand cards is Flush.
     *
     * @return true if the hand cards is Flush, false otherwise.
     */
    public boolean isFlush() {
        Set<Character> suits = new HashSet<>();
        for (Card card : cards) {
            suits.add(card.getSuit().getSymbol());
        }
        return suits.size() == 1 && cards.size() == 5;
    }

    /**
     * Used to check if the hand cards is Straight.
     *
     * @return true if the hand  cards is Straight, false otherwise.
     */
    public boolean isStraight() {
        if (cards.size() != 5) {
            return false;
        }
        HashMap<Card.Rank, Integer> mp = new HashMap<>();
        List<Integer> ranks = new ArrayList<>();
        mp.put(Card.Rank.ACE, 1);
        mp.put(Card.Rank.TWO, 2);
        mp.put(Card.Rank.THREE, 3);
        mp.put(Card.Rank.FOUR, 4);
        mp.put(Card.Rank.FIVE, 5);
        mp.put(Card.Rank.SIX, 6);
        mp.put(Card.Rank.SEVEN, 7);
        mp.put(Card.Rank.EIGHT, 8);
        mp.put(Card.Rank.NINE, 9);
        mp.put(Card.Rank.TEN, 10);
        mp.put(Card.Rank.JACK, 11);
        mp.put(Card.Rank.QUEEN, 12);
        mp.put(Card.Rank.KING, 13);
        for (Card card : cards) {
            ranks.add(mp.get(card.getRank()));
        }
        Collections.sort(ranks);
        int res = 0;
        for (Card card : cards) {
            if (card.getRank() == Card.Rank.ACE) {
                res += 1;
            }
        }
        if (res == 1) {
            if (ranks.get(0) == 1 && ranks.get(1) == 10 && ranks.get(2) == 11 &&
                    ranks.get(3) == 12 && ranks.get(4) == 13) {
                return true;
            } else {
                return ranks.get(0) == 1 && ranks.get(1) == 2 && ranks.get(2) == 3 &&
                        ranks.get(3) == 4 && ranks.get(4) == 5;
            }
        } else {
            for (int i = 0; i < ranks.size() - 1; i++) {
                if (ranks.get(i) + 1 != ranks.get(i + 1)) {
                    return false;
                }
            }
            return true;
        }
    }
}
