package com.igor0ser;

public class Runner {
	public static void main(String[] args) {
		
		
		Game game = new Game("Igor");
		do{
		System.out.println("Шаг 1");
		System.out.println("Рубашкой вверх выкладывают: " + game.step1());
		System.out.println("Шаг 2");
		System.out.println("Королем становится " + game.step2().getmName());
		System.out.println("Шаг 3");
		game.step3();
		System.out.println("Шаг 4");
		}
		while(game.step4());
	}
}
