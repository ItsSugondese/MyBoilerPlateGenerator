package org.example.screen;

import org.example.MainFrame;
import org.example.actions.ActionPerformer;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {

    //variable for panel dimensions
    private int width, height;

    //variable for sizing buttons
    private int buttonWidth, buttonHeight;

    //instance of MainFrame class
    private MainFrame frame;

    //All the buttons and label
    private JLabel asking;
    private JButton rentButton;
    private JButton sellButton;

    //variable for designing using html
    private String startHtml, endHtml;

    //instance for event Listener
    private ActionPerformer actionPerformer;

    public HomePage(MainFrame frame, int width, int height){

        actionPerformer = new ActionPerformer(frame);

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

        //for rent button declaration, properties and panel adding
        rent();

        //for sell button declaration, properties and panel adding
        sell();

        //for asking label declaration, properties and panel adding
        ask();
    }

    void ask(){
        asking = new JLabel(startHtml + "<span> What you wanna do?? </span>" + endHtml);
        asking.setBounds(rentButton.getX(), rentButton.getY()-30, 250, 20);
        add(asking);
    }

    void rent(){
        rentButton  = new JButton(startHtml + "Rent an Instrument" + endHtml);
        rentButton.setName("Rent an Instrument");
        rentButton.setBounds(width/2 - buttonWidth/2, height/2 - buttonHeight - 5 , buttonWidth, buttonHeight);
        rentButton.addActionListener(actionPerformer);
        add(rentButton);
    }

    void sell(){
        sellButton = new JButton(startHtml + "Sell an Instrument" + endHtml);
        sellButton.setName("Sell an Instrument");
        sellButton.setBounds(width/2 - buttonWidth/2, height/2 + 5, buttonWidth , buttonHeight);
        sellButton.addActionListener(actionPerformer);
        add(sellButton);
    }



    void panelFeatures() {
        setSize(new Dimension(width, height));
        setLayout(null);
        setOpaque(false);
    }
}
