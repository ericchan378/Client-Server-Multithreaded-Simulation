
public class Clock implements Runnable{
	
	public static long time = System.currentTimeMillis();
	
	private ParadeShow ps = null;
	private String name = "Clock";
	
	public Clock (ParadeShow ps) {
		this.ps = ps;
		new Thread(this).start();
	}

	@Override
	public void run() {

		//day starts at 10:30am
		msg("Day has started at 10:30AM");
		//clock sleeps 30 minutes (1 min = 100 ms) until 11:00am
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//11:00AM
		msg("Parade has started at 11:00AM");
		ps.startMarch("11:00AM");
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//11:15AM
		msg("Show has started at 11:15AM");
		ps.showStart("11:15AM");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//11:45AM
		msg("Show has ended at 11:45AM");
		ps.showEnd();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//12:00PM
		msg("Parade has started at 12:00PM");
		ps.startMarch("12:00PM");
		
		try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//12:45PM
		msg("Show has started at 12:45PM");
		ps.showStart("12:45AM");
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//1:00PM
		msg("Parade has started at 1:00PM");
		ps.startMarch("1:00PM");
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//1:15PM
		msg("Show has ended at 1:15PM");
		ps.showEnd();
		
		try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//2:00PM
		msg("Parade has started at 2:00PM");
		ps.startMarch("2:00PM");
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//2:15PM
		msg("Show has started at 2:15PM");
		ps.showStart("2:15PM");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//2:45PM
		msg("Show has ended at 2:45PM");
		ps.showEnd();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//3:00PM
		msg("Parade has started at 3:00PM");
		ps.startMarch("3:00PM");
		
		try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//3:45PM
		msg("Show has started at 3:45PM");
		ps.showStart("3:45AM");
		ps.setNoMoreShows();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//4:00PM
		msg("Parade has started at 4:00PM");
		ps.startMarch("4:00PM");
		ps.setNoMoreParades();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//4:15PM
		msg("Show has ended at 4:15PM");
		ps.showEnd();
		
		try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//5:00PM
		msg("The day has ended at 5:00PM");
		
		msg("Finished execution.");
	}
	
	public String getName() {
		return this.name;
	}
	
	public void msg(String m) {
		 System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
	}

}
