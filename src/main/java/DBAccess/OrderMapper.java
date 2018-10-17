/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author claudia
 */
public class OrderMapper
{
     public static void createOrder(Order order) throws Exception {
    try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO `orders` (order_id, userid, length, width, height, sent) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getOrder_id());
            ps.setInt(2, order.getUser_id());
            ps.setInt(3, order.getLength());
            ps.setInt(4, order.getWidth());
            ps.setInt(5, order.getHeight());
            ps.setBoolean(6, order.isShipped());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            order.setOrder_id(id);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static int countOrders(int id) throws Exception {
        int count = 0;
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT COUNT(id) AS count FROM `orders` WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet ids = ps.executeQuery();
            if (ids.next()) {
                count = ids.getInt("count");
            } else {
                throw new Exception("Could not find any orders!");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new Exception(ex.getMessage());
        }
        return count;
    }

    public static List<Order> getAllOrdersbyUser(int id) throws Exception {
        List<Order> orders = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM orders WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet ids = ps.executeQuery();
            while (ids.next()) {
                int order_id = ids.getInt("order_id");
                int user_id = ids.getInt("user_id");
                int length = ids.getInt("length");
                int width = ids.getInt("width");
                int height = ids.getInt("height");
                Boolean shipped = ids.getBoolean("shipped");
                Order order = new Order(order_id, user_id, length, width, height, shipped);
                order.setOrder_id(id);
                orders.add(order);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new Exception(ex.getMessage());
        }
        return orders;
    }

    public static int countAllOrders() throws Exception {
        int count = 0;
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT COUNT(order_id) AS count FROM orders";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet ids = ps.executeQuery();
            if (ids.next()) {
                count = ids.getInt("count");
            } else {
                throw new Exception("There are no orders!");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new Exception(ex.getMessage());
        }
        return count;
    }

    public static List<Order> getAllOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM orders";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet ids = ps.executeQuery();
            while (ids.next()) {
                int order_id = ids.getInt("order_id");
                int user_id = ids.getInt("userid");
                int length = ids.getInt("length");
                int width = ids.getInt("width");
                int height = ids.getInt("height");
                Boolean shipped = ids.getBoolean("shipped");
                Order order = new Order(order_id,user_id, length, width, height, shipped);
                order.setOrder_id(order_id);
                orders.add(order);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new Exception(ex.getMessage());
        }
        return orders;
    }

    static void shipOrder(int id) throws Exception {
        Order order = getOrderById(id);
        if (order == null) {
            throw new Exception("The order does not exist!");
        } else if ("true".equals(order.isShipped())) {
            throw new Exception("That order is shipped!");
        } else {
            try {
                Connection con = Connector.connection();
                String SQL = "UPDATE orders SET orderShipped = 'true' WHERE order_id = ?";
                PreparedStatement ps = con.prepareStatement(SQL);
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException | ClassNotFoundException ex) {
                throw new Exception(ex.getMessage());
            }
        }
    }

    private static Order getOrderById(int id) throws Exception {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM orders WHERE order_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet ids = ps.executeQuery();
            while (ids.next()) {
                int order_id = ids.getInt("order_id");
                int user_id = ids.getInt("user_id");
                int length = ids.getInt("length");
                int width = ids.getInt("width");
                int height = ids.getInt("height");
                Boolean shipped = ids.getBoolean("orderShipped");
                Order order = new Order(order_id, user_id, length, width, height, shipped);
                order.setOrder_id(id);
                return order;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new Exception(ex.getMessage());
        }
        return null;
    }
}
