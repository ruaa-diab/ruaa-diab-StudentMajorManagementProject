package application;

public class MajorData implements Comparable<MajorData> {

	private int acceptanceg;
	private LinkedList<Student> students;
	private double twWieght;
	private String major;
	private double ptWieght;

	MajorData() {
		this(null, 0, 0, 0, new LinkedList<>());
	}

	MajorData(String major, int acceptanceg, double twWieght, double ptWieght, LinkedList<Student> students) {
		setMajor(major);
		setAcceptanceg(acceptanceg);
		setTwWieght(twWieght);
		setPtWieght(ptWieght);
		setStudents(students);
	}
	MajorData(String  major, int acceptanceg, double twWieght, double ptWieght) {
		setMajor(major);
		setAcceptanceg(acceptanceg);
		setTwWieght(twWieght);
		setPtWieght(ptWieght);
		setStudents(students);
		setStudents(new LinkedList<>());
	}
	

	public int getAcceptanceg() {
		return acceptanceg;
	}

	public void setAcceptanceg(int acceptanceg) {

		this.acceptanceg = acceptanceg;
	}

	public LinkedList<Student> getStudents() {
		return students;
	}

	public void setStudents(LinkedList<Student> students) {
		this.students = students;
	}

	public double getTwWieght() {
		return twWieght;
	}

	public void setTwWieght(double twWieght) {
		this.twWieght = twWieght;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public double getPtWieght() {
		return ptWieght;
	}

	public void setPtWieght(double ptWieght) {
		this.ptWieght = ptWieght;
	}

	@Override
	public int compareTo(MajorData o) {
		try {
			return this.getMajor().compareToIgnoreCase(o.getMajor());
		} catch (NullPointerException e) {
			System.out.println("missing info");
			return -1;
		}
	}

	@Override
	public String toString() {
		
			return this.getMajor()+" its acceptance grade "+this.getAcceptanceg();
		
	}

	
	
	
	//4
	
	public  LinkedList<String> topMajor(int n) {
		if (major == null) {

			return new LinkedList<> ();
		}
		if(n<=0) {
			return new LinkedList<>();
		}
		if(n>this.getStudents().size()) {
			return new LinkedList<>();
			
		}
		LinkedList<String > t=new LinkedList<>();
		
		LinkedList<Student> students=this.getStudents();
		students.removeDuplicate();
		for (int i=0;i<n;i++) {
			t.insert(students.getAt(i).toString());
			
		}
		return t;
	}
	
	
	
	
	
	
	
	

}
