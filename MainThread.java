
public class MainThread {

	public static void main(String[] args) {
		
		int numGreen = 14;
		int numOrange = 7;
		int numSeats = 10;
		
		if(args.length == 2) {
			numGreen = Integer.parseInt(args[0]);
			numOrange = Integer.parseInt(args[1]);
			numSeats = (numGreen + numOrange) / 2;
		}
		
		ParadeShow ps = new ParadeShow();
		ps.setNumSeats(numSeats);
		
		new Staff(ps);
		new Clock(ps);
		
		//create student threads
		for (int i = 0; i < numGreen; i++) {
			new GreenStudent(i, ps);
		}
		for (int i = 0; i < numOrange; i++) {
			new OrangeStudent(i, ps);
		}

	}

}
