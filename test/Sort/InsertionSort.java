/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sort;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author tiend
 */
public class InsertionSort extends SortAlgorithm {

    public InsertionSort(ArrayList<JLabel> a, int speed, int delay, JLabel nos,ArrayList<JButton> button, boolean upSort,javax.swing.JPanel ct) {
        super(a, speed, delay, nos,button,upSort,ct);
    }
    public void InsertionSort() {
        int n = super.getN();
        int j;
        ColorCompleted(0);
        try {
                Thread.sleep(getSpeed());
            } catch (Exception e) {}
        for(int i=1;i<=n-1;i++) {
            j=i;
            ColorExpect(i);
            try {
                Thread.sleep(getSpeed());
            } catch (Exception e) {}
            
            boolean flag = false;
            if(getUpSort() && getValue(j)<getValue(j-1)) flag = true;
            else if (!getUpSort() && getValue(j)>getValue(j-1)) flag = true;
            
            while ((j>0)&& flag) {
                Swap(j,j-1);
                j--;
                try {
                if(getUpSort() && getValue(j)<getValue(j-1)) flag = true;
                else if (!getUpSort() && getValue(j)>getValue(j-1)) flag = true;
                else flag = false;
                Thread.sleep(getSpeed());
            } catch (Exception e) {}
            }
            ColorCompleted(j);
        }
    }
    @Override
    public void run() {
        InsertionSort();
        mainButtonPage();
    }
    
}
