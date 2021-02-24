package view;

import controller.abc;
import model.Aparat;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Autorizare extends JPanel {
	private JTextField textField;
	private JTable table;
	int numar=0;
	public Autorizare() {
		setLayout(new BorderLayout(0, 0));

		JPanel panelNord = new JPanel();
		add(panelNord, BorderLayout.NORTH);
		panelNord.setLayout(null);
		panelNord.setPreferredSize(new Dimension(200,150));
		JLabel lblNewLabel = new JLabel("Introdu Seria aparat separata prin virgula");
		lblNewLabel.setBounds(10, 10, 232, 42);
		add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(10, 50, 186, 19);
		add(textField);
		textField.setColumns(10);

		Object[][] data = new Object[100][3];
		String[] column = { "NrCrt", "IdAparatFix", "Seria" };

		JButton btnNewButton = new JButton("Adauga");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abc a=new abc();

				String text = textField.getText();

				for (String serie : text.split(",")) {

					try {
						Aparat aparat = a.serie(serie);
						data[numar][0]=numar+1;
						data[numar][1]=aparat.getIdAparatFix();
						data[numar][2]=aparat.getSerie();
						numar++;

					} catch (SQLException exception) {
						exception.printStackTrace();
					}

				}
				TestTableModel tbModel = new TestTableModel(column, data);
				tbModel.fireTableDataChanged();
				table.setModel(tbModel);
			}
		});
		btnNewButton.setBounds(10, 79, 85, 21);
		add(btnNewButton);
		table = new JTable(data,column);
		add(table, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane(table);
		add(panelNord, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		
	}
}
