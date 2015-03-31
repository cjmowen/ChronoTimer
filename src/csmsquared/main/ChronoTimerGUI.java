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


public class ChronoTimerGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterId;
	private JLabel lblChronoTimer;
	private JLabel lblRaceType;
	private ChronoTimer chrono = new ChronoTimer();
	private JTextField txtRun;

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
		btnStop.setBounds(41, 210, 89, 23);
		contentPane.add(btnStop);
		
		JButton btnNewRun = new JButton("New Run");
		btnNewRun.setBounds(41, 257, 89, 23);
		contentPane.add(btnNewRun);
		
		JButton btnEndRun = new JButton("End Run");
		btnEndRun.setBounds(41, 302, 89, 23);
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
		RacerList.setBounds(506, 140, 103, 195);
		contentPane.add(RacerList);
		
		JLabel lblRacerList = new JLabel("Racer List");
		lblRacerList.setBounds(527, 115, 67, 14);
		contentPane.add(lblRacerList);
		
		JLabel lblRun = new JLabel("RUN :");
		lblRun.setBounds(45, 357, 46, 14);
		contentPane.add(lblRun);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(175, 353, 72, 23);
		contentPane.add(btnPrint);
		
		final JTextArea textAreaRun = new JTextArea();
		textAreaRun.setEditable(false);
		textAreaRun.setBounds(59, 438, 247, 109);
		contentPane.add(textAreaRun);
		
		JLabel lblRun_1 = new JLabel("RUN PRINT");
		lblRun_1.setBounds(132, 413, 90, 14);
		contentPane.add(lblRun_1);
		
		txtRun = new JTextField();
		txtRun.setText("# Run");
		txtRun.setBounds(77, 354, 60, 20);
		contentPane.add(txtRun);
		txtRun.setColumns(10);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(243, 122, 72, 23);
		contentPane.add(btnSelect);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
				chrono.num(Integer.parseInt(txtEnterId.getText()));
//				RacerList.setText(RacerList.getText()+"\n"+txtEnterId.getText());
//				ActivityMonitor.setText(ActivityMonitor.getText()+"\n Player Added : "+txtEnterId.getText());
//				
				RacerList.setText(chrono.getRacerQueue());
				}
				catch(Exception ex)
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
					chrono.endRun();
					chrono.newRun();
					//System.out.println(ex.getMessage());
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
					
				}
			}
		});
		
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
				chrono.start();
				RacerList.setText(chrono.getRacerQueue());
				ActivityMonitor.setText(ActivityMonitor.getText()+"\n"+"# Racer Started");
				}catch(IllegalStateException ex)
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
				
				ActivityMonitor.setText(ActivityMonitor.getText()+"\n"+"# Racer Stopped");	
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
		
		
	}
}
