/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sort;

import Point.Point;
import java.awt.Color;
import java.awt.Font;
import java.time.Duration;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author tiend
 */
public class MergeSort extends SortAlgorithm {
    private Color ColorLeft = new Color(0, 141, 218);
    private Color ColorRight = new Color(172, 226, 225); 
    private int d;

    public MergeSort(ArrayList<JLabel> a, int speed, int delay, JLabel nos, javax.swing.JPanel mp,ArrayList<JButton> button, boolean upSort,javax.swing.JPanel ct) {
        super(a, speed, delay, nos, mp, button, upSort,ct);
        d = 0;
    }
    
    public void ColorLeft(JLabel i) {
        i.setBackground(ColorLeft);
    }
    public void ColorRight(JLabel i) {
        i.setBackground(ColorRight);
    }
    
    public void MoveSubDown(JLabel a) {
        int ex = a.getY() + d;
        while (a.getY() != ex) {
            try {
                a.setLocation(a.getX(), a.getY() + getDelay());
                Thread.sleep(getSpeed());
            } catch (InterruptedException e) {
            }
        }
    }

    public void MoveSubUp(JLabel a) {
        int ex = a.getY() - d;
        while (a.getY() != ex) {
            try {
                a.setLocation(a.getX(), a.getY() - getDelay());
                Thread.sleep(getSpeed());
            } catch (InterruptedException e) {
            }
        }
    }

    public void defaultMove() {
        setMove();
        d*=2;
    }

    public void setMove() {
        d = getLabel(0).getHeight() + 2;
        if(d%2!=0) d++;
    }

    public void findPosition(JLabel s, JLabel k) {
        int d = s.getX() < k.getX() ? 1 : -1;
        while (s.getX() != k.getX()) {
            try {
                s.setLocation(s.getX() + getDelay() * d, s.getY());
                Thread.sleep(getSpeed());
            } catch (InterruptedException ex) {
            }
        }
    }

    public void setFormatEmpty(JLabel a) {
        a.setBackground(Color.GRAY);
        a.setOpaque(true);
        a.setText("");
        Font f = new Font("Arial", Font.CENTER_BASELINE,(int)(25*getRateSize()));
        a.setFont(f);
        a.setHorizontalAlignment(JLabel.CENTER);
        a.setVerticalAlignment(JLabel.CENTER);
    }

    public void swapLocation(JLabel L1, JLabel L2) {
        Point temp = new Point(L1.getX(), L1.getY());
        L1.setLocation(L2.getX(), L2.getY());
        L2.setLocation(temp.getX(), temp.getY());
    }
    
    public void copyFormat(JLabel a,JLabel b) {
        a.setBackground(b.getBackground());
        a.setForeground(b.getForeground());
        a.setText(b.getText());
    }
    public int getSubValue(JLabel a) {
        return Integer.parseInt(a.getText());
    }

    public void merge(int l, int m, int r) {
        defaultMove();
        for (int i = l; i <= r; i++) ColorSelected(i);      

        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        ArrayList<JLabel> LA = new ArrayList<>(n1);
        ArrayList<JLabel> RA = new ArrayList<>(n2);
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i) {
            JLabel a = new JLabel();
            setFormatEmpty(a);
            a.setBounds(getLabel(l + i).getBounds());
            LA.add(a);
            getMp().add(a);
            MoveSubDown(getLabel(l + i));
            swapLocation(a,getLabel(l+i));
            copyFormat(a,getLabel(l+i));
            setFormatEmpty(getLabel(l+i));
            ColorLeft(LA.getLast());
        }
        for (int j = 0; j < n2; ++j) {
            JLabel a = new JLabel();
            setFormatEmpty(a);
            a.setBounds(getLabel(m + j + 1).getBounds());

            RA.add(a);
            getMp().add(a);
            MoveSubUp(getLabel(m + j + 1));
            swapLocation(a,getLabel(m+j+1));
            copyFormat(a,getLabel(m+j+1));
            setFormatEmpty(getLabel(m+j+1));
            ColorRight(RA.getLast());
        }
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0;
        // Initial index of merged subarry array 
        int k = l;
        setMove();
        
        //tren khuc nay da on
        //cha la cai o duoi
        while (i < n1 && j < n2) {
            boolean flag = false;
            if(getUpSort() && getSubValue(LA.get(i)) <= getSubValue(RA.get(j))) flag = true;
            else if(!getUpSort() && getSubValue(LA.get(i)) >= getSubValue(RA.get(j))) flag = true;
            if (flag) {
           
                swapLocation(LA.get(i),getLabel(k));
                copyFormat(getLabel(k),LA.get(i));
                setFormatEmpty(LA.get(i));
                MoveSubUp(getLabel(k));
                findPosition(getLabel(k),LA.get(i));
                MoveSubUp(getLabel(k));
                i++;
                
            } else {
                swapLocation(RA.get(j),getLabel(k));
                copyFormat(getLabel(k),RA.get(j));
                setFormatEmpty(RA.get(j));
                MoveSubDown(getLabel(k));
                findPosition(getLabel(k),RA.get(j));
                MoveSubDown(getLabel(k));
                j++;
            }
            ColorCompleted(k);
            k++;
        }
        //dad is dad
        while (i < n1) {
            swapLocation(LA.get(i),getLabel(k));
            copyFormat(getLabel(k),LA.get(i));
            setFormatEmpty(LA.get(i));
            MoveSubUp(getLabel(k));
            findPosition(getLabel(k), LA.get(i));
            MoveSubUp(getLabel(k));
            ColorCompleted(k);
            i++;
            k++;
        }
        while (j < n2) {
            swapLocation(RA.get(j),getLabel(k));
            copyFormat(getLabel(k),RA.get(j));
            setFormatEmpty(RA.get(j));
            MoveSubDown(getLabel(k));
            findPosition(getLabel(k), RA.get(j));
            MoveSubDown(getLabel(k));
            ColorCompleted(k);
            j++;
            k++;
        }
        for (JLabel s : LA) getMp().remove(s);
        for (JLabel s : RA) getMp().remove(s);
        LA.clear();
        RA.clear();
    }

    // Main function that sorts arr[l..r] using 
    // merge() 
    void MergeSort(int l, int r) {
        if (l < r) {
            // Find the middle point 
            int m = (l + r) / 2;

            // Sort first and second halves 
            int nextMove =(int)(70*getRateSize());
            moveArray(getLabel(l).getY() + nextMove, l, m);
            MergeSort(l, m);
            moveArray(getLabel(r).getY() + nextMove, m + 1, r);
            MergeSort(m + 1, r);
            merge(l, m, r);
            moveArray(getLabel(l).getY() - nextMove, l, r);
        }
        for(int i=l;i<=r;i++) ColorNormal(i);
    }

    public void run() {
        setMove(getLabel(0).getHeight() + 6);
        int initY = getLabel(0).getY();
        moveArray(60, 0, getN() - 1);
        MergeSort(0, getN() - 1);
        for(int i=0;i<getN();i++) ColorCompleted(i);
        moveArray(initY,0,getN()-1);
        mainButtonPage();
    }
}
