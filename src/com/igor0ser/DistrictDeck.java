package com.igor0ser;

import java.util.ArrayList;
import java.util.Collections;

public class DistrictDeck {
	//вынес создание колоды кварталов в отдельныйй класс

	public static ArrayList<District> districtDeck() {

		District[] typesOfCards = new District[28]; //создаем типы карт
		// gold
		typesOfCards[0] = new District("Domain", Color.GOLD, (byte) 3, (byte) 5);
		typesOfCards[1] = new District("Castle", Color.GOLD, (byte) 4, (byte) 5);
		typesOfCards[2] = new District("Palace", Color.GOLD, (byte) 5, (byte) 2);
		// blue
		typesOfCards[3] = new District("Temple", Color.BLUE, (byte) 1, (byte) 3);
		typesOfCards[4] = new District("Church", Color.BLUE, (byte) 2, (byte) 3);
		typesOfCards[5] = new District("Monastery", Color.BLUE, (byte) 3,
				(byte) 4);
		typesOfCards[6] = new District("Cathedral", Color.BLUE, (byte) 5,
				(byte) 2);
		// green
		typesOfCards[7] = new District("Tavern", Color.GREEN, (byte) 1,
				(byte) 5);
		typesOfCards[8] = new District("Store", Color.GREEN, (byte) 2, (byte) 4);
		typesOfCards[9] = new District("Market", Color.GREEN, (byte) 2,
				(byte) 4);
		typesOfCards[10] = new District("Warehouse", Color.GREEN, (byte) 3,
				(byte) 3);
		typesOfCards[11] = new District("Harbour", Color.GREEN, (byte) 4,
				(byte) 3);
		typesOfCards[12] = new District("Town Hall", Color.GREEN, (byte) 5,
				(byte) 2);
		// red
		typesOfCards[13] = new District("Watchtower", Color.RED, (byte) 1,
				(byte) 3);
		typesOfCards[14] = new District("Prison", Color.RED, (byte) 2, (byte) 3);
		typesOfCards[15] = new District("Barracks", Color.RED, (byte) 3,
				(byte) 3);
		typesOfCards[16] = new District("Fortress", Color.RED, (byte) 5,
				(byte) 3);
		// purple
		typesOfCards[17] = new District("Haunted City", Color.PURPLE, (byte) 5,
				(byte) 1);
		typesOfCards[18] = new District("Keep", Color.PURPLE, (byte) 3,
				(byte) 1);
		typesOfCards[19] = new District("Labaratory", Color.PURPLE, (byte) 5,
				(byte) 1);
		typesOfCards[20] = new District("Smithy", Color.PURPLE, (byte) 5,
				(byte) 1);
		typesOfCards[21] = new District("Graveyard", Color.PURPLE, (byte) 5,
				(byte) 1);
		typesOfCards[22] = new District("Observatory", Color.PURPLE, (byte) 5,
				(byte) 1);
		typesOfCards[23] = new District("School of Magic", Color.PURPLE,
				(byte) 6, (byte) 1);
		typesOfCards[24] = new District("Library", Color.PURPLE, (byte) 6,
				(byte) 1);
		typesOfCards[25] = new District("Great Wall", Color.PURPLE, (byte) 6,
				(byte) 1);
		typesOfCards[26] = new District("Univercity", Color.PURPLE, (byte) 6,
				(byte) 1);
		typesOfCards[27] = new District("Dragon Gate", Color.PURPLE, (byte) 6,
				(byte) 1);

		ArrayList<District> deck = new ArrayList<District>(); // создаем колоду по количесиву типов в колоде
		for (District card : typesOfCards) {
			for (byte i = 0; i < card.getmQuantityInDeck(); i++) {
				deck.add(card);
			}
		}

		Collections.shuffle(deck);
		return deck;
	}

}
