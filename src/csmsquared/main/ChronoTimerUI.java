package csmsquared.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import csmsquared.chronoEvents.ChronoListener;
import csmsquared.chronoEvents.LaneEvent;
import csmsquared.race.RaceType;

public class ChronoTimerUI {
	private final Dimension DEFAULT_WINDOW_SIZE = new Dimension(800, 600);
	private final Dimension BUTTON_SIZE = new Dimension(110, 25);
	private final Dimension FIELD_SIZE = new Dimension(200, 25);
	
	private int numLanes;

	private JFrame frame;
	private ChronoTimer chrono;
	
	private JLabel[] laneLabels;
	private JButton[] laneButtons;
	private JCheckBox[] laneCheckBoxes;
	
	private JButton chronoTimerPowerBtn, newRunBtn, newRacerBtn, printBtn, exportBtn;
	private JTextArea runDisplay, racerQueueDisplay;
	private JTextField printField, exportField, newRacerField;
	private JComboBox<RaceType> runTypeComboBox;
	
	private JPanel laneControls, runControls, dataOutputControls, dataDisplays;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChronoTimerUI window = new ChronoTimerUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChronoTimerUI() {
		numLanes = ChronoTimer.NUM_CHANNELS / 2;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(DEFAULT_WINDOW_SIZE);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initializePowerControls();
		initializeLaneControls();
		initializeRunControls();
		initializeDataOutputControls();
		initializeDataDisplays();
		
		addContents();
		
		setContentsEnabled(false);
	}

	/**
	 * Enable/disable the UI controls.
	 * @param enabled Whether or not the controls should be enabled.
	 */
	private void setContentsEnabled(boolean enabled) {
		for(int i = 0; i < numLanes; ++i) {
			laneButtons[i].setEnabled(enabled && laneCheckBoxes[i].isSelected());
			laneButtons[i].setText("Start");	// Reset the buttons
			laneCheckBoxes[i].setEnabled(enabled);
			laneCheckBoxes[i].setSelected(false);	// Reset the check boxes
		}
		
		newRunBtn.setEnabled(enabled);
		newRacerBtn.setEnabled(enabled);
		printBtn.setEnabled(enabled);
		exportBtn.setEnabled(enabled);
		runDisplay.setEnabled(enabled);
		racerQueueDisplay.setEnabled(enabled);
		printField.setEnabled(enabled);
		exportField.setEnabled(enabled);
		newRacerField.setEnabled(enabled);
		runTypeComboBox.setEnabled(enabled);
	}
	
	/**
	 * Add components to the frame.
	 */
	private void addContents() {
		frame.setLayout(new BorderLayout());
		
		JPanel mainControls = new JPanel();
		mainControls.setLayout(new BoxLayout(mainControls, BoxLayout.Y_AXIS));
		
		chronoTimerPowerBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		runControls.setAlignmentX(Component.RIGHT_ALIGNMENT);
		dataOutputControls.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		mainControls.add(chronoTimerPowerBtn);
		mainControls.add(runControls);
		mainControls.add(dataOutputControls);

		frame.add(mainControls, BorderLayout.EAST);
		frame.add(laneControls, BorderLayout.SOUTH);
		frame.add(dataDisplays, BorderLayout.CENTER);
	}
	
	/**
	 * Initialize the power controls for the ChronoTimer system.
	 */
	private void initializePowerControls() {
		chronoTimerPowerBtn = new JButton("Off");
		chronoTimerPowerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(chronoTimerPowerBtn.getText().equals("Off")) {
					chrono = new ChronoTimer();
					chrono.addActionListener(new ChronoListener() {
						@Override
						public void onLaneEvent(LaneEvent e) {
							runTypeComboBox.setEnabled(false);	// Cannot change during a race
							
							if(e.isStart()) {
								laneButtons[e.getLane() - 1].setText("Stop");
							}
							else {
								laneButtons[e.getLane() - 1].setText("Start");
							}
						}
					});
					
					chronoTimerPowerBtn.setText("On");
					setContentsEnabled(true);
				}
				else {
					chrono = null;
					chronoTimerPowerBtn.setText("Off");;
					setContentsEnabled(false);
				}
			}
		});
	}
	
	/**
	 * Initialize the controls that govern the race lanes.
	 */
	private void initializeLaneControls() {
		laneControls = new JPanel();
		
		laneLabels = new JLabel[numLanes];
		laneButtons = new JButton[numLanes];
		laneCheckBoxes = new JCheckBox[numLanes];
		
		// Build the control panel for each lane, and add them to the main panel
		for(int i = 0; i < numLanes; ++i) {
			JPanel laneControlPanel = new JPanel();
			laneControlPanel.setLayout(new BoxLayout(laneControlPanel, BoxLayout.Y_AXIS));
			
			laneLabels[i] = new JLabel("Lane " + 1);
			laneButtons[i] = new JButton("Start");
			laneCheckBoxes[i] = new JCheckBox();
			
			laneButtons[i].addActionListener(new LaneButtonListener(i + 1));
			laneCheckBoxes[i].addActionListener(new LaneCheckBoxListener(i + 1));
			
			laneControlPanel.add(laneLabels[i]);
			laneControlPanel.add(laneButtons[i]);
			laneControlPanel.add(laneCheckBoxes[i]);
			
			laneControls.add(laneControlPanel);
		}
	}
	
	/**
	 * Initialize the controls for creating runs and adding racers.
	 */
	private void initializeRunControls() {
		runControls = new JPanel();
		runControls.setLayout(new BoxLayout(runControls, BoxLayout.Y_AXIS));
		
		JPanel newRacerPanel = new JPanel();
		newRacerPanel.setLayout(new BoxLayout(newRacerPanel, BoxLayout.X_AXIS));
		JPanel newRunPanel = new JPanel();
		newRunPanel.setLayout(new BoxLayout(newRunPanel, BoxLayout.X_AXIS));
		
		newRacerBtn = new JButton("Add Racer");
		newRacerBtn.setPreferredSize(BUTTON_SIZE);
		newRacerField = new JTextField("Enter racer id");
		newRacerField.setPreferredSize(FIELD_SIZE);
		
		newRunBtn = new JButton("New Run");
		newRunBtn.setPreferredSize(BUTTON_SIZE);
		runTypeComboBox = new JComboBox<RaceType>(RaceType.values());
		runTypeComboBox.setPreferredSize(FIELD_SIZE);
		runTypeComboBox.setSelectedIndex(1);
		
		newRacerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(newRacerField.getText());
					chrono.num(id);
				}
				catch(NumberFormatException ex) {
					alert("Invalid ID", "Valid racer IDs are integers from 1 to 99999");
				}
				finally {
					// Clear the field to allow the user to quickly enter another number
					newRacerField.setText("");
				}
			}
		});
		
		newRacerField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newRacerBtn.doClick();
			}
		});
		
		newRunBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// This will end the run if it exists. If it doesn't exist, endRun()
				// will throw an exception, but either way, newRun() gets called.
				try {
					chrono.endRun();
				}
				finally {
					chrono.newRun();
					runTypeComboBox.setEnabled(true);
				}
			}
		});
		
		runTypeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					chrono.setRaceType((RaceType) runTypeComboBox.getSelectedItem());
				}
				catch(IllegalStateException ex) {
					alert("Cannot do that", "Cannot change the race type in the middle of a race");
				}
			}
		});
		
		newRacerBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		newRacerField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		newRunBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		runTypeComboBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		newRacerPanel.add(newRacerField);
		newRacerPanel.add(newRacerBtn);
		
		newRunPanel.add(runTypeComboBox);
		newRunPanel.add(newRunBtn);
		
		runControls.add(newRacerPanel);
		runControls.add(newRunPanel);
		
		runControls.setMaximumSize(runControls.getPreferredSize());
	}
	
	/**
	 * Initialize the controls for printing and exporting run data.
	 */
	private void initializeDataOutputControls() {
		dataOutputControls = new JPanel();
		dataOutputControls.setLayout(new BoxLayout(dataOutputControls, BoxLayout.Y_AXIS));
		
		JPanel printPanel = new JPanel();
		printPanel.setLayout(new BoxLayout(printPanel, BoxLayout.X_AXIS));
		JPanel exportPanel = new JPanel();
		exportPanel.setLayout(new BoxLayout(exportPanel, BoxLayout.X_AXIS));
		
		printBtn = new JButton("Print Run");
		printBtn.setPreferredSize(BUTTON_SIZE);
		printField = new JTextField("Enter run number");
		printField.setPreferredSize(FIELD_SIZE);
		
		exportBtn = new JButton("Export Run");
		exportBtn.setPreferredSize(BUTTON_SIZE);
		exportField = new JTextField("Enter run number");
		exportField.setPreferredSize(FIELD_SIZE);
		
		// TODO: add listeners
		
		printField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		printBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		exportField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		exportBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		printPanel.add(printField);
		printPanel.add(printBtn);
		
		exportPanel.add(exportField);
		exportPanel.add(exportBtn);
		
		dataOutputControls.add(printPanel);
		dataOutputControls.add(exportPanel);
		
		dataOutputControls.setMaximumSize(dataOutputControls.getPreferredSize());
	}
	
	/**
	 * Initialize the components that display information about the runs.
	 */
	private void initializeDataDisplays() {
		dataDisplays = new JPanel();
		
		runDisplay = new JTextArea();
		racerQueueDisplay = new JTextArea();
		
		JScrollPane runDisplayScrollPane = new JScrollPane(runDisplay);
		JScrollPane racerQueueDisplayScrollPane = new JScrollPane(racerQueueDisplay);
		
		dataDisplays.add(runDisplayScrollPane);
		dataDisplays.add(racerQueueDisplayScrollPane);
	}
	
	
	private void alert(String title, String message) {
		JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	
	private class LaneButtonListener implements ActionListener{
		private int lane;
		
		public LaneButtonListener(int lane) {
			this.lane = lane;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			
			try {
				if(source.getText().equals("Start")) {
					chrono.start(lane);
				}
				else {
					chrono.stop(lane);
				}
			}
			catch(IllegalStateException ex) {
				alert("Cannot " + source.getText(), ex.getMessage());
			}
		}
	}
	
	
	private class LaneCheckBoxListener implements ActionListener {
		private int lane;
		
		public LaneCheckBoxListener(int lane) {
			this.lane = lane;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JCheckBox checkBox = (JCheckBox) e.getSource();
			
			chrono.toggle(lane * 2 - 1);
			chrono.toggle(lane * 2);
			laneButtons[lane - 1].setEnabled(checkBox.isSelected());
		}
	}
}















































