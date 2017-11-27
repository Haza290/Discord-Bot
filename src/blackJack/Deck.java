package blackJack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	ArrayList<Card> cardsInDeck;
	
	public Deck() {
		populateDeck();
	}
	
	public Card drawCard() {
		
		Random rand = new Random();
		int randInt = rand.nextInt(cardsInDeck.size());
		Card card = cardsInDeck.get(randInt);
		cardsInDeck.remove(randInt);
		return card;
	}
	
	public void populateDeck() {
		
		cardsInDeck = new ArrayList<Card>();
		
		cardsInDeck.add(new Card(1, "Heart", "Ace"));
		cardsInDeck.add(new Card(2, "Heart", "2"));
		cardsInDeck.add(new Card(3, "Heart", "3"));
		cardsInDeck.add(new Card(4, "Heart", "4"));
		cardsInDeck.add(new Card(5, "Heart", "5"));
		cardsInDeck.add(new Card(6, "Heart", "6"));
		cardsInDeck.add(new Card(7, "Heart", "7"));
		cardsInDeck.add(new Card(8, "Heart", "8"));
		cardsInDeck.add(new Card(9, "Heart", "9"));
		cardsInDeck.add(new Card(10, "Heart", "10"));
		cardsInDeck.add(new Card(10, "Heart", "Jack"));
		cardsInDeck.add(new Card(10, "Heart", "Queen"));
		cardsInDeck.add(new Card(10, "Heart", "King"));
		
		cardsInDeck.add(new Card(1, "Diamond", "Ace"));
		cardsInDeck.add(new Card(2, "Diamond", "2"));
		cardsInDeck.add(new Card(3, "Diamond", "3"));
		cardsInDeck.add(new Card(4, "Diamond", "4"));
		cardsInDeck.add(new Card(5, "Diamond", "5"));
		cardsInDeck.add(new Card(6, "Diamond", "6"));
		cardsInDeck.add(new Card(7, "Diamond", "7"));
		cardsInDeck.add(new Card(8, "Diamond", "8"));
		cardsInDeck.add(new Card(9, "Diamond", "9"));
		cardsInDeck.add(new Card(10, "Diamond", "10"));
		cardsInDeck.add(new Card(10, "Diamond", "Jack"));
		cardsInDeck.add(new Card(10, "Diamond", "Queen"));
		cardsInDeck.add(new Card(10, "Diamond", "King"));
		
		cardsInDeck.add(new Card(1, "Spade", "Ace"));
		cardsInDeck.add(new Card(2, "Spade", "2"));
		cardsInDeck.add(new Card(3, "Spade", "3"));
		cardsInDeck.add(new Card(4, "Spade", "4"));
		cardsInDeck.add(new Card(5, "Spade", "5"));
		cardsInDeck.add(new Card(6, "Spade", "6"));
		cardsInDeck.add(new Card(7, "Spade", "7"));
		cardsInDeck.add(new Card(8, "Spade", "8"));
		cardsInDeck.add(new Card(9, "Spade", "9"));
		cardsInDeck.add(new Card(10, "Spade", "10"));
		cardsInDeck.add(new Card(10, "Spade", "Jack"));
		cardsInDeck.add(new Card(10, "Spade", "Queen"));
		cardsInDeck.add(new Card(10, "Spade", "King"));
		
		cardsInDeck.add(new Card(1, "Club", "Ace"));
		cardsInDeck.add(new Card(2, "Club", "2"));
		cardsInDeck.add(new Card(3, "Club", "3"));
		cardsInDeck.add(new Card(4, "Club", "4"));
		cardsInDeck.add(new Card(5, "Club", "5"));
		cardsInDeck.add(new Card(6, "Club", "6"));
		cardsInDeck.add(new Card(7, "Club", "7"));
		cardsInDeck.add(new Card(8, "Club", "8"));
		cardsInDeck.add(new Card(9, "Club", "9"));
		cardsInDeck.add(new Card(10, "Club", "10"));
		cardsInDeck.add(new Card(10, "Club", "Jack"));
		cardsInDeck.add(new Card(10, "Club", "Queen"));
		cardsInDeck.add(new Card(10, "Club", "King"));
	}

}
