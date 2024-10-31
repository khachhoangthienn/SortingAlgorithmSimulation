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
public class SelectionSort extends SortAlgorithm {
    public SelectionSort(ArrayList<JLabel> a, int speed, int delay, JLabel nos,ArrayList<JButton> button, boolean upSort,javax.swing.JPanel ct) {
        super(a, speed, delay, nos,button, upSort,ct);
    }

    public void SelectionSort() {
        int lowindex, lowkey;
        int n = getN();
        for (int i = 0; i <= n - 2; i++) {
            lowkey = getValue(i);
            lowindex = i;
            ColorExpect(i);
            MoveUp(i);
            try {
                Thread.sleep(super.getSpeed());
            } catch (Exception e) {
            }
            for (int j = i + 1; j <= n - 1; j++) {
                ColorSelected(j);
                MoveUp(j);
                if (j - 1 != lowindex) {
                    ColorNormal(j - 1);
                }
                boolean flag = false;
                if(getUpSort() && getValue(j) < lowkey) flag = true;
                else if(!getUpSort() && getValue(j)>lowkey) flag = true;
                
                if (flag) {

                    ColorExpect(j);

                    if (lowindex != i) {
                        MoveDown(lowindex);
                        ColorNormal(lowindex);
                    }

                    lowkey = getValue(j);
                    lowindex = j;
                } else {
                    MoveDown(j);
                }
                try {
                    Thread.sleep(super.getSpeed());
                } catch (Exception e) {
                }
            }
            if (n - 1 != lowindex) {
                ColorNormal(n - 1);
            }
            MoveDown(i);
            if (i != lowindex) {
                MoveDown(lowindex);
                Swap(i, lowindex);
                ColorNormal(lowindex);
            }
            ColorCompleted(i);
        }
        ColorCompleted(n - 1);
    }

    @Override
    public void run() {
        SelectionSort();
        mainButtonPage();
    }

}
