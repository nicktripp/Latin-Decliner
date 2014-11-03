package latin;


//fix reverse for irregular gen pl i stem or abl sg
public class ThirdDecl extends Noun
{
	/**
	 * 
	 * @param word - the declined word
	 * @param aGender - Gender of word.
	 * @param aNumber - Number of word entered 
	 * @param aCase - Case of word entered
	 */
	public ThirdDecl(String word, Gender aGender, Number aNumber, Case aCase)
	{
		super(word, aGender, aNumber, aCase, Noun.Declension.THIRD);
		
		stem = null;
		findStem();
		
	}
	
	private void findStem()
	{
			if (	(getOriginalNumber() != Noun.Number.SINGULAR || getOriginalCase() != Noun.Case.NOM) && 
					(getGender() != Noun.Gender.NEUTER || getOriginalNumber() != Noun.Number.SINGULAR || getOriginalCase() != Noun.Case.ACC)	)
			{
				stem = "ERROR";
				String thisEnding = getEnding(getOriginalNumber(), getOriginalCase());
				int endingLength = thisEnding.length();
				if (getText().length() < endingLength)
					return;
				suffix = getText().substring(getText().length() - endingLength);
				if (suffix.equalsIgnoreCase(Noun.toClearText(thisEnding)))
				{
					stem = getText().substring(0, (getText().length() - thisEnding.length()) );
				}
			
				for (String e : I_STEMS)
				{
					if (	getText().substring(0, (getText().length() - thisEnding.length())).equalsIgnoreCase(e) || 
							getText().substring(0, getText().length() - 3).equalsIgnoreCase(e))
					{	
						if (getOriginalCase() == Noun.Case.ABL && getOriginalNumber() == Noun.Number.SINGULAR &&
								suffix.equalsIgnoreCase(Noun.toClearText("i")) )
						{
							stem = getText().substring(0, getText().length() - thisEnding.length() );
						}
						else if (getOriginalCase() == Noun.Case.GEN && getOriginalNumber() == Noun.Number.PLURAL &&
								getText().substring(getText().length() - 3).equalsIgnoreCase("ium") )
						{
							stem = getText().substring(0, getText().length() - 3);
						}
					}
				}
			}
			
				
		
	}
	
	
	public String getStem()
	{
		if (stem == null)
			return ERROR_MESSAGE;
		else
			return stem;
	}
	
	/**
	 * Declines the word
	 * @return a 5x2 array, where the columns are {sg, pl}, and the rows are {nom, gen, dat, acc, abl}
	 */
	public String[][] decline()
	{
		String[][] declension = new String[7][2];
		
		if (stem != null)
		{
			declension = super.decline();
		}
		else
		{
			for (int x = 0; x < 7; x++)
			{
				for (int y = 0; y < 2; y++)
				{
					declension[x][y] = ERROR_MESSAGE;
				}
			}
			declension[0][0] = decline(Noun.Number.SINGULAR, Noun.Case.NOM);
			declension[5][0] = decline(Noun.Number.SINGULAR, Noun.Case.VOC);
			if (getGender() == Noun.Gender.NEUTER)
				declension[3][0] = decline(Noun.Number.SINGULAR, Noun.Case.ACC);
		}
			
		return declension;
	}
	
	/**
	 * Declines the word
	 * @param aNumber - the number of the desired declination
	 * @param aCase - the case of the desired declination
	 * @return the word declined in the number and case desired
	 */
	public String decline(Number aNumber, Case aCase)
	{
		String declined;
		if (stem != null)
		{
			if (	(aNumber == Noun.Number.SINGULAR && 
						(aCase == Noun.Case.NOM || aCase == Noun.Case.VOC)) ||
					(getGender() == Noun.Gender.NEUTER && aNumber == Noun.Number.SINGULAR && aCase == Noun.Case.ACC) )
			{
				declined = ERROR_MESSAGE;
			}
			else
			{
				declined = super.decline(aNumber, aCase);
			}
		}
		else if (	(aNumber != Noun.Number.SINGULAR || 
					(aCase != Noun.Case.NOM && aCase != Noun.Case.VOC) ) && 
					(getGender() != Noun.Gender.NEUTER || aNumber != Noun.Number.SINGULAR || aCase != Noun.Case.ACC) )
		{
			declined = ERROR_MESSAGE;
		}
		else
		{
			declined = getText();
		}
		
		return declined;
	}
	
	
	public String getEnding(Number aNumber, Case aCase)
	{
		if (getGender() == Noun.Gender.NEUTER)
		{
			if (isIStem() && aNumber == Noun.Number.SINGULAR && aCase == Noun.Case.ABL)
			{
				return "\u012B";
			}
			else if (isIStem() && aNumber == Noun.Number.PLURAL && aCase == Noun.Case.GEN)
			{
				return "i" + super.getEnding(ENDINGS, aNumber, aCase);
			}
			
			return super.getEnding(NEUTER_ENDINGS, aNumber, aCase);
		}
		else if (isIStem() && aCase == Noun.Case.GEN && aNumber == Noun.Number.PLURAL)
		{
			return "i" + super.getEnding(ENDINGS, aNumber, aCase);
		}
		else if (getStem().equalsIgnoreCase("nav") && aCase == Noun.Case.ABL && aNumber == Noun.Number.SINGULAR)
		{
			return "\u012B";
		}
		else
		{
			return super.getEnding(ENDINGS, aNumber, aCase);
		}
	}
	
	private boolean isIStem()
	{
		for (String e : I_STEMS)
		{
			if (getStem().equalsIgnoreCase(e))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private String stem;
	private String suffix;
	
	private static final String ERROR_MESSAGE = "UNKNOWN";
	
	private static final String[] I_STEMS = {"animal", "art", "arc", "ass", "aur", "civ", "fin", "gent", "host", "ign", "mar", "ment", "mont", "mort", "nav", "noct", 
		"nub", "part", "urb", "v", "infant", "luct", "caed", "exemplar"};
	
	//Last is Locative, Vocative not included (same as nominative)
	private static final String[][] ENDINGS = 
		{{"", "\u0113s"}, {"is", "um"}, {"\u012B", "ibus"}, {"em", "\u0113s"}, {"e", "ibus"}, {"is", "ibus"}};
	private static final String[][] NEUTER_ENDINGS = 
		{{"", "a"}, {"is", "um"}, {"\u012B", "ibus"}, {"", "a"}, {"e", "ibus"}, {"is", "ibus"}};
	
}
