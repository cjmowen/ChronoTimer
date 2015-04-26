package csmsquared.main;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import csmsquared.chronoEvents.ChronoListener;
import csmsquared.chronoEvents.LaneEvent;
import csmsquared.race.RaceType;


@SuppressWarnings("serial")
public class ChronoTimerGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblChronoTimer, lblRaceType, lblRunNumber;
	private JTextField txtEnterId, txtRun, txtExportRun;
	private ChronoTimer chrono;

	private JComboBox<String> raceTypeComboBox;
	
	private JTextArea txtRacerStatus, racerList;
	
	private JButton btnExport, btnOn, btnStartGroup, btnNewRun, btnNewButton, btnPrint;
	private JButton[] buttons;
	
	private JRadioButton[] radios;
	
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
		
		setUpContent();

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

		raceTypeComboBox = new JComboBox<String>();
		raceTypeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Individual", "Group", "ParallelIndividual", "ParallelGrouo"}));
		raceTypeComboBox.setBounds(101, 123, 121, 20);
		contentPane.add(raceTypeComboBox);

		btnNewRun.setBounds(226, 122, 89, 23);
		contentPane.add(btnNewRun);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(490, 121, -94, 190);
		contentPane.add(textArea);

		racerList = new JTextArea();
		racerList.setLineWrap(true);
		racerList.setWrapStyleWord(true);
		racerList.setBounds(623, 126, 103, 195);
		contentPane.add(racerList);

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
		
		JRadioButton radio1, radio2, radio3, radio4, radio5, radio6;
		radio1 = radios[0];
		radio2 = radios[1];
		radio3 = radios[2];
		radio4 = radios[3];
		radio5 = radios[4];
		radio6 = radios[5];

		radio1.setBounds(175, 365, 31, 23);
		contentPane.add(radio1);

		radio2.setBounds(251, 365, 31, 23);
		contentPane.add(radio2);

		radio3.setBounds(329, 365, 31, 23);
		contentPane.add(radio3);

		radio4.setBounds(415, 365, 36, 23);
		contentPane.add(radio4);

		radio5.setBounds(496, 365, 36, 23);
		contentPane.add(radio5);

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
		
		JButton btn1, btn2, btn3, btn4, btn5, btn6;
		btn1 = buttons[0];
		btn2 = buttons[1];
		btn3 = buttons[2];
		btn4 = buttons[3];
		btn5 = buttons[4];
		btn6 = buttons[5];

		btn1.setBounds(145, 335, 72, 23);
		contentPane.add(btn1);

		btn2.setBounds(227, 335, 71, 23);
		contentPane.add(btn2);

		btn3.setBounds(308, 335, 75, 23);
		contentPane.add(btn3);

		btn4.setBounds(393, 335, 69, 23);
		contentPane.add(btn4);

		btn5.setBounds(472, 335, 74, 23);
		contentPane.add(btn5);

		btn6.setBounds(567, 335, 72, 23);
		contentPane.add(btn6);

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
		
		btnOn.setBounds(101, 21, 89, 23);
		contentPane.add(btnOn);
		
		final JLabel lblRaceTypeMsg = new JLabel("");
		lblRaceTypeMsg.setBounds(31, 166, 296, 14);
		contentPane.add(lblRaceTypeMsg);

		lblRunNumber = new JLabel("");
		lblRunNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRunNumber.setBounds(581, 45, 220, 23);
		contentPane.add(lblRunNumber);
		
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
	}

	private void setUpContent() {
		
		buttons = new JButton[6];
		radios = new JRadioButton[6];
		
		for(int i = 0; i < buttons.length; ++i) {
			buttons[i] = new JButton("Start");
			buttons[i].setEnabled(false);
			buttons[i].addActionListener(new StartButtonListener(i + 1));
			
			radios[i] = new JRadioButton();
			radios[i].addActionListener(new RadioButtonListener(i + 1));
		}
		
		btnOn = new JButton("OFF");
		btnOn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnOn.getText().equals("OFF")) {
					chrono = new ChronoTimer();
					chrono.addActionListener(new ChronoListener() {
						@Override
						public void onLaneEvent(LaneEvent e) {
							int lane = e.getLane();
							
							if(e.isStart()) {
								buttons[lane - 1].setText("Stop");
							}
							else {
								buttons[lane - 1].setText("Start");
							}
						}
					});

					setContentsEnabled(true);
					
					btnOn.setText("ON");
				}
				else {
					setContentsEnabled(false);
					
					chrono = null; // TODO: check that nothing fucks up because of this
					btnOn.setText("OFF");
				}
			}
		});
		
		btnNewRun = new JButton("New Run");
		btnNewRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					RaceType raceType;
					switch(raceTypeComboBox.getSelectedItem().toString()) {
					case "Indvidual":
						raceType = RaceType.Individual;
					case "Group":
						raceType = RaceType.Group;
					case "Parallel Individual":
						raceType = RaceType.ParallelIndividual;
					case "Parallel Group":
						raceType = RaceType.ParallelGroup;
					default:
						// TODO: different default
						raceType = RaceType.Individual;
					}
					
					chrono.setRaceType(raceType);
					chrono.newRun();
				}
				catch(IllegalStateException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage()); // TODO: change message
				}
			}
		});
		
		setContentsEnabled(false);
	}
	
	private void setContentsEnabled(boolean bool) {
		for(int i = 0; i < buttons.length; ++i) {
			radios[i].setEnabled(bool);
			radios[i].setSelected(false);
			buttons[i].setEnabled(false);
		}
	}
	
	private class StartButtonListener implements ActionListener {
		private int lane;
		
		public StartButtonListener(int lane) {
			this.lane = lane;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(buttons[lane - 1].getText().equals("Start")) {
				chrono.trigger(lane * 2 - 1);
			}
			else {
				chrono.trigger(lane * 2);
			}
		}
		
	}
	
	private class RadioButtonListener implements ActionListener {
		private int lane;
		
		public RadioButtonListener(int lane) {
			this.lane = lane;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(radios[lane - 1].isSelected()) {
				buttons[lane - 1].setEnabled(true);
			}
			else {
				buttons[lane - 1].setEnabled(false);
			}
		}
	}
}
