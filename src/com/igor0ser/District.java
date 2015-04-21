package com.igor0ser;

public class District implements Comparable<Object> {
	private String mName;
	private Color mColor;
	private byte mPrice;
	private byte mQuantityInDeck;
	private String mAbility;

	public District(String mName, Color mColor, byte mPrice,
			byte mQuantityInDeck) {
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
		return mName + "(" + mColor.toString().charAt(0)
				+ mColor.toString().charAt(1) + "," + mPrice + ")";
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((mAbility == null) ? 0 : mAbility.hashCode());
		result = prime * result + ((mColor == null) ? 0 : mColor.hashCode());
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());
		result = prime * result + mPrice;
		result = prime * result + mQuantityInDeck;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		District other = (District) obj;
		if (mAbility == null) {
			if (other.mAbility != null)
				return false;
		} else if (!mAbility.equals(other.mAbility))
			return false;
		if (mColor != other.mColor)
			return false;
		if (mName == null) {
			if (other.mName != null)
				return false;
		} else if (!mName.equals(other.mName))
			return false;
		if (mPrice != other.mPrice)
			return false;
		if (mQuantityInDeck != other.mQuantityInDeck)
			return false;
		return true;
	}

}
