package com.igor0ser;

public class Runner {
	public static void main(String[] args) {
		Game game = new Game("Igor");
		
		while(game.step4()){
		System.out.println("Шаг 1");
		System.out.println("Рубашкой вверх выкладывают: " + game.step1());
		System.out.println("Королем является" + game.step2());
		game.step3();
		}
	}
}
