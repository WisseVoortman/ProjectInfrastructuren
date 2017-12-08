package ClientApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



/*
 * the data and the methods to work with it.
 */
public class Model implements Runnable{

	private List<AbstractView> views;
	private int var = 0;
	
    public Model() {
    	//constructor
    }
    
    public void start() {
    	// automaticaly calls run();
		new Thread(this).start();
	}
    
    public void run() {
    	//initial given run method based on a number of ticks
    }
    
    public void stop(){
    	
    }
    
    public void addView(AbstractView view) {
		views.add(view);
	}

    public void updateViews(){
    	
        for( AbstractView v : views ) {
           // v.updateView();
            v.repaint();
        }
    }
    
    public List<AbstractView> getViews() {
		return views;
	};
}

