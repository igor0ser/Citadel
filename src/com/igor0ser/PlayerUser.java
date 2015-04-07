package com.igor0ser;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PlayerUser extends Player {
	private Scanner mScanner = new Scanner(System.in);;
	public PlayerUser(String name) {
		super(name);
	}
	
	
	
	
	@Override

	public void chooseCharacter(List<Character> characterDeck){
		
		System.out.println("choose a character");
		System.out.println(Arrays.toString(characterDeck.toArray()));
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
	
	public void turn() {
		System.out.println("your money: " + this.getmCoins());
		System.out.println("your hand: " + Arrays.toString(this.getmHand().toArray()));
		System.out.println("Choose: money(0) or build(1)?");
		byte choice = mScanner.nextByte();
			if (choice==1){
				this.build();
			}
			else{
				//this.takeMoney();
			}
	}
	public District build() {
		System.out.println("You are building a district!");
		byte choice = mScanner.nextByte();
		District buildingThisTurn = this.getmHand().remove(choice);
		this.getmTable().add(buildingThisTurn);
		this.addmCoins((byte)-buildingThisTurn.getmPrice());
		System.out.println("You had builded a " + buildingThisTurn);
		return buildingThisTurn;
		
	}
		
}
