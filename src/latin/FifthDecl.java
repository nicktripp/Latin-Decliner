package latin;

public class FifthDecl extends Noun 
{

	public FifthDecl(String word, Gender aGender, Number aNumber, Case aCase) 
	{
		super(word, aGender, aNumber, aCase, Noun.Declension.FIFTH);
		
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
	}
	
	@Override
	public String decline(Number aNumber, Case aCase)
	{
		if (aCase == Noun.Case.LOC)
		{
			return "-";
		}
		
		return super.decline(aNumber, aCase);
	}

	@Override
	public String getStem() 
	{
			return stem;
	}

	@Override
	public String getEnding(Number aNumber, Case aCase) 
	{
		if (aNumber == Noun.Number.SINGULAR && 
				(aCase == Noun.Case.GEN || aCase == Noun.Case.DAT) )
		{
			for (String e: Noun.VOWELS)
			{
				if (stem.endsWith(e))
				{
					return ALTERNATE_ENDING;
				}
			}
		}
		
		return super.getEnding(ENDINGS, aNumber, aCase);
	}
	
	private String stem;
	
	//Last is Locative, Vocative not included (same as nominative)
	private static final String[][] ENDINGS = 
		{{"\u0113s", "\u0113s"}, {"e\u012B", "\u0113rum"}, {"e\u012B", "\u0113bus"}, {"em", "\u0113s"}, {"\u0113", "\u0113bus"}, {" - ", " - "}};
	
	private static final String ALTERNATE_ENDING = "\u0113\u012B";
}
