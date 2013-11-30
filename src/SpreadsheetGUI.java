import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SpreadsheetGUI extends JFrame{

	public static void main(String[] args) {
		SpreadsheetGUI hallo = new SpreadsheetGUI();
		hallo.start();

	}
	
	JTable table;
	DefaultTableModel model;
	
	public void start(){
		
	
		
		model = new DefaultTableModel(Sheet.getColumns(), Sheet.getRows());
		table = new JTable(model);
		JScrollPane pane = new JScrollPane(table);
		
		for(int i = 0; i < Sheet.getColumns(); i++){
			for(int j = 0; i < Sheet.getRows(); j++){
				table.setValueAt(Sheet.getCell(i, j).getFormula(), i, j);
			}
		}
		
		add(pane);
		
		setVisible(true);
        setSize(500,500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}

}
