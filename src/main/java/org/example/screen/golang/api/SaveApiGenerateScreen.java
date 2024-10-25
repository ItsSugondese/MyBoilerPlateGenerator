package org.example.screen.golang.api;

import org.example.MainFrame;
import org.example.constants.filepath.golang.FilePathConstants;
import org.example.constants.screen.ScreenConstants;
import org.example.constants.variables.VariableConstants;
import org.example.repository.modulepathrepo.golang.ModulePathRepo;
import org.example.utils.ActionPerformer;
import org.example.utils.FileWriterHelper;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class SaveApiGenerateScreen extends JPanel {

    //variable for panel dimensions
    private int width, height;

    //variable for sizing buttons
    private int buttonWidth, buttonHeight;

    //instance of MainFrame class
    private MainFrame frame;


    private JButton backButton;
    private JButton generateButton;

    private JTextField moduleNameTextField;

    private JRadioButton withUpdateSaveRadioButton;
    private JRadioButton withImageSaveRadioButton;
    private ButtonGroup group;

    private JTextPane codeGeneratedTextArea;

    private JScrollPane scrollPane;
    //variable for designing using html
    private String startHtml, endHtml;

    public SaveApiGenerateScreen(MainFrame frame, int width, int height){

        buttonWidth = 200;
        buttonHeight = 30;

        this.frame = frame;
        this.width = width;
        this.height = height;

        startHtml = "<html> <style> h2{font-family: \"Comic Sans MS\", \"Comic Sans\", cursive;}span{font-family: fantasy}</style> <center><h2>";
        endHtml = "</h2> </center> </html>";

        //this method contains all the panel properties
        panelFeatures();

        //this method contains all the components like label and buttons declaration
        materials();
    }

    void materials(){
        //for golang button declaration, properties and panel adding
        backButtonInit();


        radioButtonsInit();
        moduleNameTextFieldInit();
        generateButtonInit();

        codeGeneratedTextAreaInit();

        //for asking label declaration, properties and panel adding


    }

    void radioButtonsInit(){


        withUpdateSaveRadioButton = new JRadioButton("With Update");
        withUpdateSaveRadioButton.setSelected(true);
        withUpdateSaveRadioButton.setBounds(width / 2 - buttonWidth/2, backButton.getY() + backButton.getHeight() + 10,
                buttonWidth, buttonHeight);
        add(withUpdateSaveRadioButton);

        withImageSaveRadioButton = new JRadioButton("With Image");
        withImageSaveRadioButton.setBounds(withUpdateSaveRadioButton.getX(), withUpdateSaveRadioButton.getY() + withUpdateSaveRadioButton.getHeight() + 5, buttonWidth, buttonHeight);
        add(withImageSaveRadioButton);

        group = new ButtonGroup();
        group.add(withUpdateSaveRadioButton);
        group.add(withImageSaveRadioButton);


    }

    void backButtonInit(){
        backButton = new JButton(startHtml + "Back" + endHtml);
        backButton.setBounds(VariableConstants.BACK_BUTTON_X, VariableConstants.BACK_BUTTON_Y, VariableConstants.BACK_BUTTON_WIDTH, VariableConstants.BACK_BUTTON_HEIGHT);
        backButton.addActionListener(new ActionPerformer(frame, ScreenConstants.API));
        add(backButton);
    }

    void codeGeneratedTextAreaInit(){
        codeGeneratedTextArea = new JTextPane();     // Wrap at word boundaries
//        codeGeneratedTextArea.setContentType("text/html");
        codeGeneratedTextArea.setBorder((new LineBorder(Color.BLACK)));

        // Set JTextArea alignment
        codeGeneratedTextArea.setMargin(new Insets(10, 10, 10, 10));
        scrollPane = new JScrollPane(codeGeneratedTextArea);
        scrollPane.setBounds(10, generateButton.getY() + generateButton.getHeight() + 30, width - 20, height/2);
//        add(scrollPane, BorderLayout.CENTER);
        add(scrollPane);
    }

    void moduleNameTextFieldInit(){
        moduleNameTextField = new JTextField();
        moduleNameTextField.setBounds(width/2 - buttonWidth/2, withImageSaveRadioButton.getY() + withImageSaveRadioButton.getHeight() + 10, buttonWidth, buttonHeight);

        // Add DocumentListener to track changes on every keystroke
        moduleNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLabel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLabel();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLabel(); // Handles attribute changes, not commonly used for plain text
            }

            private void updateLabel() {
                generateButton.setEnabled(verifyGenerateButtonClickable());
            }
        });
        add(moduleNameTextField);
    }

    void generateButtonInit(){
        int generateButtonWidth = 120;
        generateButton = new JButton("Generate");
        generateButton.setBounds((moduleNameTextField.getX() + moduleNameTextField.getWidth()/2) - generateButtonWidth/2, moduleNameTextField.getY() + moduleNameTextField.getHeight() + 10,
                generateButtonWidth, buttonHeight);
        generateButton.setEnabled(verifyGenerateButtonClickable());

        generateButton.addActionListener(e -> {
            String moduleName = moduleNameTextField.getText().trim().replace(" ", "-").toLowerCase();

            ButtonModel selectedModel = group.getSelection();
            if(selectedModel == withUpdateSaveRadioButton.getModel()){
                String text = FileWriterHelper.readAndWriteFromStorageFileToTextArea(FilePathConstants.RESOURCE_SAVE_API_PATH, moduleName);
                codeGeneratedTextArea.setText(text);
            }
            else if(selectedModel == withImageSaveRadioButton.getModel()){
                String text = FileWriterHelper.readAndWriteFromStorageFileToTextArea(FilePathConstants.RESOURCE_SAVE_API_IMAGE_PATH, moduleName);
                codeGeneratedTextArea.setText(text);
            }

        });

        add(generateButton);
    }

    private boolean verifyGenerateButtonClickable(){
        return (moduleNameTextField.getText() != null && !moduleNameTextField.getText().isBlank());
    }

    void panelFeatures() {
        setSize(new Dimension(width, height));
        setLayout(null);
        setOpaque(false);
    }
}

