package latin;

public class FirstDecl extends Noun
{
	/**
	 * 
	 * @param word - the declined word
	 * @param aGender - Gender of word. null if unknown
	 * @param aNumber - Number of word entered 
	 * @param aCase - Case of word entered
	 */
	public FirstDecl(String word, Gender aGender, Number aNumber, Case aCase)
	{
		super(word, aGender, aNumber, aCase, Noun.Declension.FIRST);
		
		findStem();
		
		/*   DO THIS IN ANOTHER CLASS?
		 * //Finding the gender
		if (aGender == null)
		{
			boolean done = false;
			int i = 0;
			while (!done)
			{
				String e = GENDER_EXCEPTIONS[i];
				
				if (e.equalsIgnoreCase(stem))
				{
					gender = Noun.Gender.MASCULINE;
					done = true;
				}
				else if (i == (GENDER_EXCEPTIONS.length - 1))
				{
					done = true;
				}
				else
				{
					gender = Noun.Gender.FEMENINE;
				}
				
				i++;
			}
		}
		else
			gender = aGender;
			*/
	}
	
	private void findStem()
	{
		stem = "ERROR";
		String thisEnding = getEnding(getOriginalNumber(), getOriginalCase());
		int endingLength = thisEnding.length();
		if (getText().length() < endingLength)
			return;
		String suffix = getText().substring(getText().length() - endingLength);
		if (suffix.equalsIgnoreCase(Noun.toClearText(thisEnding)))
		{
			stem = getText().substring(0, (getText().length() - thisEnding.length()) );
		}
		
	}
	
	public String getStem()
	{
		return stem;
	}
	
	
	public String getEnding(Number aNumber, Case aCase) 
	{
		//Exception for Dat/Abl/Loc pl for filia/dea
		if (	(getStem().equalsIgnoreCase("fili") || getStem().equalsIgnoreCase("de") || getStem().equalsIgnoreCase("lup") || 
					getStem().equalsIgnoreCase("equ") || getStem().equalsIgnoreCase("simi")) &&
				(aCase == Noun.Case.DAT || aCase == Noun.Case.ABL || aCase == Noun.Case.LOC) &&
				(aNumber == Noun.Number.PLURAL) )
		{
			return ALTERNATE_ENDING1;
		}
		
		return getEnding(ENDINGS, aNumber, aCase);
	}

	private String stem;
	
	private static final String ALTERNATE_ENDING1 = "\u0101bus"; //Used for Dat/Abl/Loc pl for filia/dea
	/*
	private static final String[] GENDER_EXCEPTIONS = 
		{"agricol", "accol", "adven", "athlet", "aurig", "clept", "conleg", "conviv", "conven",
			"copre", "homicid", "incol", "naut", "pirat", "poet"};
	*/
	
	// In the order Nom, Gen, Dat, Acc, Abl, Loc, (Voc is same as Nom)
	private static final String[][] ENDINGS = 
		{{"a", "ae"}, {"ae", "\u0101rum"}, {"ae", "\u012Bs"}, {"am", "\u0101s"}, {"\u0101", "\u012Bs"}, {"ae", "\u012Bs"}};
	
}
