package ClientApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {
	private static final String version = "1.0";
	
	// insert model
	private Model model;
	
	// insert main window
	private JFrame screen;
	
	// insert controller
	private Controller controller;
	
	// insert views
	private View view;
	
    public Main() {
    	//constructor   
    	System.out.println("je moet gewoon lezen....");
    	//create an instance of Model
    	model=new Model();
    	System.out.println("Model made....");
    	
    	// creat an instance of Controller
    	controller=new Controller(model);
    	System.out.println("Controller made....");
    	
    	// create an instance of View
    	view=new View(model);
    	System.out.println("View made....");
    	// create a Root window
    	screen=new JFrame("Client application");
		screen.setSize(1600, 1300);
		screen.setResizable(true);
		screen.setLayout(null);
		screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//screen.setIconImage(new ImageIcon(getClass().getResource("resource/Icon.png")).getImage());
		
		//- Full screen
		screen.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//make the menubar
		makeMenuBar(screen);
		
		// add Controllers and view elements below
		
		screen.setVisible(true);
    }
	
	public void methodname(){
		System.out.println("je moet gewoon lezen.....");
	}
	
	private void makeMenuBar(JFrame screen)
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        screen.setJMenuBar(menubar);
        
        JMenu menu;
        JMenuItem item;
        
        // create the Options menu
        menu = new JMenu("Options");
        menubar.add(menu);
        
        item = new JMenuItem("Fullscreen");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, SHORTCUT_MASK));
            item.addActionListener(e -> {
                this.screen.dispose();
                this.screen.setUndecorated(!this.screen.isUndecorated());
                this.screen.setExtendedState(JFrame.MAXIMIZED_BOTH);
                this.screen.setVisible(true);
            });
        menu.add(item);

        menu.addSeparator();
		   	
        item = new JMenuItem("Quit");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            item.addActionListener(e -> quit());
        menu.add(item);
        
     // create the Options 2 menu
        menu = new JMenu("Options 2");
        menubar.add(menu);
        
        item = new JMenuItem("Menu Item 1");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, SHORTCUT_MASK));
            item.addActionListener(e -> methodname());
		menu.add(item);
        
		item = new JMenuItem("Menu Item 2");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, SHORTCUT_MASK));
            item.addActionListener(e -> methodname());
		menu.add(item);
		
		item = new JMenuItem("Menu Item 3");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, SHORTCUT_MASK));
            item.addActionListener(e -> methodname());
		menu.add(item);
		
		item = new JMenuItem("Menu Item 4");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, SHORTCUT_MASK));
            item.addActionListener(e -> methodname());
		menu.add(item);
		
		item = new JMenuItem("Menu Item 5");
        	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, SHORTCUT_MASK));
        	item.addActionListener(e -> methodname());
        menu.add(item);
        
     // create the Over menu
        menu = new JMenu("Over...");
        menubar.add(menu);
        
        item = new JMenuItem("Over dit programma");
    		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
    		item.addActionListener(e -> showAbout());
    	menu.add(item);
    	
    	item = new JMenuItem("Het Team");
			item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, SHORTCUT_MASK));
			item.addActionListener(e -> showTeam());
		menu.add(item);
        
    } 

    private void quit() {
        System.exit(0);
    }

    private void showAbout() {
    	JOptionPane.showMessageDialog(screen, 
                "Cliënt application\n" + "Versie: " + version,
                "Cliënt application", 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showTeam() {
    	JOptionPane.showMessageDialog(screen, 
                "Project Thema 2.2\n" + "Cliënt Application\n" + "Team: " + "Jesse van Veen, " + "Mitchel van Rijn, " + "Tobias Schiphorst, " + "Rudolf Klijnhout, " + "Wisse Voortman",
                "Project Infrastructures - Jousting-systems-ltd", 
                JOptionPane.INFORMATION_MESSAGE);
    }
}