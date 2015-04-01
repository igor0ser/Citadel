package com.igor0ser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player implements Comparable {

	public Character getmCharacter() {
		return mCharacter;
	}

	public void setmCharacter(Character mCharacter) {
		this.mCharacter = mCharacter;
	}

	private String mName;
	private byte mPoints;
	private byte mCoins = 2;
	private ArrayList<District> mHand = new ArrayList<District>();
	private ArrayList<District> mTable = new ArrayList<District>();
	private Character mCharacter;
	private byte mDistrictsBuild;
	private boolean isKing;
	private Random random = new Random();

	public Player(String name) {
		mName = name;
	}

	public void addCard(District districtCard) {
		mHand.add(districtCard);
	}

	public boolean getIsKing() {
		return isKing;
	}

	public void setKing(boolean isKing) {
		this.isKing = isKing;
	}

	public void chooseCharacter(List<Character> characterDeck) {
		mCharacter = characterDeck.remove(random.nextInt(characterDeck.size()));
		System.out.println("// " + mName + " has chosen " + mCharacter);
	}

	public District build() {
		byte max = 0;
		District buildingThisTurn = null;
		for (District district : mHand) {
			if ((district.getmPrice() < mCoins) && (district.getmPrice() > max)) {
				max = district.getmPrice();
				buildingThisTurn = district;
			}
		}
		System.out.println("// " + mName + "has built a " + buildingThisTurn);
		mCoins=(byte) (mCoins-buildingThisTurn.getmPrice());
		mTable.add(buildingThisTurn);
		mHand.remove(buildingThisTurn);
		return buildingThisTurn;
		
	}

	public byte takeMoney() {
		mCoins += 2;
		System.out.println("// " + mName + " get 2 coins");
		return mCoins;
	}

	public void turn() {
		boolean canBuild = false;
		for (District district : mHand) {
			if (mCoins > district.getmPrice())
				canBuild = true; // проверяет хватает ли денег на постройку квартала
		}

		if ((random.nextInt(2) == 0) && canBuild) { // на рэндоме или строит или берет монеты 
			build();
		} else {
			takeMoney();
		}

	}

	@Override
	public int compareTo(Object o) {
		Player pl = (Player) o;
		if (mCharacter.getId() > pl.mCharacter.getId()) {
			return 1;
		} else if (mCharacter.getId() < pl.mCharacter.getId()) {
			return -1;
		} else {
			return 0;
		}
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public byte getmCoins() {
		return mCoins;
	}

	public void addmCoins(byte addCoins) {
		mCoins+= addCoins;
	}

	public ArrayList<District> getmTable() {
		return mTable;
	}

	public ArrayList<District> getmHand() {
		return mHand;
	}
}
