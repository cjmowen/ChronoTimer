package csmsquared.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.peer.LabelPeer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class GUI extends JFrame{
	
	
	
	JButton power = new JButton("Power");
	JButton function = new JButton("FUNCTION");
	JButton swap = new JButton("SWAP");
	JButton start1 = new JButton("");
	JButton start2 = new JButton("");
	JButton start3 = new JButton("");
	JButton start4 = new JButton("");
	JButton start5 = new JButton("");
	JButton start6 = new JButton("");
	JButton start7 = new JButton("");
	JButton start8 = new JButton("");
	JButton printerpower = new JButton("Printer pwr");
	JButton one = new JButton("1");
	JButton two = new JButton("2");
	JButton three = new JButton("3");
	JButton four = new JButton("4");
	JButton five = new JButton("5");
	JButton six = new JButton("6");
	JButton seven = new JButton("7");
	JButton eight = new JButton("8");
	JButton nine = new JButton("9");
	JButton star = new JButton("0");
	JButton zero = new JButton("*");
	JButton hash = new JButton("#");
	
	JLabel chronotimer = new JLabel("CHRONOTIMER 1009");
	JLabel labelone = new JLabel("1");
	JLabel labeltwo = new JLabel("2");
	JLabel labelthree = new JLabel("3");
	JLabel labelfour = new JLabel("4");
	JLabel labelfive = new JLabel("5");
	JLabel labelsix = new JLabel("6");
	JLabel labelseven = new JLabel("7");
	JLabel labeleight = new JLabel("8");
	JLabel labelStart = new JLabel("Start");
	JLabel Enabledisable = new JLabel("Enable/Disable");
	JLabel labelfinish = new JLabel("Finish");
	
	
	
	public static void main(String[] main){
		new GUI();
	}
	
	public GUI()
	{
		this.setSize(1000,1000);
		this.setVisible(true);
		this.setTitle("Chrono Timer");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(2,3));
		
		JPanel topMiddle = new JPanel();
		
		topMiddle.setLayout(new BorderLayout());
		topMiddle.add(chronotimer,BorderLayout.NORTH);
		
		JPanel insidetopMiddle = new JPanel();
		insidetopMiddle.setLayout(new GridLayout(6,5));
		
		insidetopMiddle.add(new JLabel(""));
		insidetopMiddle.add(labelone);
		insidetopMiddle.add(labelthree);
		insidetopMiddle.add(labelfive);
		insidetopMiddle.add(labelseven);
		
		
		insidetopMiddle.add(labelStart);
		insidetopMiddle.add(start1);
		insidetopMiddle.add(start3);
		insidetopMiddle.add(start5);
		insidetopMiddle.add(start7);
		
		
		insidetopMiddle.add(Enabledisable);
		insidetopMiddle.add(new JRadioButton());
		insidetopMiddle.add(new JRadioButton());
		insidetopMiddle.add(new JRadioButton());
		insidetopMiddle.add(new JRadioButton());
		
		insidetopMiddle.add(new JLabel("fourth Row"));
		insidetopMiddle.add(labeltwo);
		insidetopMiddle.add(labelfour);
		insidetopMiddle.add(labelsix);
		insidetopMiddle.add(labeleight);

		
		
		insidetopMiddle.add(new JLabel("Fifth Row"));
		insidetopMiddle.add(start2);
		insidetopMiddle.add(start4);
		insidetopMiddle.add(start6);
		insidetopMiddle.add(start8);
		
		
		insidetopMiddle.add(Enabledisable);
		insidetopMiddle.add(new JRadioButton());
		insidetopMiddle.add(new JRadioButton());
		insidetopMiddle.add(new JRadioButton());
		insidetopMiddle.add(new JRadioButton());
		
		
		topMiddle.add(insidetopMiddle,BorderLayout.CENTER);
		//topMiddle.add(new JLabel());
		
		power.setMaximumSize(new Dimension(5,5));

		
		JPanel topleft = new JPanel();
		topleft.setPreferredSize(new Dimension(100,100));
		topleft.setLayout(new FlowLayout());
		topleft.add(power);
		topleft.add(new JLabel());
		
		
		this.getContentPane().add(topleft);
		this.getContentPane().add(topMiddle);
		
		JPanel topright = new JPanel(new FlowLayout());
		topright.add(printerpower);
		this.getContentPane().add(topright);
		
		JPanel bottomleft = new JPanel();
		bottomleft.setLayout(new FlowLayout());
		
		bottomleft.add(function);
		bottomleft.add(swap);
		
		
		
		this.getContentPane().add(bottomleft);
		this.getContentPane().add(new JTextArea());
		
		JPanel BottomRight = new JPanel();
		BottomRight.setLayout(new GridLayout(4,3));
		
		BottomRight.add(one);
		BottomRight.add(two);
		BottomRight.add(three);
		BottomRight.add(four);
		BottomRight.add(five);
		BottomRight.add(six);
		BottomRight.add(seven);
		BottomRight.add(eight);
		BottomRight.add(nine);
		BottomRight.add(star);
		BottomRight.add(zero);
		BottomRight.add(hash);
		
		
		this.getContentPane().add(BottomRight);
		this.getContentPane().validate();
		
		
		
	}
	
	

}
