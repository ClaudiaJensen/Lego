/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.HashMap;

/**
 *
 * @author claudia
 */
public class HouseLogic
{
    private static HouseLogic singleton;
    
    public static HouseLogic math(){
        if(singleton == null){
            singleton = new HouseLogic();
        }
        return singleton;
    }

    HashMap<String, Integer> counter = new HashMap();

    private void counterRestart() {
        counter.clear();
        counter.put("2x4", 0);
        counter.put("2x2", 0);
        counter.put("1x2", 0);

    }

    public HashMap<String, Integer> makeHouse(int length, int width, int height) {
        counterRestart();
        for (int i = 1; i <= height; i++) {
            if (i % 2 == 0) {
                totalWidth(width);
                totalLength(length - 4);
            } else {
                totalLength(length);
                totalWidth(width - 4);
            }
            
        }
        identicalOpposite();
        return counter;
    }

    private void totalLength(int length) {
        for (int i = 0; i < length; i++) {
            if (i + 4 <= length) {
                int newlength = counter.get("2x4");
                counter.put("2x4", ++newlength);
                i = i + 4;
            } else if (i + 2 <= length) {
                int newlength = counter.get("2x2");
                counter.put("2x2", ++newlength);
            } else if (i + 1 <= length) {
                int newlength = counter.get("1x2");
                counter.put("1x2", ++newlength);
            }
        }
    }

    private void totalWidth(int width) {
        for (int i = 0; i < width; i++) {
            if (i + 4 <= width) {
                int newlength = counter.get("2x4");
                counter.put("2x4", ++newlength);
                i = i + 4;
            } else if (i + 2 <= width) {
                int newlength = counter.get("2x2");
                counter.put("2x2", ++newlength);
            } else if (i + 1 <= width) {
                int newlength = counter.get("1x2");
                counter.put("1x2", ++newlength);
            }
        }
    }

    private void identicalOpposite() {
        int twobyfour = counter.get("2x4");
        int twobytwo = counter.get("2x2");
        int onebytwo = counter.get("1x2");
        counter.put("2x4", twobyfour * 2);
        counter.put("2x2", twobytwo * 2);
        counter.put("1x2", onebytwo * 2);
    }
}