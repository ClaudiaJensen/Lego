/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import FunctionLayer.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author claudia
 */
public class PlaceOrder extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException
    {
        int userId = ((User) request.getSession().getAttribute("user")).getId();
        int orderId = ((Order) request.getSession()).getOrder_id();
        int length = (Integer) request.getSession().getAttribute("length");
        int width = (Integer) request.getSession().getAttribute("width");
        int height = (Integer) request.getSession().getAttribute("height");
        Order order = new Order(orderId, userId, length, width, height, false);
        LogicFacade.placeOrder(order);
        
        User user = (User) request.getSession().getAttribute("user");

        return "customerpage";
    }
    
}
