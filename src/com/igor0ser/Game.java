package com.igor0ser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Game {
	private String mUserName; // ��� ������-��������
	private static List<Player> mPlayerList = new ArrayList<Player>(); // ������
	private List<Player> mPlayerTemporarylList = new ArrayList<Player>(); // �������������� list ��� �������������� �������� ������� (�� ����� ���������� ������)
	private static ArrayDeque<District> mDistrictDeck; // ������ ���������
	private List<Character> characterDeck; // ������ ����������
	private Random mRandom = new Random();

	public Game(String userName) {
		this.mUserName = userName;
		//������ ����
		mDistrictDeck.addAll(DistrictDeck.districtDeck()); // ��������� ������ ���������
		mPlayerList.add(new PlayerUser(userName)); //��������� ���� � ������
		mPlayerList.add(new Player("Eddard Stark"));
		mPlayerList.add(new Player("Robert Baratheon"));
		mPlayerList.add(new Player("Rhaegar Targaryen"));
		mPlayerList.add(new Player("Tywin Lannister"));
		mPlayerList.get(mRandom.nextInt(5)).setmKing(true); // ������ - ��������

		for (Player player : mPlayerList) {
			for (int i = 0; i < 4; i++) {
				System.out.println(player.getmName() + " received "
						+ mDistrictDeck.peek());
				player.addCard(mDistrictDeck.poll()); // ������� ��������� �� 4 ����� ��������
			}
		}

	}

	public Character step1() {
		characterDeck = CharacterDeck.characterDeck(); // ����� ������ ���������� �����������
		characterDeck.remove(mRandom.nextInt(characterDeck.size())); // ������ ��������� ����������� �������� ����
		return characterDeck.remove(mRandom.nextInt(characterDeck.size())); // ������ - �������� �����
	}

	public void step2() { //����� ����������

		Iterator<Player> iterator = mPlayerList.iterator(); // ������ ����������� ������ �� ������
		while (iterator.hasNext()) {
			Player x = iterator.next();
			if (!x.ismKing()) {
				iterator.remove();
				mPlayerTemporarylList.add(x);
			} else
				break;

		}
		mPlayerList.addAll(mPlayerTemporarylList);
		mPlayerTemporarylList.clear();

		for (Player p : mPlayerList) { //������ �������� ���������
			p.chooseCharacter(characterDeck);
		}
	}

	public void step3() {
		Collections.sort(mPlayerList);
		for (Player player : mPlayerList) {
			int whomToKill = 0;
			switch (player.getmCharacter().getmName()) {
			case ASSASIN:
				whomToKill = player.kill(); // ����-�� �������
				player.turn();
				break;
			case THIEF:
				if (player.ismAlive()) {  //�������� �� ����� ����������� ������ � ������ ����
					player.robb(whomToKill); // ������ � ����-�� (����� ���� ���� ��� �����)
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
					player.buildDistrict(); // ����� ������� �� 3-� ��������� �� ���
					player.buildDistrict();
					player.getmHand().add(mDistrictDeck.pop()); //����� ��� ����� ����� ����
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

	public static ArrayDeque<District> getmDistrictDeck() {
		return mDistrictDeck;
	}

	public static List<Player> getmPlayerList() {
		return mPlayerList;
	}

}
