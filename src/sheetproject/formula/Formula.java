package sheetproject.formula;

import sheetproject.spreadsheet.Sheet;

/**
 * Abstract class for formulas
 * 
 * @author r.borst
 */
public abstract class Formula 
{
	public abstract String evaluate(String formula, Sheet data);
}
