/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 *
 * @author claudia
 */
public class Order
{
    private int order_id;
    private int user_id;
    private int length, width, heigth;
    boolean shipped;

    public Order(int order_id, int user_id, int length, int width, int heigth, boolean shipped)
    {
        this.order_id = order_id;
        this.user_id = user_id;
        this.length = length;
        this.width = width;
        this.heigth = heigth;
        this.shipped = shipped;
    }

    public int getOrder_id()
    {
        return order_id;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public int getLength()
    {
        return length;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeigth()
    {
        return heigth;
    }

    public boolean isShipped()
    {
        return shipped;
    }

    
    
}
