package latin;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class DeclinerFrame extends JFrame
{
	public DeclinerFrame()
	{
		
		this.setTitle("~~ Latin Project ~~ Auto-Noun-Decliner");
		createTextField();
		createDeclensionPanel();
        createGenderPanel();
        createNumberPanel();
        createCasePanel();
        
        
        display = new JTextArea(15, 35);
        display.setBorder(RESULTS_BORDER);
		display.setEditable(false);
		
		createButton();
		
		createMainPanel();
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		
	}
	
	private void createTextField()
	{
		wordLabel = new JLabel("Noun: ");
		wordField = new JTextField(10);
		wordPanel = new JPanel(new FlowLayout());
		wordPanel.add(wordLabel);
		wordPanel.add(wordField);	}
	
	private void createDeclensionPanel()
	{
		declensionLabel = new JLabel("Declension: ");
				
		JRadioButton firstButton = new JRadioButton("First");
		firstButton.setActionCommand("1");
		JRadioButton secondButton = new JRadioButton("Second");
		secondButton.setActionCommand("2");
		JRadioButton thirdButton = new JRadioButton("Third");
		thirdButton.setActionCommand("3");
		JRadioButton fourthButton = new JRadioButton("Fourth");
		fourthButton.setActionCommand("4");
		JRadioButton fifthButton = new JRadioButton("Fifth");
		fifthButton.setActionCommand("5");
		
		declensions = new ButtonGroup();
		declensions.add(firstButton);
		declensions.add(secondButton);
		declensions.add(thirdButton);
		declensions.add(fourthButton);
		declensions.add(fifthButton);
		
		declensionPanel = new JPanel(new FlowLayout());
		declensionPanel.add(firstButton);
		declensionPanel.add(secondButton);
		declensionPanel.add(thirdButton);
		declensionPanel.add(fourthButton);
		declensionPanel.add(fifthButton); 
	}
	
	private void createGenderPanel()
	{
		genderLabel = new JLabel("Gender: ");
		
        JRadioButton mascButton = new JRadioButton("Masculine");
        mascButton.setActionCommand("1");
        JRadioButton femButton = new JRadioButton("Feminine");
        femButton.setActionCommand("2");
        JRadioButton neutButton = new JRadioButton("Neuter");
        neutButton.setActionCommand("3");
        
        genders = new ButtonGroup();
        genders.add(mascButton);
        genders.add(femButton);
        genders.add(neutButton);
        
		genderPanel = new JPanel(new FlowLayout());
		genderPanel.add(mascButton);
		genderPanel.add(femButton);
		genderPanel.add(neutButton);
	}
	
	private void createNumberPanel()
	{
		numberLabel = new JLabel("Number: ");
		
        JRadioButton sgButton = new JRadioButton("Singular");
        sgButton.setActionCommand("1");
        JRadioButton plButton = new JRadioButton("Plural");
        plButton.setActionCommand("2");
        
        numbers = new ButtonGroup();
        numbers.add(sgButton);
        numbers.add(plButton);
        
		numberPanel = new JPanel(new FlowLayout());
		numberPanel.add(sgButton);
		numberPanel.add(plButton); 
	}
	
	private void createCasePanel()
	{
		caseLabel = new JLabel("Case: ");

		JRadioButton nomButton = new JRadioButton("NOM");
		nomButton.setActionCommand("1");
		JRadioButton genButton = new JRadioButton("GEN");
		genButton.setActionCommand("2");
		JRadioButton datButton = new JRadioButton("DAT");
		datButton.setActionCommand("3");
		JRadioButton accButton = new JRadioButton("ACC");
		accButton.setActionCommand("4");
		JRadioButton ablButton = new JRadioButton("ABL");
		ablButton.setActionCommand("5");
		
		cases = new ButtonGroup();
		cases.add(nomButton);
		cases.add(genButton);
		cases.add(datButton);
		cases.add(accButton);
		cases.add(ablButton);
        
		casePanel = new JPanel(new FlowLayout());
		casePanel.add(nomButton);
		casePanel.add(genButton);
		casePanel.add(datButton);
		casePanel.add(accButton);
		casePanel.add(ablButton);        
	}
	
	private void createButton()
	{
		declineButton = new JButton("Decline");
		
		class DeclineListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				try
				{
					String text = wordField.getText();
					if (text.length() <= 0)
						text = null;
					int declInt = Integer.parseInt(declensions.getSelection().getActionCommand());
					int genInt = Integer.parseInt(genders.getSelection().getActionCommand());
					int numInt = Integer.parseInt(numbers.getSelection().getActionCommand());
					int caseInt = Integer.parseInt(cases.getSelection().getActionCommand());
				
					Noun.Declension declension = null;
					switch(declInt)
					{
					case 1: declension = Noun.Declension.FIRST;
						break;
					case 2: declension = Noun.Declension.SECOND;
						break;
					case 3: declension = Noun.Declension.THIRD;
						break;
					case 4: declension = Noun.Declension.FOURTH;
						break;
					case 5: declension = Noun.Declension.FIFTH;
						break;
					}
					
					
					Noun.Gender gender = null;
					switch(genInt)
					{
					case 1: gender = Noun.Gender.MASCULINE;
						break;
					case 2: gender = Noun.Gender.FEMENINE;
						break;
					case 3: gender = Noun.Gender.NEUTER;
						break;
					}
					
					
					Noun.Number number = null;
					switch (numInt)
					{
					case 1: number = Noun.Number.SINGULAR;
						break;
					case 2: number = Noun.Number.PLURAL;
						break;
					}
					
					
					Noun.Case caseType = null;;
					
					switch(caseInt)
					{
					case 1: caseType = Noun.Case.NOM;
						break;
					case 2: caseType = Noun.Case.GEN;
						break;
					case 3: caseType = Noun.Case.DAT;
						break;
					case 4: caseType = Noun.Case.ACC;
						break;
					case 5: caseType = Noun.Case.ABL;
						break;
					case 6: caseType = Noun.Case.VOC;
						break;
					case 7: caseType = Noun.Case.LOC;
					}
					
					Noun noun = null;
					switch(declension)
					{
					case FIRST: noun = new FirstDecl(text, gender, number, caseType);
						break;
					case SECOND: noun = new SecondDecl(text, gender, number, caseType);
						break; 
					case THIRD: noun = new ThirdDecl(text, gender, number, caseType);
						break;
					case FOURTH: noun = new FourthDecl(text, gender, number, caseType);
						break;
					case FIFTH: noun = new FifthDecl(text, gender, number, caseType);
						break;
					}	
						
					if (noun.toString().contains("ERROR"))
					{
						display.setText(ENDING_MISMATCH_ERROR);
						display.setForeground(Color.RED);
						display.setBorder(ERROR_BORDER);
					}
					else
					{
				        display.setBorder(RESULTS_BORDER);
						display.setForeground(Color.BLACK);
						display.setText(noun.toString());
					}
					
					
					wordLabel.setForeground(Color.BLACK);
					declensionLabel.setForeground(Color.BLACK);
					genderLabel.setForeground(Color.BLACK);
					numberLabel.setForeground(Color.BLACK);
					caseLabel.setForeground(Color.BLACK);
				}
				catch (NullPointerException exception)
				{
					display.setBorder(ERROR_BORDER);
					display.setText(NULL_FIELD_ERROR);
					display.selectAll();
					display.setForeground(Color.RED);
					
					if (wordField.getText().length() <= 0)
					{
						wordLabel.setForeground(Color.RED);
					}
					else
					{
						wordLabel.setForeground(Color.BLACK);
					}
					
					if (declensions.getSelection() == null)
					{
						declensionLabel.setForeground(Color.RED);
					}
					else
					{
						declensionLabel.setForeground(Color.BLACK);
					}
					
					if (genders.getSelection() == null)
					{
						genderLabel.setForeground(Color.RED);
					}
					else
					{
						genderLabel.setForeground(Color.BLACK);
					}
					
					if (numbers.getSelection() == null)
					{
						numberLabel.setForeground(Color.RED);
					}
					else
					{
						numberLabel.setForeground(Color.BLACK);
					}
					
					if (cases.getSelection() == null)
					{
						caseLabel.setForeground(Color.RED);
					}
					else
					{
						caseLabel.setForeground(Color.BLACK);
					}
				}
			}
		}
		
		ActionListener listener = new DeclineListener();
		declineButton.addActionListener(listener);
	}
	
	private void createMainPanel()
	{
		selectionPanel = new JPanel(new GridLayout(0, 1));
		selectionPanel.add(wordPanel);
		selectionPanel.add(declensionLabel);
		selectionPanel.add(declensionPanel);
		selectionPanel.add(genderLabel);
		selectionPanel.add(genderPanel);
		selectionPanel.add(numberLabel);
		selectionPanel.add(numberPanel);
		selectionPanel.add(caseLabel);
		selectionPanel.add(casePanel);
		selectionPanel.add(declineButton);
		
		mainPanel = new JPanel(new FlowLayout());
		mainPanel.add(selectionPanel);
		mainPanel.add(display);
		
		signature = new JLabel("Designed by Nick Tripp, 2013.  Sportula!");
		mainPanel.add(signature);		
		
		add(mainPanel);
	}
	
	
	private static final Border RESULTS_BORDER = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Results:", TitledBorder.CENTER, TitledBorder.TOP);
	private static final Border ERROR_BORDER = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), "ERROR:", TitledBorder.CENTER, TitledBorder.TOP);
	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 750;
	private static final String NULL_FIELD_ERROR = "\n            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
			"\n             * * * Please fill out all fields before declining * * *" +
			"\n            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
	private static final String ENDING_MISMATCH_ERROR = "\n            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
			"\n             * * *      ERROR: Selected Ending Mismatch:    * * *" +
			"\n            * * *    Please Ensure Correct GNC Selection     * * *" +
			"\n             * * *            and/or Correct Text Input            * * *" +
			"\n            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
	
	private JPanel mainPanel;
	private JPanel selectionPanel;
	
	private JPanel wordPanel;
	private JLabel wordLabel;
	private JTextField wordField;
	
	private JLabel declensionLabel;
	private JPanel declensionPanel;
	private ButtonGroup declensions;
	
	private JLabel genderLabel;
	private JPanel genderPanel;
	private ButtonGroup genders;
	
	private JLabel numberLabel;
	private JPanel numberPanel;
	private ButtonGroup numbers;
	
	private JLabel caseLabel;
	private JPanel casePanel;
	private ButtonGroup cases;
	
	private JButton declineButton;
	private JTextArea display;
	
	private JLabel signature;
}
