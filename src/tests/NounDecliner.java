package tests;

import java.util.Scanner;

import latin.FifthDecl;
import latin.FirstDecl;
import latin.FourthDecl;
import latin.Noun;
import latin.SecondDecl;
import latin.ThirdDecl;
import latin.Noun.Case;
import latin.Noun.Declension;
import latin.Noun.Gender;
import latin.Noun.Number;

public class NounDecliner 
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		boolean done = false;
		while (!done)
		{
			System.out.println("What is your word type? (V for verb, N for noun, Q to quit)");
			String option = in.next();
			if (option.equalsIgnoreCase("v"))
			{
				//Verb Conjugation Code
			}
			else if (option.equalsIgnoreCase("n"))
			{
				System.out.println("What is your word?");
				String text = in.next();
				
				System.out.println("Declension? (1, 2, 3, 4, or 5)");
				int declInt = in.nextInt();
				Noun.Declension declension = null;
				switch(declInt)
				{
				case 1: declension = Noun.Declension.FIRST;
					break;
				case 2: declension = Noun.Declension.SECOND;
					break;
				case 3: declension = Noun.Declension.THIRD;
					break;
				case 4: declension = Noun.Declension.FOURTH;
					break;
				case 5: declension = Noun.Declension.FIFTH;
					break;
				}
				
				System.out.println("Gender? (1 for Masc, 2 for Fem, or 3 for Neut");
				int genInt = in.nextInt();
				Noun.Gender gender = null;
				
				switch(genInt)
				{
				case 1: gender = Noun.Gender.MASCULINE;
					break;
				case 2: gender = Noun.Gender.FEMENINE;
					break;
				case 3: gender = Noun.Gender.NEUTER;
					break;
				}
				
				System.out.println("Number inputed? (1 for Sg, 2 for PL)");
				int numInt = in.nextInt();
				Noun.Number number = null;
				
				switch (numInt)
				{
				case 1: number = Noun.Number.SINGULAR;
					break;
				case 2: number = Noun.Number.PLURAL;
					break;
				}
				
				System.out.println("Case inputed? (1 for Nom, 2 for Gen, 3 for Dat, 4 for Acc, 5 for Abl)");
				int caseInt = in.nextInt();
				Noun.Case caseType = null;;
				
				switch(caseInt)
				{
				case 1: caseType = Noun.Case.NOM;
					break;
				case 2: caseType = Noun.Case.GEN;
					break;
				case 3: caseType = Noun.Case.DAT;
					break;
				case 4: caseType = Noun.Case.ACC;
					break;
				case 5: caseType = Noun.Case.ABL;
					break;
				case 6: caseType = Noun.Case.VOC;
					break;
				case 7: caseType = Noun.Case.LOC;
				}
				
				Noun noun = null;
				switch(declension)
				{
				case FIRST: noun = new FirstDecl(text, gender, number, caseType);
					break;
				case SECOND: noun = new SecondDecl(text, gender, number, caseType);
					break; 
				case THIRD: noun = new ThirdDecl(text, gender, number, caseType);
					break;
				case FOURTH: noun = new FourthDecl(text, gender, number, caseType);
					break;
				case FIFTH: noun = new FifthDecl(text, gender, number, caseType);
					break;
				}
				
				System.out.println("\n" + noun.toString());
			}
			else if (option.equalsIgnoreCase("q"))
				done = true;
		}
		
		System.out.println("Red Glasses");
	}
}
