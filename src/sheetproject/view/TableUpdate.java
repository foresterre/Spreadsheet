package sheetproject.view;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import sheetproject.exception.CharacterOutOfBoundsException;
import sheetproject.exception.IllegalFormulaException;
import sheetproject.exception.NullObjectException;
import sheetproject.spreadsheet.Cell;

/**
 * Class for the TableUpdate
 * 
 * @author Robin Borst
 * @author Martijn Gribnau
 * @author Roy Klip
 * @author Mitchell Olsthoorn
 * @author Ike Rijsdijk
 * @author Alan van Rossum
 */
public class TableUpdate implements TableModelListener
{

	private View view;

	public TableUpdate(View view)
	{
		this.view = view;
	}

	@Override
	public void tableChanged(TableModelEvent e)
	{

		if (this.view.openDocument == false)
		{
			int selectedColumn = e.getColumn();
			int selectedRow = e.getFirstRow();

			String changedValue = (String) this.view.getTable().getValueAt(selectedRow, selectedColumn);

			try
			{
				Cell cell = this.view.getController().getSheet().getCell(selectedColumn + 1, selectedRow + 1);

				if (!cell.getValue().equals(changedValue))
				{
					cell.setFormula(changedValue);
					cell.setState(Cell.EDITED);
					this.view.textField.setText(this.view.getController().getSheet().getCell(selectedColumn + 1, selectedRow + 1).getFormula());
				}

			}
			catch (NullPointerException e1)
			{
				if (!changedValue.equals(""))
				{
					try
					{
						Cell newCell = new Cell(changedValue);
						newCell.setState(Cell.EDITED);
						this.view.getController().getSheet().setCell(newCell, selectedColumn + 1, selectedRow + 1);
						this.view.textField.setText(this.view.getController().getSheet().getCell(selectedColumn + 1, selectedRow + 1).getFormula());
					}
					catch (IndexOutOfBoundsException | NullObjectException e2)
					{
						// Throw exception
					}
				}
			}

			try
			{
				this.view.getController().getSheet().parse();
				this.view.reloadTable();
			}
			catch (CharacterOutOfBoundsException | IllegalFormulaException e1)
			{
				// Throw exception
			}
		}
	}
}
