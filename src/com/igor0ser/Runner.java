package com.igor0ser;

public class Runner {
	public static void main(String[] args) {
		Game game = new Game("Igor");
		
		while(game.step4()){
		System.out.println("��� 1");
		System.out.println("�������� ����� �����������: " + game.step1());
		System.out.println("������� ��������" + game.step2());
		game.step3();
		}
	}
}
