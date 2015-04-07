package com.igor0ser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Tester {
public static void main(String[] args) {
	ArrayList<District> deck = DistrictDeck.districtDeck();
	//System.out.println(Arrays.toString(deck.toArray()));
	System.out.println(deck.size());
	Random r = new Random();
	System.err.println(r.nextInt(3));
}
}
