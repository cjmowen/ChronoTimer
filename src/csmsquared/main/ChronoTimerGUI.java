package csmsquared.main;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import csmsquared.race.RaceType;


public class ChronoTimerGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterId;
	private JLabel lblChronoTimer;
	private JLabel lblRaceType;
	private ChronoTimer chrono = new ChronoTimer();
	private JTextField txtRun;
	//final JButton btnGrp = new JButton("Group");
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
	final JTextArea RacerList;
	private JTextField txtExportRun;
	JButton btnExport;
	final JButton btnOn;
	final JButton btnStartGroup;
	JButton btnNewRun;
	JButton btnNewButton;
	JButton btnPrint;
	final JComboBox raceType;
	private JLabel lblRunNumber;
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

		btnNewButton = new JButton("ADD");

		btnNewButton.setBounds(187, 79, 60, 23);
		contentPane.add(btnNewButton);

		lblChronoTimer = new JLabel("Chrono Timer");
		lblChronoTimer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChronoTimer.setBounds(417, 11, 165, 38);
		contentPane.add(lblChronoTimer);

		lblRaceType = new JLabel("Race Type :");
		lblRaceType.setBounds(31, 126, 72, 14);
		contentPane.add(lblRaceType);

		raceType = new JComboBox();
		raceType.setModel(new DefaultComboBoxModel(new String[] {"Individual", "Group", "ParallelIndividual", "ParallelGrouo"}));
		raceType.setBounds(101, 123, 121, 20);
		contentPane.add(raceType);

		btnNewRun = new JButton("New Run");
		btnNewRun.setBounds(226, 122, 89, 23);
		contentPane.add(btnNewRun);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(490, 121, -94, 190);
		contentPane.add(textArea);

		RacerList = new JTextArea();
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

		 btnPrint = new JButton("Print");
		btnPrint.setBounds(155, 230, 72, 23);
		contentPane.add(btnPrint);

		final JTextArea textAreaRun = new JTextArea();
		textAreaRun.setEditable(false);
		textAreaRun.setBounds(366, 121, 247, 109);
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
		radio2.setBounds(251, 365, 31, 23);
		contentPane.add(radio2);

		radio3 = new JRadioButton("");
		radio3.setBounds(329, 365, 31, 23);
		contentPane.add(radio3);

		radio4 = new JRadioButton("");
		radio4.setBounds(415, 365, 36, 23);
		contentPane.add(radio4);

		radio5 = new JRadioButton("");
		radio5.setBounds(496, 365, 36, 23);
		contentPane.add(radio5);

		radio6 = new JRadioButton("");
		radio6.setBounds(581, 365, 46, 23);
		contentPane.add(radio6);

		btnStartGroup = new JButton("START GROUP");
		btnStartGroup.setEnabled(false);
		btnStartGroup.setBounds(131, 275, 129, 23);
		contentPane.add(btnStartGroup);

		JLabel lblExportRun = new JLabel("Export RUN : ");
		lblExportRun.setBounds(31, 436, 72, 14);
		contentPane.add(lblExportRun);

		btnExport = new JButton("Export");
		btnExport.setBounds(169, 432, 78, 23);
		contentPane.add(btnExport);

		txtExportRun = new JTextField();
		txtExportRun.setText("Run#");
		txtExportRun.setBounds(103, 433, 50, 20);
		contentPane.add(txtExportRun);
		txtExportRun.setColumns(10);

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
		JLabel lblRacerStatus = new JLabel("RACER STATUS");
		lblRacerStatus.setBounds(765, 101, 91, 14);
		contentPane.add(lblRacerStatus);

		txtRacerStatus = new JTextArea();
		//scrolll.add(txtRacerStatus);
		txtRacerStatus.setBounds(739, 121, 172, 230);
	
		contentPane.add(txtRacerStatus);

		final JLabel lblGroupRace = new JLabel("Group Race : ");
		lblGroupRace.setBounds(31, 279, 92, 14);
		contentPane.add(lblGroupRace);
		
		btnOn = new JButton("ON");
		btnOn.setBounds(101, 21, 89, 23);
		contentPane.add(btnOn);
		setEditable(false);
		
		final JLabel lblRaceTypeMsg = new JLabel("");
		lblRaceTypeMsg.setBounds(31, 166, 296, 14);
		contentPane.add(lblRaceTypeMsg);

		lblRunNumber = new JLabel("");
		lblRunNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRunNumber.setBounds(581, 45, 220, 23);
		contentPane.add(lblRunNumber);
		
		
		
		btnOn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(btnOn.getText().compareTo("ON")==0)
				{
					
					setEditable(true);
					btnOn.setText("OFF");
					lblRaceTypeMsg.setText("Current RaceType : "+chrono.getRaceType().toString());
					lblRunNumber.setText("Current Run # "+ chrono.getRunNumber());
					lblGroupRace.setVisible(false);
					btnStartGroup.setVisible(false);
					refreshState();
				}
				else{
					setEditable(false);
					btnOn.setText("ON");
					lblRaceTypeMsg.setText("");
					refreshState();
					
				}
			}
		});
		
		btnExport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					chrono.export(Integer.parseInt(txtExportRun.getText()));
					JOptionPane.showMessageDialog(null," Data exported to \"exportdata.txt\" in the same directory for RUN :"+txtRun.getText());
				} catch (NoSuchElementException e1) {

					JOptionPane.showMessageDialog(null, e1.getMessage());
				} 
				catch(Exception e3)
				{
					JOptionPane.showMessageDialog(null," Input Error!! (Only Integers)");
				}


			}
		});


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
					
					refreshState();
					
				}



				

				catch(IllegalArgumentException ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}
		});


		txtEnterId.addActionListener(new ActionListener() {

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
					refreshState();
				}



				catch(IllegalArgumentException ex)
				{
					JOptionPane.showMessageDialog(null, "Only Numbers are allowed");
				}

			}
		});



		btnNewRun.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try{

					btnStartGroup.setVisible(false);
					lblGroupRace.setVisible(false);
					if(raceType.getSelectedItem().toString().equals("Individual"))	
					{
						chrono.setRaceType(RaceType.Individual);
						
						setButtonState(1);
					}else if(raceType.getSelectedItem().toString().equals("Group"))	{	
						btnStartGroup.setVisible(true);
						lblGroupRace.setVisible(true);

						chrono.setRaceType(RaceType.Group);
						btnStartGroup.setEnabled(true);
						setButtonState(chrono.getRacersInQueue().size());
						
							
						

					}

					else if(raceType.getSelectedItem().toString().equals("ParallelIndividual"))		
					{
						chrono.setRaceType(RaceType.ParallelIndividual);
						refreshState();
					}
					else chrono.setRaceType(RaceType.ParallelGroup);

					lblRaceTypeMsg.setText("Current RaceType : "+chrono.getRaceType().toString());
					lblRunNumber.setText("Current Run # "+ chrono.getRunNumber());;
					chrono.newRun();

					System.out.println(" After calling New Run");
					LinkedList<Integer> queue = chrono.getRacersInQueue();
					String result ="";
					for(Integer i : queue)
					{
						result +=(i.toString()+"\n");

					}

					RacerList.setText(result);
					txtRacerStatus.setText(refresh());

			
				}catch(IllegalStateException ex)
				{
					chrono.endRun();

					if(raceType.getSelectedItem().toString().equals("Individual"))			
					{
						chrono.setRaceType(RaceType.Individual);
						setButtonState(1);
					}
					else if(raceType.getSelectedItem().toString().equals("Group"))	{	
						btnStartGroup.setVisible(true);
						lblGroupRace.setVisible(true);

						chrono.setRaceType(RaceType.Group);
						btnStartGroup.setEnabled(true);
						setButtonState(chrono.getRacersInQueue().size());
						
							
						

					}

					else if(raceType.getSelectedItem().toString().equals("ParallelIndividual"))		
					{
						chrono.setRaceType(RaceType.ParallelIndividual);
						refreshState();
					}
					else chrono.setRaceType(RaceType.ParallelGroup);
					//					
					chrono.newRun();
					lblRaceTypeMsg.setText("Current RaceType : "+chrono.getRaceType().toString());
					lblRunNumber.setText("Current Run # "+ chrono.getRunNumber());;

					LinkedList<Integer> queue = chrono.getRacersInQueue();
					String result ="";
					for(Integer i : queue)
					{
						result +=(i.toString()+"\n");

					}

					RacerList.setText(result);
					txtRacerStatus.setText(refresh());


					//JOptionPane.showMessageDialog(null, ex.getMessage());
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
		
		JLabel lblChannel_1 = new JLabel("Channel 1");
		lblChannel_1.setBounds(160, 395, 62, 14);
		contentPane.add(lblChannel_1);
		
		JLabel lblChannel_2 = new JLabel("Channel 2");
		lblChannel_2.setBounds(238, 395, 60, 14);
		contentPane.add(lblChannel_2);
		
		JLabel lblChannel_3 = new JLabel("Channel 3");
		lblChannel_3.setBounds(314, 395, 60, 14);
		contentPane.add(lblChannel_3);
		
		JLabel lblChannel_4 = new JLabel("Channel 4");
		lblChannel_4.setBounds(404, 395, 58, 14);
		contentPane.add(lblChannel_4);
		
		JLabel lblChannel_5 = new JLabel("Channel 5");
		lblChannel_5.setBounds(490, 395, 56, 14);
		contentPane.add(lblChannel_5);
		
		JLabel lblChannel_6 = new JLabel("Channel 6");
		lblChannel_6.setBounds(581, 395, 58, 14);
		contentPane.add(lblChannel_6);
		
		
		

		btnStartGroup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				int totalRunner = chrono.getRacersInQueue().size();
				if(totalRunner==0)
				{
					JOptionPane.showMessageDialog(null, "No players in Racer Queue");
				}

				if(totalRunner == 1 )
				{
					if(!radio1.isSelected())
					{
						JOptionPane.showMessageDialog(null, " Please Activate Channels");
					}
					else{

						chrono.trigger(1);
						btnStart_1.setText("Stop");
						btnStartGroup.setEnabled(false);
					}
				}
				else if(totalRunner==2)
				{

					if(!radio1.isSelected() || !radio2.isSelected())
					{
						JOptionPane.showMessageDialog(null, " Please Activate Channels");
					}
					else{
						chrono.trigger(1);	
						btnStart_1.setText("Stop");
						btnStart_2.setText("Stop");
						btnStartGroup.setEnabled(false);
					}
				}
				else if(totalRunner==3)
				{

					if(!radio1.isSelected() || !radio2.isSelected() || !radio3.isSelected()){
						JOptionPane.showMessageDialog(null, " Please Activate Channels");
					}

					else{
						chrono.trigger(1);	
						btnStart_1.setText("Stop");
						btnStart_2.setText("Stop");
						btnStart_3.setText("Stop");
						btnStartGroup.setEnabled(false);
					}
				}
				else if(totalRunner==4)
				{


					if(!radio4.isSelected() || !radio1.isSelected() || !radio2.isSelected() || !radio3.isSelected()){
						JOptionPane.showMessageDialog(null, " Please Activate Channels");
					}

					else{
						chrono.trigger(1);	
						btnStart_1.setText("Stop");

						btnStart_2.setText("Stop");
						btnStart_3.setText("Stop");
						btnStart_4.setText("Stop");
						btnStartGroup.setEnabled(false);
					}
				}
				else if(totalRunner==5)
				{

					if(!radio5.isSelected() || !radio4.isSelected()  || !radio1.isSelected()|| !radio2.isSelected()|| !radio3.isSelected()){
						JOptionPane.showMessageDialog(null, " Please Activate Channels");
					}

					else{
						chrono.trigger(1);
						btnStart_1.setText("Stop");
						btnStart_2.setText("Stop");
						btnStart_3.setText("Stop");
						btnStart_4.setText("Stop");
						btnStart_5.setText("Stop");
						btnStartGroup.setEnabled(false);
					}
				}
				else if(totalRunner>=6)
				{
					if(!radio6.isSelected() || !radio5.isSelected()|| !radio4.isSelected()  || !radio1.isSelected() || !radio2.isSelected() || !radio3.isSelected()){
						JOptionPane.showMessageDialog(null, " Please Activate Channels");
					}else{
						chrono.trigger(1);
						btnStart_1.setText("Stop");
						btnStart_2.setText("Stop");
						btnStart_3.setText("Stop");
						btnStart_4.setText("Stop");
						btnStart_5.setText("Stop");
						btnStart_6.setText("Stop");
						btnStartGroup.setEnabled(false);
					}
				}
				
				
				//	JOptionPane.showMessageDialog(null, "You must activate the channles for Group Race to work !!");

				
				
			}
		});

	

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
					if(btn.getText().equals("Start") )
					{
						if(chrono.getRaceType().toString().equals("GRP"))
						{
							JOptionPane.showMessageDialog(null, "Please use \"Start Group\" button to start Group Race");
							return;
						}
						btn.setText("Stop");
						chrono.trigger(btnNumber*2-1);
						txtRacerStatus.setText(refresh());
						if(chrono.getCurrentRacers().size()==0)
							if(chrono.getRaceType().toString().equals("GRP"))
								btnStartGroup.setEnabled(true);
					//	if(btnNumber==1) btnStart_1.setEnabled(false);
						if(chrono.getCurrentRacers().size()==0)
						refreshState();


					}
					else
					{
						btn.setText("Start");
						chrono.trigger(btnNumber*2);
						txtRacerStatus.setText(refresh());
						if(chrono.getCurrentRacers().size()==0)
							refreshState();
						else
							btn.setEnabled(false);
					}
				}
			}
			else
			{
				
			
				JOptionPane.showMessageDialog(null, "Please activate the channel "+ btnNumber);
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

		LinkedList<Integer> queue = chrono.getRacersInQueue();
		String result1 ="";
		for(Integer i : queue)
		{
			result1 +=(i.toString()+"\n");

		}
		RacerList.setText(result1);




		return result;

	}
	public void setEditable(boolean state)
	{
		txtEnterId.setEditable(state);
		txtRun.setEditable(state);
		btnStart_1.setEnabled(state);
		btnStart_2.setEnabled(state);
		btnStart_3.setEnabled(state);
		btnStart_4.setEnabled(state);
		btnStart_5.setEnabled(state);
		btnStart_6.setEnabled(state);
		btnExport.setEnabled(state);
		btnNewRun.setEnabled(state);
		radio1.setEnabled(state);
		radio2.setEnabled(state);
		radio3.setEnabled(state);
		radio4.setEnabled(state);
		radio5.setEnabled(state);
		radio6.setEnabled(state);
		btnNewButton.setEnabled(state);
		btnPrint.setEnabled(state);
		raceType.setEnabled(state);
		txtExportRun.setEditable(state);
		
	}
	
	
	public void setButtonState(int size)
	{
		if(size == 0)
		{
			if(raceType.getSelectedItem().toString().equals("Group"))
				btnStartGroup.setEnabled(true);
			btnStart_1.setEnabled(false);
			btnStart_2.setEnabled(false);
			btnStart_3.setEnabled(false);
			btnStart_4.setEnabled(false);
			btnStart_5.setEnabled(false);
			btnStart_6.setEnabled(false);
			
			radio1.setEnabled(false);
			radio2.setEnabled(false);
			radio3.setEnabled(false);
			radio4.setEnabled(false);
			radio5.setEnabled(false);
			radio6.setEnabled(false);
			
			
		}
		if(size==1)
		{
			if(raceType.getSelectedItem().toString().equals("Group"))
				btnStartGroup.setEnabled(true);
			btnStart_1.setEnabled(true);
			btnStart_2.setEnabled(false);
			btnStart_3.setEnabled(false);
			btnStart_4.setEnabled(false);
			btnStart_5.setEnabled(false);
			btnStart_6.setEnabled(false);
			
			radio1.setEnabled(true);
			radio2.setEnabled(false);
			radio3.setEnabled(false);
			radio4.setEnabled(false);
			radio5.setEnabled(false);
			radio6.setEnabled(false);
			
		}
		if(size==2)
		{
			if(raceType.getSelectedItem().toString().equals("Group"))
				btnStartGroup.setEnabled(true);
			btnStart_1.setEnabled(true);
			btnStart_2.setEnabled(true);
			btnStart_3.setEnabled(false);
			btnStart_4.setEnabled(false);
			btnStart_5.setEnabled(false);
			btnStart_6.setEnabled(false);
			
			radio1.setEnabled(true);
			radio2.setEnabled(true);
			radio3.setEnabled(false);
			radio4.setEnabled(false);
			radio5.setEnabled(false);
			radio6.setEnabled(false);
			
		}
		if(size==3)
		{
			if(raceType.getSelectedItem().toString().equals("Group"))
				btnStartGroup.setEnabled(true);
			btnStart_1.setEnabled(true);
			btnStart_2.setEnabled(true);
			btnStart_3.setEnabled(true);
			btnStart_4.setEnabled(false);
			btnStart_5.setEnabled(false);
			btnStart_6.setEnabled(false);
			
			radio1.setEnabled(true);
			radio2.setEnabled(true);
			radio3.setEnabled(true);
			radio4.setEnabled(false);
			radio5.setEnabled(false);
			radio6.setEnabled(false);
			
		}
		if(size==4)
		{if(raceType.getSelectedItem().toString().equals("Group"))
			btnStartGroup.setEnabled(true);
			btnStart_1.setEnabled(true);
			btnStart_2.setEnabled(true);
			btnStart_3.setEnabled(true);
			btnStart_4.setEnabled(true);
			btnStart_5.setEnabled(false);
			btnStart_6.setEnabled(false);
			
			radio1.setEnabled(true);
			radio2.setEnabled(true);
			radio3.setEnabled(true);
			radio4.setEnabled(true);
			radio5.setEnabled(false);
			radio6.setEnabled(false);
			
		}
		if(size==5)
		{
			if(raceType.getSelectedItem().toString().equals("Group"))
				btnStartGroup.setEnabled(true);
			btnStart_1.setEnabled(true);
			btnStart_2.setEnabled(true);
			btnStart_3.setEnabled(true);
			btnStart_4.setEnabled(true);
			btnStart_5.setEnabled(true);
			btnStart_6.setEnabled(false);
			
			radio1.setEnabled(true);
			radio2.setEnabled(true);
			radio3.setEnabled(true);
			radio4.setEnabled(true);
			radio5.setEnabled(true);
			radio6.setEnabled(false);
			
		}
		if(size>=6)
		{
			if(raceType.getSelectedItem().toString().equals("Group"))
				btnStartGroup.setEnabled(true);
			btnStart_1.setEnabled(true);
			btnStart_2.setEnabled(true);
			btnStart_3.setEnabled(true);
			btnStart_4.setEnabled(true);
			btnStart_5.setEnabled(true);
			btnStart_6.setEnabled(true);
			
			radio1.setEnabled(true);
			radio2.setEnabled(true);
			radio3.setEnabled(true);
			radio4.setEnabled(true);
			radio5.setEnabled(true);
			radio6.setEnabled(true);
		}
	}
	public void refreshState()
	{
	
		if(chrono.getRaceType().toString().equals("IND"))
			if(chrono.getRacersInQueue().size()==0)
				setButtonState(0);
			else
			setButtonState(1);
		else if(chrono.getRaceType().toString().equals("GRP"))
			setButtonState(chrono.getRacersInQueue().size());
		else if(chrono.getRaceType().toString().equals("PARIND"))
			setButtonState(chrono.getRacersInQueue().size());
	}
}
