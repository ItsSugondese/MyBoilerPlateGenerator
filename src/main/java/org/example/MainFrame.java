package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.actions.golang.MakePackageScreen;
import org.example.constants.screen.ScreenConstants;
import org.example.screen.homepage.GolangHomepageScreen;
import org.example.screen.homepage.HomePageScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
    private HomePageScreen homePageScreen;
    private GolangHomepageScreen golangHomepageScreen;
    private MakePackageScreen makePackageScreen;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    //for dimension of frame
    private int width, height;

    public MainFrame(int width, int height) {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        //creating homepage class object and initializing mainframe object, width and height value
        homePageScreen = new HomePageScreen(this, width, height);
        golangHomepageScreen = new GolangHomepageScreen(this, width, height);
        makePackageScreen = new MakePackageScreen(this, width, height);

        //setting value in variable to use in dimension
        this.width = width;
        this.height = height;

        //this method contains all the features of JFrame
        frameFeatures();

        //adding homepage panel object in frame
        mainPanel.add(homePageScreen, ScreenConstants.HOME_PAGE);
        mainPanel.add(golangHomepageScreen, ScreenConstants.GOLANG_HOME_PAGE);
        mainPanel.add(makePackageScreen, ScreenConstants.MAKE_PACKAGE);
        add(mainPanel);
//        add(homePage);


        // Initially show the startup panel
        cardLayout.show(mainPanel, ScreenConstants.MAKE_PACKAGE);

        //setting frame visibility to true
        setVisible(true);
    }

    void frameFeatures() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(width, height));
        setResizable(false);
        setTitle("Boiler plate Generator");
    }



}