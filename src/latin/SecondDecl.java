package latin;

/*
 * Add a message to the user when declining an -er word.
 *  Add a virus exception message (its neuter, the i is long)
 */
public class SecondDecl extends Noun
{
	/**
	 * 
	 * @param word - the declined word
	 * @param aGender - Gender of word
	 * @param aNumber - Number of word entered 
	 * @param aCase - Case of word entered
	 */
	public SecondDecl(String word, Gender aGender, Number aNumber, Case aCase)
	{	
		super(word, aGender, aNumber, aCase, Noun.Declension.SECOND);
		
			findStem();
		
		
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
		else if (	(getText().equalsIgnoreCase("vir") && getOriginalNumber() == Noun.Number.SINGULAR && getOriginalCase() == Noun.Case.NOM) ) //vir, viri exception
		{
			stem = "vir";
		}
		else if (	getOriginalNumber() == Noun.Number.SINGULAR && 
					(getOriginalCase() == Noun.Case.NOM || 
						(getOriginalCase() == Noun.Case.ACC && getGender() == Noun.Gender.NEUTER)) && 
					getText().endsWith("er")) //Exception for -er nouns, NOT WEAK  -ER THOUGH
		{
			stem = getText();
		}
		
		
	}
		
	public String getStem()
	{
		return stem;
	}
	
	public String getEnding(Number aNumber, Case aCase)
	{
		if (	aNumber == Noun.Number.SINGULAR && aCase == Noun.Case.VOC && 
				getOriginalNumber() == Noun.Number.SINGULAR && getOriginalCase() == Noun.Case.NOM)
		{
			if (getText().endsWith("ius"))
			{
				return ALTERNATE_VOCATIVE_ENDING2;
			}
			else if (getText().endsWith("us"))
			{
				return ALTERNATE_VOCATIVE_ENDING1;
			}
		}
		else if (aNumber == Noun.Number.SINGULAR && 
				(aCase == Noun.Case.NOM || aCase == Noun.Case.VOC) )
		{		
			if (getStem().endsWith("er"))
			{
				return "";
			}
			else if (getStem().equalsIgnoreCase("vir"))
			{
				return "";
			}
		}
		
		if (getGender() == Noun.Gender.NEUTER)
		{
			return super.getEnding(NEUTER_ENDINGS, aNumber, aCase);
		}
		else
		{
			return super.getEnding(MASCULINE_ENDINGS, aNumber, aCase);
		}
	}
	
	@Override
	public String decline(Number aNumber, Case aCase)
	{
		if (	(aNumber == Noun.Number.SINGULAR && 
				((aCase == Noun.Case.GEN || aCase == Noun.Case.VOC) || 
						aCase == Noun.Case.LOC)) && 
				stem.endsWith("i"))
		{
			String mergedStem = stem.substring(0, stem.length() - 1);
			return mergedStem + getEnding(aNumber, aCase);
		}
		
		return super.decline(aNumber, aCase);
	}
	
	private String stem;
	
	private static final String ALTERNATE_VOCATIVE_ENDING1 = "e";
	private static final String ALTERNATE_VOCATIVE_ENDING2 = "\u012B";
	
	//Last is Locative, Vocative not included (same as nominative)
	private static final String[][] MASCULINE_ENDINGS = 
		{{"us", "\u012B"}, {"\u012B", "\u014Drum"}, {"\u014D", "\u012Bs"}, {"um", "\u014Ds"}, {"\u014D", "\u012Bs"}, {"\u012B", "\u012Bs"}};
	private static final String[][] NEUTER_ENDINGS = 
		{{"um", "a"}, {"\u012B", "\u014Drum"}, {"\u014D", "\u012Bs"}, {"um", "a"}, {"\u014D", "\u012Bs"}, {"\u012B", "\u012Bs"}};
	
}
