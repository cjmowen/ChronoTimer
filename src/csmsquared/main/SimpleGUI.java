package csmsquared.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class SimpleGUI extends JFrame{


	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	JButton newRun = new JButton("New Run");
	JButton endRun = new JButton("End Run");
	JLabel addPlayer = new JLabel("Add Player");
	JTextArea racerpanel = new JTextArea();
	JTextField racerID = new JTextField(5);
	
	JTextArea resultPanel = new JTextArea();
	JButton add = new JButton("Add");
	
	
	public SimpleGUI(){
				this.setSize(1000,1000);
				this.setTitle("Chrono Timer");
				this.setLayout(new BorderLayout());
				this.setVisible(true);
				this.setDefaultCloseOperation(EXIT_ON_CLOSE);
				
				JPanel west = new JPanel();
				west.setLayout(new BoxLayout(west, BoxLayout.X_AXIS));
				
				west.add(addPlayer);
				west.add(racerID);
				add.addActionListener(new addListener());
				west.add(add);
				west.add(start);
				start.addActionListener(new startListener());
				west.add(stop);
				west.add(newRun);
				this.add(west,BorderLayout.NORTH);
				racerpanel.setSize(500,500);
				this.add(new JLabel("Racer's List"));
				this.add(racerpanel,BorderLayout.CENTER);
				this.add(resultPanel,BorderLayout.SOUTH);
				
				revalidate();
		}
	
	private class addListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			racerpanel.setText(racerpanel.getText()+racerID.getText()+"\n");
			racerID.setText("");
			
		}
		
	}
	
	private class startListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			resultPanel.setText(""+racerID);
			
		}
		
	}
	
	
	public static void main(String[] args)
	{
	new SimpleGUI();
	}

}