import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class StatusFrame extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5303449573398174949L;
	
	private static String[] stringTimerValues = {"1","100","200","500"}; 
	private static String[] spawnRateValues = {"0","1","2","3","4","5","500"};
	private int antsDead;
	private int turnsTaken;
	private int currentAntPopulation;
	private JLabel labelFoodStock;
	private JLabel labelAntsDead;
	private JLabel labelTurnsTaken;
	private JLabel labelCurrentAntPopulation;
	private JButton visibilityButton;
	private JButton spawnFoodButton;
	private JButton pauseButton;
	private AntSim antSimulator;
	private JComboBox timerValues;
	private JComboBox spawnValues;
	
	public StatusFrame()
	{
		antSimulator = new AntSim(this);
		setLayout(new GridLayout(8,2));
		labelAntsDead = new JLabel();
		labelTurnsTaken = new JLabel();
		labelCurrentAntPopulation = new JLabel();
		labelFoodStock = new JLabel();
		timerValues = new JComboBox(stringTimerValues);
		spawnValues = new JComboBox(spawnRateValues);
		timerValues.setSelectedIndex(2);
		antSimulator.getTimer().setDelay((Integer.valueOf((String)timerValues.getSelectedItem())));
		spawnValues.setSelectedIndex(1);
		spawnValues.addActionListener(this);
		timerValues.addActionListener(this);
		
		
		visibilityButton = new JButton("Show Graphic Window");
		spawnFoodButton = new JButton("Stop Spawning Food");
		pauseButton = new JButton("Play");
		pauseButton.addActionListener(this);
		spawnFoodButton.addActionListener(this);
		visibilityButton.addActionListener(this);
		setAntsDead(0);
		setTurnsTaken(0);
		setCurrentAntPopulation(0);
		add(new JLabel("Ants Dead:"));
		add(labelAntsDead);
		add(new JLabel("Turns Taken:"));
		add(labelTurnsTaken);
		add(new JLabel("Current Population:"));
		add(labelCurrentAntPopulation);
		add(new JLabel("Food Stores: "));
		add(labelFoodStock);
		add(visibilityButton);
		add(spawnFoodButton);
		add(pauseButton);
		add(new JLabel());
		add(new JLabel("Speed: "));
		add(timerValues);
		add(new JLabel("Max Spawn Rate: "));
		add(spawnValues);
		
		
        antSimulator.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        antSimulator.pack();
        antSimulator.setVisible(false);
		
		
	}
	public void setAntsDead(int antsDead) {
		labelAntsDead.setText(String.valueOf(antsDead));
		this.antsDead = antsDead;
	}

	public int getAntsDead() {
		return antsDead;
	}
	public void setTurnsTaken(int turnsTaken) {
		labelTurnsTaken.setText(String.valueOf(turnsTaken));
		this.turnsTaken = turnsTaken;
	}
	public int getTurnsTaken() {
		return turnsTaken;
	}
	public void setCurrentAntPopulation(int currentAntPopulation) {
		labelCurrentAntPopulation.setText(String.valueOf(currentAntPopulation));
		this.currentAntPopulation = currentAntPopulation;
	}
	public int getCurrentAntPopulation() {
		return currentAntPopulation;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() instanceof JButton)
		{
			JButton pushedButton = (JButton) arg0.getSource();
			if(pushedButton == visibilityButton){
					if(pushedButton.getText().equalsIgnoreCase("Hide Graphic Window"))
				{
					antSimulator.setVisible(false);
					pushedButton.setText("Show Window");
				}
				else
				{
					antSimulator.setVisible(true);
					pushedButton.setText("Hide Graphic Window");
				}
			}
			else if(pushedButton == spawnFoodButton)
			{
				if(pushedButton.getText().equalsIgnoreCase("Stop Spawning Food"))
				{
					antSimulator.setSpawnFood(false);
					pushedButton.setText("Start Spawning Food");
				}
				else
				{
					antSimulator.setSpawnFood(true);
					pushedButton.setText("Stop Spawning Food");
				}
			}
			else if(pushedButton == pauseButton)
			{
				if(pushedButton.getText().equalsIgnoreCase("Pause"))
				{
					antSimulator.getTimer().stop();
					pushedButton.setText("Play");
				}
				else if(pushedButton.getText().equalsIgnoreCase("Play"))
				{
					antSimulator.getTimer().start();
					pushedButton.setText("Pause");
				}
			}
		}
		else if(arg0.getSource() instanceof JComboBox)
		{
			JComboBox comboBox = (JComboBox) arg0.getSource();
			if(comboBox == timerValues)
			{
				String speedSelected = (String)comboBox.getSelectedItem();
				antSimulator.getTimer().setDelay(Integer.valueOf(speedSelected));
			}
			else if(comboBox == spawnValues)
			{
				antSimulator.setMaxSpawnRate(Integer.valueOf((String)comboBox.getSelectedItem()));
			}
		}
		
	}
	
	public static void main(String[] args) {
		StatusFrame statusFrame = new StatusFrame();
        JFrame stus = new JFrame();
        stus.add(statusFrame);
        stus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stus.pack();
        stus.setVisible(true);

    }
	public void setFoodStock(int foodStore) {
		labelFoodStock.setText(String.valueOf(foodStore));
	}

}
