package csmsquared.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import com.google.appengine.api.xmpp.MessageType;

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
		
		setContentEnabled(false);
	}

	/**
	 * Enable/disable the UI controls.
	 * @param enabled Whether or not the controls should be enabled.
	 */
	private void setContentEnabled(boolean enabled) {
		for(int i = 0; i < numLanes; ++i) {
			laneButtons[i].setEnabled(enabled);
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
		chronoTimerPowerBtn = new JButton("On");
		// TODO: add action listener
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
			
			laneButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
				}
			});
			
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
		
		// TODO: add listeners
		
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
	
	
	private class LaneButtonListener implements ActionListener{
		private int lane;
		private int startChannel;
		private int stopChannel;
		
		public LaneButtonListener(int lane) {
			this.lane = lane;
			stopChannel = lane * 2;
			startChannel = stopChannel - 1;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			
			if(source.getText().equals("Start")) {
				chrono.trigger(startChannel);
			}
			else if(source.getText().equals("Stop")) {
				chrono.trigger(stopChannel);
			}
			else {
				JOptionPane.showMessageDialog(frame, "Something's wrong with the lanes.\nTry resarting the application.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
