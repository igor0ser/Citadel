package com.igor0ser;

public class Runner {
	public static void main(String[] args) {
		
		
		Game game = new Game("Igor");
		do{
		System.out.println("��� 1");
		System.out.println("�������� ����� �����������: " + game.step1());
		System.out.println("��� 2");
		System.out.println("������� ���������� " + game.step2().getmName());
		System.out.println("��� 3");
		game.step3();
		System.out.println("��� 4");
		}
		while(game.step4());
	}
}
