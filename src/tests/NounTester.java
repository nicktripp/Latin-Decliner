package tests;

import latin.Noun;

public class NounTester 
{

	public static void main(String[] args) 
	{
		String hand = "manus";
		String macronedHand = "man\u016Bs";
		System.out.println(macronedHand + " is equal to " + hand + " ignoring macrons: " + hand.equalsIgnoreCase(Noun.toClearText(macronedHand)));
		System.out.println(macronedHand + " cleared: " + Noun.toClearText(macronedHand));
		
		String hand2 = "agricola";
		String macronedHand2 = "\u0101gricol\u0101";
		System.out.println(macronedHand2 + " is equal to " + hand2 + " ignoring macrons: " + hand2.equalsIgnoreCase(Noun.toClearText(macronedHand2)));
		System.out.println(macronedHand2 + " cleared: " + Noun.toClearText(macronedHand2));

		String vowels = "aeiou";
		String macrons = "";
		for (String e : Noun.MACRONS)
		{
			macrons += e;
		}

		System.out.println(macrons + " is equal to " + vowels + " ignoring macrons: " + vowels.equalsIgnoreCase(Noun.toClearText(macrons)));
		System.out.println(macrons + " cleared: " + Noun.toClearText(macrons));

	}

}
