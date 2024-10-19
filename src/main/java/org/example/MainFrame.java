package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.screen.HomePage;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MainFrame extends JFrame {

    //    //Creating ArrayList using list interface
//    private List<Instrument> instrumentList;
//
//    //panels to add in frame
//    private RentSellPanel rentSellPanel;
//    private AllButtonsPanel allButtonsPanel;
//    private DisplayPanel displayPanel;
//    private RadioButtonsPanel radioButtonsPanel;
    private HomePage homePage;

    //for dimension of frame
    private int width, height;

    public MainFrame(int width, int height) {

        //creating homepage class object and initializing mainframe object, width and height value
        homePage = new HomePage(this, width, height);
        //creating arrayList
//        instrumentList = new ArrayList<>();

        //setting value in variable to use in dimension
        this.width = width;
        this.height = height;

        //this method contains all the features of JFrame
        frameFeatures();

        //adding homepage panel object in frame
        add(homePage);


        //setting frame visibility to true
        setVisible(true);
    }

    void frameFeatures() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(width, height));
        setResizable(false);
        setLayout(null);
        setTitle("Sarangi Sansar");
    }



}