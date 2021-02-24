package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import controller.abc;
import dao.DAO;
import main.PanelAutorizatii;
import model.Aparat;


public class TestButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    JTable table;
    JButton button = new JButton();
    NumberFormat nf = NumberFormat.getCurrencyInstance();
    int clickCountToStart = 1;

    public TestButtonEditor(JTable table) {
        this.table = table;
        button.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        DAO dao = new DAO();
        abc a = new abc();
        // 	String dat="";
        int row = table.getEditingRow();
        int column = table.getEditingColumn();

        Autorizare autorizare = new Autorizare();
        ScoatereDeLaAutorizare scoatereDeLaAutorizare=new ScoatereDeLaAutorizare();
        if(column==1||column==4||column==7||column==10){
            JOptionPane.showMessageDialog(new JFrame(), scoatereDeLaAutorizare);
        }

        else if(column==3||column==6||column==9||column==12) {
            JOptionPane.showMessageDialog(new JFrame(), autorizare);

        }
        stopCellEditing();
        //  System.out.println(dat);
    }


    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        button.setText(value.toString());

        if (row < 12) {
            if (column == 1 || column == 4 || column == 7 || column == 10) {
                button.setIcon(new ImageIcon(PanelAutorizatii.class.getResource("/img/minus.png")));

            } else if (column == 3 || column == 6 || column == 9 || column == 12) {
                button.setIcon(new ImageIcon(PanelAutorizatii.class.getResource("/img/plus.png")));
            }
        }
        return button;
    }

    public Object getCellEditorValue() {
        return button.getText();
    }

    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    public boolean stopCellEditing() {
        return super.stopCellEditing();
    }

    public void cancelCellEditing() {
        super.cancelCellEditing();
    }



}
