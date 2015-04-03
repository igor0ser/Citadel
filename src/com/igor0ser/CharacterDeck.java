package com.igor0ser;

import java.util.ArrayList;
import com.igor0ser.Character.Name;

public class CharacterDeck {
	//вынес создание колоды персонажей в отдельный класс
	public static ArrayList<Character> characterDeck() {
		ArrayList<Character> characterDeck = new ArrayList<Character>();
		String assasinAbility = "Choose a character you wish to assassinate. The player who has the assassinated character says nothing when assassinated or when called to take his turn. The assassinated character misses his entire turn.";
		String thiefAbility = "Choose a character from whom you wish to steal. When the player who has this character is called upon and shows his character card, you take all of his gold. You may not steal from the Assassin or the character that the Assassin kills.";
		String magicianAbility = "At any time during your turn, you may do one of the following two things: - Exchange your entire hand of cards with the hand of another player. - Discard any number of cards from your hand to the bottom of the District Deck, then draw an equal number of cards from the top of the District Deck";
		String kingAbility = "You receive one gold for each noble (yellow) district in your city. When the King is called, you immediately receive the Crown. You will now call for characters, and will be the first player to choose your character during the next round. If no King is chosen during the next round, you will keep the Crown.";
		String bishopAbility = "You receive one gold for each religious (blue) district in your city. Your districts may not be destroyed by the Warlord.";
		String merchantAbility = "You receive one gold for each trade (green) district in your city. After you take an action, you receive one extra gold. Therefore, you can either receive three gold, or draw a card and receive one gold.";
		String architectAbility = "After you take an action, you draw two extra district cards and put both in your hand. You may build up to three districts during your turn.";
		String warlordAbility = "You receive one gold for each military (red) district in your city. At the end of your turn, you may destroy one district of your choice by paying a number of gold equal to one less than the cost of the district";
		characterDeck.add(new Character((byte) 1, Name.ASSASIN,
				Color.COLORLESS, assasinAbility));
		characterDeck.add(new Character((byte) 2, Name.THIEF, Color.COLORLESS,
				thiefAbility));
		characterDeck.add(new Character((byte) 3, Name.MAGICIAN,
				Color.COLORLESS, magicianAbility));
		characterDeck.add(new Character((byte) 4, Name.KING, Color.GOLD,
				kingAbility));
		characterDeck.add(new Character((byte) 5, Name.BISHOP, Color.BLUE,
				bishopAbility));
		characterDeck.add(new Character((byte) 6, Name.MERCHANT, Color.GREEN,
				merchantAbility));
		characterDeck.add(new Character((byte) 7, Name.ARCHITECT,
				Color.COLORLESS, architectAbility));
		characterDeck.add(new Character((byte) 8, Name.WARLORD, Color.RED,
				warlordAbility));
		
		return characterDeck;
	}

}
