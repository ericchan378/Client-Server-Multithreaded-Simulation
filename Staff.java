
public class Staff implements Runnable{
	
	public static long time = System.currentTimeMillis();
	
	private ParadeShow ps = null;
	private String name = "Staff";
	
	public Staff (ParadeShow ps) {
		this.ps = ps;
		new Thread(this).start();
	}

	@Override
	public void run() {
		
		while(true) {
			if(ps.isMoreShows() == true) {
				ps.staffAction();
			}
			else {
				break;
			}
		}
		msg("Finished execution.");
	}
	
	public String getName() {
		return this.name;
	}
	
	public void msg(String m) {
		 System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
	}

}
