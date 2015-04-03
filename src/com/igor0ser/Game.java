package com.igor0ser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.igor0ser.Character.Name;
import com.igor0ser.Character.Name;

public class Game {
	private String mUserName; // имя игрока-человека
	private List<Player> mPlayerList = new ArrayList<Player>(); // игроки
	private List<Player> mPlayerTemporarylList = new ArrayList<Player>(); // дополнительный list для промежуточного хранения игроков (во время нахождения короля)
	private ArrayDeque<District> mDistrictDeck; // колода кварталов
	private List<Character> characterDeck; // колода персонажей
	private Random random = new Random();

	public Game(String userName) {
		this.mUserName = userName;
		//начало игры
		mDistrictDeck.addAll(DistrictDeck.districtDeck()); // заполняем колоду кварталов
		mPlayerList.add(new PlayerUser(userName)); //добавляем всех в список
		mPlayerList.add(new Player("Eddard Stark"));
		mPlayerList.add(new Player("Robert Baratheon"));
		mPlayerList.add(new Player("Rhaegar Targaryen"));
		mPlayerList.add(new Player("Tywin Lannister"));
		mPlayerList.get(random.nextInt(5)).setKing(true); // король - рэндомно

		for (Player player : mPlayerList) {
			for (int i = 0; i < 4; i++) {
				System.out.println(player.getmName() + " received "
						+ mDistrictDeck.peek());
				player.addCard(mDistrictDeck.poll()); // каждому раздается по 4 карты квартала
			}
		}

	}

	public Character step1() {
		characterDeck = CharacterDeck.characterDeck(); // новая колода персонажей загружается
		characterDeck.remove(random.nextInt(characterDeck.size())); // одного персонажа выбрасывает рубашкой вниз
		return characterDeck.remove(random.nextInt(characterDeck.size())); // одного - рубашкой вверх
	}

	public void step2() { //выбор персонажей

		Iterator<Player> iterator = mPlayerList.iterator(); // король становиться первым по списку
		while (iterator.hasNext()) {
			Player x = iterator.next();
			if (!x.getIsKing()) {
				iterator.remove();
				mPlayerTemporarylList.add(x);
			} else
				break;

		}
		mPlayerList.addAll(mPlayerTemporarylList);
		mPlayerTemporarylList.clear();

		for (Player p : mPlayerList) { //каждый выбирает персонажа
			p.chooseCharacter(characterDeck);
		}
	}

	public void step3() {
		Collections.sort(mPlayerList);
		for (Player player : mPlayerList) {
			switch (player.getmCharacter().getmName()) {
			case ASSASIN:

				break;
			case THIEF:

				break;
			case MAGICIAN:

				break;
			case KING:

				break;
			case BISHOP:

				break;
			case MERCHANT:

				break;
			case ARCHITECT:

				break;
			case WARLORD:

				break;
			}
		}
	}

	public boolean step4() {
		for (Player p : mPlayerList) {
			if (p.getmHand().size() > 8) {
				return false;
			}
		}
		return true;
	}

}
