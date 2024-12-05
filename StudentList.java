package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class StudentList {

	private LinkedList<Student> students;

	StudentList() {
		this.setStudents(new LinkedList<Student>());
	}

	public LinkedList<Student> getStudents() {
		return students;
	}

	public void setStudents(LinkedList<Student> students) {
		this.students = students;
	}

	public void updateStudentFile(File file) {
		try (FileWriter writer = new FileWriter(file, false)) {
			writer.write("Student ID, Name, Tawjihi Grade, Placement Test Grade, Chosen Major\n");

			for (int i = 0; i < students.size(); i++) {
				writer.write(students.getAt(i).getStudentId() + "," + students.getAt(i).getName() + ","
						+ students.getAt(i).getTjGrade() + "," + students.getAt(i).getPt() + ","
						+ students.getAt(i).getcMajor().getMajor() + "\n");

			}

		} catch (FileNotFoundException e) {
			System.out.println("couldn't find the file");
		} catch (NullPointerException ex) {
			System.out.println("there are some missing information");
		} catch (IOException exy) {
			System.out.println(exy.getMessage());

		}

	}

	public boolean updateName(String id, String newName, File File) { // so here because search compares by admark we
																		// have to do it
		// manually
		if (newName == null || id == null) {
			return false;
		}

		for (int i = 0; i < students.size(); i++) {
			if (students.getAt(i).getStudentId().compareToIgnoreCase(id) == 0) {
				Student std = students.getAt(i);
				students.getAt(i).setName(newName);
				updateStudentFile(File);
				return true;

			}
		}

		System.out.println("student not found");
		return false;

	}

	public boolean updateTg(String id, double tg, File file) {
		if (tg < 0.0 || id == null) {
			return false;
		}

		for (int i = 0; i < students.size(); i++) {
			if (students.getAt(i).getStudentId().compareToIgnoreCase(id) == 0) {
				students.getAt(i).setTjGrade(tg);
				updateStudentFile(file);
				return true;

			}
		}

		System.out.println("student not found");
		return false;

	}

	public boolean updatePt(String id, int pt, File file) {

		if (pt < 0 || id == null) {
			return false;
		}

		for (int i = 0; i < students.size(); i++) {
			if (students.getAt(i).getStudentId().equalsIgnoreCase(id)) {
				students.getAt(i).setPt(pt);
				updateStudentFile(file);
				return true;
			}
		}

		System.out.println("student not found");
		return false;
	}

	public boolean majorValidation(MajorList l, MajorData major) {
		for (int i = 0; i < l.getMajorlist().size(); i++) {
			if (major.getMajor().compareToIgnoreCase(l.getMajorlist().getAt(i).getMajor()) == 0) {
				return true;

			}
		}

		return false;
	}

	public LinkedList<String> rejected(MajorData major, MajorList l) {
		if (major == null || major.getMajor() == null || major.getMajor() == null || major.getMajor() == null) {
			return new LinkedList<>();
		}

		if (!this.majorValidation(l, major)) {
			System.out.println("this major doesn't exist");
			return new LinkedList<>();
		}
		int c = 0;
		LinkedList<Student> studentsRejected = new LinkedList<>();
		LinkedList<String> studentsRej = new LinkedList<>();

		String id = null;

		for (int i = 0; i < students.size(); i++) {
			if (students.getAt(i).getcMajor().compareTo(major) == 0
					&& students.getAt(i).getcMajor().getMajor().compareToIgnoreCase(major.getMajor()) == 0) {
				id = students.getAt(i).getStudentId();
				boolean found = false;
				for (int j = 0; j < major.getStudents().size(); j++) {
					if (major.getStudents().getAt(j).getStudentId().compareToIgnoreCase(id) == 0) {
						found = true;
						break;
					}
				}
				if (found == false) {
					studentsRejected.insert(students.getAt(i));
					c++;
				}
			}
		}

		if (studentsRejected.size() == 0) {
			return new LinkedList<>();
		}

		for (int o = 0; o < studentsRejected.size(); o++) {
			if (studentsRejected.getAt(o).getTjGrade() > studentsRejected.getAt(o).getPt()) {
				studentsRej.insert("name of major : " + major.getMajor() + " number of people rejected in it " + c + " "
						+ studentsRejected.getAt(o).getName() + "  " + studentsRejected.getAt(o).getStudentId()
						+ "  this student was rejected because his or her placment test grade was too  low");

			} else {
				studentsRej.insert("name of major : " + major.getMajor() + " number of people rejected in it " + c + " "
						+ studentsRejected.getAt(o).getName() + " " + studentsRejected.getAt(o).getStudentId()
						+ "  this student was rejected because his or her tawjihi  grade was too  low");
			}

		}
		return studentsRej;
	}

	public String acceptanceRate(MajorData major, MajorList l) {
		if (major == null || major.getMajor() == null || major.getMajor() == null) {
			return null;
		}

		if (!this.majorValidation(l, major)) {
			System.out.println("this major doesn't exist");
			return null;
		}

		int c = 0;

		for (int i = 0; i < students.size(); i++) {
			if (students.getAt(i).getcMajor().compareTo(major) == 0
					&& students.getAt(i).getcMajor().getMajor().compareToIgnoreCase(major.getMajor()) == 0) {
				String id = students.getAt(i).getStudentId();
				boolean found = false;

				for (int j = 0; j < major.getStudents().size(); j++) {
					if (major.getStudents().getAt(j).getStudentId().compareToIgnoreCase(id) == 0) {
						found = true;
						break;
					}
				}

				if (!found) {
					c++;
				}
			}
		}

		int inMajor = major.getStudents().size();
		int total = inMajor + c;

		// Handle division by zero
		if (total == 0) {
			return "0%"; // Return 0% if there are no students
		}

		double acceptanceRate = (double) inMajor / total * 100; // Calculate acceptance rate
		return String.format("%.2f%%", acceptanceRate); // Return formatted string
	}

	public int rejectedNo(MajorData major, MajorList l) {
		if (major == null || major.getMajor() == null) { // Fixed duplicate check
			return 0;
		}

		if (!this.majorValidation(l, major)) {
			System.out.println("This major doesn't exist");
			return 0;
		}

		int c = 0;
		String id = null;

		for (int i = 0; i < students.size(); i++) { // Ensure 'students' is defined
			if (students.getAt(i).getcMajor().getMajor().compareToIgnoreCase(major.getMajor()) == 0) {
				id = students.getAt(i).getStudentId();
				boolean found = false;

				for (int j = 0; j < major.getStudents().size(); j++) {
					if (major.getStudents().getAt(j).getStudentId().compareToIgnoreCase(id) == 0) {
						found = true;
						break;
					}
				}

				if (!found) {
					c++;
				}
			}
		}
		return c;
	}

	public boolean add(Student std, File file) {

		for (int i = 0; i < this.getStudents().size(); i++) {
			if (std.getName().compareToIgnoreCase(this.getStudents().getAt(i).getName()) == 0
					&& (std.getTjGrade() == (this.getStudents().getAt(i).getTjGrade())
							&& (std.getPt() == (this.getStudents().getAt(i).getPt())
									&& (std.getcMajor()).compareTo(this.getStudents().getAt(i).getcMajor()) == 0))) {
				return false;
			}

		}
		std.setStudentId(Integer.toString(this.getStudents().size() + 1));

		this.getStudents().insert(std);
		this.updateStudentFile(file);
		return true;

	}

	public boolean delete(String id, File file) {

		boolean found = false;
		for (int i = 0; i < this.getStudents().size(); i++) {
			if (this.getStudents().getAt(i).getStudentId().compareToIgnoreCase(id) == 0) {
				
				
				this.getStudents().getAt(i).getcMajor().getStudents().delete(this.getStudents().getAt(i));//to remove the student from student list of majors
				
				this.getStudents().delete(this.getStudents().getAt(i));
				found = true;
				
			}
		}

		if (found = true) {
			this.updateStudentFile(file);
			return true;
		}

		found = false;
		return false;

	}
	
	
	
	
	
	
	
	
	
	
	
	
public String search(String id) {
		
		
		boolean found=false;
		for(int i=0;i<this.getStudents().size();i++) {
			if(this.getStudents().getAt(i).getStudentId().compareToIgnoreCase(id)==0) {
				return this.getStudents().getAt(i).toString();
			}
		}
		
		
		return null;
		
		
		
		
		
		
	}
	
	

}
