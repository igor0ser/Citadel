package com.igor0ser;

public class Character {
	private byte id;
	private Name mName;
	private Color mColor;
	private String mAbilities;

	public Character(byte id, Name mName, Color mColor, String mAbilities) {
		this.id = id;
		this.mColor = mColor;
		this.mName = mName;
		this.mAbilities = mAbilities;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", came=" + mName + ", color="
				+ mColor + "]";
	}

	enum Name{
		ASSASIN,
		THIEF,
		MAGICIAN,
		KING,
		BISHOP,
		MERCHANT,
		ARCHITECT,
		WARLORD
	}
	
	
}
