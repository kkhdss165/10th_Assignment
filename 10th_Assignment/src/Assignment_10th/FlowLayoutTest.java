package Assignment_10th;

import java.awt.*;
import javax.swing.*;
 
public class FlowLayoutTest extends JFrame {
       public FlowLayoutTest() {
 
             setTitle("FlowLayoutTest");
             setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             JPanel panel;
             
             panel = new JPanel();
             panel.setLayout(new FlowLayout(FlowLayout.CENTER));
             
             panel.add(new JButton("Button1"));
             panel.add(new JButton("Button2"));
             panel.add(new JButton("Button3"));
             panel.add(new JButton("B4"));
             panel.add(new JButton("Long Button5"));
             add(panel);
 
             pack();
             setVisible(true);
       }
       
       public static void main(String[] ar) {
    	   new FlowLayoutTest();
       }
}