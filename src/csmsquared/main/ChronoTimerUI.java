package csmsquared.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
	private JLabel runTypeLbl, exportLbl;
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
		
		initializeChronoTimerControls();
		initializeLaneControls();
		initializeRunControls();
		initializeDataOutputControls();
		initializeDataDisplays();
		
		addContents();
		
		setContentEnabled(false);
	}

	
	private void setContentEnabled(boolean enabled) {
		// TODO: turn on/off content with the chrono timer
	}
	
	
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
	
	
	private void initializeChronoTimerControls() {
		chronoTimerPowerBtn = new JButton("On");
		// TODO: add action listener
	}
	
	
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
			
			laneControlPanel.add(laneLabels[i]);
			laneControlPanel.add(laneButtons[i]);
			laneControlPanel.add(laneCheckBoxes[i]);
			
			// TODO: add listeners to lane controls
			
			laneControls.add(laneControlPanel);
		}
	}
	
	
	private void initializeRunControls() {
		runControls = new JPanel();
		runControls.setLayout(new BoxLayout(runControls, BoxLayout.Y_AXIS));
		
//		JPanel newRacerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//		JPanel newRunPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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
	
	
	private void initializeDataDisplays() {
		dataDisplays = new JPanel();
		
		runDisplay = new JTextArea();
		racerQueueDisplay = new JTextArea();
		
		JScrollPane runDisplayScrollPane = new JScrollPane(runDisplay);
		JScrollPane racerQueueDisplayScrollPane = new JScrollPane(racerQueueDisplay);
		
		dataDisplays.add(runDisplayScrollPane);
		dataDisplays.add(racerQueueDisplayScrollPane);
	}
}
