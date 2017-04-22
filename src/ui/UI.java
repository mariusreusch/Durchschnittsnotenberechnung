package ui;

import info.clearthought.layout.TableLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import parser.Parser;

import businessObjects.Subject;
import businessObjects.Grade;
import calculation.Calculator;
import dataAccess.BISSubjects;


public class UI extends JFrame implements ActionListener {
	private TableLayout bgPanelLayout;
	private JPanel contentPanel;
	
	private static final String SELECT_GRADE = "Note wählen...";
	private static final String SELECT_ECTS = "ECTS wählen...";
	private static final int WIDTH = 800;
	private static final int HEIGHT = 768;
	private static final int ROW_HEIGHT = 19;
	private static final int ROW_WITH_BUTTON_HEIGHT = 30;
	private static final int SEPERATE = 1;
	private static final Font HEADLINE = new Font("Arial", Font.BOLD, 14);

	private JComboBox promod1Box = new JComboBox();
	private JComboBox einfWiBox = new JComboBox();
	private JComboBox bwlBox = new JComboBox();
	private JComboBox db1Box = new JComboBox();
	private JComboBox makroBox = new JComboBox();
	private JComboBox inetWorkBox = new JComboBox();
	private JComboBox matheBox = new JComboBox();
	private JComboBox reweBox = new JComboBox();
	private JComboBox algoDatBox = new JComboBox();
	private JComboBox promod2Box = new JComboBox();
	private JComboBox sysarchBox = new JComboBox();
	private JComboBox gpBox = new JComboBox();
	private JComboBox praesmodBox = new JComboBox();
	private JComboBox compnetzeBox = new JComboBox();
	private JComboBox softEngBox = new JComboBox();
	private JComboBox logSupBox = new JComboBox();
	private JComboBox isswBox = new JComboBox();
	private JComboBox db2Box = new JComboBox();
	private JComboBox softprojBox = new JComboBox();
	private JComboBox busprojBox = new JComboBox();
	private JComboBox netzdesignBox = new JComboBox();
	private JComboBox sozIntBox = new JComboBox();
	private JComboBox thesisBox = new JComboBox();
	private JComboBox thesisProjBox = new JComboBox();
	private JComboBox mdlPruefBox = new JComboBox();
	
	private List<Subject> subjects;
	private List<Subject> electiveSubjects = new ArrayList<Subject>();
	private List<Subject> additionalSubjects = new ArrayList<Subject>();
	private Map<String, JComboBox> subjectComboBoxes = new HashMap<String, JComboBox>();

	
	private JLabel lblOverallAverageGrade;	
	private int electiveSubjectHeadlineRowNumber;
	private int additionalSubjectHeadlineRowNumber;
	private int addElectiveSubjectButtonRowNumber;
	private int addAdditionalSubjectButtonRowNumber;
	
	private static int addedElectiveSubjectsCounter = 0;
	private static int addedAdditionalSubjectsCounter = 0;
	
	private JButton btnCalculateOverallAverageGrade;
	private List<Integer> ectsList = new ArrayList<Integer>();

	public UI() {
		subjects = new ArrayList<Subject>();
		init();
	}

	public UI(String text, Parser parser) {
		getNoten(text, parser);
		init();
	}

	private void init() {
		setTitle("Notenkalkulator");
		initECTS();
		getContentPane().setBackground(Color.WHITE);
		generateLayout();
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(getParent());
		setVisible(true);
	}

	private void getNoten(String text, Parser parser) {
		subjects = parser.processInput(text);
	}

	public void initECTS() {
		this.ectsList.add(1);
		this.ectsList.add(2);
		this.ectsList.add(3);
		this.ectsList.add(4);
		this.ectsList.add(5);
		this.ectsList.add(6);
		this.ectsList.add(7);
		this.ectsList.add(8);
		this.ectsList.add(9);
		this.ectsList.add(10);
	}

	public void generateLayout() {
		initBackgroundPanel();
		initTableLayout();
		initContentPanel();
		initBoxMap();
		initBoxSelectValues();
		addHeadRow(contentPanel);
		addSemesterLabels(contentPanel);
		addGrundstudiumComponents(contentPanel);
		addHauptstudiumComponents(contentPanel);
		addThesisComponents(contentPanel);
		addElectiveSubjectsHeadline(contentPanel);
		addAdditionalSubjectHeadline(contentPanel);					
		insertParsedGrades();
		insertClosingLayoutComponents();
		calculateEndnote();
	}
	
	private void initBackgroundPanel(){
		ImageIcon backgroundImage = null;
		backgroundImage = new ImageIcon("resources//images//background.png");
		JLabel background = new JLabel();
		background.setIcon(backgroundImage);
		background.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(background);
	}
	
	private void initTableLayout(){
		bgPanelLayout = new TableLayout(new double[][] {
				{ 120, TableLayout.FILL, 120, 120 },
				{ ROW_HEIGHT, ROW_HEIGHT, ROW_HEIGHT, ROW_HEIGHT, ROW_HEIGHT,
						ROW_HEIGHT, SEPERATE, ROW_HEIGHT, ROW_HEIGHT,
						ROW_HEIGHT, ROW_HEIGHT, ROW_HEIGHT, ROW_HEIGHT,
						ROW_HEIGHT, ROW_HEIGHT, ROW_HEIGHT, ROW_HEIGHT,
						SEPERATE, ROW_HEIGHT, ROW_HEIGHT, ROW_HEIGHT,
						ROW_HEIGHT, ROW_HEIGHT, SEPERATE, ROW_HEIGHT,
						ROW_HEIGHT, ROW_HEIGHT, ROW_HEIGHT, ROW_HEIGHT,
						ROW_HEIGHT, ROW_HEIGHT, ROW_HEIGHT } });
		bgPanelLayout.setHGap(5);
		bgPanelLayout.setVGap(5);
	}
	
	private void initContentPanel(){
		contentPanel = new JPanel();		
		contentPanel.setLayout(bgPanelLayout);
		contentPanel.setBorder(new EmptyBorder(5, 15, 5, 15));
		contentPanel.setPreferredSize(new Dimension(560, 890));
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel);
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.setBackground(Color.WHITE);
		getContentPane().add(scrollPane);	
	}
	
	private void enhanceContentPanelHeight(int additionalHeight){
		int currentHeight = (int) contentPanel.getPreferredSize().getHeight();
		int currentWidth = (int) contentPanel.getPreferredSize().getWidth();
		int newHeight = currentHeight + additionalHeight + 5;
		contentPanel.setPreferredSize(new Dimension(currentWidth, newHeight));
	}

	private void insertParsedGrades() {		
		insertRequiredSubjects();
		insertElectiveSubjects();
		insertAdditionalSubjects();
	}
	
	private void insertRequiredSubjects(){
		for (Subject subject : subjects) {
			String subjectName = subject.getName();
			for (Entry<String, JComboBox> entry : subjectComboBoxes.entrySet()) {
				if (entry.getKey() != null) {
					if (subjectName.equals(entry.getKey())) {
						entry.getValue().setSelectedItem(subject.getGrade());
					}
				}
			}
			if(subject.isElectiveSubject()){
				electiveSubjects.add(subject);
			}
			else if(subject.isAdditionalSubject()){
				additionalSubjects.add(subject);
			}
		}
	}
		
	private void insertElectiveSubjects(){
		int rowNumber;
		int subjectCount=0;
		for(Subject subject : electiveSubjects){
			subjectCount++;
			rowNumber = electiveSubjectHeadlineRowNumber + subjectCount;
			insertSubject(subject, rowNumber);
		}	
		rowNumber = electiveSubjectHeadlineRowNumber + electiveSubjects.size() + 1;
		addElectiveSubjectButtonRowNumber = rowNumber;
		initAddSubjectButton(rowNumber, "Füge WPV hinzu", new AddElectiveSubjectActionListener());
	}
	
	private void insertAdditionalSubjects(){
		int rowNumber;
		for(Subject subject : additionalSubjects){
			rowNumber = bgPanelLayout.getNumRow();
			insertSubject(subject, rowNumber);
		}		
		rowNumber = bgPanelLayout.getNumRow();
		addAdditionalSubjectButtonRowNumber = rowNumber;
		initAddSubjectButton(rowNumber, "Füge Zusatzfach hinzu", new AddAdditionalSubjectActionListener());		
	}
	
	private void insertSubject(Subject subject, int rowNumber){		
		bgPanelLayout.insertRow(rowNumber, ROW_HEIGHT);			
		JLabel lblSubjectName = new JLabel(subject.getName());
		initLabel(contentPanel, lblSubjectName, "1, "+rowNumber, false);
		JLabel lblSubjectEcts = new JLabel(Integer.toString(subject.getEcts()));
		initLabel(contentPanel, lblSubjectEcts, "2, "+rowNumber, true);
		JComboBox subjectGradeBox = createSubjectGradeComboBox();
		subjectGradeBox.setSelectedItem(subject.getGrade());
		subjectComboBoxes.put(subject.getName(), subjectGradeBox);
		contentPanel.add(subjectGradeBox, "3, "+rowNumber);
		enhanceContentPanelHeight(ROW_HEIGHT);
	}
	
	private void initAddSubjectButton(int rowNumber, String buttonText, ActionListener listener){
		bgPanelLayout.insertRow(rowNumber, ROW_WITH_BUTTON_HEIGHT);
		JButton btnAddSubject = new JButton();			
		btnAddSubject.addActionListener(listener);
		contentPanel.add(btnAddSubject, "2, "+rowNumber+", 3, "+rowNumber);
		btnAddSubject.setText(buttonText);			
	}
		
	private JComboBox createSubjectGradeComboBox(){
		JComboBox subjectGradeComboBox = new JComboBox();
		subjectGradeComboBox.addItem(SELECT_GRADE);		
		for (Grade grade : Grade.values()) {
			subjectGradeComboBox.addItem(grade.getGrade());
		}
		return subjectGradeComboBox;
	}
	
	private JComboBox createSubjectEctsComboBox(){
		JComboBox subjectEctsComboBox = new JComboBox();
		subjectEctsComboBox.addItem(SELECT_ECTS);		
		for (int ects : ectsList) {
			subjectEctsComboBox.addItem(ects);
		}
		return subjectEctsComboBox;
	}
		
	private void insertClosingLayoutComponents(){
		int nextRowInTableLayout;
		nextRowInTableLayout = bgPanelLayout.getNumRow();
		bgPanelLayout.insertRow(nextRowInTableLayout, SEPERATE);
		JPanel separator = new JPanel();
		contentPanel.add(separator, "0, "+nextRowInTableLayout+", 3, "+nextRowInTableLayout);
		separator.setBackground(Color.BLACK);	
		
		nextRowInTableLayout = bgPanelLayout.getNumRow();
		bgPanelLayout.insertRow(nextRowInTableLayout, ROW_WITH_BUTTON_HEIGHT);		
		
		lblOverallAverageGrade = new JLabel();
		initLabel(contentPanel, lblOverallAverageGrade, "1, "+nextRowInTableLayout, false);
		lblOverallAverageGrade.setFont(HEADLINE);
		
		btnCalculateOverallAverageGrade = new JButton();
		btnCalculateOverallAverageGrade.addActionListener(this);			
		contentPanel.add(btnCalculateOverallAverageGrade, "2, "+nextRowInTableLayout+", 3, "+nextRowInTableLayout);
		btnCalculateOverallAverageGrade.setText("Note berechnen");			
	}

	private void initBoxSelectValues() {
		for (Entry<String, JComboBox> entry : subjectComboBoxes.entrySet()) {
			entry.getValue().addItem(SELECT_GRADE);
		}
		for (Grade grade : Grade.values()) {
			for (Entry<String, JComboBox> entry : subjectComboBoxes.entrySet()) {
				entry.getValue().addItem(grade.getGrade());
			}
		}
	}
	
	private void initBoxMap() {
		subjectComboBoxes.put(BISSubjects.PROMOD1, promod1Box);
		subjectComboBoxes.put(BISSubjects.EINF_WI, einfWiBox);
		subjectComboBoxes.put(BISSubjects.DB1, db1Box);
		subjectComboBoxes.put(BISSubjects.BWL, bwlBox);
		subjectComboBoxes.put(BISSubjects.MAKRO, makroBox);
		subjectComboBoxes.put(BISSubjects.INETWORKING, inetWorkBox);
		subjectComboBoxes.put(BISSubjects.MATHE, matheBox);
		subjectComboBoxes.put(BISSubjects.REWE, reweBox);
		subjectComboBoxes.put(BISSubjects.ALGODAT, algoDatBox);
		subjectComboBoxes.put(BISSubjects.SYSARCH, sysarchBox);
		subjectComboBoxes.put(BISSubjects.PROMOD2, promod2Box);
		subjectComboBoxes.put(BISSubjects.GP, gpBox);
		subjectComboBoxes.put(BISSubjects.PRAES_MOD, praesmodBox);
		subjectComboBoxes.put(BISSubjects.COMPUTERNETZE, compnetzeBox);
		subjectComboBoxes.put(BISSubjects.SOFT_ENG, softEngBox);
		subjectComboBoxes.put(BISSubjects.LOG_SUP, logSupBox);
		subjectComboBoxes.put(BISSubjects.ISSW, isswBox);
		subjectComboBoxes.put(BISSubjects.DB2, db2Box);
		subjectComboBoxes.put(BISSubjects.SOFT_PROJ, softprojBox);
		subjectComboBoxes.put(BISSubjects.BUS_PROJ, busprojBox);
		subjectComboBoxes.put(BISSubjects.NETZDESIGN, netzdesignBox);
		subjectComboBoxes.put(BISSubjects.SOZ_INT, sozIntBox);
		subjectComboBoxes.put(BISSubjects.THESIS, thesisBox);
		subjectComboBoxes.put(BISSubjects.BACHELOR_PROJ, thesisProjBox);
		subjectComboBoxes.put(BISSubjects.MDL_PRUEF, mdlPruefBox);
	}

	public void addHeadRow(JPanel background) {
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setFont(HEADLINE);
		initLabel(background, lblSemester, "0, 0", true);
		JLabel lblVorlesung = new JLabel("Vorlesung");
		lblVorlesung.setFont(HEADLINE);
		initLabel(background, lblVorlesung, "1, 0", false);
		JLabel lblECTS = new JLabel("ECTS");
		lblECTS.setFont(HEADLINE);
		initLabel(background, lblECTS, "2, 0", true);
		JLabel lblNote = new JLabel("Note");
		lblNote.setFont(HEADLINE);
		initLabel(background, lblNote, "3, 0", true);
	}

	public void addSemesterLabels(JPanel background) {
		JLabel lblSem1 = new JLabel("1");
		initLabel(background, lblSem1, "0, 2, 0, 5", true);
		JLabel lblSem2 = new JLabel("2");
		initLabel(background, lblSem2, "0, 7, 0, 11", true);
		JLabel lblSem3 = new JLabel("3");
		initLabel(background, lblSem3, "0, 13, 0, 16", true);
		JLabel lblSem4 = new JLabel("4");
		initLabel(background, lblSem4, "0, 18, 0, 22", true);
		JLabel lblSem6 = new JLabel("6");
		initLabel(background, lblSem6, "0, 24, 0, 27", true);
		JLabel lblSem7 = new JLabel("7");
		initLabel(background, lblSem7, "0, 29, 0, 31", true);
	}

	public void addGrundstudiumComponents(JPanel background) {
		JLabel lblGrundstudium = new JLabel("Grundstudium");
		initLabel(background, lblGrundstudium, "0, 1, 3, 1", true);
		lblGrundstudium.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0,
				Color.BLACK));
		lblGrundstudium.setFont(HEADLINE);
		lblGrundstudium.setBackground(Color.GREEN);
		lblGrundstudium.setOpaque(true);

		JLabel lblABWL = new JLabel("Allgemeine Betriebswirtschaftslehre");
		initLabel(background, lblABWL, "1, 2", false);
		JLabel lblABWL_ECTS = new JLabel("7");
		initLabel(background, lblABWL_ECTS, "2, 2", true);
		background.add(bwlBox, "3, 2");

		JLabel lblDB1 = new JLabel("Datenbanken 1");
		initLabel(background, lblDB1, "1, 3", false);
		JLabel lblDB1_ECTS = new JLabel("7");
		initLabel(background, lblDB1_ECTS, "2, 3", true);
		background.add(db1Box, "3, 3");

		JLabel lblEinfWI = new JLabel("Einführung in die Wirtschaftsinformatik");
		initLabel(background, lblEinfWI, "1, 4", false);
		JLabel lblEinfWI_ECTS = new JLabel("8");
		initLabel(background, lblEinfWI_ECTS, "2, 4", true);
		background.add(einfWiBox, "3, 4");

		JLabel lblProMod1 = new JLabel("Programmieren und Modellieren 1");
		initLabel(background, lblProMod1, "1, 5", false);
		JLabel lblProMod1_ECTS = new JLabel("8");
		initLabel(background, lblProMod1_ECTS, "2, 5", true);
		background.add(promod1Box, "3, 5");

		JPanel trennlinie = new JPanel();
		background.add(trennlinie, "0, 6, 3, 6");
		trennlinie.setBackground(Color.BLACK);

		JLabel lblAlgoDat = new JLabel("Algorithmen und Datenstrukturen");
		initLabel(background, lblAlgoDat, "1, 7", false);
		JLabel lblAlgoDat_ECTS = new JLabel("7");
		initLabel(background, lblAlgoDat_ECTS, "2, 7", true);
		background.add(algoDatBox, "3, 7");

		JLabel lblIW = new JLabel("Internetworking");
		initLabel(background, lblIW, "1, 8", false);
		JLabel lblIW_ECTS = new JLabel("7");
		initLabel(background, lblIW_ECTS, "2, 8", true);
		background.add(inetWorkBox, "3, 8");

		JLabel lblMakro = new JLabel("Makro-Ökonomie");
		initLabel(background, lblMakro, "1, 9", false);
		JLabel lblMakro_ECTS = new JLabel("2");
		initLabel(background, lblMakro_ECTS, "2, 9", true);
		background.add(makroBox, "3, 9");

		JLabel lblMathe = new JLabel("Mathematik und Statistik");
		initLabel(background, lblMathe, "1, 10", false);
		JLabel lblMathe_ECTS = new JLabel("6");
		initLabel(background, lblMathe_ECTS, "2, 10", true);
		background.add(matheBox, "3, 10");

		JLabel lblReWe = new JLabel("Rechnungswesen und Controlling");
		initLabel(background, lblReWe, "1, 11", false);
		JLabel lblReWe_ECTS = new JLabel("8");
		initLabel(background, lblReWe_ECTS, "2, 11", true);
		background.add(reweBox, "3, 11");
	}

	public void addHauptstudiumComponents(JPanel background) {
		JLabel lblHauptstudium = new JLabel("Hauptstudium");
		initLabel(background, lblHauptstudium, "0, 12, 3, 12", true);
		lblHauptstudium.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0,
				Color.BLACK));
		lblHauptstudium.setFont(HEADLINE);

		JLabel lblGP = new JLabel("Geschäftsprozesse");
		initLabel(background, lblGP, "1, 13", false);
		JLabel lblGP_ECTS = new JLabel("7");
		initLabel(background, lblGP_ECTS, "2, 13", true);
		background.add(gpBox, "3, 13");

		JLabel lblPraesMod = new JLabel("Präsentations- und Moderationstechnik");
		initLabel(background, lblPraesMod, "1, 14", false);
		JLabel lblPraesMod_ECTS = new JLabel("3");
		initLabel(background, lblPraesMod_ECTS, "2, 14", true);
		background.add(praesmodBox, "3, 14");

		JLabel lblProMod2 = new JLabel("Programmieren und Modellieren 2");
		initLabel(background, lblProMod2, "1, 15", false);
		JLabel lblProMod2_ECTS = new JLabel("7");
		initLabel(background, lblProMod2_ECTS, "2, 15", true);
		background.add(promod2Box, "3, 15");

		JLabel lblSysArch = new JLabel("System-Architekturen");
		initLabel(background, lblSysArch, "1, 16", false);
		JLabel lblSysArch_ECTS = new JLabel("7");
		initLabel(background, lblSysArch_ECTS, "2, 16", true);
		background.add(sysarchBox, "3, 16");

		JPanel trennlinie = new JPanel();
		background.add(trennlinie, "0, 17, 3, 17");
		trennlinie.setBackground(Color.BLACK);

		JLabel lblCN = new JLabel("Computernetze");
		initLabel(background, lblCN, "1, 18", false);
		JLabel lblCN_ECTS = new JLabel("5");
		initLabel(background, lblCN_ECTS, "2, 18", true);
		background.add(compnetzeBox, "3, 18");

		JLabel lblDB2 = new JLabel("Datenbanken 2");
		initLabel(background, lblDB2, "1, 19", false);
		JLabel lblDB2_ECTS = new JLabel("5");
		initLabel(background, lblDB2_ECTS, "2, 19", true);
		background.add(db2Box, "3, 19");

		JLabel lblISSW = new JLabel("Integrierte Standard-Software");
		initLabel(background, lblISSW, "1, 20", false);
		JLabel lblISSW_ECTS = new JLabel("5");
		initLabel(background, lblISSW_ECTS, "2, 20", true);
		background.add(isswBox, "3, 20");

		JLabel lblLogistik = new JLabel("Logistik und Supply-Chain-Management");
		initLabel(background, lblLogistik, "1, 21", false);
		JLabel lblLogistik_ECTS = new JLabel("5");
		initLabel(background, lblLogistik_ECTS, "2, 21", true);
		background.add(logSupBox, "3, 21");

		JLabel lblSWE = new JLabel("Software Engineering");
		initLabel(background, lblSWE, "1, 22", false);
		JLabel lblSWE_ECTS = new JLabel("7");
		initLabel(background, lblSWE_ECTS, "2, 22", true);
		background.add(softEngBox, "3, 22");

		JPanel trennlinie2 = new JPanel();
		background.add(trennlinie2, "0, 23, 3, 23");
		trennlinie2.setBackground(Color.BLACK);

		JLabel lblBP = new JLabel("Business-Projekt");
		initLabel(background, lblBP, "1, 24", false);
		JLabel lblBP_ECTS = new JLabel("7");
		initLabel(background, lblBP_ECTS, "2, 24", true);
		background.add(busprojBox, "3, 24");

		JLabel lblNetzdesign = new JLabel("Netzdesign");
		initLabel(background, lblNetzdesign, "1, 25", false);
		JLabel lblNetzdesign_ECTS = new JLabel("7");
		initLabel(background, lblNetzdesign_ECTS, "2, 25", true);
		background.add(netzdesignBox, "3, 25");

		JLabel lblSWP = new JLabel("Software-Projekt");
		initLabel(background, lblSWP, "1, 26", false);
		JLabel lblSWP_ECTS = new JLabel("8");
		initLabel(background, lblSWP_ECTS, "2, 26", true);
		background.add(softprojBox, "3, 26");

		JLabel lblSI = new JLabel("Soziale Interaktion");
		initLabel(background, lblSI, "1, 27", false);
		JLabel lblSI_ECTS = new JLabel("2");
		initLabel(background, lblSI_ECTS, "2, 27", true);
		background.add(sozIntBox, "3, 27");
	}

	public void addThesisComponents(JPanel background) {
		JLabel lblThesisHeadline = new JLabel("Thesis und mündliche Prüfung");
		initLabel(background, lblThesisHeadline, "0, 28, 3, 28", true);
		lblThesisHeadline.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0,
				Color.BLACK));
		lblThesisHeadline.setFont(HEADLINE);

		JLabel lblThesis = new JLabel("Thesis");
		initLabel(background, lblThesis, "1, 29", false);
		JLabel lblThesis_ECTS = new JLabel("12");
		initLabel(background, lblThesis_ECTS, "2, 29", true);
		background.add(thesisBox, "3, 29");

		JLabel lblThesisProjekt = new JLabel("Thesis-Projekt");
		initLabel(background, lblThesisProjekt, "1, 30", false);
		JLabel lblThesisProjekt_ECTS = new JLabel("12");
		initLabel(background, lblThesisProjekt_ECTS, "2, 30", true);
		background.add(thesisProjBox, "3, 30");

		JLabel lblMuendliche = new JLabel("Mündliche Prüfung");
		initLabel(background, lblMuendliche, "1, 31", false);
		JLabel lblMuendliche_ECTS = new JLabel("6");
		initLabel(background, lblMuendliche_ECTS, "2, 31", true);
		background.add(mdlPruefBox, "3, 31");
	}

	public void addElectiveSubjectsHeadline(JPanel background) {
		electiveSubjectHeadlineRowNumber = bgPanelLayout.getNumRow();
		bgPanelLayout.insertRow(electiveSubjectHeadlineRowNumber, ROW_HEIGHT);
		JLabel lblWPV = new JLabel("WPVs");
		initLabel(background, lblWPV, "0, "+electiveSubjectHeadlineRowNumber+", 3, "+electiveSubjectHeadlineRowNumber, true);
		lblWPV.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0,
				Color.BLACK));
		lblWPV.setFont(HEADLINE);		
	}
	
	public void addAdditionalSubjectHeadline(JPanel background){
		additionalSubjectHeadlineRowNumber = bgPanelLayout.getNumRow();
		bgPanelLayout.insertRow(additionalSubjectHeadlineRowNumber, ROW_HEIGHT);
		JLabel lblAdditionalSubjectsHeadline = new JLabel("Zusatzfächer");
		initLabel(background, lblAdditionalSubjectsHeadline, "0, "+additionalSubjectHeadlineRowNumber+", 3, "+additionalSubjectHeadlineRowNumber, true);
		lblAdditionalSubjectsHeadline.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0,
				Color.BLACK));
		lblAdditionalSubjectsHeadline.setFont(HEADLINE);				
	}

	public void initLabel(JPanel background, JLabel lbl, String position,
			boolean center) {
		background.add(lbl, position);
		if (center) {
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		calculateEndnote();
	}

	private void calculateEndnote() {
//		initBoxMap();
		//double overallAverageGrade = new Calculator().calculate(subjects, gradeMap);
		//lblOverallAverageGrade.setText("Durchschnittsnote: " + overallAverageGrade);
	}
	
	
	class AddElectiveSubjectActionListener implements ActionListener{		
		@Override
		public void actionPerformed(ActionEvent e) {
			Subject newElectiveSubject = new Subject();
			String subjectName = "";
			int rowNumber = addElectiveSubjectButtonRowNumber+addedElectiveSubjectsCounter;
			bgPanelLayout.insertRow(rowNumber, ROW_HEIGHT);	
			addedElectiveSubjectsCounter++;
			subjectName = "WPV " + addedElectiveSubjectsCounter;
			JLabel lblAddedElectiveSubjectName = new JLabel(subjectName);
			initLabel(contentPanel, lblAddedElectiveSubjectName, "1, "+rowNumber, false);
			newElectiveSubject.setElectiveSubject(true);
			newElectiveSubject.setName(subjectName);
			subjects.add(newElectiveSubject);
			JComboBox subjectEctsBox = createSubjectEctsComboBox();
			subjectEctsBox.setSelectedItem(SELECT_ECTS);
			contentPanel.add(subjectEctsBox, "2, "+rowNumber);
			
			JComboBox subjectGradeBox = createSubjectGradeComboBox();
			subjectGradeBox.setSelectedItem(SELECT_GRADE);
			contentPanel.add(subjectGradeBox, "3, "+rowNumber);
			enhanceContentPanelHeight(ROW_HEIGHT);
			
			addAdditionalSubjectButtonRowNumber++;
		}		
	}
	
	class AddAdditionalSubjectActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int rowNumber = addAdditionalSubjectButtonRowNumber+addedAdditionalSubjectsCounter;
			bgPanelLayout.insertRow(rowNumber, ROW_HEIGHT);	
			addedAdditionalSubjectsCounter++;
			JLabel lblAddedElectiveSubjectName = new JLabel("Zusatzfach " + addedAdditionalSubjectsCounter);
			initLabel(contentPanel, lblAddedElectiveSubjectName, "1, "+rowNumber, false);
			
			JComboBox subjectEctsBox = createSubjectEctsComboBox();
			subjectEctsBox.setSelectedItem(SELECT_ECTS);
			contentPanel.add(subjectEctsBox, "2, "+rowNumber);
			
			JComboBox subjectGradeBox = createSubjectGradeComboBox();
			subjectGradeBox.setSelectedItem(SELECT_GRADE);
			contentPanel.add(subjectGradeBox, "3, "+rowNumber);
			enhanceContentPanelHeight(ROW_HEIGHT);
		}		
	}
}
