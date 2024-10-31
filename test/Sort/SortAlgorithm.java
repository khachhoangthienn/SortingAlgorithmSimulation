/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sort;

import Point.Point;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author tiend
 */
public class SortAlgorithm extends Thread {

    private ArrayList<JLabel> a;
    private int n; // so luong pt
    private int speed; // sleep
    private int space; //space y khi hoan doi
    private int selectedspace; //y khi duoc chon
    private int delay; //so buoc di chuyen
    private JLabel nos; //number of swap
    private Color normal = Color.white; 
    private Color selected = new Color(255, 243, 199);
    private Color expect = new Color(252, 129, 158);
    private Color completed = new Color(247, 65, 143);
    private int NumberOfSwap;
    private javax.swing.JPanel mp;
    private javax.swing.JPanel ct;
    private ArrayList<JButton> button;
    private boolean upSort;
    private float rateSize;

    public SortAlgorithm() {
        this.a = null;
        this.n = 0;
        this.speed = 0;
        this.space = 0;
        this.delay = 0;
        this.selectedspace = 0;
        this.nos = null; //bien Label cap nhat
        this.NumberOfSwap = 0; //so lan Swap
        this.upSort = true;
        this.button = null;
        this.rateSize = (float) 0.0;
    }
    public boolean getUpSort() {
        return upSort;
    }
    public SortAlgorithm(ArrayList<JLabel> a, int speed, int delay, JLabel nos, javax.swing.JPanel mp,ArrayList<JButton> button, boolean upSort,javax.swing.JPanel ct) {
        this.a = a;
        this.n = a.size();
        this.speed = speed;
        this.space = a.getFirst().getHeight()+6;
        if(this.space%2==1) this.space++;
        this.delay = delay;
        this.selectedspace = space / 3;
        this.nos = nos;
        NumberOfSwap = 0;
        this.mp = mp;
        this.button = button;
        this.upSort = upSort;
        this.ct = ct;
        this.rateSize = (float)(1.0*this.ct.getWidth()/1375);
    }

    public SortAlgorithm(ArrayList<JLabel> a, int speed, int delay, JLabel nos,ArrayList<JButton> button,boolean upSort,javax.swing.JPanel ct) {
        this.a = a;
        this.n = a.size();
        this.speed = speed;
        this.space = a.getFirst().getHeight()+6;
        if(this.space%2!=0) this.space++;
        this.delay = delay;
        this.selectedspace = space / 3;
        this.nos = nos;
        NumberOfSwap = 0;
        this.mp = new javax.swing.JPanel();
        this.button = button;
        this.upSort = upSort;
        this.ct = ct;
        this.rateSize = (float)(1.0*this.ct.getWidth()/1375);
    }

    public void mainButtonPage() {
        ct.removeAll();
        ct.add(button.get(0));
        ct.add(button.get(1));
        ct.repaint();
    }
    public int getDelay() {
        return delay;
    }
    public float getRateSize() {
        return rateSize;
    }
    public ArrayList<JLabel> getLabelList() {
        return a;
    }

    public javax.swing.JPanel getMp() {
        return mp;
    }

    public JLabel getLabel(int i) {
        return a.get(i);
    }
    public int getIndex(JLabel label) {
    return a.indexOf(label);
}
    public void Draw() {
        mp.revalidate();
        mp.repaint();
    }

    public void setNos() {
        nos.setText(String.valueOf(NumberOfSwap));
    }

    public int getN() {
        return n;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public int getValue(int i) {// lay gia tri cua label
        return Integer.parseInt(a.get(i).getText());
    }
    public void setValue(int i,int value) {
        a.get(i).setText(value+"");
    }

    public void AnimationSwap(JLabel label1, JLabel label2) {

        Point start = new Point(label1);
        Point end = new Point(label2);
        int dy1 = end.getX() - start.getX() > 0 ? -1 : 1;
        int dy2 = -dy1;
        int dx1 = -dy1;
        int dx2 = -dx1;
        while (label1.getY() != end.getY() + space * dy1) {
            label1.setLocation(label1.getX(), label1.getY() + dy1 * delay);
            label2.setLocation(label2.getX(), label2.getY() + dy2 * delay);
            try {
                Thread.sleep(speed);
            } catch (Exception e) {
            }
        }
        while (label1.getX() != end.getX()) {
            label1.setLocation(label1.getX() + dx1 * delay, label1.getY());
            label2.setLocation(label2.getX() + dx2 * delay, label2.getY());
            try {
                Thread.sleep(speed);
            } catch (Exception e) {
            }
        }
        while (label1.getX() != end.getX() || label1.getY() != end.getY()) {
            label1.setLocation(label1.getX(), label1.getY() - dy1 * delay);
            label2.setLocation(label2.getX(), label2.getY() - dy2 * delay);
            try {
                Thread.sleep(speed);
            } catch (Exception e) {
            }
        }
    }

    public void Swap(int i, int j) {
        if(i==j) return;
        AnimationSwap(a.get(i), a.get(j));
        NumberOfSwap++;
        setNos();
        Collections.swap(a, i, j);
    }

    public void ColorNormal(int i) {
        a.get(i).setBackground(normal);
    }

    public void ColorSelected(int i) {
        a.get(i).setBackground(selected);
    }

    public void ColorCompleted(int i) {
        a.get(i).setBackground(completed);
    }

    public void ColorExpect(int i) {
        a.get(i).setBackground(expect);
    }

    public void MoveUp(int i) {
        int y = a.get(i).getY();
        int x = a.get(i).getX();
        while (a.get(i).getY() > y - selectedspace) {
            a.get(i).setLocation(x, a.get(i).getY() - 1);
            try {
                Thread.sleep(speed * 2);
            } catch (Exception e) {
            }
        }
    }
    public void setMove(int i) {
        selectedspace = i;
    }

    public void setSelectedSpace(int i) {
        selectedspace = i;
    }

    public void MoveDown(int i) {
        int y = a.get(i).getY();
        int x = a.get(i).getX();
        while (a.get(i).getY() < y + selectedspace) {
            a.get(i).setLocation(x, a.get(i).getY() + 1);
            try {
                Thread.sleep(speed * 2);
            } catch (Exception e) {
            }
        }
    }

    public void fontWhile(JLabel l) { //font mp
        Font f = new Font("Arial", Font.CENTER_BASELINE, 25);
        l.setFont(f);
        l.setOpaque(true);
        Color c = Color.white;
        l.setBackground(c);
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setVerticalAlignment(JLabel.CENTER);

    }

    public void moveArray(int y, int i, int j) {
        int p = getLabel(i).getY() < y ? 1 : -1;
        while (getLabel(i).getY() != y) {
            for (int u = i; u <= j; u++) {
                getLabel(u).setLocation(getLabel(u).getX(), getLabel(u).getY() + getDelay() * p);
                if(Math.abs(getLabel(u).getY()-y)==1) getLabel(u).setLocation(getLabel(u).getX(), y);
            }
            try {
                Thread.sleep(getSpeed());
            } catch (InterruptedException ex) {
            }
        }
    }

    public void setSmallSize() {
        int height = getLabel(0).getHeight();
        while (height != 25) {
            for (JLabel a : getLabelList()) {
                a.setBounds(a.getX(), a.getY(), a.getWidth(), height - 1);
            }
            height--;
            try {
                Thread.sleep((getSpeed()));
            } catch (InterruptedException ex) {
            }
        }
        for (JLabel a : getLabelList()) {
            a.setFont(new Font("Arial", Font.BOLD, 20));
        }
    }

    public void setNormalSize() {
        int height = getLabel(0).getHeight();
        while (height != (int)(rateSize*64)) {
            for (JLabel a : getLabelList()) {
                a.setBounds(a.getX(), a.getY(), a.getWidth(), height + 1);
            }
            height++;
            try {
                Thread.sleep((getSpeed()));
            } catch (InterruptedException ex) {
            }
        }
        for (JLabel a : getLabelList()) {
            a.setFont(new Font("Arial", Font.BOLD, (int)(rateSize*25)));
        }
    }
public int getMidX() {
        return mp.getWidth()/2;
    }
    @Override
    public void run() {
    }
}
