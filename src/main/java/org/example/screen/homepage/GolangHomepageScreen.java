package org.example.screen.homepage;

import org.example.MainFrame;
import org.example.utils.ActionPerformer;
import org.example.constants.screen.ScreenConstants;

import javax.swing.*;
import java.awt.*;

public class GolangHomepageScreen extends JPanel {

    //variable for panel dimensions
    private int width, height;

    //variable for sizing buttons
    private int buttonWidth, buttonHeight;

    //instance of MainFrame class
    private MainFrame frame;

    //All the buttons and label
    private JLabel asking;

    private JButton projectStructureButton;
    private JButton apiButton;
    private JButton backButton;

    //variable for designing using html
    private String startHtml, endHtml;

    //instance for event Listener
    private ActionPerformer actionPerformer;

    public GolangHomepageScreen(MainFrame frame, int width, int height){

//        actionPerformer = new ActionPerformer(frame);

        buttonWidth = 200;
        buttonHeight = 90;

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
        projectStructure();

        api();

        //for asking label declaration, properties and panel adding
        ask();

        back();

    }

    void ask(){
        asking = new JLabel(startHtml + "<span> Select operations: </span>" + endHtml);
        asking.setBounds(projectStructureButton.getX(), projectStructureButton.getY()-30, 250, 20);
        add(asking);
    }

    void projectStructure(){
        projectStructureButton = new JButton(startHtml + "Make Packages" + endHtml);
        projectStructureButton.setBounds(width/2 - buttonWidth/2, height/2 - buttonHeight - 5 , buttonWidth, buttonHeight);
        projectStructureButton.addActionListener(new ActionPerformer(frame, ScreenConstants.MAKE_PACKAGE));
        add(projectStructureButton);
    }

    void api(){
        apiButton = new JButton(startHtml + "API" + endHtml);
        apiButton.setBounds(projectStructureButton.getX(), projectStructureButton.getY() + projectStructureButton.getHeight() + 20 , buttonWidth, buttonHeight);
//        apiButton.addActionListener(actionPerformer);
        add(apiButton);
    }

    void back(){
        backButton = new JButton(startHtml + "Back" + endHtml);
        backButton.setBounds(10, 10, 100, 30);
        backButton.addActionListener(new ActionPerformer(frame, ScreenConstants.HOME_PAGE));
        add(backButton);
    }




    void panelFeatures() {
        setSize(new Dimension(width, height));
        setLayout(null);
        setOpaque(false);
    }
}

