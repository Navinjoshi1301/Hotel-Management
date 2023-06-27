import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

import net.proteanit.sql.DbUtils;

public class Home extends  JFrame {


     JPanel mainn;
     JTable table1;
     JButton customerButton;
     JButton maxAmountButton;
     JButton recentOrderButton;
     JButton menuButton;
     JButton mostOrderedButton;
     JButton maxOrderButton;
     JTextField textId;
     JScrollPane displayTable;
     JButton searchButton;


     static  String customerrr;
     static  int column;
//    static  int column1;
    static int method;
    static  JFrame frame;

    public static void main(String[] args) {

       Home hm= new Home();
       hm.Homee();
    }

    Connection con;
    PreparedStatement pst;

    public void Homee() {
        connect();
         frame = new JFrame("Home");
        frame.setMinimumSize(new Dimension(1500, 790));

        frame.setContentPane(mainn);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerData();
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuData();
            }
        });

        maxOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maxOrder();
            }
        });

        mostOrderedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostOrdered();
            }
        });

        maxAmountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maxAmountPaid();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchData();
            }
        });
    }

    public void connect() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mySql://localhost:3307/restaurent", "root", "Navin@3322");

            if (con.isClosed()) {
                System.out.println("Connection isn't created");
            } else {
                System.out.println("Connection Created...");
            }

        } catch (Exception e) {

            System.out.println(e + "\nSomething Went Wrong...!");
            // TODO: handle exception
        }
    }

    void customerData() {
        try {
            String q = "Select * from Customer";

            pst = con.prepareStatement(q);

            ResultSet set = pst.executeQuery();

            table1.setModel(DbUtils.resultSetToTableModel(set));

        } catch (Exception e) {
            e.printStackTrace();
        }
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1){
                    Details ds= new Details();
                    method = 1;
    frame.dispose();
                    JTable target=(JTable) e.getSource();
                    int row= target.getSelectedRow();
                     column=target.getSelectedColumn();
                     if(column==1 ||column==2){
                         column=0;
                     }
                    customerrr= target.getValueAt(row,column).toString();
//                    System.out.println(column);
//                    System.out.println(customerrr+"Homee");
                    ds.Detail();
//                    JOptionPane.showMessageDialog(null,target.getValueAt(row,column));
                }
            }
        });

    }

    void menuData() {
        try {
            String q = "Select * from menu";

            pst = con.prepareStatement(q);

            ResultSet set = pst.executeQuery();

            table1.setModel(DbUtils.resultSetToTableModel(set));

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1){
                    Details ds= new Details();
                    method = 2;
                    frame.dispose();

                    JTable target1=(JTable) e.getSource();
                    int row= target1.getSelectedRow();
                    column=target1.getSelectedColumn();
                    if (column==1){
                        column=0;
                    }
                    customerrr= target1.getValueAt(row,column).toString();
                    System.out.println(column);

                    System.out.println(customerrr+"Homee");
                    ds.Detail();
//                    JOptionPane.showMessageDialog(null,target.getValueAt(row,column));
                }
            }
        });
    }

    void maxOrder()  {
        try {
            String q = "select cstmr_name,mob_number,count(order_id)as" +
                    " OrderCount from customer join orderr on " +
                    "customer.cstmr_id= orderr.Custmr_id group by " +
                    "cstmr_name,mob_number order by OrderCount desc limit 1";

            pst = con.prepareStatement(q);

            ResultSet set = pst.executeQuery();

            table1.setModel(DbUtils.resultSetToTableModel(set));

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1){
                    Details ds= new Details();
                    method = 3;
                    frame.dispose();

                    JTable target=(JTable) e.getSource();
                    int row= target.getSelectedRow();
                    column=target.getSelectedColumn();
                    if(column==2){
                        column=0;
                    }
                    customerrr= target.getValueAt(row,column).toString();
                    System.out.println(column);
                    System.out.println(customerrr+"Homee");
                    ds.Detail();
//                    JOptionPane.showMessageDialog(null,target.getValueAt(row,column));
                }
            }
        });
    }

    void maxAmountPaid()    {
        try {
            String q = "select Custmr_id,cstmr_name,mob_number,sum(price)as max_amount from customer \n" +
                    "join orderr \n" +
                    "on customer.cstmr_id= orderr.Custmr_id \n" +
                    "join Menu \n" +
                    "on menu.Item_Id=orderr.Itemm_id \n" +
                    "group by Custmr_id,cstmr_name,mob_number\n" +
                    "order by max_amount desc";
            pst = con.prepareStatement(q);

            ResultSet set = pst.executeQuery();

            table1.setModel(DbUtils.resultSetToTableModel(set));

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1){
                    Details ds= new Details();
                    method = 4;
                    frame.dispose();

                    JTable target=(JTable) e.getSource();
                    int row= target.getSelectedRow();
                    column=target.getSelectedColumn();
                    if(column==1||column==2||column==3){
                        column=0;
                    }
                    customerrr= target.getValueAt(row,column).toString();
//                    System.out.println(column);
//                    System.out.println(customerrr+"Homee");
                    ds.Detail();
//                    JOptionPane.showMessageDialog(null,target.getValueAt(row,column));
                }
            }
        });
    }

    void mostOrdered() {
        try {
            String q = "select Itemm_id,Item_Name,count(Itemm_id)as total_order from Menu \n" +
                    "join orderr \n" +
                    "on menu.Item_Id=orderr.Itemm_id \n" +
                    "group by Item_Name,Itemm_id\n" +
                    "order by total_order desc limit 1";
            pst = con.prepareStatement(q);

            ResultSet set = pst.executeQuery();

            table1.setModel(DbUtils.resultSetToTableModel(set));

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1){
                    Details ds= new Details();
                    method = 5;
                    frame.dispose();

                    JTable target=(JTable) e.getSource();
                    int row= target.getSelectedRow();

                    column=target.getSelectedColumn();
                    if (column==1||column==2){
                        column=0;
                    }
                    customerrr= target.getValueAt(row,column).toString();
//                    System.out.println(column);
//                    System.out.println(customerrr+"Homee");
                    ds.Detail();
//                    JOptionPane.showMessageDialog(null,target.getValueAt(row,column));
                }
            }
        });
    }

    void searchData() {
        try {

            String customer = textId.getText();

            String q = "select * from customer where cstmr_id = ?";

            pst = con.prepareStatement(q);
            pst.setString(1, customer);

            ResultSet set = pst.executeQuery();

            if (set.next() == true) {
                set = pst.executeQuery();
                table1.setModel(DbUtils.resultSetToTableModel(set));
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Customer ID...!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1){
                    Details ds= new Details();
                    method = 6;
                    frame.dispose();

                    JTable target=(JTable) e.getSource();
                    int row= target.getSelectedRow();
                    column=target.getSelectedColumn();
                    if (column==1 || column==2){
                        column=0;
                    }
                    customerrr= target.getValueAt(row,column).toString();
//                    System.out.println(column);
//                    System.out.println(customerrr+"Homee");
                    ds.Detail();
//                    JOptionPane.showMessageDialog(null,target.getValueAt(row,column));
                }
            }
        });
    }

//    void resentOrder() {
//        try {
//            String q = "SELECT customer.cstmr_id, customer.cstmr_name,\n" +
//                    "customer.mob_number, menu.Item_Name, menu.Price \n" +
//                    "FROM customer \n" +
//                    "LEFT JOIN orderr  ON customer.cstmr_id = orderr.custmr_id\n" +
//                    "LEFT JOIN menu ON orderr.Itemm_id = menu.Item_id\n" +
//                    "WHERE customer.cstmr_id = 17";
//            pst = con.prepareStatement(q);
//
//            ResultSet set = pst.executeQuery();
//
//            table1.setModel(DbUtils.resultSetToTableModel(set));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            // TODO: handle exception
//        }
//        table1.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount()==1){
//                    Details ds= new Details();
//                    method = 7;
//
//                    JTable target=(JTable) e.getSource();
//                    int row= target.getSelectedRow();
//                    column=target.getSelectedColumn();
//                    customerrr= target.getValueAt(row,column).toString();
////                    System.out.println(column);
////                    System.out.println(customerrr+"Homee");
//                    ds.Detail();
////                    JOptionPane.showMessageDialog(null,target.getValueAt(row,column));
//                }
//            }
//        });
//    }



}

