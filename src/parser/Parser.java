package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataAccess.BISSubjects;
import businessObjects.Subject;

public abstract class Parser {
	protected static final String REQUIRED_SUBJECTS_LABEL = "Pflichtfächer:";
	protected static final String ELECTIVE_SUBJECTS_LABEL = "Wahlpflichtfächer:";
	protected static final String ADDITIONAL_SUBJECTS_LABEL = "Zusatzfächer:";
	protected static final String END_OF_ADDITIONAL_SUBJECTS_LABEL = "Status Anerkennung";	
	
	private String requiredSubjectsString = "";
	private String electiveSubjectsString = "";
	private String additionalSubjectsString = "";
	
	protected HashMap<String, Subject> businessInformationSystemsSubject = BISSubjects.getInstance().getBISSubjects();
	protected List<Subject> subjects = new ArrayList<Subject>();
	protected List<Subject> electiveSubjects = new ArrayList<Subject>();
	protected List<Subject> additionalSubjects = new ArrayList<Subject>();
		
	protected Parser(){
		
	}
	
	protected abstract void parseRequiredSubjects(String subjectString);
	protected abstract void parseElectiveSubjects(String subjectString);
	protected abstract void parseAdditionalSubjects(String subjectString);
	
	public List<Subject> processInput(String content) {
		requiredSubjectsString = getStringBetweenTwoStrings(content, REQUIRED_SUBJECTS_LABEL, ELECTIVE_SUBJECTS_LABEL);		
		electiveSubjectsString = getStringBetweenTwoStrings(content, ELECTIVE_SUBJECTS_LABEL, ADDITIONAL_SUBJECTS_LABEL);
		additionalSubjectsString = getStringBetweenTwoStrings(content, ADDITIONAL_SUBJECTS_LABEL, END_OF_ADDITIONAL_SUBJECTS_LABEL);
		parseRequiredSubjects(requiredSubjectsString);
		parseElectiveSubjects(electiveSubjectsString);
		parseAdditionalSubjects(additionalSubjectsString);
		return subjects;
	}
	
	protected String getStringBetweenTwoStrings(String contentString, String startString, String endString){
		String parsableString = "";
		if(contentString.contains(startString)){
			int startIndex = contentString.indexOf(startString);
			parsableString = contentString.substring(startIndex);
			if(contentString.contains(endString)){
				int endIndex = parsableString.indexOf(endString);
				parsableString = parsableString.substring(0, endIndex);
			}			
		}
		
		return parsableString;
	}
	
	protected Subject checkScanForCourse(String scan) {
		Subject subject = null;
		for(String courseNo : businessInformationSystemsSubject.keySet()){
			if (scan.contains(courseNo)) {
				subject = businessInformationSystemsSubject.get(courseNo);
			}
		}
		return subject;
	}	
}