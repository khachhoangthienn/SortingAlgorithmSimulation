/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sort;

import Point.Point;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.time.Duration;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author tiend
 */
public class HeapSort extends SortAlgorithm {

    private int spaceNode;
    private ArrayList<Point> initPosition;
    private ArrayList<JLabel> line1;
    private ArrayList<JLabel> line2;

    public HeapSort(ArrayList<JLabel> a, int speed, int delay, JLabel nos, javax.swing.JPanel mp,ArrayList<JButton> button, boolean upSort,javax.swing.JPanel ct) {
        super(a, speed, delay, nos, mp,button,upSort,ct);
        line1 = new ArrayList<>();
        line2 = new ArrayList<>();
        initPosition = new ArrayList<>();
        spaceNode = 150;
    }

    public void PushDown(int first, int last) {
        int r = first;
        while (r <= (last - 1) / 2) {
            if(getUpSort()) {
               if (last == 2 * r + 1) {
                if (getValue(r) > getValue(last)) {
                    Swap(r, last);
                }
                r = last;
            } else if ((getValue(r) > getValue(2 * r + 1)) && (getValue(2 * r + 1) <= getValue(2 * r + 2))) {
                Swap(r, 2 * r + 1);
                r = 2 * r + 1;
            } else if ((getValue(r) > getValue(2 * r + 2)) && (getValue(2 * r + 2) < getValue(2 * r + 1))) {
                Swap(r, 2 * r + 2);
                r = 2 * r + 2;
            } else {
                r = last;
            } 
            } else {
                if (last == 2 * r + 1) {
                if (getValue(r) < getValue(last)) {
                    Swap(r, last);
                }
                r = last;
            } else if ((getValue(r) < getValue(2 * r + 1)) && (getValue(2 * r + 1) >= getValue(2 * r + 2))) {
                Swap(r, 2 * r + 1);
                r = 2 * r + 1;
            } else if ((getValue(r) < getValue(2 * r + 2)) && (getValue(2 * r + 2) > getValue(2 * r + 1))) {
                Swap(r, 2 * r + 2);
                r = 2 * r + 2;
            } else {
                r = last;
            }
            }
            
        }
    }

    public JLabel getDad(int childIndex) {
        return getLabel((childIndex - 1) / 2);
    }

    public void moveJLabeltoTree(JLabel lb, int x, int y) {
        MoveUp(getIndex(lb));
        Point p = new Point(lb.getX(),lb.getY());
        initPosition.add(p);
        int d = lb.getX() < x ? 1 : -1;
        while (lb.getX() != x) {
            try {
                lb.setLocation(lb.getX() + d * getDelay(), lb.getY());
                Thread.sleep(getSpeed());
            } catch (InterruptedException ex) {
            }
        }
        while (lb.getY() > y) {
            try {
                lb.setLocation(lb.getX(), lb.getY() - getDelay());
                Thread.sleep(getSpeed());
            } catch (InterruptedException ex) {
            }
        }
    }

    public void drawPointToPoint(JLabel dad, JLabel sub) {
        int d = dad.getX() > sub.getX() ? -1 : 1;
        int dadX = d == -1 ? dad.getX() : dad.getX() + dad.getWidth();
        int dadY = dad.getY() + dad.getHeight() / 2;
        int subX = sub.getX() + sub.getWidth() / 2;
        int subY = sub.getY();

        JLabel connectDad = new JLabel();
        JLabel connectSub = new JLabel();

        line1.add(connectDad);
        line2.add(connectSub);

        connectDad.setBounds(dadX, dadY, 0, 3);
        connectSub.setBounds(subX, dadY, 3, 0);

        connectDad.setBackground(Color.lightGray);
        connectSub.setBackground(Color.lightGray);
//
        getMp().add(connectDad);
        getMp().add(connectSub);
//
        connectDad.setOpaque(true);
        connectSub.setOpaque(true);
        Draw();
//
        int width = Math.abs(dadX - subX);
        int height = Math.abs(dadY - subY);
        while(connectDad.getWidth()!=width) {
            if(d==-1) {
                try {
                    connectDad.setBounds(connectDad.getX()+getDelay()*d, connectDad.getY(), connectDad.getWidth()-getDelay()*d, connectDad.getHeight());
                    if(Math.abs(connectDad.getWidth()-width)<=1) connectDad.setBounds(subX, connectDad.getY(), width, connectDad.getHeight());
                    Thread.sleep(getSpeed());
                } catch (InterruptedException ex) {}
            } else {
                try {
                    connectDad.setBounds(connectDad.getX(), connectDad.getY(), connectDad.getWidth()+getDelay()*d, connectDad.getHeight());
                    if(Math.abs(connectDad.getWidth()-width)<=1) connectDad.setBounds(connectDad.getX(), connectDad.getY(), width, connectDad.getHeight());
                    Thread.sleep(getSpeed());
                } catch (InterruptedException ex) {}
            }
        }
        
        while(connectSub.getHeight()!=height) {
            try {
                connectSub.setBounds(connectSub.getX(),connectSub.getY(),connectSub.getWidth(),connectSub.getHeight()+getDelay());
                if(Math.abs(connectSub.getHeight() - height)<=1) connectSub.setBounds(connectSub.getX(),connectSub.getY(), connectSub.getWidth(),height);
                Thread.sleep(getSpeed());
            } catch (InterruptedException ex) {}
        }
    }
    public void delLastBrand() {
        JLabel L1 = line1.getLast();
        JLabel L2 = line2.getLast();
        int d = L1.getX()<L2.getX()? -1:1; //trai == 1
        
        while(L1.getWidth()>0) {
            try {
                if(d==1) {
                    L1.setBounds(L1.getX()-getDelay(), L1.getY(), L1.getWidth()-getDelay(),L1.getHeight());
                    if(L1.getWidth()==1) L1.setBounds(L1.getX(), L1.getY(), 0, L1.getHeight());
                } else {
                    L1.setBounds(L1.getX(), L1.getY(), L1.getWidth()-getDelay(),L1.getHeight());
                    if(L1.getWidth()==1) L1.setBounds(L1.getX(), L1.getY(), 0, L1.getHeight());
                }
                Thread.sleep(getSpeed());
            } catch (InterruptedException ex) {}
        }
        while(L2.getHeight()>0) {
            try {
                L2.setBounds(L2.getX()-getDelay(), L2.getY(), L2.getWidth(), L2.getHeight()-getDelay());
                if(L2.getHeight()==1) L2.setBounds(L2.getX(),L2.getY(),L2.getWidth(),0);
                Thread.sleep(getSpeed());
            } catch (InterruptedException ex) {}
        }
        line1.removeLast();
        line2.removeLast();
    }
    
    public void moveInit(JLabel a) {
        Point p = initPosition.getFirst();
        while(a.getY()!=p.getY()) {
            a.setLocation(a.getX(), a.getY()+getDelay());
            if(a.getY()-p.getY()==1) a.setLocation(a .getX(), p.getY());
            try {
                Thread.sleep(getSpeed());
            }catch (Exception e) {}
        }
        int d = a.getX()>p.getX()? -1:1;
        while(a.getX()!=p.getX()) {
            a.setLocation(a.getX()+d*getDelay(), a.getY());
            try {
                Thread.sleep(getSpeed());
            }catch (Exception e) {}
        }
        initPosition.remove(p);
        MoveDown(getIndex(a));
    }
    public void buildTree() {
        setMove(getLabel(0).getHeight() + 6);
        Point p = new Point(getLabel(0).getX(),getLabel(0).getY());
        moveJLabeltoTree(getLabel(0), getMidX(), 0);
        for (int i = 1; i <= getN() - 1; i++) {
            Point pi = new Point(getLabel(i).getX(),getLabel(i).getY());
            int LoR = i % 2 == 0 ? 1 : -1;
            JLabel dad = getDad(i);
            int indexDad = (i - 1) / 2;
            if (indexDad == 0) {
                moveJLabeltoTree(getLabel(i), dad.getX() + 256 * LoR, dad.getY() + 140);
            } else if (indexDad == 1 || indexDad == 2) {
                moveJLabeltoTree(getLabel(i), dad.getX() + 130 * LoR, dad.getY() + 116);
            } else {
                moveJLabeltoTree(getLabel(i), dad.getX() + 64 * LoR, dad.getY() + 130);
            }
            drawPointToPoint(dad, getLabel(i));
        }

    }

    public void HeapSort() {
        ArrayList<JLabel> SubArray = new ArrayList<JLabel>();
        for (int u = 0; u <= getN() - 1; u++) {// luu vet
            JLabel label = new JLabel();
            label.setBounds(getLabel(u).getBounds());
            label.setOpaque(true);
            label.setBackground(Color.gray);
            getMp().add(label);
            SubArray.add(label);
        }
        for (int i = (getN() - 2) / 2; i >= 0; i--) {
            PushDown(i, getN() - 1);
        }
        for (int i = getN() - 1; i >= 2; i--) {
            Swap(0, i);
            ColorCompleted(i);
            getMp().remove(SubArray.getLast());
            SubArray.removeLast();
            delLastBrand();
            moveInit(getLabel(i));
            PushDown(0, i - 1);
        }
        Swap(0, 1);
        
        ColorCompleted(1);
        getMp().remove(SubArray.getLast());
        SubArray.removeLast();    
        delLastBrand();      
        moveInit(getLabel(1));
        
       
        ColorCompleted(0);
        getMp().remove(SubArray.getLast());
        SubArray.removeLast();         
        moveInit(getLabel(0));
    }

    public void AnimationSwap(JLabel dad, JLabel sub) {
        ColorExpect(getIndex(dad));
        ColorExpect(getIndex(sub));
        Point psub = new Point(sub);
        Point pdad = new Point(dad);
        int d = pdad.getX() > psub.getX() ? -1 : 1;
        while (dad.getY() != psub.getY()) {
            dad.setLocation(dad.getX(), dad.getY() + getDelay());
            if (dad.getY() > psub.getY()) {
                dad.setLocation(dad.getX(), psub.getY());
            }
            try {
                Thread.sleep(getSpeed());
            } catch (InterruptedException ex) {
            }
        }
        while (dad.getX() != psub.getX()) {
            dad.setLocation(dad.getX() + getDelay() * d, dad.getY());
            if (Math.abs(dad.getX() - sub.getX()) < 60) {
                sub.setLocation(sub.getX() + getDelay() * d, sub.getY());
            }
            try {
                Thread.sleep(getSpeed());
            } catch (InterruptedException ex) {
            }
        }
        while (sub.getY() != pdad.getY()) {
            sub.setLocation(sub.getX(), sub.getY() - getDelay());
            try {
                Thread.sleep(getSpeed());
            } catch (InterruptedException ex) {
            }
        }
        while (sub.getX() != pdad.getX()) {
            sub.setLocation(sub.getX() - getDelay() * d, sub.getY());
            try {
                Thread.sleep(getSpeed());
            } catch (InterruptedException ex) {
            }
        }
        ColorNormal(getIndex(dad));
        ColorNormal(getIndex(sub));
    }


    public void run() {
        int initY = getLabel(0).getY();
        int nextMove = getMp().getHeight()-getLabel(0).getHeight()*2;
        moveArray(nextMove, 0, getN() - 1);
        ArrayList<JLabel> SubArray = new ArrayList<JLabel>();
        for (int u = 0; u <= getN() - 1; u++) {// luu vet
            JLabel label = new JLabel();
            label.setBounds(getLabel(u).getBounds());
            label.setOpaque(true);
            label.setBackground(Color.gray);
            getMp().add(label);
            SubArray.add(label);
        }
        buildTree();
        HeapSort();
        
        
        for (JLabel label : SubArray) {  //xoa vet
            getMp().remove(label);
        }
        initPosition.clear();
        moveArray(initY,0,getN()-1);
        mainButtonPage();
    }
    

}
