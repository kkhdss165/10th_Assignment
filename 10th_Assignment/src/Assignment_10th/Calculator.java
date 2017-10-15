package Assignment_10th;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Cal extends JFrame implements ActionListener
{
	
	private JTextField txt;
	private JPanel panel;
	private String[] labels = {
			"Backspace", "", "", "CE", "C",
			"7", "8", "9", "/", "sqrt",
			"4", "5", "6", "*", "%",
			"1", "2", "3", "-", "1/x",
			"0", "+/-", ".", "+", "=",
	};	
	String N1 ="" , N2 ="";//�� ����
	String SignN1 ="", SignN2 ="", Exp="";//�����ڿ� ������ ��ȣ�� ������ ����
	
	public Cal()
	{
		setTitle("A calculator");
		
		txt = new JTextField(20);
		txt.setHorizontalAlignment(JTextField.RIGHT);
		add(txt, BorderLayout.NORTH);
		panel = new JPanel();
		panel.setLayout(new GridLayout(5, 5));
		add(panel, BorderLayout.CENTER);
		for (int i = 0; i < labels.length; i++) {
			JButton btn = new JButton(labels[i]);
			btn.addActionListener(this);
			btn.setPreferredSize(new Dimension(110, 30));
			panel.add(btn);
		}
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String Command = e.getActionCommand();
		System.out.println("SignN1 = "+ SignN1 + " N1 = " + N1 +" SignN2 = " + SignN2 +" N2 ="+ N2 +" Exp =" + Exp + " Command = " + Command);
		/////////////////////////////////////
		if (txt.getText().length() == 0 && (Command == "*" || Command == "/" || Command == "="))
			return;
		if (Command == "")
		{
			return;
		}
		else if (Command == "C")
		{
			N1=""; SignN1="";
			N2=""; SignN2=""; Exp="";
			txt.setText("");
			return;
		}
		else if (Command == "CE" || Command =="Backspace")
		{
			if (N2.length()!=0)
			{
				String temp="";
				for(int i=0;i<N2.length()-1;i++)
					temp+=N2.charAt(i);
				N2=temp;
			}
			else if (SignN2.length()!=0)
			{
				String temp="";
				for(int i=0;i<SignN2.length()-1;i++)
					temp+=SignN2.charAt(i);
				SignN2=temp;
			}
			else if (Exp.length()!=0)
			{
				Exp="";
			}
			else if (N1.length()!=0)
			{
				String temp="";
				for(int i=0;i<N1.length()-1;i++)
					temp+=N1.charAt(i);
				N1=temp;
			}
			else if (SignN1.length()!=0)
			{
				String temp="";
				for(int i=0;i<SignN1.length()-1;i++)
					temp+=SignN1.charAt(i);
				SignN1=temp;
			}
			else return;
			
			String temp="";
			for(int i=0;i<txt.getText().length()-1;i++)
				temp+=txt.getText().charAt(i);
			txt.setText(temp);
			return;
		}
		else if (Command == "=")
		{
			if(N2.length()!=0)
			{
				EXP();
			}
			return;
		}
		else if (Command == "+" || Command == "-")
		{
			if(N2.length()!=0)
			{
				EXP();	Exp = Command;
			}
			else if (SignN1.length() == 0)
				SignN1 = Command;
			else if (Exp.length() == 0)
			{
				Exp = Command;
			}
			else if (SignN2.length() == 0)
			{
				SignN2 = Command;
			}
			txt.setText(txt.getText()+Command);
			return;
		}
		else if (Command == "sqrt" || Command == "1/x")
		{
			if(N2.length()!=0)
			{
				EXP();
				//System.out.println("!!!!!!!");
			}
			else if(N1.length()!=0)
			{
				//System.out.println("!!!!!!!");
			}
			else return;
			switch(Command)
			{
			case"sqrt": if (SignN1=="-")return; else{ N1 = "0"+Math.sqrt(Double.parseDouble(N1));};break;
			case"1/x":	N1 = ""+(1.0/Double.parseDouble(N1)); break;
			default:return;
			}
			
			txt.setText(SignN1+N1);
			
			return;
		}
		else if (Command == "*" || Command == "/"||Command == "%")
		{
			if(N2.length()!=0)
			{
				EXP();	Exp = Command;
			}
			else if (SignN1.length() == 0)
				return;
			else if (Exp.length() == 0)
			{
				Exp = Command;
			}
			else if (SignN2.length() == 0)
			{
				return;
			}
			txt.setText(txt.getText()+Command);
			return;
		}
		else if (Command == "+/-")
		{
			if(N2.length()!=0 || SignN2.length()!=0)
			{
				if(SignN2=="-")
					SignN2="+";
				else
					SignN2="-";
			}
			else if(Exp.length()!=0)
			{
				SignN2="+";
			}
			else if(N1.length()!=0 || SignN1.length()!=0)
			{
				if(SignN1=="-")
					SignN1="+";
				else
					SignN1="-";

			}
			else if(N1.length()==0)
			{
				SignN1="+";
			}
			else return;
			txt.setText(SignN1 + N1 + Exp + SignN2 + N2 );
			return;
		}
		else if (Command == ".")
		{
			if (Exp == "")
			{
				if (N1.length()==0)
				{
					N1 = "0.";
					txt.setText(txt.getText()+"0.");
					return;
				}
				for(int i = 0;i<N1.length();i++)
				{
					if(N1.charAt(i) == '.')
						return;
				}
				N1 += Command;
				txt.setText(txt.getText()+Command);
				return;
			}
			else
			{
				if (N2.length()==0)
				{
					N2 = "0.";
					txt.setText(txt.getText()+"0.");
				}
				for(int i = 0;i<N2.length();i++)
				{
					if(N2.charAt(i) == '.')
						return;
				}
				N2 += Command;
				txt.setText(txt.getText()+Command);
				return;
			}
		}
		else//����
		{
			//������ 0�ΰ��� ���� �Ͽ���
			if(Exp == "/" && N2 == "" && Command == "0")
				return;
			if (Exp == "")
			{
				if (SignN1.length()==0)
				{
					SignN1 = "+";					
				}
				N1 += Command;
				
				if (N1.length()!=1&&N1.charAt(0)=='0'&&N1.charAt(0)!='.')
				{
					String temp="";
					for(int i=1;i<N1.length();i++)
						temp+=N1.charAt(i);
					N1=temp;
					if(SignN1=="-")
					{
						txt.setText("-"+N1);
					}
					else
					{
						txt.setText(N1);
					}
					return;
				}
					
			}
			else
			{
				if (SignN2.length()==0)
				{
					SignN2 = "+";
				}
				N2 += Command;
			}
		}
		/////////////////////////////////////
		
		
		txt.setText(txt.getText()+Command);
	}
	String EXPResult()
	{
		String Rt;
		double dA = Double.parseDouble(N1);
		double dB = Double.parseDouble(N2);
		
		if (SignN1 =="-")
			dA = -1 * dA;
		if (SignN2 =="-")
			dB = -1 * dB;
		
		switch(Exp)
		{
		case"+": Rt = Double.toString(dA + dB);	break;
		case"-": Rt = Double.toString(dA - dB); break;
		case"*": Rt = Double.toString(dA * dB); break;
		case"/": Rt = Double.toString(dA / dB); break;
		case"%": Rt = Double.toString(dA % dB); break;
		default: return "ERROR";
		}
		if (Rt.charAt(Rt.length()-2)=='.'&&Rt.charAt(Rt.length()-1)=='0')
		{
			String newRt = "";
			for(int i = 0; i< Rt.length()-2 ; i++)
				newRt += Rt.charAt(i);
			return newRt;
		}
		
		return Rt;
	}
	void EXP()
	{
		String tp = EXPResult();
		if (tp.charAt(0) == '-')
		{
			N1 = tp.replace("-","");	SignN1 ="-";
		}
		else
		{
			N1 = tp; 	SignN1 = "+";
		}
		Exp = "";	N2 = ""; SignN2 ="";
		
		txt.setText(tp);
		
			
		return;
	}
}
public class Calculator
{
	public static void main(String args[])
	{
		Cal S = new Cal();
	}
}