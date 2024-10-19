package org.example.actions;

import org.example.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionPerformer implements ActionListener {

    //instrance of mainframe class
    private MainFrame frame;

    //for text decoration using HTML
    String startHtml, endHtml;


    public ActionPerformer(MainFrame frame) {
        this.frame = frame;
        startHtml = "<html> <head> <style> " +
                "h3{" +
                "text-align:center;" +
                "margin-top:0px;" +
                "font-family: \"Comic Sans MS\", \"Comic Sans\", cursive;} " +
                "</style> " +
                "</head> <body><h3>";
        endHtml = "</h3> </body></html>";
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
