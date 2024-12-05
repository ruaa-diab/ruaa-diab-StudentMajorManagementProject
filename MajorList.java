package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class MajorList {
	private DlinkedList<MajorData> majorlist;

	MajorList() {
		this.majorlist = new DlinkedList<MajorData>();

	}

	public DlinkedList<MajorData> getMajorlist() {
		return majorlist;
	}

	public void setMajorlist(DlinkedList<MajorData> majorlist) {
		this.majorlist = majorlist;
	}
	
	
	public void updateMajorFile(File file) {
		try(FileWriter writer=new FileWriter(file,false)){
			writer.write("Major, AcceptanceGrade, TawjihiWeight, PlacementTestWeight\n");
		
		for(int i=0;i<majorlist.size();i++) {
			writer.write(majorlist.getAt(i).getMajor()+","+
					+
					majorlist.getAt(i).getAcceptanceg()+","+
					majorlist.getAt(i).getTwWieght()+","+
					majorlist.getAt(i).getPtWieght()+"\n");
			
		}
			
		}catch(FileNotFoundException e) {
			System.out.println("couldn't find the file");
		}catch(NullPointerException ex) {
			System.out.println("there are some missing information");
		}catch(IOException exy) {
			System.out.println(exy.getMessage());
			
		}
		
	}
	
	
	
	
	

	public boolean delete(String name,File file) {
		
		
		boolean found=false;
		for(int i=0;i<this.getMajorlist().size();i++) {
			if(this.getMajorlist().getAt(i).getMajor().compareToIgnoreCase(name)==0) {
				this.getMajorlist().delete(this.getMajorlist().getAt(i));
				found=true;
				
			}
		}
		
		
		if(found=true) {
			this.updateMajorFile(file);
			return true;
		}
		
		found=false;
		return false;
		
		
		
		
		
		
	}
	
	
	
public String search(String name) {
		
		
	
		for(int i=0;i<this.getMajorlist().size();i++) {
			if(this.getMajorlist().getAt(i).getMajor().compareToIgnoreCase(name)==0) {
				return this.getMajorlist().getAt(i).toString();
			}
		}
		
		
		return null;
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
	

	public boolean updateNameM(String name, String newName,File file) { // so here because search compares by admark we have to do
															// it manually
		if (newName == null||name==null) {
			return false;
		}

		for (int i = 0; i < majorlist.size(); i++) {
			if (majorlist.getAt(i).getMajor().compareToIgnoreCase(name) == 0) {
				majorlist.getAt(i).setMajor(newName);
				updateMajorFile(file);
				return true;

			}
		}

		System.out.println("major not found");
		return false;

	}

	public boolean updateAg(String name, int ag,File file) {
		if (ag < 0.0||name==null) {
			return false;
		}

		for (int i = 0; i < majorlist.size(); i++) {
			if (majorlist.getAt(i).getMajor().compareToIgnoreCase(name) == 0) {
				majorlist.getAt(i).setAcceptanceg(ag);
				updateMajorFile(file);
				return true;
			}
		}

		System.out.println("major not found");
		return false;

	}

	public boolean updateTw(String name, double tw,File file) {
		if (tw < 0.0||name==null) {
			return false;
		}

		for (int i = 0; i < majorlist.size(); i++) {
			if (majorlist.getAt(i).getMajor().compareToIgnoreCase(name) == 0) {
				majorlist.getAt(i).setTwWieght(tw);
				updateMajorFile(file);
				return true;
			}
		}

		System.out.println("major not found");
		return false;

	}

	public boolean updateTp(String name, double tp,File file) {
		if (tp < 0.0||name==null) {
			return false;
		}

		for (int i = 0; i < majorlist.size(); i++) {
			if (majorlist.getAt(i).getMajor().compareToIgnoreCase(name) == 0) {
				majorlist.getAt(i).setPtWieght(tp);
				updateMajorFile(file);
				
				return true;
			}
		}

		System.out.println("major not found");
		return false;

	}
	
	
	public Student studentValidation(StudentList l,String id) {
		for(int i=0;i<l.getStudents().size();i++) {
			if(l.getStudents().getAt(i).getStudentId().compareToIgnoreCase(id)==0) {
				return l.getStudents().getAt(i);
			}
			}
		return null;
		
	}
	public MajorData majorValidation(String name) {
		for(int i=0;i<this.getMajorlist().size();i++) {
			if(name.compareToIgnoreCase(this.getMajorlist().getAt(i).getMajor())==0) {
				return this.getMajorlist().getAt(i);
			}
			}
		return null;
		
		
	}

	public LinkedList<MajorData> majorSuggestion(String id ,StudentList l) {

		if (id == null) {
			return null;
		}
		if (majorlist.size() <= 0) {
			System.out.println("the university is not ready for student yet");
			return null;
		}
		if(l==null||l.getStudents().size()<=0) {
			System.out.println("there r no students registerd in this university yet");
			return null;
			
		}
		 Student std=studentValidation(l,id);
		if(std==null) {
			System.out.println("you are not a registerd student here you have to register first to see your major suggestion ");
                return null;
		}
           
		LinkedList<MajorData> majorsAllowed = new LinkedList<>();

		for (int i = 0; i < majorlist.size(); i++) {

			double adMark = std.getTjGrade() * majorlist.getAt(i).getTwWieght()
					+ std.getPt() * majorlist.getAt(i).getPtWieght();

			if (adMark >= majorlist.getAt(i).getAcceptanceg()) {
				majorsAllowed.insert(majorlist.getAt(i));

			}

		}
		return majorsAllowed;

	}

	public LinkedList<MajorData> majorAlt(String id, MajorData chosen, StudentList l) {
	    if (id == null) {
	        return new LinkedList<>(); // Return an empty list
	    }
	    if (majorlist.size() <= 0) {
	        System.out.println("The university is not ready for students yet.");
	        return new LinkedList<>(); // Return an empty list
	    }
	    if (l == null || l.getStudents().size() <= 0) {
	        System.out.println("There are no students registered in this university yet.");
	        return new LinkedList<>(); // Return an empty list
	    }

	    LinkedList<MajorData> majorsAllowed= majorSuggestion(id,l);
	    majorsAllowed.delete(chosen);
	    


	    return majorsAllowed;
	}

	
public int stdInmajor(MajorData major) {
		
		if (major == null) {

			return 0;
		}
		
		if(majorValidation(major.getMajor())==null) {
			System.out.println("this major doesn't exist");
			return 0;
		}
		return major.getStudents().size();

	}

	

	public void stdInmajor() {
		if (majorlist.size() == 0) {

			System.out.println("ther r no majors yet");
		}
		for (int i = 0; i < majorlist.size(); i++) {
			System.out.println("number of studentsin this major (" + majorlist.getAt(i).getMajor() + ") :"
					+ majorlist.getAt(i).getStudents().size());
		}

	}

	public void rejectedStudents(StudentList k) {
		if (k == null) {
			System.out.println("there are no students yet");
		}

		if (majorlist.size() == 0) {
			System.out.println("ther are no majors yet");
		}
		for (int i = 0; i < majorlist.size(); i++) {
			MajorData v = majorlist.getAt(i);

			LinkedList<String> result = k.rejected(v,this);
			result.print();
		}
	}

	//1
	public LinkedList<String> acceptanceRateAllMajors(StudentList s) {
		if (s == null) {
			System.out.println("the stident list is empty");
			return  new LinkedList<>();
		}
		
		
		if (this.majorlist == null || this.majorlist.size() <= 0) {
			System.out.println("the student list is empty");
			return new LinkedList<>();
		}
		
		
		
		LinkedList<String> n=new LinkedList<>();

		for (int i = 0; i < this.getMajorlist().size(); i++) {
			
			n.insert(this.getMajorlist().getAt(i).getMajor()+" "+ s.acceptanceRate(majorlist.getAt(i),this));
			

		}
		return n;
	}

	//2
	public int studentsInAllMajors() {
	    if (this.majorlist == null || this.majorlist.size() <= 0) {
	        System.out.println("The major list is empty"); // Updated message
	        return 0;
	    }
	    int c = 0;
	    for (int i = 0; i < majorlist.size(); i++) {
	        c += majorlist.getAt(i).getStudents().size();
	    }
	    return c;
	}

	public int rejectedInAllMajors(StudentList s) {
	    if (this.majorlist == null || this.majorlist.size() <= 0) {
	        System.out.println("The major list is empty"); // Updated message
	        return 0;
	    }
	    int c = 0;
	    for (int i = 0; i < majorlist.size(); i++) {
	        c += s.rejectedNo(majorlist.getAt(i), this);
	    }
	    return c;
	}
	
	
	
	

	public boolean add(MajorData major,File file) {
		
		for(int i=0;i<this.getMajorlist().size();i++) {
			if(major.getMajor().compareToIgnoreCase(this.getMajorlist().getAt(i).getMajor())==0) {
				return false;
			}
			
		}
			this.getMajorlist().insert(major);
			this.updateMajorFile(file);
			return true;
			
			
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
