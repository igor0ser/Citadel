package com.igor0ser;

public class District {
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
	
}
