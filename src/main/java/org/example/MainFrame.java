package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.screen.golang.ApiScreen;
import org.example.screen.golang.MakeEnumScreen;
import org.example.screen.golang.MakePackageScreen;
import org.example.constants.screen.ScreenConstants;
import org.example.screen.golang.ProjectSetupScreen;
import org.example.screen.golang.api.GetApiGenerateScreen;
import org.example.screen.golang.api.PaginateApiGenerateScreen;
import org.example.screen.golang.api.SaveApiGenerateScreen;
import org.example.screen.homepage.GolangHomepageScreen;
import org.example.screen.homepage.HomePageScreen;

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
    private HomePageScreen homePageScreen;
    private GolangHomepageScreen golangHomepageScreen;

    private MakePackageScreen makePackageScreen;
    private ApiScreen apiScreen;
    private MakeEnumScreen makeEnumScreen;
    private ProjectSetupScreen projectSetupScreen;

    private SaveApiGenerateScreen saveApiGenerateScreen;
    private GetApiGenerateScreen getApiGenerateScreen;
    private PaginateApiGenerateScreen paginateApiGenerateScreen;

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
        apiScreen = new ApiScreen(this, width, height);
        makeEnumScreen = new MakeEnumScreen(this, width, height);
        projectSetupScreen = new ProjectSetupScreen(this, width, height);

        saveApiGenerateScreen = new SaveApiGenerateScreen(this, width, height);
        getApiGenerateScreen = new GetApiGenerateScreen(this, width, height);
        paginateApiGenerateScreen = new PaginateApiGenerateScreen(this, width, height);
        //setting value in variable to use in dimension
        this.width = width;
        this.height = height;

        //this method contains all the features of JFrame
        frameFeatures();

        //adding homepage panel object in frame
        mainPanel.add(homePageScreen, ScreenConstants.HOME_PAGE);
        mainPanel.add(golangHomepageScreen, ScreenConstants.GOLANG_HOME_PAGE);

        mainPanel.add(makePackageScreen, ScreenConstants.MAKE_PACKAGE);
        mainPanel.add(apiScreen, ScreenConstants.API);
        mainPanel.add(makeEnumScreen, ScreenConstants.MAKE_ENUM);
        mainPanel.add(projectSetupScreen, ScreenConstants.PROJECT_SETUP);

        mainPanel.add(saveApiGenerateScreen, ScreenConstants.SAVE_API_GENERATE);
        mainPanel.add(getApiGenerateScreen, ScreenConstants.GET_API_GENERATE);
        mainPanel.add(paginateApiGenerateScreen, ScreenConstants.PAGINATE_API_GENERATE);
        add(mainPanel);

        // Initially show the startup panel
        cardLayout.show(mainPanel, ScreenConstants.HOME_PAGE);

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
