package com.igor0ser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.igor0ser.Character.Name;

public class Game implements Comparable {
	private String userName; // имя игрока-человека
	private List<Player> mPlayers = new ArrayList<Player>(); // игроки
	private List<Player> mPlayers2 = new ArrayList<Player>(); // игроки - для нахождения Короля
	private ArrayDeque<District> mDistrictDeck = new ArrayDeque<District>(); // колода кварталов
	private List<Character> mCharacterDeck = new ArrayList<Character>();; // колода персонажей
	private Random random = new Random();

	public Game(String userName) {
		this.userName = userName;
		random = new Random();
		//mPlayers2 = new ArrayList<Player>(); //запасной лист для игроков
		//mPlayers = new ArrayList<Player>(); // Список игорков, всего 5 пока
		mPlayers.add(new PlayerUser(userName)); //добавляем всех в список
		mPlayers.add(new Player("Eddard Stark"));
		mPlayers.add(new Player("Robert Baratheon"));
		mPlayers.add(new Player("Rhaegar Targaryen"));
		mPlayers.add(new Player("Tywin Lannister"));
		mPlayers.get(random.nextInt(5)).setKing(true);
		mDistrictDeck.addAll(DistrictDeckList()); // заполняем колоду кварталов

		for (Player player : mPlayers) {
			for (int i = 0; i < 4; i++) {
				System.out.println(player.getmName() + " received "
						+ mDistrictDeck.peek());
				player.addCard(mDistrictDeck.poll()); // каждому по 4 карты квартала
			}
		}

		mCharacterDeck = new ArrayList<Character>();

	}

	public Character step1() {//первые персонажи выбрасываются
		fillCharacterDeck();

		mCharacterDeck.remove(random.nextInt(mCharacterDeck.size())); // одного персонажа выбрасывает рубашкой вниз
		return mCharacterDeck.remove(random.nextInt(mCharacterDeck.size())); // одного - рубашкой вверх
	}

	public void step2() { //выбор персонажей

		mPlayers2.clear();
		Iterator<Player> iterator = mPlayers.iterator();
		while (iterator.hasNext()) {
			Player x = iterator.next();
			if (!x.getIsKing()) {
				iterator.remove();
				mPlayers2.add(x);
			} else
				break;

		}
		mPlayers.addAll(mPlayers2);

		for (Player p : mPlayers) {
			p.chooseCharacter(mCharacterDeck);
		}
	}

	public void step3() {
		Collections.sort(mPlayers);
		for (Player p : mPlayers) {
			p.turn();
		}
	}

	public void fillCharacterDeck() {
		String assasinAbility = "Choose a character you wish to assassinate. The player who has the assassinated character says nothing when assassinated or when called to take his turn. The assassinated character misses his entire turn.";
		String thiefAbility = "Choose a character from whom you wish to steal. When the player who has this character is called upon and shows his character card, you take all of his gold. You may not steal from the Assassin or the character that the Assassin kills.";
		String magicianAbility = "At any time during your turn, you may do one of the following two things: - Exchange your entire hand of cards with the hand of another player. - Discard any number of cards from your hand to the bottom of the District Deck, then draw an equal number of cards from the top of the District Deck";
		String kingAbility = "You receive one gold for each noble (yellow) district in your city. When the King is called, you immediately receive the Crown. You will now call for characters, and will be the first player to choose your character during the next round. If no King is chosen during the next round, you will keep the Crown.";
		String bishopAbility = "You receive one gold for each religious (blue) district in your city. Your districts may not be destroyed by the Warlord.";
		String merchantAbility = "You receive one gold for each trade (green) district in your city. After you take an action, you receive one extra gold. Therefore, you can either receive three gold, or draw a card and receive one gold.";
		String architectAbility = "After you take an action, you draw two extra district cards and put both in your hand. You may build up to three districts during your turn.";
		String warlordAbility = "You receive one gold for each military (red) district in your city. At the end of your turn, you may destroy one district of your choice by paying a number of gold equal to one less than the cost of the district";
		mCharacterDeck.add(new Character((byte) 1, Name.ASSASIN,
				Color.COLORLESS, assasinAbility));
		mCharacterDeck.add(new Character((byte) 2, Name.THIEF, Color.COLORLESS,
				thiefAbility));
		mCharacterDeck.add(new Character((byte) 3, Name.MAGICIAN,
				Color.COLORLESS, magicianAbility));
		mCharacterDeck.add(new Character((byte) 4, Name.KING, Color.GOLD,
				kingAbility));
		mCharacterDeck.add(new Character((byte) 5, Name.BISHOP, Color.BLUE,
				bishopAbility));
		mCharacterDeck.add(new Character((byte) 6, Name.MERCHANT, Color.GREEN,
				merchantAbility));
		mCharacterDeck.add(new Character((byte) 7, Name.ARCHITECT,
				Color.COLORLESS, architectAbility));
		mCharacterDeck.add(new Character((byte) 8, Name.WARLORD, Color.RED,
				warlordAbility));
	}

	public ArrayList<District> DistrictDeckList() {
		District[] typesOfCards = new District[28];
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

		ArrayList<District> deck = new ArrayList<District>();

		for (District card : typesOfCards) {
			for (byte i = 0; i < card.getmQuantityInDeck(); i++) {
				deck.add(card);
			}
		}

		Collections.shuffle(deck);

		return deck;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
