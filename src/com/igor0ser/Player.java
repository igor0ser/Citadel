package com.igor0ser;

import java.util.ArrayList;
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
	private byte mCoins;
	private ArrayList<District> mHand;
	private ArrayList<District> mTable;
	private Character mCharacter;
	private byte mDistrictsBuild;
	private boolean isKing;
	private Random random;

	public Player(String name) {
		random = new Random();
		mName = name;
		mCoins = 2;
		mHand = new ArrayList<District>();
		mTable = new ArrayList<District>();
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

	public void chooseCharacter(ArrayList<Character> characterDeck) {
		mCharacter = characterDeck.remove(random.nextInt(characterDeck.size()));
		System.out.println("// " + mName + " has chosen " + mCharacter);
	}

	public void build() {
		byte max = 0;
		District buildingThisTurn = null;
		for (District district : mHand) {
			if ((district.getmPrice() < mCoins) && (district.getmPrice() > max)) {
				max = district.getmPrice();
				buildingThisTurn = district;
			}
		}
		System.out.println("// " + mName + "has built a " + buildingThisTurn);
		
		mTable.add(buildingThisTurn);
		mHand.remove(buildingThisTurn);

		

	}

	public void takeMoney() {
		mCoins += 2;
		System.out.println("// " + mName + " get 2 coins");
	}

	public void turn() {
		boolean canBuild = false;
		for (District district : mHand) {
			if (mCoins > district.getmPrice())
				canBuild = true;
		}
		
		if ((random.nextInt(2) == 0) && canBuild) {
			build();
		}	else {
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
}
