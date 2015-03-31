package csmsquared.main;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;


public class ChronoTimerGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterId;
	private JLabel lblChronoTimer;
	private JLabel lblRaceType;
	private ChronoTimer chrono = new ChronoTimer();
	private JTextField txtRun;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChronoTimerGUI frame = new ChronoTimerGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChronoTimerGUI() {
		setTitle("Chrono Timer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 949, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddPlayer = new JLabel("Add Player : ");
		lblAddPlayer.setBounds(31, 83, 72, 14);
		contentPane.add(lblAddPlayer);
		
		txtEnterId = new JTextField();
		txtEnterId.setText("Enter ID");
		txtEnterId.setBounds(113, 80, 50, 20);
		contentPane.add(txtEnterId);
		txtEnterId.setColumns(10);
		
		JButton btnNewButton = new JButton("ADD");
			
		btnNewButton.setBounds(187, 79, 60, 23);
		contentPane.add(btnNewButton);
		
		lblChronoTimer = new JLabel("Chrono Timer");
		lblChronoTimer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChronoTimer.setBounds(417, 11, 165, 38);
		contentPane.add(lblChronoTimer);
		
		lblRaceType = new JLabel("Race Type :");
		lblRaceType.setBounds(31, 126, 72, 14);
		contentPane.add(lblRaceType);
		
		final JComboBox raceType = new JComboBox();
		raceType.setModel(new DefaultComboBoxModel(new String[] {"Individual", "Group", "ParallelIndividual", "ParallelGrouo"}));
		raceType.setBounds(101, 123, 121, 20);
		contentPane.add(raceType);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(41, 162, 89, 23);
		contentPane.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(133, 162, 89, 23);
		contentPane.add(btnStop);
		
		JButton btnNewRun = new JButton("New Run");
		btnNewRun.setBounds(41, 196, 89, 23);
		contentPane.add(btnNewRun);
		
		JButton btnEndRun = new JButton("End Run");
		btnEndRun.setBounds(133, 196, 89, 23);
		contentPane.add(btnEndRun);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(490, 121, -94, 190);
		contentPane.add(textArea);
		
		final JTextArea ActivityMonitor = new JTextArea();
		ActivityMonitor.setWrapStyleWord(true);
		ActivityMonitor.setBounds(736, 121, 129, 412);
		//ActivityMonitor.setEditable(false);
		contentPane.add(ActivityMonitor);
		
		JLabel lblActivityMonitor = new JLabel("Activity Monitor");
		lblActivityMonitor.setBounds(760, 96, 89, 14);
		contentPane.add(lblActivityMonitor);
		
		
		final JTextArea RacerList = new JTextArea();
		RacerList.setLineWrap(true);
		RacerList.setWrapStyleWord(true);
		RacerList.setBounds(623, 126, 103, 195);
		contentPane.add(RacerList);
		
		JLabel lblRacerList = new JLabel("Racer List");
		lblRacerList.setBounds(641, 101, 67, 14);
		contentPane.add(lblRacerList);
		
		JLabel lblRun = new JLabel("RUN :");
		lblRun.setBounds(51, 230, 46, 14);
		contentPane.add(lblRun);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(155, 230, 72, 23);
		contentPane.add(btnPrint);
		
		final JTextArea textAreaRun = new JTextArea();
		textAreaRun.setEditable(false);
		textAreaRun.setBounds(366, 126, 247, 109);
		contentPane.add(textAreaRun);
		
		JLabel lblRun_1 = new JLabel("RUN PRINT");
		lblRun_1.setBounds(456, 101, 90, 14);
		contentPane.add(lblRun_1);
		
		txtRun = new JTextField();
		txtRun.setText("# Run");
		txtRun.setBounds(85, 230, 60, 20);
		contentPane.add(txtRun);
		txtRun.setColumns(10);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(243, 122, 72, 23);
		contentPane.add(btnSelect);
		
		final JRadioButton radio1 = new JRadioButton("");
		radio1.setBounds(175, 365, 31, 23);
		contentPane.add(radio1);
		
		final JRadioButton radio2 = new JRadioButton("");
		radio2.setBounds(243, 365, 31, 23);
		contentPane.add(radio2);
		
		final JRadioButton radio3 = new JRadioButton("");
		radio3.setBounds(329, 365, 31, 23);
		contentPane.add(radio3);
		
		final JRadioButton radio4 = new JRadioButton("");
		radio4.setBounds(415, 365, 36, 23);
		contentPane.add(radio4);
		
		final JRadioButton radio5 = new JRadioButton("");
		radio5.setBounds(482, 365, 36, 23);
		contentPane.add(radio5);
		
		final JRadioButton radio6 = new JRadioButton("");
		radio6.setBounds(581, 365, 46, 23);
		contentPane.add(radio6);
		
		final JButton btnStart_1 = new JButton("Start");
		
		btnStart_1.setBounds(145, 335, 72, 23);
		contentPane.add(btnStart_1);
		
		final JButton btnStart_2 = new JButton("Start");
		btnStart_2.setBounds(227, 335, 71, 23);
		contentPane.add(btnStart_2);
		
		final JButton btnStart_3 = new JButton("Start");
		btnStart_3.setBounds(308, 335, 75, 23);
		contentPane.add(btnStart_3);
		
		final JButton btnStart_4 = new JButton("Start");
		btnStart_4.setBounds(393, 335, 69, 23);
		contentPane.add(btnStart_4);
		
		final JButton btnStart_5 = new JButton("Start");
		btnStart_5.setBounds(472, 335, 74, 23);
		contentPane.add(btnStart_5);
		
		final JButton btnStart_6 = new JButton("Start");
		btnStart_6.setBounds(567, 335, 72, 23);
		contentPane.add(btnStart_6);
		
		JLabel lblActivate = new JLabel("Activate/Deactivate :");
		lblActivate.setBounds(10, 374, 120, 14);
		contentPane.add(lblActivate);
		
		JLabel lblChannel = new JLabel("Channel :");
		lblChannel.setBounds(70, 339, 60, 14);
		contentPane.add(lblChannel);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
				chrono.num(Integer.parseInt(txtEnterId.getText()));
//				RacerList.setText(RacerList.getText()+"\n"+txtEnterId.getText());
//				ActivityMonitor.setText(ActivityMonitor.getText()+"\n Player Added : "+txtEnterId.getText());
//				
				LinkedList<Integer> queue = chrono.getRacersInQueue();
				
				for(Integer i : queue)
				{
					RacerList.setText(RacerList.getText()+i.toString()+"\n");
					
				}
				
				}
				catch(IllegalArgumentException ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		
		btnNewRun.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					chrono.newRun();
				ActivityMonitor.setText(ActivityMonitor.getText()+"\n New run Started");
				}catch(IllegalStateException ex)
				{
//					chrono.endRun();
//					chrono.newRun();
//					//System.out.println(ex.getMessage());
					
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				
				
			}
		});
		
		btnEndRun.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
				chrono.endRun();
				ActivityMonitor.setText(ActivityMonitor.getText()+"\n Run Ended");
				}catch(IllegalStateException ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
				chrono.start();
				RacerList.setText(chrono.getRacerQueue());
				LinkedList<String> racersStarted = chrono.getCurrentRacers();
				for(String r: racersStarted)
				{
					ActivityMonitor.setText(ActivityMonitor.getText() + "\n" + r);
				}
				
				}catch(IllegalArgumentException/*StateException*/ ex)
				{
					chrono.stop();
					chrono.start();
				}
			}
		});
		
		btnStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
				chrono.stop(0);
				RacerList.setText(chrono.getRacerQueue());
				
				
				
				LinkedList<String> racersStarted = chrono.getFinishedRacers();
				for(String r: racersStarted)
				{
					ActivityMonitor.setText(ActivityMonitor.getText() + "\n" + r);
				}
					
				}catch(IllegalStateException ex)
				{
					
				}
			}
		});
		
		btnPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try{
				
				textAreaRun.setText(chrono.print(Integer.parseInt(txtRun.getText())));
				}
				catch(Exception ee)
				{
					JOptionPane.showMessageDialog(null, ee.getMessage());
				}
			}
		});
		
		btnSelect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ActivityMonitor.setText(ActivityMonitor.getText()+"\n"+raceType.getSelectedItem().toString()+" RaceType Selected");
				try{	
				if(raceType.getSelectedItem().toString().equals("Individual"))				
					chrono.setRaceType(RaceType.Individual);
				else if(raceType.getSelectedItem().toString().equals("Group"))				
					chrono.setRaceType(RaceType.Group);
				else if(raceType.getSelectedItem().toString().equals("ParallelIndvidual"))				
					chrono.setRaceType(RaceType.ParallelIndividual);
				else chrono.setRaceType(RaceType.ParallelGroup);
				}catch(IllegalStateException ex)
				{
					System.out.println(ex.getMessage() + "in btnSelect listener");
				}
				
			}
		});
		
		radio1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chrono.toggle(1);
				chrono.toggle(2);
				
			}
		});
		

		radio1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chrono.toggle(1);
				chrono.toggle(2);
				
			}
		});
		

		radio2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chrono.toggle(3);
				chrono.toggle(4);
				
			}
		});
		

		radio3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chrono.toggle(5);
				chrono.toggle(6);
				
			}
		});
		

		radio4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chrono.toggle(7);
				chrono.toggle(8);
				
			}
		});
		

		radio5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chrono.toggle(9);
				chrono.toggle(10);
				
			}
		});
		

		radio6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chrono.toggle(11);
				chrono.toggle(12);
				
			}
		});
		
		
		btnStart_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(radio1.isSelected()){
				
					if(btnStart_1.getText().equals("Start"))
					{
						btnStart_1.setText("Stop");
						chrono.trigger(1);
						ActivityMonitor.setText(ActivityMonitor.getText()+"\n"+"# player started ");
					}	
					else
					{
						btnStart_1.setText("Start");
						chrono.trigger(2);
						ActivityMonitor.setText(ActivityMonitor.getText()+"\n"+"# player stopped ");
					}
					
					
					
					
					}
			}
		});
		
		btnStart_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(radio2.isSelected()){
				
					if(btnStart_2.getText().equals("Start"))
					{
						btnStart_2.setText("Stop");
						chrono.trigger(3);
					}	
					else
					{
						btnStart_2.setText("Start");
						chrono.trigger(4);
					}
					
					
					
					
					}
			}
		});
		
btnStart_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(radio3.isSelected()){
				
					if(btnStart_3.getText().equals("Start"))
					{
						btnStart_3.setText("Stop");
						chrono.trigger(5);
					}	
					else
					{
						btnStart_3.setText("Start");
						chrono.trigger(6);
					}
					
					
					
					
					}
			}
		});

btnStart_4.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(radio4.isSelected()){
		
			if(btnStart_4.getText().equals("Start"))
			{
				btnStart_4.setText("Stop");
				chrono.trigger(7);
			}	
			else
			{
				btnStart_4.setText("Start");
				chrono.trigger(8);
			}
			
			
			
			
			}
	}
});

btnStart_5.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(radio5.isSelected()){
		
			if(btnStart_5.getText().equals("Start"))
			{
				btnStart_5.setText("Stop");
				chrono.trigger(9);
			}	
			else
			{
				btnStart_5.setText("Start");
				chrono.trigger(10);
			}
			
			
			
			
			}
	}
});

btnStart_6.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(radio6.isSelected()){
		
			if(btnStart_6.getText().equals("Start"))
			{
				btnStart_6.setText("Stop");
				chrono.trigger(11);
			}	
			else
			{
				btnStart_6.setText("Start");
				chrono.trigger(12);
			}
			
			
			
			
			}
	}
});



		
		
	}
}
