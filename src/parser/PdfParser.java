package parser;

import java.util.Scanner;

import businessObjects.Subject;

public class PdfParser extends Parser {
	private static PdfParser pdfParser = null;

	private PdfParser() {
		super();
	}
	
	public static PdfParser getInstance() {
		if (pdfParser == null) {
			pdfParser = new PdfParser();
		}
		return pdfParser;
	}
	
	public double getGrade(String course) {
		double note = 0.0;
		String noteAlsString = "";
		int punktIndex = 0;
		punktIndex = course.indexOf(".");
		if (punktIndex > 0) {
			noteAlsString = course.substring(punktIndex - 1, punktIndex + 2);
			note = Double.parseDouble(noteAlsString);
		}
		return note;
	}
	
	
	@Override
	protected void parseRequiredSubjects(String subjectString){
		Scanner scanner = new Scanner(subjectString);		
		Subject subject;
		double grade = 0.0;
		while (scanner.hasNextLine()) {
			String scan = scanner.nextLine();
			subject = checkScanForCourse(scan);
			if (subject != null) {
				grade = getGrade(scan);
				if (grade != 5.0 && grade != 0.0) {
					subject.setGrade(grade);
					subjects.add(subject);
				}
			}
		}
		scanner.close();
	}
	
	@Override
	protected void parseElectiveSubjects(String subjectString){
		Subject electiveSubject;
		System.out.println(subjectString);
		Scanner scanner = new Scanner(subjectString);			
		while (scanner.hasNext()) {
			String scan = scanner.next();				
			if(scan.matches(".*\\d{5}.*")){
				electiveSubject = new Subject();
				electiveSubject.setElectiveSubject(true);
				electiveSubject.setGrundstudium(false);				
				if(scanner.hasNext()) {
					scan = scanner.next();
					electiveSubject.setName(scan);					
					if(scanner.hasNext()) {
						scan = scanner.next();
						if(scan.matches("\\d{1,2}")){
							electiveSubject.setEcts(Integer.parseInt(scan));
						}
						else { 
							electiveSubject.setName(electiveSubject.getName() + " " + scan);
							if(scanner.hasNext()) {
								scan = scanner.next();
								electiveSubject.setEcts(Integer.parseInt(scan));
							}								
						}
					}
					while(scanner.hasNext()){
						scan = scanner.next();
						if (scan.contains(".") && scan.length() == 3) {
							electiveSubject.setGrade(Double.parseDouble(scan));
							electiveSubject.setName(electiveSubject.getName().replaceAll("[^\\wäöüß.]"," "));
							electiveSubjects.add(electiveSubject);
							break;
						}
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
				if(scanner.hasNext()) {
					scan = scanner.next();
					additionalSubject.setName(scan);
					if(scanner.hasNext()) {
						scan = scanner.next();
						if(scan.matches("\\d{1,2}")){
							additionalSubject.setEcts(Integer.parseInt(scan));
						}
						else { 
							additionalSubject.setName(additionalSubject.getName() + " " + scan);
							if(scanner.hasNext()) {
								scan = scanner.next();
								additionalSubject.setEcts(Integer.parseInt(scan));
							}								
						}
					}
					while(scanner.hasNext()){
						scan = scanner.next();
						if (scan.contains(".") && scan.length() == 3) {
							additionalSubject.setGrade(Double.parseDouble(scan));
							additionalSubject.setName(additionalSubject.getName().replaceAll("[^\\wäöüß.]"," "));
							additionalSubjects.add(additionalSubject);
							break;
						}
					}
				}
			}			
		}
		subjects.addAll(additionalSubjects);
		scanner.close();
	}
}
