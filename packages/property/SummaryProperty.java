package property;

import component.ICal;
import value.TextLineValue;

public class SummaryProperty
{
	private TextLineValue textLineValue;
	
	public TextLineValue getTextLineValue() { return textLineValue; }
	
	public SummaryProperty(TextLineValue model)
	{
		textLineValue = new TextLineValue(model);
	}
	
	public String toString()
	{
		String result = "SUMMARY";
		if(textLineValue.getLanguageValue() != null) result += ";";
		else result += ":";
		return result + textLineValue.toString() + ICal.n; 
	}
	
	public SummaryProperty(String value)
	{
		this(new TextLineValue(value));
	}
}