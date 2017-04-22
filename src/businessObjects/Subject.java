package businessObjects;
public class Subject {
	private String courseNumber;
	private String name;
	private double grade;
	private int ects;
	private boolean grundstudium;
	private boolean electiveSubject;
	private boolean additionalSubject;

	public Subject() {

	}

	public Subject(String courseNumber, String name, double grade, int ects, boolean grundstudium) {
		this(courseNumber, name, grade, ects, grundstudium, false);
	}
	
	public Subject(String courseNumber, String name, double grade, int ects, boolean grundstudium, boolean electiveSubject) {
		this.name = name;
		this.grade = grade;
		this.ects = ects;
		this.grundstudium = grundstudium;
		this.electiveSubject = electiveSubject;
	}
	
	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public int getEcts() {
		return ects;
	}

	public void setEcts(int ects) {
		this.ects = ects;
	}

	public boolean isGrundstudium() {
		return grundstudium;
	}

	public void setGrundstudium(boolean grundstudium) {
		this.grundstudium = grundstudium;
	}

	public boolean isElectiveSubject() {
		return electiveSubject;
	}

	public void setElectiveSubject(boolean electiveSubject) {
		this.electiveSubject = electiveSubject;
	}

	public boolean isAdditionalSubject() {
		return additionalSubject;
	}

	public void setAdditionalSubject(boolean additionalSubject) {
		this.additionalSubject = additionalSubject;
	}
}
