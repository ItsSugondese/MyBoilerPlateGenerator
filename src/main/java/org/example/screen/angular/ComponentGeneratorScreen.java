package org.example.screen.angular;

import org.example.MainFrame;
import org.example.constants.filepath.golang.FilePathConstants;
import org.example.constants.screen.ScreenConstants;
import org.example.constants.variables.VariableConstants;
import org.example.enums.FolderNameEnums;
import org.example.enums.LanguageNameEnums;
import org.example.repository.golang.modulepath.ModulePathRepo;
import org.example.utils.ActionPerformer;
import org.example.utils.CommandLineRunner;
import org.example.utils.helper.FileWriterHelper;
import org.example.utils.uihelper.CustomPopUp;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class ComponentGeneratorScreen extends JPanel {

    //variable for panel dimensions
    private int width, height;

    //variable for sizing buttons
    private int buttonWidth, buttonHeight;

    //instance of MainFrame class
    private MainFrame frame;

    private JLabel pathLabel;

    private JRadioButton bothRadioButton;
    private JRadioButton onlyComponentRadioButton;
    private ButtonGroup group;

    private JButton backButton;
    private JButton pathSelectorButton;
    private JButton generateButton;

    private JTextField moduleNameTextField;
    //variable for designing using html
    private String startHtml, endHtml;

    public ComponentGeneratorScreen(MainFrame frame, int width, int height){

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


        //labels
        pathLabelInit();
        pathSelectorButtonInit();

        radioButtonsInit();

        moduleNameTextFieldInit();
        generateButtonInit();

        //for asking label declaration, properties and panel adding


    }


    void backButtonInit(){
        backButton = new JButton(startHtml + "Back" + endHtml);
        backButton.setBounds(VariableConstants.BACK_BUTTON_X, VariableConstants.BACK_BUTTON_Y, VariableConstants.BACK_BUTTON_WIDTH, VariableConstants.BACK_BUTTON_HEIGHT);
        backButton.addActionListener(new ActionPerformer(frame, ScreenConstants.ANGULAR_HOME_PAGE));
        add(backButton);
    }

    void pathLabelInit(){
        String path = ModulePathRepo.getModulePath(LanguageNameEnums.ANGULAR);

        if (path == null){
            path = "No path specified";
        }
        pathLabel = new JLabel(startHtml + "<span> " + path + "</span>" + endHtml);
        Dimension labelSize = pathLabel.getPreferredSize();

        pathLabel.setBounds(width / 2 - labelSize.width/2, backButton.getY() + backButton.getHeight() + 10,
                labelSize.width, labelSize.height);
        add(pathLabel);
    }


    void pathSelectorButtonInit(){
        pathSelectorButton = new JButton("Select Folder");
        pathSelectorButton.setBounds(width / 2 - buttonWidth/2, pathLabel.getY() + pathLabel.getHeight(),
                buttonWidth, buttonHeight);
        pathSelectorButton.addActionListener(e -> {
            String selectedPath = ModulePathRepo.getModulePath(LanguageNameEnums.ANGULAR);
            JFileChooser fileChooser = new JFileChooser(selectedPath == null? "" : selectedPath);
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Allow only directories to be selected
            fileChooser.setAcceptAllFileFilterUsed(false); // Disable the "All files" option

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFolder = fileChooser.getSelectedFile();
                // Write lines to the file, creating it if it doesn't exist, and appending if it does
                try {
                    String path = ModulePathRepo.setModulePath(selectedFolder, LanguageNameEnums.ANGULAR);
                    pathLabel.setText(path);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Selected folder: " + selectedFolder.getAbsolutePath());
            }
        });

        add(pathSelectorButton);
    }



    void radioButtonsInit(){
        bothRadioButton = new JRadioButton("Both");
        bothRadioButton.setSelected(true);
        bothRadioButton.setBounds(width / 2 - buttonWidth, pathSelectorButton.getY() + pathSelectorButton.getHeight() + 30,
                buttonWidth, buttonHeight);
        add(bothRadioButton);

        onlyComponentRadioButton = new JRadioButton("Only Component");
        onlyComponentRadioButton.setBounds(bothRadioButton.getX() + bothRadioButton.getWidth() , bothRadioButton.getY(), buttonWidth, buttonHeight);
        add(onlyComponentRadioButton);

        group = new ButtonGroup();

        group.add(bothRadioButton);
        group.add(onlyComponentRadioButton);
    }

    void moduleNameTextFieldInit(){
        moduleNameTextField = new JTextField();
        moduleNameTextField.setBounds(width/2 - 100, height/2 - 15, buttonWidth, buttonHeight);

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
//        generateButton.setEnabled(verifyGenerateButtonClickable());

        generateButton.addActionListener(e -> {
            generateButton.setEnabled(false);

            String moduleName = moduleNameTextField.getText().trim().replace(" ", "-").toLowerCase();
            String modulePath = ModulePathRepo.getModulePath(LanguageNameEnums.ANGULAR) + File.separator + moduleName;


            String outputCommandValue = null;
            try {
                outputCommandValue = CommandLineRunner.runLanguageBasedCommand(ModulePathRepo.getModulePath(LanguageNameEnums.ANGULAR),
                        moduleNameTextField.getText().trim(), LanguageNameEnums.ANGULAR);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

//            moduleNameTextField.setText("");
//            CustomPopUp.showPopUpMessage(frame, "Files created successfully");
            CustomPopUp.showPopUpMessage(frame, outputCommandValue);


        });

        add(generateButton);
    }

    private boolean verifyGenerateButtonClickable(){
        return ModulePathRepo.getModulePath(LanguageNameEnums.ANGULAR) != null && (moduleNameTextField.getText() != null && !moduleNameTextField.getText().isBlank());
    }

    void panelFeatures() {
        setSize(new Dimension(width, height));
        setLayout(null);
        setOpaque(false);
    }
}

