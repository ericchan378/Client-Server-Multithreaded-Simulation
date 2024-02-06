import java.util.Random;
import java.util.Vector;

public class GreenStudent implements Runnable{
	
	public static long time = System.currentTimeMillis();
	
	private String name;
	//private int id;
	private ParadeShow ps = null;
	private Vector<String> parades = new Vector<String>();
	private Vector<String> shows = new Vector<String>();
	
	public GreenStudent (int id, ParadeShow ps) {
		setName ("GreenStudent-" + id);
		//this.id = id;
		this.ps = ps;
		
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		
		Random r = new Random();
		try {
			//sleep random in milliseconds (10ms = 1min)
			Thread.sleep(r.nextInt(4500));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while(true) {
				if (ps.isMoreParades() == true) {
					msg("Student has arrived at the meeting space for the parade.");
					//gather at meeting place to form groups and march
					ps.gatherGreen(name);
					parades.addElement(ps.getCurrentParade());
					
					msg("Student is having a snack");
					//snack break
					ps.snack();
				}
				else {
					break;
				}
				
				if (ps.isMoreShows() == true) {
					msg("Student has arrived at circus tent entrance for the show.");
					ps.watchShow(name);
					shows.addElement(ps.getCurrentShow());
				}
				else {
					break;
				}
		}
		msg("Finished execution.");
		
/*		msg("Marched " + parades.size() + " parades.");
		while(parades.size() > 0) {
			msg("Marched the parade at " + parades.elementAt(0));
			parades.removeElementAt(0);
		}
		while(shows.size() > 0) {
			msg("Marched the parade at " + shows.elementAt(0));
			shows.removeElementAt(0);
		}*/
	}
	
	public void setName (String n) {
		this.name = n;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void msg(String m) {
		 System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
	}

}
