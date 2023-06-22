import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Details extends JFrame {
     JPanel panel1;
     JButton button1;
     JTable table1;
  static   Details d;

    Connection con;
    PreparedStatement pst;


    public static void main(String[] args) {
     d  = new Details();
        d.Detail();
    }
    public  void Detail(){

        connect();
        if (Home.method==1){
            customerMenu();
        }
        else if(Home.method==2){
            menuData();
        }
        else if(Home.method==3){
            maxOrder();
        }
        else if(Home.method==4){
            maxAmountpaid();
        }
        else if(Home.method==5){
            mostOrder();
        }
        else if(Home.method==6){
            searchData();
        }


        JFrame frame = new JFrame("Details");
        frame.setMinimumSize(new Dimension(1500, 790));
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose();
                Home hmm= new Home();
                hmm.Homee();



            }
        });
    }
    public void connect() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mySql://localhost:3307/dhaba", "root", "Satyam@123");

            if (con.isClosed()) {
                System.out.println("Connection isn't created");
            } else {
                System.out.println("Connection Created...");
            }

        } catch (Exception e) {

            System.out.println(e + "\nSomething Went Wrong...!");
            // TODO: handle exception
        }
    };
    void customerMenu(){
        try {
            String q = "";

            if (Home.column == 0){
                q = "SELECT customer.cstmr_id as Id,customer.cstmr_name as Name,\n" +
                        " group_concat(menu.Item_Name  separator ' | ')as Item_Name,sum(menu.price)as Bill_Amount\n" +
                        "FROM customer \n" +
                        "\n" +
                        "LEFT JOIN orderr  ON customer.cstmr_id = orderr.custmr_id\n" +
                        "LEFT JOIN menu ON orderr.Itemm_id = menu.Item_id\n" +
                        "where cstmr_id=?\n" +
                        "group by customer.cstmr_id,customer.cstmr_name\n"
                ;
            }

            pst = con.prepareStatement(q);
            String str=Home.customerrr;
            System.out.println(str+"Details");
            pst.setString(1,str);

            ResultSet set = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(set));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    void menuData(){
        try {
            String q = "";

            if ((Home.column == 0)){
                q = "SELECT menu.Item_Name, group_concat(distinct cstmr_name separator ' | ')as Customers,menu.Price\n" +
                        "  FROM customer \n" +
                        "  LEFT JOIN orderr  ON customer.cstmr_id = orderr.custmr_id\n" +
                        "  LEFT JOIN menu ON orderr.Itemm_id = menu.Item_id\n" +
                        "  where menu.item_id=?";
            }

            else if(Home.column == 2){
                q = "select Price,Item_id,Item_name from menu where price=?;";
            }
            pst = con.prepareStatement(q);
            String str=Home.customerrr;
            System.out.println(str+"Details");
            pst.setString(1,str);

            ResultSet set = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(set));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void maxOrder(){
        try {
            String q = "";

            if (Home.column == 0){
                q = "SELECT customer.cstmr_id as Id,customer.cstmr_name as Name,\n" +
                        " group_concat(menu.Item_Name  separator ' | ')as Item_Name,sum(menu.price)as Bill_Amount\n" +
                        "FROM customer \n" +
                        "\n" +
                        "LEFT JOIN orderr  ON customer.cstmr_id = orderr.custmr_id\n" +
                        "LEFT JOIN menu ON orderr.Itemm_id = menu.Item_id\n" +
                        "where cstmr_name=?\n" +
                        "group by customer.cstmr_id,customer.cstmr_name\n"
                ;
            }
            else if(Home.column == 1){
                q = "SELECT customer.cstmr_id as Id,customer.cstmr_name as Name,\n" +
                        " group_concat(menu.Item_Name  separator ' | ')as Item_Name,sum(menu.price)as Bill_Amount\n" +
                        "FROM customer \n" +
                        "\n" +
                        "LEFT JOIN orderr  ON customer.cstmr_id = orderr.custmr_id\n" +
                        "LEFT JOIN menu ON orderr.Itemm_id = menu.Item_id\n" +
                        "where mob_number=?\n" +
                        "group by customer.cstmr_id,customer.cstmr_name\n"
                ;
            }
//            else if(Home.column == 2){
//                q = "SELECT customer.cstmr_id as Id,customer.cstmr_name as Name,\n" +
//                        " group_concat(menu.Item_Name  separator ' | ')as Item_Name,sum(menu.price)as Bill_Amount\n" +
//                        "FROM customer \n" +
//                        "\n" +
//                        "LEFT JOIN orderr  ON customer.cstmr_id = orderr.custmr_id\n" +
//                        "LEFT JOIN menu ON orderr.Itemm_id = menu.Item_id\n" +
//                        "where cstmr_name =?\n" +
//                        "group by customer.cstmr_id,customer.cstmr_name\n"
//                ;
//            }
            pst = con.prepareStatement(q);
            String str=Home.customerrr;
            System.out.println(str+"Details");
            pst.setString(1,str);

            ResultSet set = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(set));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    void recentOrder(){
//        try {
//            String q = "";
//
//            if (Home.column == 0){
//                q = "SELECT customer.cstmr_id as Id,customer.cstmr_name as Name,\n" +
//                        " group_concat(menu.Item_Name  separator ' | ')as Item_Name,sum(menu.price)as Bill_Amount\n" +
//                        "FROM customer \n" +
//                        "\n" +
//                        "LEFT JOIN orderr  ON customer.cstmr_id = orderr.custmr_id\n" +
//                        "LEFT JOIN menu ON orderr.Itemm_id = menu.Item_id\n" +
//                        "where cstmr_id=?\n" +
//                        "group by customer.cstmr_id,customer.cstmr_name\n"
//                        ;
//            }
//            else if(Home.column == 1){
//                q = "SELECT customer.cstmr_id as Id,customer.cstmr_name as Name,\n" +
//                        " group_concat(menu.Item_Name  separator ' | ')as Item_Name,sum(menu.price)as Bill_Amount\n" +
//                        "FROM customer \n" +
//                        "\n" +
//                        "LEFT JOIN orderr  ON customer.cstmr_id = orderr.custmr_id\n" +
//                        "LEFT JOIN menu ON orderr.Itemm_id = menu.Item_id\n" +
//                        "where cstmr_Name=?\n" +
//                        "group by customer.cstmr_id,customer.cstmr_name\n"
//                ;
//            }
//            else if(Home.column == 2){
//                q = "SELECT customer.cstmr_id as Id,customer.cstmr_name as Name,\n" +
//                        " group_concat(menu.Item_Name  separator ' | ')as Item_Name,sum(menu.price)as Bill_Amount\n" +
//                        "FROM customer \n" +
//                        "\n" +
//                        "LEFT JOIN orderr  ON customer.cstmr_id = orderr.custmr_id\n" +
//                        "LEFT JOIN menu ON orderr.Itemm_id = menu.Item_id\n" +
//                        "where mob_number=?\n" +
//                        "group by customer.cstmr_id,customer.cstmr_name\n"
//                ;
//            }
//            else if(Home.column == 3){
//                q = "SELECT customer.cstmr_id as Id,customer.cstmr_name as Name,\n" +
//                        " group_concat(menu.Item_Name  separator ' | ')as Item_Name,sum(menu.price)as Bill_Amount\n" +
//                        "FROM customer \n" +
//                        "\n" +
//                        "LEFT JOIN orderr  ON customer.cstmr_id = orderr.custmr_id\n" +
//                        "LEFT JOIN menu ON orderr.Itemm_id = menu.Item_id\n" +
//                        "where Item_Name=?\n" +
//                        "group by customer.cstmr_id,customer.cstmr_name\n"
//                ;
//            }
//            else if(Home.column == 4){
//                q = "SELECT customer.cstmr_id as Id,customer.cstmr_name as Name,\n" +
//                        " group_concat(menu.Item_Name  separator ' | ')as Item_Name,sum(menu.price)as Bill_Amount\n" +
//                        "FROM customer \n" +
//                        "\n" +
//                        "LEFT JOIN orderr  ON customer.cstmr_id = orderr.custmr_id\n" +
//                        "LEFT JOIN menu ON orderr.Itemm_id = menu.Item_id\n" +
//                        "where Price=?\n" +
//                        "group by customer.cstmr_id,customer.cstmr_name\n"
//                ;
//            }
//            pst = con.prepareStatement(q);
//            String str=Home.customerrr;
//            System.out.println(str+"Details");
//            pst.setString(1,str);
//
//            ResultSet set = pst.executeQuery();
//            table1.setModel(DbUtils.resultSetToTableModel(set));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }


    void mostOrder(){
        try {
            String q = "";

            if ((Home.column == 0)){
                q = "SELECT menu.Item_Name, group_concat(distinct cstmr_name separator ' | ')as Customers,menu.Price\n" +
                        "                        FROM customer \n" +
                        "                        LEFT JOIN orderr  ON customer.cstmr_id = orderr.custmr_id\n" +
                        "                        LEFT JOIN menu ON orderr.Itemm_id = menu.Item_id\n" +
                        "                        where menu.item_id=?";
            }
            pst = con.prepareStatement(q);
            String str=Home.customerrr;
            System.out.println(str+"Details");
            pst.setString(1,str);

            ResultSet set = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(set));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    void maxAmountpaid(){
        try {
            String q = "";

            if (Home.column == 0){
                q = "SELECT Item_Name,Price FROM customer \n" +
                        "LEFT JOIN orderr  ON customer.cstmr_id = orderr.custmr_id\n" +
                        "LEFT JOIN menu ON orderr.Itemm_id = menu.Item_id\n" +
                        "where cstmr_id =?;";
            }

            pst = con.prepareStatement(q);
            String str=Home.customerrr;
            System.out.println(str+"Details");
            pst.setString(1,str);

            ResultSet set = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(set));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    void searchData(){
        try {
            String q = "";

            if (Home.column == 0){
                q = "SELECT customer.cstmr_id as Id,customer.cstmr_name as Name,\n" +
                        " group_concat(menu.Item_Name  separator ' | ')as Item_Name,sum(menu.price)as Bill_Amount\n" +
                        "FROM customer \n" +
                        "\n" +
                        "LEFT JOIN orderr  ON customer.cstmr_id = orderr.custmr_id\n" +
                        "LEFT JOIN menu ON orderr.Itemm_id = menu.Item_id\n" +
                        "where cstmr_id=?\n" +
                        "group by customer.cstmr_id,customer.cstmr_name\n"
                ;
            }
            pst = con.prepareStatement(q);
            String str=Home.customerrr;
            System.out.println(str+"Details");
            pst.setString(1,str);

            ResultSet set = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(set));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
