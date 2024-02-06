import java.util.Random;
import java.util.Vector;

public class ParadeShow {
	public static long time = System.currentTimeMillis();
	
	//parade
	private Vector<Object> waitingGreen = new Vector<Object>();
	private Vector<Object> waitingOrange = new Vector<Object>();
	private Vector<Object> paradeGroup = new Vector<Object>();
	private int marchTime;
	private String currentParade;
	private boolean moreParades = true;
	
	//show
	private int numSeats;
	private Boolean entryOpen = true;
	private Object staff = new Object();
	private Vector<Object> showLine = new Vector<Object>();
	private Vector<Object> tent = new Vector<Object>();
	private String currentShow;
	private Boolean moreShows = true;
	
	public void gatherGreen(String name) {
		
		Object g = new Object();
		Vector<Object> group = new Vector<Object>();
		
		synchronized(g) {
			
			if(waitingGreen.size() > 0 && waitingOrange.size() > 0) {
				
				group.addElement(waitingGreen.elementAt(0));
				group.addElement(waitingOrange.elementAt(0));
				
				waitingGreen.removeElementAt(0);
				waitingOrange.removeElementAt(0);
				
				//System.out.println("Group created.");
				paradeGroup.addElement(g);
				
				if(moreParades == true) {
					try {
						g.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else {
					return;
				}
				
				//March for some time
				try {
					Thread.sleep(marchTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				//notify green and orange students after finish marching
				while(group.size() > 0) {
					synchronized(group.elementAt(0)) {
						group.elementAt(0).notify();
						group.removeElementAt(0);
					}
				}
			}
			else {
				try {
					waitingGreen.addElement(g);
					g.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public void gatherOrange(String name) {
		
		Object o = new Object();
		Vector<Object> group = new Vector<Object>();
		
		synchronized(o) {
			if(waitingGreen.size() > 1) {
				
				group.addElement(waitingGreen.elementAt(1));
				group.addElement(waitingGreen.elementAt(0));
				
				waitingGreen.removeElementAt(1);
				waitingGreen.removeElementAt(0);
				
				//System.out.println("Group created.");
				paradeGroup.addElement(o);
				
				if(moreParades == true) {
					try {
						o.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else {
					return;
				}
				
				//March for some time
				try {
					Thread.sleep(marchTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//System.out.println("["+(System.currentTimeMillis()-time)+"] "+name + ": has finished marching.");
				//notify green and orange students after finish marching
				while(group.size() > 0) {
					synchronized(group.elementAt(0)) {
						group.elementAt(0).notify();
						group.removeElementAt(0);
					}
				}
			}
			else {
				try {
					waitingOrange.addElement(o);
					o.wait();
					System.out.println("["+(System.currentTimeMillis()-time)+"] "+name + ": has finished marching.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//clock
	public void startMarch(String time) {
		//System.out.println(paradeGroup.size());
		
		this.currentParade = time;
		Random r = new Random();
		//marching random time from 10-25 minutes
		this.marchTime = (r.nextInt(15) * 100) + 1000;
		
		//notify groups to march
		while(paradeGroup.size() > 0) {
			synchronized(paradeGroup.elementAt(0)) {
				paradeGroup.elementAt(0).notify();
				paradeGroup.removeElementAt(0);
			}
		}
	}
	
	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}
	
	//students line up for show
	public void watchShow(String name) {
		Object s = new Object();
		Random r = new Random();
		
		synchronized(s) {
			while(moreShows == true) {
				System.out.println("["+(System.currentTimeMillis()-time)+"] "+name + ": is now on line for the show.");
				showLine.addElement(s);
				
				try {
					s.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
				if(moreShows == true) {
					if(entryOpen == true) {
						//if there is room in the tent, take a seat
						if(tent.size() < numSeats) {
							tent.addElement(s);
							System.out.println("["+(System.currentTimeMillis()-time)+"] "+name + ": has taken a seat for the show.");
							try {
								s.wait();
								System.out.println("["+(System.currentTimeMillis()-time)+"] "+name + ": has finished watching the show.");
								break;
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						//else wander the park for a while (5-10 minutes)
						else {
							try {
								System.out.println("["+(System.currentTimeMillis()-time)+"] "+name + ": is wandering the park.");
								Thread.sleep((r.nextInt(5) * 100) + 500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if(moreShows == false) {
								break;
							}
						}
					}
				}
				else {
					break;
				}
			}
		}
	}
	
	//actions that the staff will do
	public void staffAction() {
		
		synchronized(staff) {
			while(true) {
				//if show is in session, wait for it to end
				if(entryOpen == false) {
					try {
						staff.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				//show ended, open entry
				entryOpen = true;
				
				//previous students leave
				while(tent.size() > 0) {
					synchronized(tent.elementAt(0)) {
						tent.elementAt(0).notify();
						tent.removeElementAt(0);
					}
				}
				
				if(moreShows == true) {
					//waiting students released
					while(showLine.size() > 0) {
						synchronized(showLine.elementAt(0)) {
							showLine.elementAt(0).notify();
							showLine.removeElementAt(0);
						}
					}
				}
				else {
					break;
				}
				
				//wait for clock to signal show start
				try {
					staff.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				//show starting, close entry
				entryOpen = false;
			}
		}
	}
	
	//clock to notify staff of show start and end
	public void showStart(String time) {
		this.currentShow = time;
		
		synchronized(staff) {
			staff.notify();
		}
	}
	
	public void showEnd() {
		synchronized(staff) {
			staff.notify();
		}
	}
	
	
	public String getCurrentParade() {
		return this.currentParade;
	}
	
	public String getCurrentShow() {
		return this.currentShow;
	}
	
	public void setNoMoreShows() {
		this.moreShows = false;
		while(showLine.size() > 0) {
			synchronized(showLine.elementAt(0)) {
				showLine.elementAt(0).notify();
				showLine.removeElementAt(0);
			}
		}
	}
	
	public Boolean isMoreShows() {
		return this.moreShows;
	}
	
	public void setNoMoreParades() {
		this.moreParades = false;
		
		while(waitingGreen.size() > 0) {
			synchronized(waitingGreen.elementAt(0)) {
				waitingGreen.elementAt(0).notify();
				waitingGreen.removeElementAt(0);
			}
		}
		
		while(waitingOrange.size() > 0) {
			synchronized(waitingOrange.elementAt(0)) {
				waitingOrange.elementAt(0).notify();
				waitingOrange.removeElementAt(0);
			}
		}
	}
	
	public Boolean isMoreParades() {
		return this.moreParades;
	}
	
	public void snack() {
		Random r = new Random();
		try {
			Thread.sleep((r.nextInt(5) * 100) + 500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
