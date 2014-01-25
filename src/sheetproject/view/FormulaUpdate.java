package sheetproject.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormulaUpdate implements ActionListener
{
	private View view;
	
	public FormulaUpdate(View view)
	{
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (!(this.view.getTable().getSelectedColumn() == -1 || this.view.getTable().getSelectedRow() == -1)) {
			int selectedColumn = this.view.getTable().getSelectedColumn();
			int selectedRow = this.view.getTable().getSelectedRow();
			String formula = this.view.textField.getText();
			
			this.view.getController().getSheet().getCell(selectedColumn + 1, selectedRow + 1).setFormula(formula);
			this.view.getTable().setValueAt(formula, selectedRow, selectedColumn);
			
			
		}
		
	
	}
}
