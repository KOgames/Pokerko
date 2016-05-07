
package pet.ui.eq;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

/**
 * A panel that shoes the given list of card buttons in the west of a border
 * layout
 */
class CardPanel extends JPanel {
	
	private final CardButton[] cardButtons;
	private final int minCards;
	private final int maxCards;
	
	/**
	 * create a card panel with max cards cards
	 */
	public CardPanel(String name, int minCards, int maxCards) {
		super(new GridBagLayout());
		this.minCards = minCards;
		this.maxCards = maxCards;
		
		setBorder(BorderFactory.createTitledBorder(name));
		cardButtons = new CardButton[maxCards];
		
		JPanel p = new JPanel(new GridLayout(1, maxCards, 5, 5));
		for (int n = 0; n < cardButtons.length; n++) {
			CardButton cl = new CardButton();
			cardButtons[n] = cl;
			p.add(cl);
		}
		
		GridBagConstraints g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = 0;
		add(p, g);
	}
	
	/** add the given details panel either to the right of or below the cards */
	protected void addDetails (JComponent c, boolean below) {
		GridBagConstraints g = new GridBagConstraints();
		g.gridx = below ? 0 : 1;
		g.gridy = below ? 1 : 0;
		g.weightx = 1;
		g.fill = GridBagConstraints.BOTH;
		add(c, g);
	}
	
	/**
	 * Get the card buttons.
	 */
	public List<CardButton> getCardButtons () {
		return Collections.unmodifiableList(Arrays.asList(cardButtons));
	}
	
	/**
	 * set all cards to blank
	 */
	public void clearCards () {
		for (CardButton b : cardButtons) {
			b.setCard(null);
		}
	}
	
	public void setCard (String c, int n) {
		cardButtons[n].setCard(c);
	}
	
	/**
	 * calls set card for each card button with the given cards or null
	 */
	public void setCards (List<String> cards) {
		if (cards.size() > cardButtons.length) {
			throw new RuntimeException();
		}
		for (int n = 0; n < cardButtons.length; n++) {
			cardButtons[n].setCard(n < cards.size() ? cards.get(n) : null);
		}
	}
	
	public int getMinCards () {
		return minCards;
	}
	
	public int getMaxCards () {
		return maxCards;
	}
	
	/**
	 * Get the cards displayed
	 */
	public List<String> getCards () {
		List<String> cards = new ArrayList<>();
		for (CardButton b : cardButtons) {
			if (b.getCard() != null) {
				cards.add(b.getCard());
			}
		}
		return cards;
	}
	
	public void setCardsHidden (boolean hide) {
		for (CardButton cl : cardButtons) {
			cl.setCardHidden(hide);
		}
	}
	
}
