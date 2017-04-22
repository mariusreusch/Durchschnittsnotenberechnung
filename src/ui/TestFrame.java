package ui;
import info.clearthought.layout.TableLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class TestFrame extends javax.swing.JFrame {
	private JPanel bgPanel;
	private JButton btnBerechnen;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TestFrame inst = new TestFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public TestFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setBackground(Color.WHITE);
			
			ImageIcon backgroundImage = null;
			backgroundImage = new ImageIcon(
					"resources//images//background.png");
			JLabel background = new JLabel();
			background.setBorder(new EmptyBorder(5,15,5,15));
			background.setIcon(backgroundImage);
			TableLayout bgPanelLayout = new TableLayout(
					new double[][] {
							{TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL}, 
							{TableLayout.FILL, 
								TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, 1,
								TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL,
								TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, 1,
								TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, 1,
								TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL,
								TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL,
								TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL, TableLayout.FILL,
								TableLayout.FILL}
					}
					);
			bgPanelLayout.setHGap(5);
			bgPanelLayout.setVGap(5);
			background.setLayout(bgPanelLayout);
			getContentPane().add(background);
			
			addHeadRow(background);
			
			addSemesterLabels(background);
			
			addGrundstudiumLabels(background);
			
			addHauptstudiumLabels(background);
			
			addThesisLabels(background);
			
			addWPVLabels(background);
			
			btnBerechnen = new JButton();
			background.add(btnBerechnen, "3, 38");
			btnBerechnen.setText("Durchschnittsnote berechnen");
				
			pack();
			setSize(1024, 768);
			

		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	public void addHeadRow(JLabel background) {
		JLabel lblSemester = new JLabel("Semester");
		initLabel(background, lblSemester, "0, 0", true);
		JLabel lblVorlesung = new JLabel("Vorlesung");
		initLabel(background, lblVorlesung, "1, 0", true);
		JLabel lblECTS = new JLabel("ECTS");
		initLabel(background, lblECTS, "2, 0", true);
		JLabel lblNote = new JLabel("Note");
		initLabel(background, lblNote, "3, 0", true);
	}
	
	public void addSemesterLabels(JLabel background) {
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
	
	public void addGrundstudiumLabels(JLabel background) {
		JLabel lblGrundstudium = new JLabel("Grundstudium");
		initLabel(background, lblGrundstudium, "0, 1, 3, 1", true);
		lblGrundstudium.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
		
		JLabel lblABWL = new JLabel("Allgemeine Betriebswirtschaftslehre");
		initLabel(background, lblABWL, "1, 2", false);
		JLabel lblABWL_ECTS = new JLabel("7");
		initLabel(background, lblABWL_ECTS, "2, 2", true);
		JLabel lblDB1 = new JLabel("Datenbanken 1");
		initLabel(background, lblDB1, "1, 3", false);
		JLabel lblDB1_ECTS = new JLabel("7");
		initLabel(background, lblDB1_ECTS, "2, 3", true);
		JLabel lblEinfWI = new JLabel("Einf�hrung in die Wirtschaftsinformatik");
		initLabel(background, lblEinfWI, "1, 4", false);
		JLabel lblEinfWI_ECTS = new JLabel("8");
		initLabel(background, lblEinfWI_ECTS, "2, 4", true);
		JLabel lblProMod1 = new JLabel("Programmieren und Modellieren 1");
		initLabel(background, lblProMod1, "1, 5", false);
		JLabel lblProMod1_ECTS = new JLabel("8");
		initLabel(background, lblProMod1_ECTS, "2, 5", true);
		
		JPanel trennlinie = new JPanel();
		background.add(trennlinie, "0, 6, 3, 6");
		trennlinie.setBackground(Color.BLACK);
		
		JLabel lblAlgoDat = new JLabel("Algorithmen und Datenstrukturen");
		initLabel(background, lblAlgoDat, "1, 7", false);
		JLabel lblAlgoDat_ECTS = new JLabel("7");
		initLabel(background, lblAlgoDat_ECTS, "2, 7", true);
		JLabel lblIW = new JLabel("Internetworking");
		initLabel(background, lblIW, "1, 8", false);
		JLabel lblIW_ECTS = new JLabel("7");
		initLabel(background, lblIW_ECTS, "2, 8", true);
		JLabel lblMakro = new JLabel("Makro-�konomie");
		initLabel(background, lblMakro, "1, 9", false);
		JLabel lblMakro_ECTS = new JLabel("2");
		initLabel(background, lblMakro_ECTS, "2, 9", true);
		JLabel lblMathe = new JLabel("Mathematik und Statistik");
		initLabel(background, lblMathe, "1, 10", false);
		JLabel lblMathe_ECTS = new JLabel("6");
		initLabel(background, lblMathe_ECTS, "2, 10", true);
		JLabel lblReWe = new JLabel("Rechnungswesen und Controlling");
		initLabel(background, lblReWe, "1, 11", false);
		JLabel lblReWe_ECTS = new JLabel("8");
		initLabel(background, lblReWe_ECTS, "2, 11", true);
	}
	
	public void addHauptstudiumLabels(JLabel background) {
		JLabel lblHauptstudium = new JLabel("Hauptstudium");
		initLabel(background, lblHauptstudium, "0, 12, 3, 12", true);
		lblHauptstudium.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
		
		JLabel lblGP = new JLabel("Gesch�ftsprozesse");
		initLabel(background, lblGP, "1, 13", false);
		JLabel lblGP_ECTS = new JLabel("7");
		initLabel(background, lblGP_ECTS, "2, 13", true);
		JLabel lblPraesMod = new JLabel("Pr�sentations- und Moderationstechnik");
		initLabel(background, lblPraesMod, "1, 14", false);
		JLabel lblPraesMod_ECTS = new JLabel("3");
		initLabel(background, lblPraesMod_ECTS, "2, 14", true);
		JLabel lblProMod2 = new JLabel("Programmieren und Modellieren 2");
		initLabel(background, lblProMod2, "1, 15", false);
		JLabel lblProMod2_ECTS = new JLabel("7");
		initLabel(background, lblProMod2_ECTS, "2, 15", true);
		JLabel lblSysArch = new JLabel("System-Architekturen");
		initLabel(background, lblSysArch, "1, 16", false);
		JLabel lblSysArch_ECTS = new JLabel("7");
		initLabel(background, lblSysArch_ECTS, "2, 16", true);
		
		JPanel trennlinie = new JPanel();
		background.add(trennlinie, "0, 17, 3, 17");
		trennlinie.setBackground(Color.BLACK);
		
		JLabel lblCN = new JLabel("Computernetze");
		initLabel(background, lblCN, "1, 18", false);
		JLabel lblCN_ECTS = new JLabel("5");
		initLabel(background, lblCN_ECTS, "2, 18", true);
		JLabel lblDB2 = new JLabel("Datenbanken 2");
		initLabel(background, lblDB2, "1, 19", false);
		JLabel lblDB2_ECTS = new JLabel("5");
		initLabel(background, lblDB2_ECTS, "2, 19", true);
		JLabel lblISSW = new JLabel("Integrierte Standard-Software");
		initLabel(background, lblISSW, "1, 20", false);
		JLabel lblISSW_ECTS = new JLabel("5");
		initLabel(background, lblISSW_ECTS, "2, 20", true);
		JLabel lblLogistik = new JLabel("Logistik und Supply-Chain-Management");
		initLabel(background, lblLogistik, "1, 21", false);
		JLabel lblLogistik_ECTS = new JLabel("5");
		initLabel(background, lblLogistik_ECTS, "2, 21", true);
		JLabel lblSWE = new JLabel("Software Engineering");
		initLabel(background, lblSWE, "1, 22", false);
		JLabel lblSWE_ECTS = new JLabel("7");
		initLabel(background, lblSWE_ECTS, "2, 22", true);
		
		JPanel trennlinie2 = new JPanel();
		background.add(trennlinie2, "0, 23, 3, 23");
		trennlinie2.setBackground(Color.BLACK);
		
		JLabel lblBP = new JLabel("Business-Projekt");
		initLabel(background, lblBP, "1, 24", false);
		JLabel lblBP_ECTS = new JLabel("7");
		initLabel(background, lblBP_ECTS, "2, 24", true);
		JLabel lblNetzdesign = new JLabel("Netzdesign");
		initLabel(background, lblNetzdesign, "1, 25", false);
		JLabel lblNetzdesign_ECTS = new JLabel("7");
		initLabel(background, lblNetzdesign_ECTS, "2, 25", true);
		JLabel lblSWP = new JLabel("Software-Projekt");
		initLabel(background, lblSWP, "1, 26", false);
		JLabel lblSWP_ECTS = new JLabel("8");
		initLabel(background, lblSWP_ECTS, "2, 26", true);
		JLabel lblSI = new JLabel("Soziale Interaktion");
		initLabel(background, lblSI, "1, 27", false);
		JLabel lblSI_ECTS = new JLabel("2");
		initLabel(background, lblSI_ECTS, "2, 27", true);
	}
	
	public void addThesisLabels(JLabel background) {
		JLabel lblThesisHeadline = new JLabel("Thesis und m�ndliche Pr�fung");
		initLabel(background, lblThesisHeadline, "0, 28, 3, 28", true);
		lblThesisHeadline.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
		
		JLabel lblThesis = new JLabel("Thesis");
		initLabel(background, lblThesis, "1, 29", false);
		JLabel lblThesisProjekt = new JLabel("Thesis-Projekt");
		initLabel(background, lblThesisProjekt, "1, 30", false);
		JLabel lblMuendlichb = new JLabel("M�ndliche Pr�fung");
		initLabel(background, lblMuendlichb, "1, 31", false);
	}
	
	public void addWPVLabels(JLabel background) {
		JLabel lblWPV = new JLabel("WPVs");
		initLabel(background, lblWPV, "0, 32, 3, 32", true);
		lblWPV.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
	}
	
	public void initLabel(JLabel background, JLabel lbl, String position, boolean center) {
		background.add(lbl, position);
		
		if(center)
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
	}

}
