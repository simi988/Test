package main;

import controller.abc;
import view.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PanelAutorizatii extends JPanel {

    private static final abc a = new abc();
    private JTable jtb;
    private static final Locale lc = new Locale("ro");
    private static final DateFormatSymbols dfs = new DateFormatSymbols(lc);


    public PanelAutorizatii() throws SQLException {
        initView();
    }


    private void initView() throws SQLException {
        setLayout(new BorderLayout());
        JLabel jlbAn = new JLabel("An");
        Calendar cl = Calendar.getInstance();
        int anCurent = cl.get(Calendar.YEAR);
        Integer[] ani = new Integer[anCurent - 2017 + 1];
        for (int i = 0; i < ani.length; i++) {
            ani[i] = 2017 + i;
        }
        JComboBox jcbAn = new JComboBox(ani);

        JPanel jpNord = new JPanel();
        jpNord.setPreferredSize(new Dimension(800, 40));
        jpNord.setLayout(null);

        jlbAn.setBounds(10, 10, 20, 20);
        jcbAn.setBounds(40, 10, 70, 20);

        jpNord.add(jlbAn);
        jpNord.add(jcbAn);

        String selectedItem = jcbAn.getSelectedItem().toString();

        Object[][] extracted = getObjectFromDataBase(Integer.parseInt(selectedItem));

        String[] head = {"Luna", "", "", "", "", "", "", "", "", "", "", "", "", ""};


        jcbAn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = jcbAn.getSelectedItem().toString();
                try {

                    Object[][] objects = getObjectFromDataBase(Integer.parseInt(s));
                    TestTableModel tbModel = new TestTableModel(head, objects);
                    tbModel.fireTableDataChanged();
                    jtb.setModel(tbModel);
                    createTable();

                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });

        TestTableModel tbModel = new TestTableModel(head, extracted);
        jtb = new JTable(tbModel) {
            @Override
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        createTable();
        JScrollPane scrollPane = new JScrollPane(jtb);
        add(jpNord, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

    }

    private void createTable() {
        TableColumnModel tcm = jtb.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(100);
        tcm.getColumn(2).setPreferredWidth(90);
        tcm.getColumn(5).setPreferredWidth(90);
        tcm.getColumn(8).setPreferredWidth(90);
        tcm.getColumn(11).setPreferredWidth(90);
        tcm.getColumn(13).setPreferredWidth(90);

        tcm.getColumn(1).setCellRenderer(new ButtonRenderer1());
        tcm.getColumn(2).setCellRenderer(new ButtonRenderer2());
        tcm.getColumn(3).setCellRenderer(new ButtonRenderer3());

        tcm.getColumn(4).setCellRenderer(new ButtonRenderer1());
        tcm.getColumn(5).setCellRenderer(new ButtonRenderer2());
        tcm.getColumn(6).setCellRenderer(new ButtonRenderer3());

        tcm.getColumn(7).setCellRenderer(new ButtonRenderer1());
        tcm.getColumn(8).setCellRenderer(new ButtonRenderer2());
        tcm.getColumn(9).setCellRenderer(new ButtonRenderer3());

        tcm.getColumn(10).setCellRenderer(new ButtonRenderer1());
        tcm.getColumn(11).setCellRenderer(new ButtonRenderer2());
        tcm.getColumn(12).setCellRenderer(new ButtonRenderer3());

        tcm.getColumn(13).setCellRenderer(new ButtonRenderer2());


        tcm.getColumn(1).setCellEditor(new TestButtonEditor(jtb));
        tcm.getColumn(2).setCellEditor(new TestButtonEditor(jtb));
        tcm.getColumn(3).setCellEditor(new TestButtonEditor(jtb));

        tcm.getColumn(4).setCellEditor(new TestButtonEditor(jtb));
        tcm.getColumn(5).setCellEditor(new TestButtonEditor(jtb));
        tcm.getColumn(6).setCellEditor(new TestButtonEditor(jtb));

        tcm.getColumn(7).setCellEditor(new TestButtonEditor(jtb));
        tcm.getColumn(8).setCellEditor(new TestButtonEditor(jtb));
        tcm.getColumn(9).setCellEditor(new TestButtonEditor(jtb));

        tcm.getColumn(10).setCellEditor(new TestButtonEditor(jtb));
        tcm.getColumn(11).setCellEditor(new TestButtonEditor(jtb));
        tcm.getColumn(12).setCellEditor(new TestButtonEditor(jtb));
        tcm.getColumn(13).setCellEditor(new TestButtonEditor(jtb));


        ColumnGroup cgClasaA1 = new ColumnGroup("ClasaA");
        cgClasaA1.add(tcm.getColumn(1));
        cgClasaA1.add(tcm.getColumn(2));
        cgClasaA1.add(tcm.getColumn(3));

        ColumnGroup cgClasaB1 = new ColumnGroup("ClasaB");
        cgClasaB1.add(tcm.getColumn(4));
        cgClasaB1.add(tcm.getColumn(5));
        cgClasaB1.add(tcm.getColumn(6));

        ColumnGroup cgClasaA2 = new ColumnGroup("Clasa A");
        cgClasaA2.add(tcm.getColumn(7));
        cgClasaA2.add(tcm.getColumn(8));
        cgClasaA2.add(tcm.getColumn(9));

        ColumnGroup cgClasaB2 = new ColumnGroup("Clasa B");
        cgClasaB2.add(tcm.getColumn(10));
        cgClasaB2.add(tcm.getColumn(11));
        cgClasaB2.add(tcm.getColumn(12));


        ColumnGroup cg1 = new ColumnGroup("Ampera Autorizate / Luna");
        cg1.add(cgClasaA1);
        cg1.add(cgClasaB1);
        ColumnGroup cg2 = new ColumnGroup("RedLong Autorizate / Luna");
        cg2.add(cgClasaA2);
        cg2.add(cgClasaB2);

        ColumnGroup cg3 = new ColumnGroup("Total");
        cg3.add(tcm.getColumn(13));

        GroupableTableHeader header = (GroupableTableHeader) jtb.getTableHeader();
        header.addColumnGroup(cg1);
        header.addColumnGroup(cg2);
        header.addColumnGroup(cg3);
    }

    private Object[][] getObjectFromDataBase(int anSelectat) throws SQLException {
        String[] luni = dfs.getMonths();
        Object[][] data = new Object[13][14];
        Map<Integer, List<Integer>> ap = a.ap(anSelectat);
        int sumAmpA = 0;
        int sumAmpB = 0;
        int sumRedA = 0;
        int sumRedB = 0;
        for (int i = 0; i < data.length; i++) {
            if (i < 12) {
                data[i][0] = luni[i].substring(0, 1).toUpperCase() + luni[i].substring(1);
            } else {
                data[i][0] = "Total";

            }

        }

        for (Map.Entry<Integer, List<Integer>> integerListEntry : ap.entrySet()) {
            Integer key = integerListEntry.getKey();
            if (key != 1) {
                List<Integer> value = integerListEntry.getValue();
                data[key - 2][2] = value.get(0);
                sumAmpA += value.get(0);
                data[key - 2][5] = value.get(1);
                sumAmpB += value.get(1);
                data[key - 2][8] = value.get(2);
                sumRedA += value.get(2);
                data[key - 2][11] = value.get(3);
                sumRedB += value.get(3);
                data[key - 2][13] = value.get(0) + value.get(1) + value.get(2) + value.get(3);
            }
        }
        Map<Integer, List<Integer>> anNou = a.ap(anSelectat + 1);
        for (Map.Entry<Integer, List<Integer>> integerListEntry : anNou.entrySet()) {
            Integer key = integerListEntry.getKey();
            if (key == 1) {
                List<Integer> value = integerListEntry.getValue();
                data[11][2] = value.get(0);
                sumAmpA += value.get(0);
                data[11][5] = value.get(1);
                sumAmpB += value.get(1);
                data[11][8] = value.get(2);
                sumRedA += value.get(2);
                data[11][11] = value.get(3);
                sumRedB += value.get(3);
                data[11][13] = value.get(0) + value.get(1) + value.get(2) + value.get(3);
            }
        }

        data[12][2] = sumAmpA;
        data[12][5] = sumAmpB;
        data[12][8] = sumRedA;
        data[12][11] = sumRedB;
        data[12][13]=sumAmpA+sumAmpB+sumRedA+sumRedB;
        return data;
    }

    static class ButtonRenderer1 implements TableCellRenderer {
        JButton button = new JButton();
        JButton button2 = new JButton();
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if(row<12)
            { button.setIcon(new ImageIcon(PanelAutorizatii.class.getResource("/img/minus.png")));

            return button;
            }
            else
            return button2;
        }
    }

    static class ButtonRenderer2 implements TableCellRenderer {
        JButton button = new JButton();

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            button.setText(value.toString());

            return button;
        }
    }

    static class ButtonRenderer3 implements TableCellRenderer {
        JButton button = new JButton();
        JButton button2 = new JButton();
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        	if(row<12)
        	{ button.setIcon(new ImageIcon(PanelAutorizatii.class.getResource("/img/plus.png")));

            return button;
        	}
        	else
        		return button2;
        }
    }

    public static void main(String[] arg) throws SQLException {


        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(800, 400);
        jf.add(new PanelAutorizatii());
        jf.setVisible(true);

    }

}
