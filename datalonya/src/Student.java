import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

class Student implements Comparable<Student>{
	
	private int id;
	protected String name;
	protected int duration;
	double minRating;
	public static ArrayList<Student> studentsWaiting = new ArrayList<Student>();
	public static PriorityQueue<Student> studentsWithNoHouse = new PriorityQueue<Student>();
 
	public Student(int id, String name, int duration, double minRating) {
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.minRating = minRating;
	}

	@Override
	public int compareTo(Student s) {
		// TODO Auto-generated method stub
		return this.id - s.id;
	}
	
	//updates the waiting list and the students that couldn't get a house for the new semester
	protected static void newSemester() {
		Iterator<Student> itr = studentsWaiting.iterator();
		while(itr.hasNext()) {
			Student s = itr.next();
			s.duration-=1;
			if(s.duration==0) {
				studentsWithNoHouse.add(s);
				itr.remove();
			}
		}
		Collections.sort(studentsWaiting);
	}
	
	//makes the necessary allocations by matching the available houses and students
	//for total semesters time
	public static void locationPeriod(int semesters) {
		
		for(int i=0; i<semesters; i++) {
			Iterator<House> itr = House.availableHouses.iterator();
		
			while(itr.hasNext()) {
			
				House h = itr.next();
			
				if(Student.studentsWaiting.isEmpty())
					break;
			
				Iterator<Student> sItr = studentsWaiting.iterator();
			
				while(sItr.hasNext()) {
					Student s = sItr.next();
				
					if(h.isMatching(s)) {
						h.locate(s);
						sItr.remove();
						itr.remove();
						break;
					}
				}				
			}
			
			newSemester();
			House.newSemester();
		}
	}
}
