package view;

import javax.swing.table.AbstractTableModel;

public class TestTableModel extends AbstractTableModel{

	String head[];
	private Object[][] data;



	public TestTableModel(String head[], Object data[][]){

		this.head=head;
		this.data=data;

	}

	public int getColumnCount() {
		return head.length;
	}

	public int getRowCount() {
		if(data!=null)
			return data.length;
		else return 0;
	}

	public String getColumnName(int col) {
		return head[col];
	}

	public Object getValueAt(int row, int col) {

		if(data!=null && data[row][col] != null)
			return data[row][col];
		else return "";
	}
	
	public Class getColumnClass(int c) {

		return getValueAt(0, c).getClass();
	}
	
	public boolean isCellEditable(int row, int column){

		return true;

	}

	public void setValueAt(Object value, int row, int col) {

		data[row][col] = value;
		fireTableCellUpdated(row, col);


	}

	public void removeRow(int row) {
	for (int i = 0; i < data[row].length; i++) {

			data[row][i]="";
		}
	}





}
