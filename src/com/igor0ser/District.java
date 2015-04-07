package com.igor0ser;

public class District implements Comparable<Object>{
	private String mName;
	private Color mColor;
	private byte mPrice;
	private byte mQuantityInDeck;
	private String mAbility;

	public District(String mName, Color mColor, byte mPrice, byte mQuantityInDeck) {
		super();
		this.mName = mName;
		this.mColor = mColor;
		this.mPrice = mPrice;
		this.mQuantityInDeck = mQuantityInDeck;
	}

	public String getmName() {
		return mName;
	}

	public Color getmColor() {
		return mColor;
	}

	public byte getmPrice() {
		return mPrice;
	}

	public byte getmQuantityInDeck() {
		return mQuantityInDeck;
	}

	public String getmAbility() {
		return mAbility;
	}

	@Override
	public String toString() {
		return "[Name=" + mName + ", Color=" + mColor + ", Price="
				+ mPrice + "]";
	}
	
	@Override
	public int compareTo(Object o) {
		District other = (District) o;
		if (mPrice > other.mPrice) {
			return 1;
		} else if (mPrice < other.mPrice) {
			return -1;
		} else {
			return 0;
		}
	}
}
