package latin;

public class FourthDecl extends Noun 
{

	public FourthDecl(String word, Gender aGender, Number aNumber, Case aCase) 
	{
		super(word, aGender, aNumber, aCase, Noun.Declension.FOURTH);
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
	public String getStem() 
	{
			return stem;
	}

	@Override
	public String getEnding(Number aNumber, Case aCase) 
	{
		if (getGender() == Noun.Gender.NEUTER)
		{
			return super.getEnding(NEUTER_ENDINGS, aNumber, aCase);
		}
		else
		{
			return super.getEnding(MASCULINE_ENDINGS, aNumber, aCase);
		}
	}
	
	private String stem;
	
	//Last is Locative, Vocative not included (same as nominative)
	private static final String[][] MASCULINE_ENDINGS = 
		{{"us", "\u016Bs"}, {"\u016Bs", "uum"}, {"u\u012B", "ibus"}, {"um", "\u016Bs"}, {"\u016B", "ibus"}, {"\u016B", "ibus"}};
	private static final String[][] NEUTER_ENDINGS = 
		{{"\u016B", "ua"}, {"\u016Bs", "uum"}, {"u\u012B", "ibus"}, {"\u016B", "ua"}, {"\u016B", "ibus"}, {"\u016B", "ibus"}};

}
