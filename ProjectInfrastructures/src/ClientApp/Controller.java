package ClientApp;

import javax.swing.*;

import java.awt.event.*;

@SuppressWarnings("serial")
public class Controller extends AbstractController {
	
	private Model model;
	public JButton start;
	public JButton stop;
	private JButton tick;
	private JButton ticksHour;
	private JButton ticksDay;
	
	public Controller(Model model) {
		super(model);
		this.model=model;
		
		start=new JButton("start");
		start.addActionListener(this);
		stop=new JButton("stop");
		stop.addActionListener(this);
		stop.setEnabled(false);
		tick=new JButton("1 minuut verder");
		tick.addActionListener(this);
		ticksHour=new JButton("1 uur verder");
		ticksHour.addActionListener(this);
		ticksDay=new JButton("1 dag verder");
		ticksDay.addActionListener(this);
		
		
		this.setLayout(null);
		add(start);
		add(stop);
		add(tick);
		add(ticksHour);
		add(ticksDay);
		// setBounds x/horizontal co�rdinates, y/vertical co�rdinates, width, Height,
		start.setBounds(25, 0, 100, 30);
		stop.setBounds(135, 0, 100, 30);
		tick.setBounds(245, 0, 125, 30);
		ticksHour.setBounds(380, 0, 120, 30);
		ticksDay.setBounds(510, 0, 120, 30);

		setVisible(true);
	}
		
	public void actionPerformed(ActionEvent e) {	
		if (e.getSource()==start) {
			System.out.println("Starting the simulator!");
			model.start();
			this.start.setEnabled(false);
			this.tick.setEnabled(false);
			this.ticksHour.setEnabled(false);
			this.ticksDay.setEnabled(false);
			this.stop.setEnabled(true);
			System.out.println("The simulator has been started!");
		}
		
		if (e.getSource()==stop) {
			System.out.println("Stopping the simulator!");
			model.stop();
			this.start.setEnabled(true);
			this.tick.setEnabled(true);
			this.ticksHour.setEnabled(true);
			this.ticksDay.setEnabled(true);
			this.stop.setEnabled(false);
			System.out.println("The simulator has been stopped!");
		}
		
		if (e.getSource()==tick) {
			System.out.println("Doing a single tick");
			//model.tick();
			System.out.println("Done with doing a single tick");
		}
		
		if (e.getSource()==ticksHour) {
			System.out.println("Starting the simulator for 60 ticks");
			new Thread( ()-> {
				this.start.setEnabled(false);
				this.tick.setEnabled(false);
				this.ticksHour.setEnabled(false);
				this.ticksDay.setEnabled(false);
				//model.ticksHour();
				this.start.setEnabled(true);
				this.tick.setEnabled(true);
				this.ticksHour.setEnabled(true);
				this.ticksDay.setEnabled(true);
			}).start();
			System.out.println("60 ticks done!");
		}
		
		if (e.getSource()==ticksDay) {
			System.out.println("Starting a day worth of ticks");
			new Thread( ()-> {
				this.start.setEnabled(false);
				this.tick.setEnabled(false);
				this.ticksHour.setEnabled(false);
				this.ticksDay.setEnabled(false);
				//model.ticksDay();
				this.start.setEnabled(true);
				this.tick.setEnabled(true);
				this.ticksHour.setEnabled(true);
				this.ticksDay.setEnabled(true);
			}).start();
			System.out.println("day worth of ticks done!");
		}
	}
	
}
