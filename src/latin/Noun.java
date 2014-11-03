package latin;

public abstract class Noun 
{
	public Noun(String word, Gender aGender, Number aNumber, Case aCase, Declension aDeclension)
	{
		text = word;
		
		gender = aGender;
		number = aNumber;
		caseType = aCase;
		declension = aDeclension;
	}
	
	public final String getText() { return text; }
	
	public final Case getOriginalCase() { return caseType; }
	
	public final Gender getGender() { return gender; }
	
	public final Number getOriginalNumber() { return number;} 
	
	public final Declension getDeclension() { return declension; }
	
	/**
	 * Helper method used with each declension's own getEnding method
	 * @param endings - The string[7][2] array containing the endings being used
	 * @param aNumber - The number of the ending desired
	 * @param aCase - The case of the ending desired
	 * @return the ending specific to the declension, number, and case given.
	 */
	protected final String getEnding(String[][] endings, Number aNumber, Case aCase)
	{
		int caseNum = 0;
		
		switch(aCase)
		{
		case NOM: caseNum = 0;
			break;
		case GEN: caseNum = 1;
			break;
		case DAT: caseNum = 2;
			break;
		case ACC: caseNum = 3;
			break;
		case ABL: caseNum = 4;
			break;
		case VOC: caseNum = 0;
			break;
		case LOC: caseNum = 5;
		}
		
		int numNum = 0;
		
		switch(aNumber)
		{
		case SINGULAR: numNum = 0;
			break;
		case PLURAL: numNum = 1;
			break;
		}
		
		
		return endings[caseNum][numNum];
	}
	
	/**
	 * Declines the word in all cases/numbers using other decline method
	 * @return a 7x2 array, where the columns are {sg, pl}, and the rows are {nom, gen, dat, acc, abl, loc, voc}
	 */
	public String[][] decline()
	{
		String[][] declension = new String[7][2];
		
		int x = 0;
		for (Noun.Case cas : Noun.Case.values())
		{
			int y = 0;
			for (Noun.Number num : Noun.Number.values())
			{
				declension[x][y] = decline(num, cas);
				y++;
			}
			x++;
		}
		
		return declension;
	}
	
	/**
	 * Declines the word in the case and number desired
	 * @param aNumber - the number of the desired declination
	 * @param aCase - the case of the desired declination
	 * @return the word declined in the number and case desired
	 */
	public String decline(Number aNumber, Case aCase)
	{
		
		String declined = getStem() + getEnding(aNumber, aCase);
		if (getGender() == Gender.NEUTER && aNumber == Number.SINGULAR && aCase == Case.ACC)
		{
			declined = getStem() + getEnding(Number.SINGULAR, Case.NOM);
		}
		
		return declined;
	}
	
	public String toString()
	{
		String obj = "Word given (Stem*Ending): " + getStem() + "*" + getEnding(number, caseType) +"\nDeclension: " 
				 + getDeclension().toString() + "\nGender: " + getGender().toString() 
				 + "\nNumber inputed: " + getOriginalNumber().toString() + "\nCase inputed: " + getOriginalCase().toString() + "\n\nDeclined Word: \n";
		
		int i = 0;
		String[][] declension = decline();
		for(Case cas : Case.values())
		{
			int j = 0;
			obj += "( " + cas.toString() + " ) ";
			
			for (@SuppressWarnings("unused") Number num : Number.values())
			{
				obj += declension[i][j] + ", ";
				j++;
			}
			
			obj += "\n";
			i++;
		}
		
		return obj;
	}
	
	/**
	 * Returns a new String of the same text, but with macrons replaced with normal vowels.
	 * @param macronedString - the string to be cleared of macrons
	 * @return a macron-free version of the string inputed.
	 */
	public static String toClearText(String macronedString)
	{
		String restOfString = macronedString.substring(0, macronedString.length() - 1);
		String focusLetter = macronedString.substring(macronedString.length() - 1);
		String clearedLetter = focusLetter;
		
		int j = 0;
		for (String e : MACRONS)
		{
			if (focusLetter.equalsIgnoreCase(e))
			{
				clearedLetter = VOWELS[j];
			}
			j++;
		}
		
		if (macronedString.length() <= 1)
		{
			return clearedLetter;
		}
		else
		{
			return toClearText(restOfString) + clearedLetter;
		}
	}

	/**
	 * Abstract method - each declension assigns its own stem
	 * @return the stem of the word (word - ending)
	 */
	public abstract String getStem();

	/**
	 * Abstract method - each declension defines and decides which sets of endings to use.
	 * @param aNumber - the number of the ending desired
	 * @param aCase - the case of the ending desired
	 * @return the ending in the desired case and number
	 */
	public abstract String getEnding(Number aNumber, Case aCase);

	private String text;
	private Gender gender;
	private Number number;
	private Case caseType;
	private Declension declension;
	
	public static final String[] MACRONS = {"\u0101", "\u0113", "\u012B", "\u014D", "\u016B"};
	public static final String[] VOWELS = {"a", "e", "i", "o", "u"};
	
	public static enum Gender {MASCULINE, FEMENINE, NEUTER};
	public static enum Number {SINGULAR, PLURAL};
	public static enum Case {NOM, GEN, DAT, ACC, ABL, VOC, LOC};
	public static enum Declension {FIRST, SECOND, THIRD, FOURTH, FIFTH};
	
}
