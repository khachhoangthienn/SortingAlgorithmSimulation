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
public class BubbleSort extends SortAlgorithm {

    public BubbleSort(ArrayList<JLabel> a, int speed,int delay, JLabel nos,ArrayList<JButton> button, boolean upSort,javax.swing.JPanel ct) {
        super(a, speed, delay, nos,button,upSort,ct);
    }
    public void BubbleSort() {
        int n = getN();
        for(int i=0;i<n-1;i++) {
            if(i!=0) ColorCompleted(i-1);
            try {
                Thread.sleep(super.getSpeed());
            } catch (Exception e) {}
            for(int j=n-1;j>=i+1;j--) {
                ColorSelected(j);
                ColorSelected(j-1);
                MoveUp(j);
                MoveUp(j-1);
                boolean flag = false;
                if(getUpSort() && getValue(j)< getValue(j-1)) flag = true;
                else if(!getUpSort() && getValue(j)>getValue(j-1)) flag = true;
                if(flag) {
                    ColorExpect(j);
                    ColorExpect(j-1);
                    MoveDown(j);
                    MoveDown(j-1); 
                    Swap(j,j-1);
                    ColorNormal(j);
                    ColorNormal(j-1);
                }else {
                    ColorNormal(j);
                    ColorNormal(j-1);
                    MoveDown(j);
                    MoveDown(j-1);
                }
            }
        }
        ColorCompleted(n-2);
        ColorCompleted(n-1);
    }
    @Override
    public void run() {
        BubbleSort();
        mainButtonPage();
        
    }
    
}
