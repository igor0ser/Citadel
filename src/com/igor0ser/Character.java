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
		return mName.toString();
	}

	enum Name {
		ASSASIN, THIEF, MAGICIAN, KING, BISHOP, MERCHANT, ARCHITECT, WARLORD
	}

	public Color getmColor() {
		return mColor;
	}

	public Name getmName() {
		return mName;
	}

	public String getmAbilities() {
		return mAbilities;
	}

}
