package blackJack;

import java.util.ArrayList;

import net.dv8tion.jda.core.entities.User;

public class Player {
	
	int total;
	int numOfCards;
	ArrayList<Card> cards;
	User user;
	Deck deck;
	
	public Player(User user, Deck deck) {
		reset();
		this.user = user;
		this.deck = deck;
	}
	
	public void drawCard() {
		Card drawnCard = deck.drawCard();
		total += drawnCard.value;
		numOfCards ++;
		cards.add(drawnCard);
	}
	
	public void reset() {
		total = 0;
		numOfCards = 0;
		cards = new ArrayList<Card>();
		
	}

}
