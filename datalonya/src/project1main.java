import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Collections;

public class project1main {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		int totSemesters = 0;

		while(in.hasNext()) {
			
			String indicator = in.next();
			
			if(indicator.equals("h")) {
				
				int id = in.nextInt();
				int duration = in.nextInt();
				double rating = Double.parseDouble(in.next());
				
				if(duration==0)
					House.availableHouses.add(new House(id, duration, rating));
				else
					House.fullHouses.add(new House(id, duration, rating));
				
			}else if(indicator.equals("s")) {
				
				int id = in.nextInt();
				String name = in.next();
				int duration = in.nextInt();
				double rating = Double.parseDouble(in.next());
				
				//the duration time of locating students will be the maximum amount 
				//of semesters among students
				if(duration>totSemesters)
					totSemesters = duration;
				
				//if a student has semesters, s/he is added to the waiting list
				if(duration==0)
					Student.studentsWithNoHouse.add(new Student(id, name, duration, rating));
				else
					Student.studentsWaiting.add(new Student(id, name, duration, rating));
			}
		}
		
		//waiting lists of the students and the available houses are sorted according to their id's
		Collections.sort(Student.studentsWaiting);
		Collections.sort(House.availableHouses);
		
		Student.locationPeriod(totSemesters);
		
		//printing the students' name that couldn't stay in a house
		//in an ascending order of their id's on the output file
		while(!Student.studentsWithNoHouse.isEmpty()) {
			out.println(Student.studentsWithNoHouse.poll().name);
		}
	
		in.close();
		out.close();	
	}
}
