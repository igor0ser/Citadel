package com.igor0ser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.igor0ser.Character.Name;

public class Player implements Comparable<Object> {

	private String mName;
	private int mCoins = 2; // деньги - изначально по 2 монеты
	private ArrayList<District> mHand = new ArrayList<District>();
	private ArrayList<District> mTable = new ArrayList<District>();
	private Character mCharacter;
	private boolean mKing; // король или нет
	private boolean mRobbed; //обворован или нет 
	private boolean mAlive = true; //убит или нет
	private boolean firstBuild8Dustricts;
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
		System.out.println(mName + " выбрал " + mCharacter);
	}

	public void turn() {
		getMoneyForSameColorDistricts();
		takeMoneyOrCards();
		buildDistrict();
	}

	private boolean takeMoneyOrCards() {
		if (mHand.size() > 0 && Collections.max(mHand).getmPrice() > 3) {
			takeMoney(2);
			return true;
		} else {
			takeCard();
			return false;
		}

	}

	public void takeMoney(int coins) {
		mCoins += coins;
		System.out.println(mName + " получает " + coins + " монет.");
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
		System.out.println(mName + " берет карту " + chosen.getmName());
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
		System.out.println(mName + " построил " + buildDistrict.getmName());
		return buildDistrict;
	}

	private int getMoneyForSameColorDistricts() {
		int result = 0;
		if (!mCharacter.getmColor().equals(Color.COLORLESS)) {
			for (District district : mTable) {
				if (mCharacter.getmColor().equals(district.getmColor())
						|| district.getmName().equals("School of Magic")) {
					result++;
				}
			}
			mCoins += result;
			System.out.println(mName + " получил " + result
					+ " монет за одноцветные кварталы.");
		}
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
		} while (robbed == killedOne);

		for (Player player : Game.getmPlayerList()) {
			if (player.getmCharacter().getId() == robbed) {
				player.mRobbed = true;
			}
		}

		System.out.println(this.mName + " грабит игрoка с персонажем "
				+ Name.values()[robbed - 1]);

		return robbed;
	}

	public int kill() {
		int whomToKill = random.nextInt(7) + 2;
		for (Player player : Game.getmPlayerList()) {
			if (player.getmCharacter().getId() == whomToKill) {
				player.mAlive = false;
			}
		}
		System.out.println(this.mName + " убивает игрoка с персонажем "
				+ Name.values()[whomToKill - 1]);
		return whomToKill;
	}

	public void coronation() {
		if (!mKing) {
			for (Player player : Game.getmPlayerList()) {
				player.setmKing(false);
			}
			setmKing(true);
		}
		System.out
				.println("!!!КОРОНАЦИЯ!!! Королем становится - " + this.mName);
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
			System.out.println(mName + " мертв в этом ходу");
			return false;
		}
		System.out.println(mName + " жив в этом ходу");
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

	public String wizardChangeCards() {
		if (mHand.size() > 3 && Collections.max(mHand).getmPrice() > 3) {
			System.out.println(mName + "(wizard) не меняется");
			return "Don't want to change";
		} else {
			if (mHand.size() < 2) {
				Player victim = Game.getmPlayerList().get(0);
				for (Player player : Game.getmPlayerList()) {
					if (player.mHand.size() > victim.mHand.size()
							&& this != player) {
						victim = player;
					}
				}
				ArrayList<District> temp = new ArrayList<District>();
				temp.addAll(mHand);
				mHand.clear();
				mHand.addAll(victim.mHand);
				victim.mHand.clear();
				victim.mHand.addAll(temp);
				System.out.println(mName + "(wizard) меняется с "
						+ victim.mName);
				return "Change with " + victim.mName;
			} else {
				int size = mHand.size();
				Game.getmDistrictDeck().addAll(mHand);
				mHand.clear();
				for (int i = 0; i < size; i++) {
					mHand.add(Game.getmDistrictDeck().pop());
				}
				System.out.println(mName + "(wizard) меняется с колодой");
				return "Change with deck";
			}
		}
	}

	public District destroyDistrict() {
		Player victim;
		do {
			victim = Game.getmPlayerList().get(
					random.nextInt(Game.getmPlayerList().size() - 1));
		} while (victim.getmCharacter().getmName().equals(Name.WARLORD)
				|| victim.getmCharacter().getmName().equals(Name.BISHOP));

		ArrayList<District> availableToDestroy = new ArrayList<>();
		for (District district : victim.mTable) {
			if (mCoins > district.getmPrice() - 1
					&& !district.getmName().equals("Keep")) {
				availableToDestroy.add(district);
			}
		}
		if (availableToDestroy.size() < 1) {
			System.out.println(mName + " не уничтожил ни одного квартала ");
			return null;

		} else {
			District aim = availableToDestroy.remove(random
					.nextInt(availableToDestroy.size()));
			victim.mTable.remove(aim);
			mCoins-=aim.getmPrice()+1;
			System.out.println(mName + " уничтожил квартал " + aim
					+ " у игрока " + victim.mName);
			return aim;
		}

	}

	public int points() {
		int result = 0;

		if (mTable.size() > 7) {
			if (firstBuild8Dustricts) {
				result += 4;
			} else {
				result += 2;
			}
		}

		for (District district : mTable) {
			result += district.getmPrice();
			if (district.getmName().equals("Univercity")
					|| district.getmName().equals("Dragon Gate")) {
				result += 2;
			}
		}

		if (DistrictDeck.colorsOfDistrict(mTable) > 4) {
			result += 3;
		}

		return result;
	}

	@Override
	public String toString() {
		return mName + "(" + mCharacter + ") " + mTable.size() + "кв/ "
				+ mHand.size() + "карт/ " + mCoins + "мон";
	}

	public void setFirstBuild8Dustricts(boolean firstBuild8Dustricts) {
		this.firstBuild8Dustricts = firstBuild8Dustricts;
	}

	public boolean isFirstBuild8Dustricts() {
		return firstBuild8Dustricts;
	}
}
