package parser;
import java.util.Scanner;

import businessObjects.Subject;

public class WebsiteParser extends Parser {
	private static WebsiteParser websiteParser = null;

	private WebsiteParser() {
		super();
	}

	public static Parser getInstance() {
		if (websiteParser == null) {
			websiteParser = new WebsiteParser();
		}
		return websiteParser;
	}

	@Override
	protected void parseRequiredSubjects(String subjectString) {
		Scanner scanner = new Scanner(subjectString);
		Subject subject = null;
		Subject scannedSubject = null;
		while (scanner.hasNext()) {
			String scan = scanner.next();
			scannedSubject = checkScanForCourse(scan);
			
			if(scannedSubject!=null){
				subject = scannedSubject;
			}
					
			if (scan.contains(",") && scan.length() == 3) {
				double grade = Double.parseDouble(scan.replace(',', '.'));
				
				if (grade != 5.0) {
					subject.setGrade(grade);
					subjects.add(subject);
				}
			}
			
		}
		scanner.close();	
	}

	@Override
	protected void parseElectiveSubjects(String subjectString) {
		Subject electiveSubject;
		Scanner scanner = new Scanner(subjectString);
			
		while (scanner.hasNext()) {
			String scan = scanner.next();
			if(scan.matches(".*\\d{5}.*")){
				electiveSubject = new Subject();
				electiveSubject.setElectiveSubject(true);
				electiveSubject.setGrundstudium(false);	
				
				while(scanner.hasNext()){
					scan = scanner.next();
					if(scan.equals("-")){
						break;
					}
					else {
						String electiveSubjectName = electiveSubject.getName();
						if(electiveSubjectName==null){
							electiveSubject.setName(scan);
						}
						else {
							electiveSubject.setName(electiveSubjectName + " " +scan);
						}	
					}
				}				
				while(scanner.hasNext()){
					scan = scanner.next();
					if (scan.contains(".") && scan.length() == 3) {
						electiveSubject.setEcts(new Double(Double.parseDouble(scan)).intValue());
						break;
					}
				}				
				while(scanner.hasNext()){
					scan = scanner.next();
					if (scan.contains(",") && scan.length() == 3) {
						electiveSubject.setGrade(Double.parseDouble(scan.replace(',', '.')));
						electiveSubjects.add(electiveSubject);
						break;
					}
				}
			}			
		}
		subjects.addAll(electiveSubjects);
		scanner.close();
	}

	@Override
	protected void parseAdditionalSubjects(String subjectString) {
		Subject additionalSubject;
		Scanner scanner = new Scanner(subjectString);
			
		while (scanner.hasNext()) {
			String scan = scanner.next();
			if(scan.matches(".*\\d{5}.*")){
				additionalSubject = new Subject();
				additionalSubject.setAdditionalSubject(true);
				additionalSubject.setGrundstudium(false);	
				
				while(scanner.hasNext()){
					scan = scanner.next();
					if(scan.equals("-")){
						break;
					}
					else {
						String electiveSubjectName = additionalSubject.getName();
						if(electiveSubjectName==null){
							additionalSubject.setName(scan);
						}
						else {
							additionalSubject.setName(electiveSubjectName + " " +scan);
						}	
					}
				}				
				while(scanner.hasNext()){
					scan = scanner.next();
					if (scan.contains(".") && scan.length() == 3) {
						additionalSubject.setEcts(new Double(Double.parseDouble(scan)).intValue());
						break;
					}
				}				
				while(scanner.hasNext()){
					scan = scanner.next();
					if (scan.contains(",") && scan.length() == 3) {
						additionalSubject.setGrade(Double.parseDouble(scan.replace(',', '.')));
						additionalSubjects.add(additionalSubject);
						break;
					}
				}
			}			
		}
		subjects.addAll(additionalSubjects);
		scanner.close();
	}
}

