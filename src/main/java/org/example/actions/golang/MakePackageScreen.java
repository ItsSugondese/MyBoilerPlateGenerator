package org.example.actions.golang;

import org.example.Main;
import org.example.MainFrame;
import org.example.constants.filepath.golang.FilePathConstants;
import org.example.constants.screen.ScreenConstants;
import org.example.constants.variables.VariableConstants;
import org.example.enums.FolderNameEnums;
import org.example.repository.modulepathrepo.golang.ModulePathRepo;
import org.example.utils.ActionPerformer;
import org.example.utils.FileWriterHelper;
import org.example.utils.uihelper.CustomPopUp;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MakePackageScreen extends JPanel {

    //variable for panel dimensions
    private int width, height;

    //variable for sizing buttons
    private int buttonWidth, buttonHeight;

    //instance of MainFrame class
    private MainFrame frame;

    //All the buttons and label
    private JLabel asking;
    private JLabel pathLabel;

    private JButton projectStructureButton;
    private JButton apiButton;
    private JButton backButton;
    private JButton pathSelectorButton;
    private JButton generateButton;

    private JTextField moduleNameTextField;
    //variable for designing using html
    private String startHtml, endHtml;

    public MakePackageScreen(MainFrame frame, int width, int height){

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

        moduleNameTextFieldInit();
        generateButtonInit();

        //for asking label declaration, properties and panel adding


    }


    void backButtonInit(){
        backButton = new JButton(startHtml + "Back" + endHtml);
        backButton.setBounds(VariableConstants.BACK_BUTTON_X, VariableConstants.BACK_BUTTON_Y, VariableConstants.BACK_BUTTON_WIDTH, VariableConstants.BACK_BUTTON_HEIGHT);
        backButton.addActionListener(new ActionPerformer(frame, ScreenConstants.HOME_PAGE));
        add(backButton);
    }

    void pathLabelInit(){
        String path = ModulePathRepo.getModulePath();

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
            String selectedPath = ModulePathRepo.getModulePath();
            JFileChooser fileChooser = new JFileChooser(selectedPath == null? "" : selectedPath);
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Allow only directories to be selected
            fileChooser.setAcceptAllFileFilterUsed(false); // Disable the "All files" option

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFolder = fileChooser.getSelectedFile();
                // Write lines to the file, creating it if it doesn't exist, and appending if it does
                try {
                    String path = selectedFolder.getAbsolutePath();
                    Files.write(Paths.get(FilePathConstants.MODULE_PATH), Arrays.asList(path));
                    pathLabel.setText(path);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Selected folder: " + selectedFolder.getAbsolutePath());
            }
        });

        add(pathSelectorButton);
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
            String modulePath = ModulePathRepo.getModulePath() + File.separator + moduleName;

            File folder = new File(modulePath);

            // Check if the folder exists
            if (folder.exists()) {
              return;
            }

            folder.mkdir();

            List<FolderNameEnums> subFoldersEnums = Arrays.stream(FolderNameEnums.values()).toList();

            for(FolderNameEnums subFolderEnum : subFoldersEnums){
                String snakeCaseModuleName = moduleName.replace("-", "_").toLowerCase();
                String toNameSubFolder = subFolderEnum.getName();

                if(subFolderEnum == FolderNameEnums.NAVIGATOR)
                    toNameSubFolder = moduleName + "-" + subFolderEnum.getName();

                String subFolderPath = modulePath + File.separator + toNameSubFolder;

                File subFolderDirectory = new File(subFolderPath);
                subFolderDirectory.mkdir();

                String toNameGoFile = null;

                if(subFolderEnum == FolderNameEnums.ROUTE)
                    toNameGoFile = "routes";
                else
                    toNameGoFile = subFolderEnum.getName();
                String fileName = snakeCaseModuleName + "_" + toNameGoFile + ".go";
                String fileNameCreatePath = subFolderPath + File.separator + fileName;

//                    Files.createFile(Paths.get(fileNameCreatePath));
                    if(subFolderEnum == FolderNameEnums.ROUTE)
                        FileWriterHelper.readAndWriteFromStorageFileToFile(FilePathConstants.RESOURCE_GO_ROUTE_PATH, fileNameCreatePath, moduleName);
                    else if(subFolderEnum == FolderNameEnums.CONTROLLER)
                        FileWriterHelper.readAndWriteFromStorageFileToFile(FilePathConstants.RESOURCE_CONTROLLER_PATH, fileNameCreatePath, moduleName);
                    else if(subFolderEnum == FolderNameEnums.SERVICE)
                        FileWriterHelper.readAndWriteFromStorageFileToFile(FilePathConstants.RESOURCE_SERVICE_PATH, fileNameCreatePath, moduleName);
                    else if(subFolderEnum == FolderNameEnums.MODEL)
                        FileWriterHelper.readAndWriteFromStorageFileToFile(FilePathConstants.RESOURCE_MODEL_PATH, fileNameCreatePath, moduleName);
                    else if(subFolderEnum == FolderNameEnums.REPOSITORY)
                        FileWriterHelper.readAndWriteFromStorageFileToFile(FilePathConstants.RESOURCE_REPOSITORY_PATH, fileNameCreatePath, moduleName);
                    else if(subFolderEnum == FolderNameEnums.NAVIGATOR)
                        FileWriterHelper.readAndWriteFromStorageFileToFile(FilePathConstants.RESOURCE_NAVIGATOR_PATH, fileNameCreatePath, moduleName);
                    else if(subFolderEnum == FolderNameEnums.DTO) {
                        String requestName = snakeCaseModuleName + "_" + "request.go";
                        String responseName = snakeCaseModuleName + "_" + "details_response.go";
                        String dtoRequestFileNameCreatePath = subFolderPath + File.separator + requestName;
                        String dtoResponseFileNameCreatePath = subFolderPath + File.separator + responseName;
                        FileWriterHelper.readAndWriteFromStorageFileToFile(FilePathConstants.RESOURCE_DTO_REQUEST_PATH, dtoRequestFileNameCreatePath, moduleName);
                        FileWriterHelper.readAndWriteFromStorageFileToFile(FilePathConstants.RESOURCE_DTO_RESPONSE_PATH, dtoResponseFileNameCreatePath, moduleName);

                }
            }

            moduleNameTextField.setText("");
            CustomPopUp.showPopUpMessage(frame, "Files created successfully");


        });

        add(generateButton);
    }

    private boolean verifyGenerateButtonClickable(){
        return ModulePathRepo.getModulePath() != null && (moduleNameTextField.getText() != null && !moduleNameTextField.getText().isBlank());
    }

    void panelFeatures() {
        setSize(new Dimension(width, height));
        setLayout(null);
        setOpaque(false);
    }
}

