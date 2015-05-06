package csmsquared.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import javax.swing.Box;
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
	
	private final Color HINT_TEXT_COLOR = Color.GRAY;
	
	private final String ENTER_RUN_TEXT = "Enter run number";
	private final String ENTER_RACER_TEXT = "Enter racer ID";
	
	private final int COMPONENT_SPACING = 3;
	
	private int numLanes;
	private boolean resetInProgress; // This field helps with resetting the JComboBox without firing an event

	private JFrame frame;
	private ChronoTimer chrono;
	
	private JLabel[] laneLabels;
	private JButton[] laneStartButtons;
	private JButton[] laneCancelButtons;
	private JCheckBox[] laneCheckBoxes;
	
	private JLabel currentRunLbl;
	private JButton chronoTimerPowerBtn, newRunBtn, newRacerBtn, printBtn, exportBtn;
	private JTextArea runDisplay, racerQueueDisplay;
	private JTextField printField, exportField, newRacerField;
	private JComboBox<RaceType> runTypeComboBox;
	
	private JPanel powerControls, stateDisplay, runControls, dataOutputControls, laneControls, dataDisplay;

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
		resetInProgress = false;
		
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
		frame.setMinimumSize(new Dimension(750, 300));
		
		initializePowerControls();
		initializeStateDisplay();
		initializeLaneControls();
		initializeRunControls();
		initializeDataOutputControls();
		initializeDataDisplay();
		
		addContents();
		
		setContentsEnabled(false);
	}

	/**
	 * Enable/disable the UI controls.
	 * @param enabled Whether or not the controls should be enabled.
	 */
	private void setContentsEnabled(boolean enabled) {
		for(int i = 0; i < numLanes; ++i) {
			laneStartButtons[i].setEnabled(enabled && laneCheckBoxes[i].isSelected());
			laneStartButtons[i].setText("Start");	// Reset the buttons
			laneCancelButtons[i].setEnabled(false);
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
	
	
	private void resetContents() {
		for(int i = 0; i < numLanes; ++i) {
			laneStartButtons[i].setText("Start");
			laneCheckBoxes[i].setSelected(false);
		}
		
		resetInProgress = true;
		{
			newRacerField.setText(ENTER_RACER_TEXT);
			newRacerField.setForeground(HINT_TEXT_COLOR);
			runTypeComboBox.setSelectedIndex(0);
			printField.setText(ENTER_RUN_TEXT);
			printField.setForeground(HINT_TEXT_COLOR);
			exportField.setText(ENTER_RUN_TEXT);
			exportField.setForeground(HINT_TEXT_COLOR);
		}
		resetInProgress = false;
		
		setContentsEnabled(false);
	}
	
	/**
	 * Add components to the frame.
	 */
	private void addContents() {
		frame.setLayout(new BorderLayout());
		
		JPanel mainControls = new JPanel();
		mainControls.setLayout(new BoxLayout(mainControls, BoxLayout.Y_AXIS));
		
		powerControls.setAlignmentX(Component.LEFT_ALIGNMENT);
		stateDisplay.setAlignmentX(Component.LEFT_ALIGNMENT);
		runControls.setAlignmentX(Component.LEFT_ALIGNMENT);
		dataOutputControls.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		mainControls.add(powerControls);
		mainControls.add(Box.createVerticalStrut(COMPONENT_SPACING));
		mainControls.add(stateDisplay);
		mainControls.add(Box.createVerticalStrut(COMPONENT_SPACING));
		mainControls.add(runControls);
		mainControls.add(Box.createVerticalStrut(COMPONENT_SPACING));
		mainControls.add(dataOutputControls);

		frame.add(mainControls, BorderLayout.EAST);
		frame.add(laneControls, BorderLayout.SOUTH);
		frame.add(dataDisplay, BorderLayout.CENTER);
	}
	
	/**
	 * Initialize the power controls for the ChronoTimer system.
	 */
	private void initializePowerControls() {
		powerControls = new JPanel();
		powerControls.setLayout(new BoxLayout(powerControls, BoxLayout.Y_AXIS));
		
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
								// A racer started
								laneStartButtons[e.getLane() - 1].setText("Stop");
								laneCancelButtons[e.getLane() - 1].setEnabled(true);
							}
							else {
								// A racer finished
								laneStartButtons[e.getLane() - 1].setText("Start");
								laneCancelButtons[e.getLane() - 1].setEnabled(false);
							}
						}
					});
					
					chronoTimerPowerBtn.setText("On");
					setContentsEnabled(true);
					
					startUpdateLoop();
				}
				else {
					chrono = null;
					chronoTimerPowerBtn.setText("Off");
					runDisplay.setText("");
					racerQueueDisplay.setText("");
					
					resetContents();
				}
				
				updateCurrentRunLabel();
			}
		});
		
		// This nested grid layout allows the power button to stretch the full width of the control panel
		JPanel powerPanel = new JPanel();
		powerPanel.setLayout(new GridLayout());
		powerPanel.add(chronoTimerPowerBtn);
		
		powerControls.add(powerPanel);
		
		powerControls.setMaximumSize(new Dimension(powerControls.getMaximumSize().width, powerControls.getPreferredSize().height));
	}
	
	
	private void initializeStateDisplay() {
		stateDisplay = new JPanel();
		stateDisplay.setLayout(new BoxLayout(stateDisplay, BoxLayout.Y_AXIS));
		
		currentRunLbl = new JLabel();
		currentRunLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
		updateCurrentRunLabel();
		
		stateDisplay.add(currentRunLbl);
		stateDisplay.setMaximumSize(stateDisplay.getPreferredSize());
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
		newRacerField = new JTextField(ENTER_RACER_TEXT);
		newRacerField.setPreferredSize(FIELD_SIZE);
		newRacerField.setForeground(HINT_TEXT_COLOR);
		
		newRunBtn = new JButton("New Run");
		newRunBtn.setPreferredSize(BUTTON_SIZE);
		runTypeComboBox = new JComboBox<RaceType>(RaceType.values());
		runTypeComboBox.setPreferredSize(FIELD_SIZE);
		runTypeComboBox.setSelectedIndex(0);	// Default race type is Individual, at index 0
		
		newRacerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(newRacerField.getText());
					chrono.num(id);
					updateRacerQueue();
				}
				catch(NumberFormatException ex) {
					alert("Invalid ID", "Valid racer IDs are integers from 0 to 99,999.");
				}
				catch(IllegalArgumentException ex) {
					alert("Invalid Racer", ex.getMessage());
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
		
		newRacerField.addFocusListener(new TextFieldListener(newRacerField, ENTER_RACER_TEXT));
		
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
					
					updateCurrentRunLabel();
				}
			}
		});
		
		runTypeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(resetInProgress) return; // Do nothing if the UI is resetting
				
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
		newRacerPanel.add(Box.createHorizontalStrut(COMPONENT_SPACING));
		newRacerPanel.add(newRacerBtn);
		
		newRunPanel.add(runTypeComboBox);
		newRunPanel.add(Box.createHorizontalStrut(COMPONENT_SPACING));
		newRunPanel.add(newRunBtn);
		
		runControls.add(newRunPanel);
		runControls.add(Box.createVerticalStrut(COMPONENT_SPACING));
		runControls.add(newRacerPanel);
		
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
		printField = new JTextField(ENTER_RUN_TEXT);
		printField.setPreferredSize(FIELD_SIZE);
		printField.setForeground(HINT_TEXT_COLOR);
		
		exportBtn = new JButton("Export Run");
		exportBtn.setPreferredSize(BUTTON_SIZE);
		exportField = new JTextField(ENTER_RUN_TEXT);
		exportField.setPreferredSize(FIELD_SIZE);
		exportField.setForeground(HINT_TEXT_COLOR);
		
		printBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int runNumber = Integer.parseInt(printField.getText());
					chrono.print(runNumber);
				}
				catch(NumberFormatException ex) {
					alert("Invalid Entry", printField.getText() + " is not a valid run number.\nValid run numbers are integers greater than zero.");
				}
				catch(NoSuchElementException ex) {
					alert("No Such Run", "Run " + printField.getText() + " does not exist.");
				}
				finally {
					printField.setText("");
				}
			}
		});
		
		printField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				printBtn.doClick();
			}
		});
		
		printField.addFocusListener(new TextFieldListener(printField, ENTER_RUN_TEXT));
		
		exportBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int runNumber = Integer.parseInt(exportField.getText());
					chrono.export(runNumber);
				}
				catch(NumberFormatException ex) {
					alert("Invalid Entry", exportField.getText() + " is not a valid run number.\nValid run numbers are integers greater than zero.");
				}
				catch(NoSuchElementException ex) {
					alert("No Such Run", "Run " + exportField.getText() + " does not exist.");
				}
				catch (IOException e1) {
					alert("File Error", "Could not export the run data.");
				}
				finally {
					exportField.setText("");
				}
			}
		});
		
		exportField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportBtn.doClick();
			}
		});
		
		exportField.addFocusListener(new TextFieldListener(exportField, ENTER_RUN_TEXT));
		
		printField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		printBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		exportField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		exportBtn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		printPanel.add(printField);
		printPanel.add(Box.createHorizontalStrut(COMPONENT_SPACING));
		printPanel.add(printBtn);
		
		exportPanel.add(exportField);
		exportPanel.add(Box.createHorizontalStrut(COMPONENT_SPACING));
		exportPanel.add(exportBtn);
		
		dataOutputControls.add(printPanel);
		dataOutputControls.add(Box.createVerticalStrut(COMPONENT_SPACING));
		dataOutputControls.add(exportPanel);
		
		dataOutputControls.setMaximumSize(dataOutputControls.getPreferredSize());
	}
	
	/**
	 * Initialize the controls that govern the race lanes.
	 */
	private void initializeLaneControls() {
		laneControls = new JPanel();
		
		laneLabels = new JLabel[numLanes];
		laneStartButtons = new JButton[numLanes];
		laneCancelButtons = new JButton[numLanes];
		laneCheckBoxes = new JCheckBox[numLanes];
		
		// Build the control panel for each lane, and add them to the main panel
		for(int i = 0; i < numLanes; ++i) {
			JPanel laneControlPanel = new JPanel();
			laneControlPanel.setLayout(new BoxLayout(laneControlPanel, BoxLayout.Y_AXIS));
			
			laneLabels[i] = new JLabel("Lane " + (i + 1));
			laneCheckBoxes[i] = new JCheckBox();
			laneStartButtons[i] = new JButton("Start");
			laneCancelButtons[i] = new JButton("Cancel");
			
			laneStartButtons[i].addActionListener(new LaneStartButtonListener(i + 1));
			laneCancelButtons[i].addActionListener(new LaneCancelButtonListener(i + 1));
			laneCheckBoxes[i].addActionListener(new LaneCheckBoxListener(i + 1));
			
			JPanel labelPanel = new JPanel();
			labelPanel.add(laneCheckBoxes[i]);
			labelPanel.add(laneLabels[i]);

			labelPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
			laneStartButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			laneCancelButtons[i].setAlignmentX(Component.CENTER_ALIGNMENT);

			// Need to set all in order to get the buttons to stay at the right size
			laneStartButtons[i].setPreferredSize(BUTTON_SIZE);
			laneCancelButtons[i].setPreferredSize(BUTTON_SIZE);
			laneStartButtons[i].setMinimumSize(BUTTON_SIZE);
			laneCancelButtons[i].setMinimumSize(BUTTON_SIZE);
			laneStartButtons[i].setMaximumSize(BUTTON_SIZE);
			laneCancelButtons[i].setMaximumSize(BUTTON_SIZE);
			
			laneControlPanel.add(labelPanel);
			laneControlPanel.add(laneStartButtons[i]);
			laneControlPanel.add(Box.createVerticalStrut(COMPONENT_SPACING));
			laneControlPanel.add(laneCancelButtons[i]);
			
			laneControls.add(laneControlPanel);
		}
	}
	
	/**
	 * Initialize the components that display information about the runs.
	 */
	private void initializeDataDisplay() {
		dataDisplay = new JPanel();
		dataDisplay.setLayout(new GridLayout(1, 2));	// This makes the contents stay full-size
		
		runDisplay = new JTextArea();
		racerQueueDisplay = new JTextArea();
		
		JScrollPane runDisplayScrollPane = new JScrollPane(runDisplay);
		JScrollPane racerQueueDisplayScrollPane = new JScrollPane(racerQueueDisplay);

		dataDisplay.add(runDisplayScrollPane);
		dataDisplay.add(racerQueueDisplayScrollPane);
	}
	
	
	private void alert(String title, String message) {
		JOptionPane.showMessageDialog(frame, message, title, JOptionPane.DEFAULT_OPTION);
	}
	
	/**
	 * Start a new thread that updates the text areas with information from the ChronoTimer.
	 */
	private  void startUpdateLoop() {
		Thread updateLoop = new Thread() {
			@Override
			public void run() {
				while(chrono != null) {
					try {
						runDisplay.setText(chrono.toString());
						sleep(10);	// This stops the terrible lag
					}
					catch(ConcurrentModificationException e) {
						// Drive on
					}
					catch(InterruptedException ex) {
						// Keep going
					}
				}
			}
		};
		
		updateLoop.start();
	}
	
	
	private void updateCurrentRunLabel() {
		String run = chrono == null ? "N/A" : Integer.toString(chrono.getRunNumber());
		currentRunLbl.setText("Current Run: " + run);
	}
	
	
	private void updateRacerQueue() {
		LinkedList<Integer> racers = chrono.getRacersInQueue();
		StringBuilder racerQueue = new StringBuilder();
		for(Integer racer : racers) {
			racerQueue.append(racer + "\n");
		}
		
		racerQueueDisplay.setText(racerQueue.toString());
	}
	
	
	private class LaneStartButtonListener implements ActionListener{
		private int lane;
		
		public LaneStartButtonListener(int lane) {
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
			finally {
				updateRacerQueue();
			}
		}
	}
	
	
	private class LaneCancelButtonListener implements ActionListener {
		private int lane;
		
		public LaneCancelButtonListener(int lane) {
			this.lane = lane;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			chrono.cancel(lane);
			laneStartButtons[lane - 1].setText("Start");
			laneCancelButtons[lane - 1].setEnabled(false);
			
			updateRacerQueue();
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
			laneStartButtons[lane - 1].setEnabled(checkBox.isSelected());
		}
	}
	
	
	private class TextFieldListener implements FocusListener {
		private JTextField textField;
		private String defaultMessage;
		
		public TextFieldListener(JTextField textField, String defaultMessage) {
			this.textField = textField;
			this.defaultMessage = defaultMessage;
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			if(textField.getText().equals(defaultMessage)) {
				textField.setText("");
				textField.setForeground(Color.BLACK);
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			if(textField.getText().equals("")) {
				textField.setText(defaultMessage);
				textField.setForeground(HINT_TEXT_COLOR);
			}
		}
	}
}















































