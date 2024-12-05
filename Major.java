package application;

public class Major {

	private String name;
	private String majorId;

	Major() {
	}

	Major(String name, String majorId) {
		setName(name);
		setMajorId(majorId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMajorId() {
		return majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	
	@Override 
	public String toString() {
		return this.name;
	}
	
}
