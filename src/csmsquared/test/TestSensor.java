package csmsquared.test;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import csmsquared.sensor.Sensor;
import csmsquared.sensor.SensorListener;

public class TestSensor extends JFrame{

	JButton sButton1, sButton2;
	JTextField box1;

	Sensor sensor1, sensor2;

	public TestSensor(){
		super("Test the sensor");
		this.setSize(800, 300);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addShit();

		setVisible(true);
	}

	private void addShit(){
		sensor1 = new Sensor();
		sensor1.addSensorListener(new SensorListener(){

			@Override
			public void sensorTripped() {
				box1.setText("Sensor 1 fired");
			}

		});

		sensor2 = new Sensor();
		sensor2.addSensorListener(new SensorListener(){

			@Override
			public void sensorTripped() {
				box1.setText("Sensor 2 fired");
			}

		});

		sButton1 = new JButton("Sensor 1");
		sButton1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sensor1.trip();
			}
		});

		sButton2 = new JButton("Sensor 2");
		sButton2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sensor2.trip();
			}
		});

		box1 = new JTextField();
		box1.setPreferredSize(new Dimension(100, 50));

		add(sButton1);
		add(sButton2);
		add(box1);
	}

	public static void main(String[] args) {
		TestSensor ts = new TestSensor();
	}

}
