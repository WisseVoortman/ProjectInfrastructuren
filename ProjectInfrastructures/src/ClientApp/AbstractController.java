package ClientApp;

import javax.swing.*;

import java.awt.event.*;

@SuppressWarnings("serial")
public abstract class AbstractController extends JPanel implements ActionListener {

	protected Model model;
	
	public AbstractController(Model model) {
		this.model=model;
	}
	
}
