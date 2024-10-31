/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sort;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author tiend
 */
public class QuickSort extends SortAlgorithm {

    private Color ColorPivot = new Color(144, 210, 109);
    private Color ColorLeft = new Color(0, 141, 218);
    private Color ColorRight = new Color(172, 226, 225); 
    private Color CenterPivot = new Color(76, 205, 153);
    private int Wheel = 40;

    public QuickSort(ArrayList<JLabel> a, int speed, int delay, JLabel nos, javax.swing.JPanel mp,ArrayList<JButton> button, boolean upSort,javax.swing.JPanel ct) {
        super(a, speed, delay, nos, mp,button,upSort,ct);
    }

    public void setFontNotice(JLabel Notice, int Xcenter, int Ycenter) {
        Notice.setOpaque(false);
        Notice.setFont(new Font("Arial", Font.BOLD, 14));
        Notice.setForeground(ColorPivot);
        Notice.setBounds(Xcenter, Ycenter, 100, 30);

    }

    public void ColorPivot(int i) {
        getLabel(i).setBackground(ColorPivot);
    }
    public void ColorLeft(int i) {
        getLabel(i).setBackground(ColorLeft);
    }
    public void ColorRight(int i) {
        getLabel(i).setBackground(ColorRight);
    }

    public int FindPivot(int i, int j) {
        int firstkey;
        int k;
        k = i + 1;
        firstkey = getValue(i);
        while (k <= j && getValue(k) == firstkey) {
            k++;
            try {
                Thread.sleep(getSpeed());
            } catch (Exception e) {
            }
        }
        if (k > j) {
            for (int x = i; x <= j; x++) {
                ColorCompleted(x);
            }
            return -1;
        }
        if (getValue(k) > firstkey) {
            ColorPivot(k);
            ColorNormal(i);
            return k;
        } else {
            ColorPivot(i);
            ColorNormal(k);
            }
            return i;       
        }

    public int Partition(int i, int j, int pivot) {//loi o ham` nay`
        
        int L, R;
        L = i;
        R = j;

        while (L <= R) {
            ColorLeft(L);
            ColorRight(R);
           if(L!=R)  {
               MoveUp(L);
               MoveUp(R);
           }
           if(getUpSort()) {
               while (getValue(L) < pivot) {
                L++;
                
            if(L!=R) MoveUp(L);
            MoveDown(L - 1); 
                ColorLeft(L);
                try {
                    Thread.sleep(getSpeed()*30);
                } catch (Exception e) {
                }
            }
            while (getValue(R) >= pivot) {
                R--;
                ColorRight(R);
              if(L!=R)  MoveUp(R);
                MoveDown(R + 1);
                try {
                    Thread.sleep(getSpeed()*50);
                } catch (Exception e) {
                }
            }
           }  else {
               while (getValue(L) >= pivot) {
                L++;
            if(L!=R) MoveUp(L);
            
            MoveDown(L - 1); 
                ColorLeft(L);
                try {
                    Thread.sleep(getSpeed()*30);
                } catch (Exception e) {
                }
            }
            while (getValue(R) < pivot) {
                    try {
                        R--;
                        ColorRight(R);
                        if (L != R) {
                            MoveUp(R);
                        }

                        MoveDown(R + 1);
                        Thread.sleep(getSpeed() * 50);
                    } catch (Exception e) {
                    }
                }
            }
           
            
            if(L!=R) {
            if(L<R) MoveDown(L);
                MoveDown(R);
            
            if (L < R) {
                ColorExpect(L);
                ColorExpect(R);
                Swap(L, R);
                ColorRight(R);
                ColorLeft(L);
                }
            }
        }
        getLabel(L).setBackground(CenterPivot);
        return L;
    }
    public void QuickSort(int i, int j) {
        JLabel NoticePivot = new JLabel();
        //JLabel line = new JLabel();
        for (int u = i; u <= j; u++) {
            ColorNormal(u);
        }
        int pivot;
        int pivotindex =-1, k=-1;
        try {
        pivotindex = FindPivot(i, j);
        }catch (Exception e) {System.out.println("Find pivot error");}
        if (pivotindex != -1) {
            //
            NoticePivot = new JLabel("P = " + getValue(pivotindex));
            int Xcenter = (getLabel(i).getX() + getLabel(j).getX()) / 2;
            int Ycenter = getLabel(i).getY() - 23;
            setFontNotice(NoticePivot, Xcenter, Ycenter);
            getMp().add(NoticePivot);
            Draw();
            //
            pivot = getValue(pivotindex);
            try {
            k = Partition(i, j, pivot);
            
//            line.setBounds(getLabel(k).getX()-7,0,1,1000);
//            line.setOpaque(true);
//            line.setBackground(Color.white);
//            getMp().add(line);
            
            
            
            }catch(Exception e) {System.out.println("Partition error");}
            ArrayList<JLabel> SubArray = new ArrayList<JLabel>();
            for (int u = 0; u <= j; u++) {// luu vet
                JLabel label = new JLabel();
                label.setBounds(getLabel(u).getBounds());
                label.setOpaque(true);
                label.setBackground(Color.gray);
                getMp().add(label);
                SubArray.add(label);
            }
            //Draw();
            moveArray(getLabel(i).getY() + Wheel, i, k - 1);
            QuickSort(i, k - 1);
            moveArray(getLabel(j).getY() + Wheel, k, j);
            QuickSort(k, j);
            moveArray(getLabel(i).getY() - Wheel, i, k-1);
            moveArray(getLabel(j).getY() - Wheel, k, j);
            for (JLabel label : SubArray) {  //xoa vet
                getMp().remove(label);
            }
        }
        getMp().remove(NoticePivot);
//        getMp().remove(line);
    }
    
    public void run() {
        try {
        setSelectedSpace(10);
        int initY = getLabel(0).getY();
        moveArray(28, 0, getN() - 1);
        setSpace(30);
        setSmallSize();
        QuickSort(0, getN() - 1);
        Draw();
        setNormalSize();
        moveArray(initY,0,getN()-1);
        } catch (Exception e) {
            System.out.println("Co Loi!");
        }
        mainButtonPage();
    }
}
