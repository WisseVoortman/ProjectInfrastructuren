package ClientApp;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class View extends AbstractView {

	private Model model;
	private JLabel label;
	
	public View(Model model) {
		super(model);
		this.model=model;

		
	}

}
