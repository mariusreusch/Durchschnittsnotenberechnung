package calculation;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JComboBox;

import businessObjects.Subject;

public class Calculator {
	
	private List<Subject> vorlesungen;
	private Map<String, JComboBox> notenMap;
	
	public Calculator() {
		
	}
	
	public double calculate(List<Subject> vorlesungen, Map<String, JComboBox> notenMap2) {
		this.vorlesungen = vorlesungen;
		this.notenMap = notenMap2;
		int totalCredits = 0;
		double endnote = 0.0;
		
		vorlesungen = getCourseMarks(vorlesungen);

		totalCredits = calculateTotalCreditPoints(vorlesungen);
		for(Subject subject : vorlesungen) {
			if(subject.getGrade() != 0.0) {
				int credits = subject.getEcts();
				if(subject.isGrundstudium()) {
					credits = credits / 2;
				}
				endnote += ((subject.getGrade() * credits) / totalCredits);
			}
		}
		return endnote;
	}

	private List<Subject> getCourseMarks(List<Subject> vorlesungen2) {
		for (Subject subject : vorlesungen2) {
			for (Entry<String, JComboBox> entry : notenMap.entrySet()) {
				if (entry.getKey() != null && entry.getKey() != null) {
					if (entry.getKey().equals(subject.getName())) {
						if(!(entry.getValue().getSelectedItem() instanceof String)) {
							Double note = (Double) entry.getValue().getSelectedItem();
							subject.setGrade(note);
						}
					}
				}
			}
		}
		return vorlesungen;
	}

	private int calculateTotalCreditPoints(List<Subject> vorlesungen) {
		int totalCredits = 0;
		for (Subject subject : vorlesungen) {
			if(subject.getGrade() != 0.0) {
				int credits = subject.getEcts();
				if(subject.isGrundstudium()) {
					credits = credits / 2;
				}
				totalCredits += credits;
			}
		}
		return totalCredits;
	}

}
