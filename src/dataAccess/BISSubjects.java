package dataAccess;

import java.util.HashMap;

import businessObjects.Subject;

public class BISSubjects {
	public static final String BACHELOR_PROJ_NO = "20023";
	public static final String BACHELOR_PROJ = "Bachelor Projekt";
	
	public static final String MDL_PRUEF_NO = "20990";
	public static final String MDL_PRUEF = "Mündliche Prüfung";
	
	public static final String THESIS_NO = "20999";
	public static final String THESIS = "Thesis";
	
	public static final String BWL_NO = "20001";
	public static final String BWL = "Allgemeine Betriebswirtschaftslehre";
	
	public static final String DB1_NO = "20002";
	public static final String DB1 = "Datenbanken 1";
	
	public static final String EINF_WI_NO = "20003";
	public static final String EINF_WI = "Einführung in die Wirtschaftsinformatik";
	
	public static final String PROMOD1_NO = "20104";
	public static final String PROMOD1 = "Programmieren und Modellieren 1";
	
	public static final String ALGODAT_NO = "20005";
	public static final String ALGODAT = "Algorithmen und Datenstrukturen";
	
	public static final String INETWORKING_NO = "20006";
	public static final String INETWORKING = "Internetworking";
	
	public static final String MAKRO_NO = "20007";
	public static final String MAKRO = "Makro-Ökonomie";
	
	public static final String MATHE_NO = "20008";
	public static final String MATHE = "Mathematik und Statistik";
	
	public static final String REWE_NO = "20009";
	public static final String REWE = "Rechnungswesen und Controlling";
	
	public static final String GP_NO = "20010";
	public static final String GP = "Geschäftsprozesse";
	
	public static final String PROMOD2_NO = "20011";
	public static final String PROMOD2 = "Programmieren und Modellieren 2";
	
	public static final String SYSARCH_NO = "20012";
	public static final String SYSARCH = "System-Architekturen";
	
	public static final String PRAES_MOD_NO = "20021";
	public static final String PRAES_MOD = "Präsentations- und Moderationstechnik";
	
	public static final String COMPUTERNETZE_NO = "20013";
	public static final String COMPUTERNETZE = "Computernetze";
	
	public static final String ISSW_NO = "20015";
	public static final String ISSW = "Integrierte Standard Software";
	
	public static final String DB2_NO = "20014";
	public static final String DB2 = "Datenbanken 2";
	
	public static final String LOG_SUP_NO = "20016";
	public static final String LOG_SUP = "Logistik und Supply-Chain-Management";
	
	public static final String SOFT_ENG_NO = "20017";
	public static final String SOFT_ENG = "Software-Engineering";
	
	public static final String BUS_PROJ_NO = "20018";
	public static final String BUS_PROJ = "Business-Projekt";
	
	public static final String NETZDESIGN_NO = "20019";
	public static final String NETZDESIGN = "Netzdesign";
	
	public static final String SOFT_PROJ_NO = "20020";
	public static final String SOFT_PROJ = "Software-Projekt";
	
	public static final String SOZ_INT_NO = "20022";
	public static final String SOZ_INT = "Soziale Interaktion";
	
	private HashMap<String, Subject> businessInformationSystemsSubjects = new HashMap<String, Subject>();
	private static BISSubjects instance = null;
	
	private BISSubjects(){
		initBusinessInformationSubjects();
	}
	
	public static BISSubjects getInstance(){
		if(instance==null){
			instance = new BISSubjects();
		}
		return instance;
	}
		
	private void initBusinessInformationSubjects() {
		businessInformationSystemsSubjects.put(BWL_NO, new Subject(BWL_NO, BWL, 0.0, 7, true));
		businessInformationSystemsSubjects.put(DB1_NO, new Subject(DB1_NO, DB1, 0.0, 7, true));
		businessInformationSystemsSubjects.put(EINF_WI_NO, new Subject(EINF_WI_NO, EINF_WI, 0.0, 8, true));
		businessInformationSystemsSubjects.put(PROMOD1_NO, new Subject(PROMOD1_NO, PROMOD1, 0.0, 8, true));
		businessInformationSystemsSubjects.put(ALGODAT_NO, new Subject(ALGODAT_NO, ALGODAT, 0.0, 7, true));
		businessInformationSystemsSubjects.put(INETWORKING_NO, new Subject(INETWORKING_NO, INETWORKING, 0.0, 7, true));
		businessInformationSystemsSubjects.put(MAKRO_NO, new Subject(MAKRO_NO, MAKRO, 0.0, 2, true));
		businessInformationSystemsSubjects.put(REWE_NO, new Subject(REWE_NO, REWE, 0.0, 8, true));
		businessInformationSystemsSubjects.put(MATHE_NO, new Subject(MATHE_NO, MATHE, 0.0, 6, true));
		businessInformationSystemsSubjects.put(GP_NO, new Subject(GP_NO, GP, 0.0, 7, false));
		businessInformationSystemsSubjects.put(PROMOD2_NO, new Subject(PROMOD2_NO, PROMOD2, 0.0, 7, false));
		businessInformationSystemsSubjects.put(SYSARCH_NO, new Subject(SYSARCH_NO, SYSARCH, 0.0, 7, false));
		businessInformationSystemsSubjects.put(PRAES_MOD_NO, new Subject(PRAES_MOD_NO, PRAES_MOD, 0.0, 3, false));
		businessInformationSystemsSubjects.put(COMPUTERNETZE_NO, new Subject(COMPUTERNETZE_NO, COMPUTERNETZE, 0.0, 5, false));
		businessInformationSystemsSubjects.put(ISSW_NO, new Subject(ISSW_NO, ISSW, 0.0, 5, false));
		businessInformationSystemsSubjects.put(DB2_NO, new Subject(DB2_NO, DB2, 0.0, 5, false));
		businessInformationSystemsSubjects.put(LOG_SUP_NO, new Subject(LOG_SUP_NO, LOG_SUP, 0.0, 5, false));
		businessInformationSystemsSubjects.put(SOFT_ENG_NO, new Subject(SOFT_ENG_NO, SOFT_ENG, 0.0, 7, false));
		businessInformationSystemsSubjects.put(BUS_PROJ_NO, new Subject(BUS_PROJ_NO, BUS_PROJ, 0.0, 7, false));
		businessInformationSystemsSubjects.put(NETZDESIGN_NO, new Subject(NETZDESIGN_NO, NETZDESIGN, 0.0, 7, false));
		businessInformationSystemsSubjects.put(SOFT_PROJ_NO, new Subject(SOFT_PROJ_NO, SOFT_PROJ, 0.0, 6, false));
		businessInformationSystemsSubjects.put(SOZ_INT_NO, new Subject(SOZ_INT_NO, SOZ_INT, 0.0, 2, false));
		businessInformationSystemsSubjects.put(BACHELOR_PROJ_NO, new Subject(BACHELOR_PROJ_NO, BACHELOR_PROJ, 0.0, 12, false));
		businessInformationSystemsSubjects.put(MDL_PRUEF_NO, new Subject(MDL_PRUEF_NO, MDL_PRUEF, 0.0, 6, false));
		businessInformationSystemsSubjects.put(THESIS_NO, new Subject(THESIS_NO, THESIS, 0.0, 12, false));
	}
	
	public HashMap<String, Subject> getBISSubjects(){
		return this.businessInformationSystemsSubjects;
	}
	
	public Subject getBISSubjectByCourseNumber(String courseNumber){
		return this.businessInformationSystemsSubjects.get(courseNumber);
	}
}
