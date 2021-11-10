import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

class House implements Comparable<House>{
	private int id;
	private int duration;
	private double rating;
	protected static ArrayList<House> fullHouses = new ArrayList<House>();
	protected static ArrayList<House> availableHouses = new ArrayList<House>();

	public House(int id, int duration, double rating) {
		this.id=id;
		this.duration = duration;
		this.rating= rating;
	}

	@Override
	public int compareTo(House h) {
		// TODO Auto-generated method stub
		return this.id - h.id;
	}
	
	//checks if the student and the house can be matched
	protected boolean isMatching(Student s) {
		if(s.minRating<=this.rating && this.duration==0 && s.duration!=0)
			return true;
		return false;
	}
	
	//does the operation of locating a student in a house
	//turns the available house into a full one
	public void locate(Student s) {
		this.duration+=s.duration;
		fullHouses.add(this);
	}
	
	//updates the lists of full and available houses for the new semester
	protected static void newSemester() {
		Iterator<House> itr = fullHouses.iterator();
		while(itr.hasNext()) {
			House h = itr.next();
			
			h.duration-=1;
			
			if(h.duration==0) {
				availableHouses.add(h);
				itr.remove();
			}
		}
		Collections.sort(fullHouses);
		Collections.sort(availableHouses);
	}
	
}