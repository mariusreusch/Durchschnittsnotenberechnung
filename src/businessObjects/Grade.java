package businessObjects;
public enum Grade {
	EINSNULL(1.0), EINSDREI(1.3), EINSFUENF(1.5), EINSSIEBEN(1.7), ZWEINULL(2.0), ZWEIDREI(2.3), ZWEISIEBEN(
			2.7), DREINULL(3.0), DREIDREI(3.3), DREISIEBEN(3.7), VIERNULL(4.0), FUENF(
			5.0);

	private double grade;

	private Grade(double note) {
		this.grade = note;
	}

	public double getGrade() {
		return grade;
	}
}