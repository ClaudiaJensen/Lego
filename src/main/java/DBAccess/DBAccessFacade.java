/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import FunctionLayer.User;
import java.util.List;

/**
 *
 * @author claudia
 */
public class DBAccessFacade
{
    public static User SignIn(String email, String password) throws LoginSampleException {

        return UserMapper.login(email, password);
    }

    public static User user(User user) throws LoginSampleException {

        UserMapper.createUser(user);
        return user;
    }

    public static void createOrder(Order order) throws Exception {
        OrderMapper.createOrder(order);
    }

    public static int getOrderCount(int id) throws Exception {
        return OrderMapper.countOrders(id);
    }

    public static List<Order> getAllOrdersByUser(int id) throws Exception {
        return OrderMapper.getAllOrdersbyUser(id);
    }

    public static int getAllOrderCount() throws Exception {
        return OrderMapper.countAllOrders();
    }

    public static List<Order> getAllOrders() throws Exception {
        return OrderMapper.getAllOrders();
    }

    public static void sendOrder(int id) throws Exception {
        OrderMapper.shipOrder(id);
    }
}
