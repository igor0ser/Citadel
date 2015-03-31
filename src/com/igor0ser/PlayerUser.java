package com.igor0ser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PlayerUser extends Player {
	private Scanner mScanner;
	public PlayerUser(String name) {
		super(name);
	}
	
	
	
	
	@Override

	public void chooseCharacter(ArrayList<Character> characterDeck){
		System.out.println("choose a character");
		System.out.println(Arrays.toString(characterDeck.toArray()));
		mScanner = new Scanner(System.in);
		byte choice = mScanner.nextByte();
		for (Character ch : characterDeck){
			if (ch.getId()==choice){
				super.setmCharacter(ch); // выбор персонажа по id
				characterDeck.remove(characterDeck.indexOf(ch)); //удаление его из колоды
				break;
			}
			}
		System.out.println("You have chosen: " + super.getmCharacter());
	}
}
