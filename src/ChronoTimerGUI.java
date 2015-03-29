import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;


public class ChronoTimerGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterId;
	private JLabel lblChronoTimer;
	private JLabel lblRaceType;

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
		setBounds(100, 100, 551, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddPlayer = new JLabel("Add Player : ");
		lblAddPlayer.setBounds(31, 83, 72, 14);
		contentPane.add(lblAddPlayer);
		
		txtEnterId = new JTextField();
		txtEnterId.setText("Enter ID");
		txtEnterId.setBounds(95, 80, 50, 20);
		contentPane.add(txtEnterId);
		txtEnterId.setColumns(10);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setBounds(150, 79, 60, 23);
		contentPane.add(btnNewButton);
		
		lblChronoTimer = new JLabel("Chrono Timer");
		lblChronoTimer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChronoTimer.setBounds(199, 30, 165, 38);
		contentPane.add(lblChronoTimer);
		
		lblRaceType = new JLabel("Race Type :");
		lblRaceType.setBounds(31, 126, 72, 14);
		contentPane.add(lblRaceType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Single", "Group Race"}));
		comboBox.setBounds(95, 123, 60, 20);
		contentPane.add(comboBox);
		
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
		
		JTextArea ActivityMonitor = new JTextArea();
		ActivityMonitor.setWrapStyleWord(true);
		ActivityMonitor.setBounds(377, 121, 129, 235);
		contentPane.add(ActivityMonitor);
		
		JLabel lblActivityMonitor = new JLabel("Activity Monitor");
		lblActivityMonitor.setBounds(398, 96, 89, 14);
		contentPane.add(lblActivityMonitor);
		
		JTextArea RacerList = new JTextArea();
		RacerList.setWrapStyleWord(true);
		RacerList.setBounds(285, 121, 82, 195);
		contentPane.add(RacerList);
		
		JLabel lblRacerList = new JLabel("Racer List");
		lblRacerList.setBounds(297, 96, 67, 14);
		contentPane.add(lblRacerList);
	}
}
