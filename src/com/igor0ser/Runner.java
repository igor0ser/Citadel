package com.igor0ser;

public class Runner {
	public static void main(String[] args) {

		Game game = new Game("Igor");
		int i = 1;
		do {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>> ХОД #" + i + " <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			i++;
			System.out.println("Шаг 1");
			System.out.println("Рубашкой вверх выкладывают: " + game.step1());
			System.out.println("Шаг 2");
			game.step2();
			System.out.println("Шаг 3");
			game.step3();
			System.out.println("Шаг 4");
			} while (game.step4());

	}
}
