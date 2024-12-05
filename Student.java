package application;

public class Student implements Comparable<Student> {

	private String studentId;
	private String name;
	private double tjGrade;
	private int pt;
	private MajorData cMajor;
	private double adMark;

	Student() {
	}

	Student(String studentId, String name, double tjGrade, int pt, double adMark) {
		setStudentId(studentId);
		setName(name);
		setTjGrade(tjGrade);
		setPt(pt);
		setAdMark(adMark);
	}

	Student(String studentId, String name, double tjGrade, int pTgrade, MajorData cMajor) {
		setStudentId(studentId);
		setName(name);
		setTjGrade(tjGrade);
		setPt(pTgrade);
		setcMajor(cMajor);
	}

	Student(String studentId, String name, double tjGrade, int pt) {
		setStudentId(studentId);
		setName(name);
		setTjGrade(tjGrade);
		setPt(pt);

	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTjGrade() {
		return tjGrade;
	}

	public void setTjGrade(double tjGrade) {
		if (this.tjGrade < 0) {
			System.out.println("invalid grade");
		} else {

			this.tjGrade = tjGrade;
		}
	}

	public double getAdMark() {
		if(this.getcMajor()==null) {
			return 0;
		}
		return (this.getTjGrade() * this.getcMajor().getTwWieght()) + (this.getPt() * this.getcMajor().getPtWieght());

	}

	public void setAdMark(double adMark) {
		if (this.adMark < 0) {
			System.out.println("invalid");
		} else {
			this.adMark = this.getAdMark();
		}
	}

	public int getPt() {
		return pt;
	}

	public void setPt(int pt) {
		if (this.pt < 0) {
			System.out.println("unvalid number");
		} else {

			this.pt = pt;

		}
	}

	@Override
	public int compareTo(Student o) {
		
		if (this.getAdMark() <o.getAdMark()) {
			return 1;
		} else if (this.getAdMark() == o.getAdMark()) {
			return 0;
		} else {
			return -1;
		}

	}

	@Override
	public String toString() {
	    return "this student: " + this.getName() + 
	           "  his or her id: " + this.getStudentId() + 
	           "  the major: " + this.getcMajor() + 
	           " tawjihi grade: " + this.getTjGrade() + 
	           "  and admission mark: " + String.format("%.2f", this.getAdMark());
	}


	public MajorData getcMajor() {
		return cMajor;
	}

	public void setcMajor(MajorData cMajor) {
		// cMajor.getStudents().insert(this);
		this.cMajor = cMajor;
	}

	public String academicval( MajorData major) {
		if (major == null || major.getMajor() == null || major.getMajor() == null) {
			System.out.println("Major information is missing.");
			return null;
		}

		if ( this.getName() == null || this.getName().isEmpty()) {
			System.out.println("Student name is missing.");
			return null;
		}

		if (this.getAdMark() == 0) {
			System.out.println("Admission mark is missing for the student.");
			return null;
		}

		try {
			String f = String.format("%.2f", this.getAdMark());

			if (this.getAdMark() >= major.getAcceptanceg()) {
				
				
				
				return (this.getName() + " is accepted into " + major.getMajor()
						+ "(admmission mark is : " + f+ ")");
			} else {
				return (this.getName() + " is  NOT accepted into " + major.getMajor()
						+" (acceptance grade for this major:  "+ major.getAcceptanceg()+")  (admmission mark is : " + f + ")");
			}

		} catch (NullPointerException e) {
			System.out.println("missing info");
		return null;
		}
	}

}
