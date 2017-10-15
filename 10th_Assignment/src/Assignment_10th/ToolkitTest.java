package Assignment_10th;

import java.awt.*;

import javax.swing.*;
public class ToolkitTest extends JFrame{
	public ToolkitTest() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setSize(300, 200);
		setLocation(screenSize.width / 2, screenSize.height / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ToolkitTest");
		Image img = kit.getImage("dog.png");
		setIconImage(img);
		setLayout(new FlowLayout());
		JButton button = new JButton("Button");
		this.add(button);
		setVisible(true);
	}
	public static void main(String[] ar) {
		new ToolkitTest();
	}
}