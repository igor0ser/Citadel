package com.igor0ser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.igor0ser.Character.Name;

public class Game {
	private static List<Player> mPlayerList = new ArrayList<Player>(); // игроки
	private List<Player> mPlayerTemporarylList = new ArrayList<Player>(); // дополнительный list для промежуточного хранения игроков (во время нахождения короля)
	private static ArrayDeque<District> mDistrictDeck = new ArrayDeque<>(); // колода кварталов
	private List<Character> characterDeck; // колода персонажей
	private Random mRandom = new Random();

	public Game(String userName) {
		//начало игры
		mDistrictDeck.addAll(DistrictDeck.districtDeck()); // заполняем колоду кварталов
		mPlayerList.add(new Player("HARRY")); //добавляем всех в список
		mPlayerList.add(new Player("RYAN"));
		mPlayerList.add(new Player("JAMIE"));
		mPlayerList.add(new Player("STEVE"));
		mPlayerList.add(new Player("BEN"));
		mPlayerList.get(mRandom.nextInt(5)).setmKing(true); // король - рэндомно

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
		Character out;
		do {
			out = characterDeck.get(mRandom.nextInt(characterDeck.size())); // одного персонажа выбрасывает рубашкой вниз
		} while (out.getmName().equals(Name.KING));
		characterDeck.remove(out);
		do {
			out = characterDeck.get(mRandom.nextInt(characterDeck.size())); // одного персонажа выбрасывает рубашкой вниз
		} while (out.getmName().equals(Name.KING));
		characterDeck.remove(out);
		return out; // одного - рубашкой вверх
	}

	public Player step2() { //выбор персонажей
		Player king = null;
		Iterator<Player> iterator = mPlayerList.iterator(); // король становиться первым по списку
		while (iterator.hasNext()) {
			Player player = iterator.next();
			if (!player.ismKing()) {
				iterator.remove();
				mPlayerTemporarylList.add(player);
			} else {
				king = player;
				System.out.println("Первым ходит король - " + king.getmName()
						+ ", остальные после него по порядку!");
				break;
			}

		}
		mPlayerList.addAll(mPlayerTemporarylList);
		mPlayerTemporarylList.clear();

		for (Player p : mPlayerList) { //каждый выбирает персонажа
			p.chooseCharacter(characterDeck);
		}
		return king;
	}

	public void step3() {
		ArrayList<Player> thisTurnList = new ArrayList<>();
		thisTurnList.addAll(mPlayerList);
		Collections.sort(thisTurnList);
		for (Player player : thisTurnList) {
			System.out.println("Ходит " + player);
			int whomToKill = -1;
			switch (player.getmCharacter().getmName()) {
			case ASSASIN:
				whomToKill = player.kill(); // кого-то убивают
				player.turn();
				break;
			case THIEF:
				if (player.ismAlive()) { //проверка на жизнь переключает булиан в случае чего
					player.robb(whomToKill); // вроует у кого-то (кроме того кого уже убили)
					player.turn();
				}
				break;
			case MAGICIAN:
				if (player.ismAlive()) {
					player.checkRobbed();
					player.turn();
					player.cardAbilities();
					player.wizardChangeCards();
				}
				break;
			case KING:
				player.coronation();
				if (player.ismAlive()) {
					player.checkRobbed();
					player.turn();
					player.cardAbilities();
				}
				break;
			case BISHOP:
				if (player.ismAlive()) {
					player.checkRobbed();
					player.turn();
					player.cardAbilities();
				}
				break;
			case MERCHANT:
				if (player.ismAlive()) {
					player.checkRobbed();
					player.turn();
					player.cardAbilities();
					player.takeMoney(1);
				}
				break;
			case ARCHITECT:
				if (player.ismAlive()) {
					player.checkRobbed();
					player.turn();
					player.cardAbilities();
					player.buildDistrict(); // может строить до 3-х кварталов за раз
					player.buildDistrict();
					player.getmHand().add(mDistrictDeck.pop()); //берет две карты после хода
					player.getmHand().add(mDistrictDeck.pop());
				}
				break;
			case WARLORD:
				if (player.ismAlive()) {
					player.checkRobbed();
					player.turn();
					player.cardAbilities();
					player.destroyDistrict();
				}
				break;
			}
			System.out.println("Походил " + player);
			System.out.println("*********************************************");
		}
		System.out.println("------------- карт осталось: "
				+ mDistrictDeck.size());
	}

	public boolean step4() {
		boolean district8 = false;
		for (Player player : mPlayerList) {
			if (player.getmTable().size() > 7) {
				player.setFirstBuild8Dustricts(true);
				district8 = true;
				break;
			}
		}
		if (district8) {
			System.out
					.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>ИГРА ОКОНЧЕНА<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			int max = 0;
			Player winner = null;
			for (Player player : mPlayerList) {
				System.out.println(player);
				System.out.println(Arrays
						.toString(player.getmTable().toArray()));
				System.out.println(">>Очки: " + player.points());
				if (player.points() > max) {
					max = player.points();
					winner = player;
				}
			}
			System.out.println("Победил - " + winner.getmName() + "!!!");
			return false;
		}

		System.out
				.println("Никто пока не постриол 8-й квартал. Игра продолжается.");
		System.out
				.println("-------------------------------------------------------------------------------------------------------------------------------");
		return true;
	}

	public static ArrayDeque<District> getmDistrictDeck() {
		return mDistrictDeck;
	}

	public static List<Player> getmPlayerList() {
		return mPlayerList;
	}

}
