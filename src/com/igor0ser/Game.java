package com.igor0ser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.igor0ser.Character.Name;

public class Game {
	private static List<Player> mPlayerList = new ArrayList<Player>(); // игроки
	private List<Player> mPlayerTemporarylList = new ArrayList<Player>(); // дополнительный list дл€ промежуточного хранени€ игроков (во врем€ нахождени€ корол€)
	private static ArrayDeque<District> mDistrictDeck = new ArrayDeque<>(); // колода кварталов
	private List<Character> characterDeck; // колода персонажей
	private Random mRandom = new Random();

	public Game(String userName) {
		//начало игры
		mDistrictDeck.addAll(DistrictDeck.districtDeck()); // заполн€ем колоду кварталов
		mPlayerList.add(new Player("HARRY")); //добавл€ем всех в список
		mPlayerList.add(new Player("RYAN"));
		mPlayerList.add(new Player("JAMIE"));
		mPlayerList.add(new Player("STEVE"));
		mPlayerList.add(new Player("BEN"));
		mPlayerList.get(mRandom.nextInt(5)).setmKing(true); // король - рэндомно

		for (Player player : mPlayerList) {
			for (int i = 0; i < 4; i++) {
				System.out.println(player.getmName() + " received "
						+ mDistrictDeck.peek());
				player.addCard(mDistrictDeck.poll()); // каждому раздаетс€ по 4 карты квартала
			}
		}

	}

	public Character step1() {
		characterDeck = CharacterDeck.characterDeck(); // нова€ колода персонажей загружаетс€
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
		Iterator<Player> iterator = mPlayerList.iterator(); // король становитьс€ первым по списку
		while (iterator.hasNext()) {
			Player player = iterator.next();
			if (!player.ismKing()) {
				iterator.remove();
				mPlayerTemporarylList.add(player);
			} else {
				king = player;
				System.out.println("ѕервым ходит король - " + king.getmName() + ", остальные после него по пор€дку!");
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
		Collections.sort(mPlayerList);
		for (Player player : mPlayerList) {
			System.out.println("’одит " + player);
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
					player.wizardChangeCards();
				}
				break;
			case KING:
				player.coronation();
				if (player.ismAlive()) {
					player.checkRobbed();
					player.turn();
				}
				break;
			case BISHOP:
				if (player.ismAlive()) {
					player.checkRobbed();
					player.turn();
				}
				break;
			case MERCHANT:
				if (player.ismAlive()) {
					player.checkRobbed();
					player.turn();
					player.takeMoney(1);
				}
				break;
			case ARCHITECT:
				if (player.ismAlive()) {
					player.checkRobbed();
					player.turn();
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
					player.destroyDistrict();
				}
				break;
			}
			System.out.println("ѕоходил " + player);
			System.out.println("*********************************************");
		}
		System.out.println("------------- карт осталось: " + mDistrictDeck.size());
	}

	public boolean step4() {
		for (Player player : mPlayerList) {
			if (player.getmTable().size() > 7) {
				System.out.println("ѕќЅ≈ƒ»Ћ " + player);
				return false;
			}
		}
		System.out.println("Ќикто пока не постриол 8-й квартал. »гра продолжаетс€.");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
		return true;
	}

	public static ArrayDeque<District> getmDistrictDeck() {
		return mDistrictDeck;
	}

	public static List<Player> getmPlayerList() {
		return mPlayerList;
	}

}
