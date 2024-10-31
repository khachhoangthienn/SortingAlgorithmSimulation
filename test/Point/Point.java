/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Point;

import javax.swing.JLabel;

/**
 *
 * @author tiend
 */
public class Point {
    private int x;
    private int y;
    public Point(int x,int y) {
        this.x = x;
        this.y = y;
    }
    public Point() {
        x = 0;
        y = 0;
    }
    public Point(JLabel l) {
        x = l.getX();
        y = l.getY();
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
