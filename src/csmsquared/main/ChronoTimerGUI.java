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

import org.hamcrest.core.IsInstanceOf;

import csmsquared.race.RaceType;


public class ChronoTimerGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterId;
	private JLabel lblChronoTimer;
	private JLabel lblRaceType;
	private ChronoTimer chrono = new ChronoTimer();
	private JTextField txtRun;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	final JButton btnStart_1;
	final JButton btnStart_2;
	final JButton btnStart_3;
	final JButton btnStart_4;
	final JButton btnStart_5;
	final JButton btnStart_6;
	JTextArea txtRacerStatus;
	final JRadioButton radio1;
	final JRadioButton radio2;
	final JRadioButton radio3;
	final JRadioButton radio4;
	final JRadioButton radio5;
	final JRadioButton radio6;
	

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
		setBounds(100, 100, 949, 825);
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
		btnNewRun.setBounds(226, 122, 89, 23);
		contentPane.add(btnNewRun);
		
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
		
		 radio1 = new JRadioButton("");
		radio1.setBounds(175, 365, 31, 23);
		contentPane.add(radio1);
		
		radio2 = new JRadioButton("");
		radio2.setBounds(243, 365, 31, 23);
		contentPane.add(radio2);
		
		radio3 = new JRadioButton("");
		radio3.setBounds(329, 365, 31, 23);
		contentPane.add(radio3);
		
		radio4 = new JRadioButton("");
		radio4.setBounds(415, 365, 36, 23);
		contentPane.add(radio4);
		
		radio5 = new JRadioButton("");
		radio5.setBounds(482, 365, 36, 23);
		contentPane.add(radio5);
		
		radio6 = new JRadioButton("");
		radio6.setBounds(581, 365, 46, 23);
		contentPane.add(radio6);
		
		btnStart_1 = new JButton("Start");
		
		btnStart_1.setBounds(145, 335, 72, 23);
		contentPane.add(btnStart_1);
		
		 btnStart_2 = new JButton("Start");
		btnStart_2.setBounds(227, 335, 71, 23);
		contentPane.add(btnStart_2);
		
		 btnStart_3 = new JButton("Start");
		btnStart_3.setBounds(308, 335, 75, 23);
		contentPane.add(btnStart_3);
		
		btnStart_4 = new JButton("Start");
		btnStart_4.setBounds(393, 335, 69, 23);
		contentPane.add(btnStart_4);
		
		 btnStart_5 = new JButton("Start");
		btnStart_5.setBounds(472, 335, 74, 23);
		contentPane.add(btnStart_5);
		
		btnStart_6 = new JButton("Start");
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
				
				LinkedList<Integer> queue = chrono.getRacersInQueue();
				String result ="";
				for(Integer i : queue)
				{
					result +=(i.toString()+"\n");
					
				}
				RacerList.setText(result);
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
					
					if(raceType.getSelectedItem().toString().equals("Individual"))				
						chrono.setRaceType(RaceType.Individual);
					else if(raceType.getSelectedItem().toString().equals("Group"))				
						chrono.setRaceType(RaceType.Group);
					else if(raceType.getSelectedItem().toString().equals("ParallelIndvidual"))				
						chrono.setRaceType(RaceType.ParallelIndividual);
					else chrono.setRaceType(RaceType.ParallelGroup);
					
					
					chrono.newRun();
					LinkedList<Integer> queue = chrono.getRacersInQueue();
					String result ="";
					for(Integer i : queue)
					{
						result +=(i.toString()+"\n");
						
					}
					RacerList.setText(result);
					txtRacerStatus.setText(refresh());
				ActivityMonitor.setText(ActivityMonitor.getText()+"\n New run Started");
				}catch(IllegalStateException ex)
				{
					chrono.endRun();
					
					if(raceType.getSelectedItem().toString().equals("Individual"))				
						chrono.setRaceType(RaceType.Individual);
					else if(raceType.getSelectedItem().toString().equals("Group"))				
						chrono.setRaceType(RaceType.Group);
					else if(raceType.getSelectedItem().toString().equals("ParallelIndvidual"))				
						chrono.setRaceType(RaceType.ParallelIndividual);
					else chrono.setRaceType(RaceType.ParallelGroup);
//					
					chrono.newRun();
					
					//JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				
				
			}
		});
		
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
				chrono.start();
				
				LinkedList<Integer> queue = chrono.getRacersInQueue();
				String result ="";
				for(Integer i : queue)
				{
					result +=(i.toString()+"\n");
					
				}
				RacerList.setText(result);
				
				
				LinkedList<String> racersStarted = chrono.getCurrentRacers();
				txtRacerStatus.setText(refresh());
				for(String r: racersStarted)
				{
					ActivityMonitor.setText(ActivityMonitor.getText() + "\n" + r);
				}
				
				}catch(IllegalArgumentException/*StateException*/ ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		
		btnStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
				chrono.stop();
				
				LinkedList<Integer> queue = chrono.getRacersInQueue();
				String result ="";
				for(Integer i : queue)
				{
					result +=(i.toString()+"\n");
					
				}
				RacerList.setText(result);
				
				
				
				LinkedList<String> racersStarted = chrono.getFinishedRacers();
				txtRacerStatus.setText(refresh());
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
		
		
		radio1.addActionListener(new actionRadioListner(1));
		radio1.addActionListener(new actionRadioListner(2));
		radio1.addActionListener(new actionRadioListner(3));
		radio1.addActionListener(new actionRadioListner(4));
		radio1.addActionListener(new actionRadioListner(5));
		radio1.addActionListener(new actionRadioListner(6));

		
		btnStart_1.addActionListener(new actionButtonListener(1, radio1));
		btnStart_2.addActionListener(new actionButtonListener(2, radio2));
		btnStart_3.addActionListener(new actionButtonListener(3, radio3));
		btnStart_4.addActionListener(new actionButtonListener(4, radio4));
		btnStart_5.addActionListener(new actionButtonListener(5, radio5));
		btnStart_6.addActionListener(new actionButtonListener(6, radio6));
		
		JLabel lblRacerStatus = new JLabel("RACER STATUS");
		lblRacerStatus.setBounds(548, 488, 91, 14);
		contentPane.add(lblRacerStatus);
		
		txtRacerStatus = new JTextArea();
		txtRacerStatus.setBounds(516, 513, 172, 230);
		contentPane.add(txtRacerStatus);


		
		
	}
	private class actionRadioListner implements ActionListener
	{
		int radio;
		public actionRadioListner(int radio) {
				
			this.radio = radio;
			// TODO Auto-generated constructor stub
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			txtRacerStatus.setText(refresh());
			chrono.toggle(radio*2);
			chrono.toggle(radio*2-1);
			
		}
		
	}
	private class actionButtonListener implements ActionListener{

		int btnNumber;
		JRadioButton radio;
		
		public actionButtonListener(int btnNumber,JRadioButton radio) {
			this.btnNumber = btnNumber;
			this.radio = radio;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(radio.isSelected()){
			
				txtRacerStatus.setText(refresh());
				
			if(e.getSource() instanceof JButton){
				
				JButton btn = (JButton) e.getSource();
				if(btn.getText().equals("Start"))
				{
					btn.setText("Stop");
					chrono.trigger(btnNumber*2-1);
				}
				else
				{
					btn.setText("Start");
					chrono.trigger(btnNumber*2);
				}
			}
			}
			
		}
		
	}
	
	public String refresh(){
		
		String result ="";
		
		result = "RUNNING : \n";
		
		LinkedList<String> currentRacer = chrono.getCurrentRacers();
		
		for(String str:currentRacer){
			result+= str+"\n";
		}
		
		result += "\n FINISHED \n";
		
LinkedList<String> finishedRacer = chrono.getFinishedRacers();
		
		for(String str:finishedRacer){
			result+= str+"\n";
		}
		
		
		return result;
		
	}
}
