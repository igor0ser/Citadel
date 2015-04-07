package com.igor0ser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.igor0ser.Character.Name;

public class Player implements Comparable<Object> {

	private String mName;
	private int mCoins = 2; // ������ - ���������� �� 2 ������
	private ArrayList<District> mHand = new ArrayList<District>();
	private ArrayList<District> mTable = new ArrayList<District>();
	private Character mCharacter;
	private boolean mKing; // ������ ��� ���
	private boolean mRobbed; //��������� ��� ��� 
	private boolean mAlive; //���� ��� ���
	private Random random = new Random();

	public Player(String name) {
		mName = name;
	}

	public void addCard(District districtCard) {
		mHand.add(districtCard);
	}

	public boolean ismKing() {
		return mKing;
	}

	public void setmKing(boolean isKing) {
		this.mKing = isKing;
	}

	public void chooseCharacter(List<Character> characterDeck) {
		mCharacter = characterDeck.remove(random.nextInt(characterDeck.size()));
	}

	public void turn() {
		getMoneyForSameColorDistricts();
		takeMoneyOrCards();
		buildDistrict();
	}

	private boolean takeMoneyOrCards() {
		if (Collections.max(mHand).getmPrice() > 3) {
			takeMoney(2);
			return true;
		} else {
			takeCard();
			return false;
		}

	}

	public void takeMoney(int coins) {
		mCoins += coins;
	}

	private District takeCard() {
		District chosen;
		ArrayList<District> districtToChoose = new ArrayList<District>();
		districtToChoose.add(Game.getmDistrictDeck().pop());
		districtToChoose.add(Game.getmDistrictDeck().pop());
		Collections.sort(districtToChoose);
		boolean isDuplicate = mTable.contains(districtToChoose.get(0))
				|| mHand.contains(districtToChoose.get(0));
		chosen = (isDuplicate) ? districtToChoose.remove(1) : districtToChoose
				.remove(0);
		Game.getmDistrictDeck().push(districtToChoose.get(0));
		mHand.add(chosen);
		return chosen;
	}

	public District buildDistrict() {
		ArrayList<District> availableToBuild = new ArrayList<District>();
		for (District district : mHand) {
			if (mCoins >= district.getmPrice() && !mTable.contains(district)) {
				availableToBuild.add(district);
			}
		}
		if (availableToBuild.size() < 1) {
			return null;
		}
		District buildDistrict = Collections.max(availableToBuild);
		mTable.add(buildDistrict);
		mHand.remove(buildDistrict);
		mCoins = mCoins - buildDistrict.getmPrice();
		return buildDistrict;
	}

	private int getMoneyForSameColorDistricts() {
		int result = 0;
		for (District district : mTable) {
			if (mCharacter.getmColor().equals(district.getmColor())) {
				result++;
			}
		}
		mCoins += result;
		return result;
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

	public int checkRobbed() {
		if (mRobbed) {
			int stolen = mCoins;
			mCoins = 0;
			mRobbed = false;
			for (Player player : Game.getmPlayerList()) {
				if (player.mCharacter.getmName().equals(Name.THIEF)) {
					player.mCoins += stolen;
					return stolen;
				}
			}
		}
		return 0;
	}

	public int robb(int killedOne) {
		int robbed;
		do {
			robbed = random.nextInt(6) + 3;
		} while (robbed != killedOne);
		Game.getmPlayerList().get(robbed).setmRobbed(true);
		return robbed;
	}

	public int kill() {
		int whomToKill = random.nextInt(7) + 2;
		Game.getmPlayerList().get(whomToKill).setmAlive(false);
		return whomToKill;
	}

	public void coronation() {
		if (!mKing) {
			for (Player player : Game.getmPlayerList()) {
				player.setmKing(false);
			}
			setmKing(true);
		}
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public int getmCoins() {
		return mCoins;
	}

	public void addmCoins(int addCoins) {
		mCoins += addCoins;
	}

	public ArrayList<District> getmTable() {
		return mTable;
	}

	public ArrayList<District> getmHand() {
		return mHand;
	}

	public boolean ismRobbed() {
		return mRobbed;
	}

	public void setmRobbed(boolean mRobbed) {
		this.mRobbed = mRobbed;
	}

	public boolean ismAlive() {
		if (!mAlive) {
			mAlive = true;
			return false;
		}
		return true;
	}

	public void setmAlive(boolean mALive) {
		this.mAlive = mALive;
	}

	public Character getmCharacter() {
		return mCharacter;
	}

	public void setmCharacter(Character mCharacter) {
		this.mCharacter = mCharacter;
	}

	public boolean wizardChangeCards() {
		if (Collections.max(mHand).getmPrice()<4){
			return false;}
		else {
			if (mHand.size()<2){
				
				return true;
			}
			else{
				
				return true;
			}
		}
	}

	public District destroyDistrict() {
		Player victim;
		do {
			victim = Game.getmPlayerList().get(random.nextInt(Game.getmPlayerList().size()-1));
		} while (!victim.getmCharacter().getmName().equals(Name.WARLORD) && !victim.getmCharacter().getmName().equals(Name.BISHOP));
		
		District aim=null;
		Collections.sort(victim.mTable);
		for (District district : victim.mTable){
			if (mCoins > district.getmPrice()-1){
				mCoins-= (district.getmPrice()-1);
				aim = district;
				break;
			}
		}
		victim.mTable.remove(aim);
		return aim;
	}
}
