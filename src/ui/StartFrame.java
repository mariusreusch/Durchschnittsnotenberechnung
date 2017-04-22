package ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import parser.Parser;
import parser.PdfParser;
import parser.WebsiteParser;

public class StartFrame extends JFrame implements ActionListener {

	private static final int HEIGHT = 385;
	private static final int WIDTH = 510;
	
	private JLabel background;
	private JLabel lblDropText;
	private JButton btnParse;
	private JButton btnInsert;
	private JButton btnPdfImport;
	private JPanel startPanel;
	private JTextArea parseArea;
	private JButton btnSend;
	private JFileChooser fileChooser;
	
	private Parser parser;

	public StartFrame() {
		setTitle("Notenkalkulator");
		getContentPane().setBackground(Color.WHITE);
		generateStartPanel();
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(getParent());
		setResizable(false);
		setVisible(true);
	}

	private void generateStartPanel() {
		startPanel = new JPanel();
		startPanel.setBackground(Color.WHITE);
		getContentPane().add(startPanel);

		generateBackground();
		generateMenu();
		generateContent();
	}

	private void generateContent() {
		JLabel welcomeText = new JLabel();
		welcomeText.setBounds(7, 2, 490, 100);
		welcomeText.setBackground(Color.BLACK);
		// welcomeText.setOpaque(true);
		background.add(welcomeText);
		
		fileChooser = new JFileChooser();
		
		lblDropText = new JLabel();
		lblDropText.setText("<html><body>Ziehe deinen Notenspiegel (PDF)<br/>" +
										"einfach auf die Oberfläche,<br/>" +
										"oder nutze eine der unten<br/>" +
										"gegebenen Optionen!</body></html>");
		lblDropText.setLocation(10, 10);
		lblDropText.setSize(200, 70);
		lblDropText.setForeground(Color.black);
		background.add(lblDropText);
		
		btnParse = new JButton("Noten parsen");
		btnParse.setLocation(50, 250);
		btnParse.setSize(150, 25);
		btnParse.addActionListener(this);
		background.add(btnParse);

		btnInsert = new JButton("Noten eintragen");
		btnInsert.setLocation(300, 250);
		btnInsert.setSize(150, 25);
		btnInsert.addActionListener(this);
		background.add(btnInsert);
		
		btnPdfImport = new JButton("Pdf importieren");
		btnPdfImport.setLocation(175, 300);
		btnPdfImport.setSize(150, 25);
		btnPdfImport.addActionListener(this);
		background.add(btnPdfImport);
		
		background.setDropTarget(new FileDrop());
	}	
	

	private void generateMenu() {
		// TODO Auto-generated method stub

	}

	private void generateBackground() {
		ImageIcon backgroundImage = null;
		backgroundImage = new ImageIcon("resources//images//background.png");
		background = new JLabel();
		background.setIcon(backgroundImage);
		background.setHorizontalAlignment(SwingConstants.CENTER);
		startPanel.add(background);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnParse)) {
			parser = WebsiteParser.getInstance();
			startPanel.removeAll();
			startPanel.repaint();
			generateParseLayout();
		} else if (e.getSource().equals(btnInsert)) {			
			this.setVisible(false);
			new UI();
		} else if (e.getSource().equals(btnSend)) {			
			this.setVisible(false);
			new UI(parseArea.getText(), parser);
		} else if (e.getSource().equals(btnPdfImport)) {
			String filepath = openFileDialogAndGetFilePath();
			processPdfFilepath(filepath);
		}
	}

	private void generateParseLayout() {
		parseArea = new JTextArea();
		parseArea.setBounds(7, 7, 490, 250);
		parseArea.setPreferredSize(new Dimension(490, 250));
		startPanel.add(parseArea);

		btnSend = new JButton("LOS!");
		btnSend.setBounds(180, 280, 150, 25);
		btnSend.addActionListener(this);
		startPanel.add(btnSend);
	}
				
	private String openFileDialogAndGetFilePath(){
		int returnVal = fileChooser.showOpenDialog(StartFrame.this);
		String filepath = "";
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();  
			filepath = file.getPath();
		}
		return filepath;
	}
	
	private void processPdfFilepath(String filepath){
		if(!filepath.equals(""))
		{
			String pdfContent = getPdfContent(filepath);
			if(!pdfContent.isEmpty()){
				parser = PdfParser.getInstance();
				setVisible(false);
				new UI(pdfContent, parser);
			}			
		}
	}
	
	private String getPdfContent(String filepath) {	
		String pdfContent = "";       
    	PDDocument document = null;
		PDFTextStripper textStripper = null;            	
			
		try {
			document = PDDocument.load(filepath);
			if(document.isEncrypted()){
				document.decrypt("");
				document.setAllSecurityToBeRemoved(true);
			}
			textStripper = new PDFTextStripper();      
			pdfContent = textStripper.getText(document);
			if(!isSelectedDocumentAHfuTranscriptOfRecords(pdfContent)){
				pdfContent="";
				showErrorMessage("Dieses PDF ist kein HFU Notenspiegel!");
			}	
			
			document.close();
		} 
		catch (IOException ex) {
			showErrorMessage("Dies ist kein valides PDF-Dokument!");
		}
		catch (Exception ex) {
			showErrorMessage("Während des Imports ist ein Fehler aufgetreten!");
		}         
        return pdfContent;
	}
	
	private boolean isSelectedDocumentAHfuTranscriptOfRecords(String documentContent){
		boolean isHfuTranscriptOfRecords = false;
		if((documentContent.contains("Notenspiegel") || documentContent.contains("Prüfungsleistungen"))
				&& documentContent.contains("https://studi-portal.hs-furtwangen.de")){
			isHfuTranscriptOfRecords = true;
		}
		return isHfuTranscriptOfRecords;
	}
	
	private void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
	}
	
	class FileDrop extends DropTarget {
		@SuppressWarnings("unchecked")
		public synchronized void drop(DropTargetDropEvent evt) {
            try {
                evt.acceptDrop(DnDConstants.ACTION_COPY);
                List<File> droppedFiles = null;
                Object droppedObject = evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);                
                droppedFiles = (List<File>) droppedObject;
                for (File file : droppedFiles) {
                	String filepath = file.getPath();
                	processPdfFilepath(filepath);	  
                }                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
	}
}
