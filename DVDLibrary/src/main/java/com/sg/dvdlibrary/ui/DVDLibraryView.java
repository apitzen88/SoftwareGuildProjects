/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author apitz_000
 */
public class DVDLibraryView {

    private UserIO io;

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public int mainMenu() {

        io.print("Main Menu");
        io.print("1. Add a DVD");
        io.print("2. Remove a DVD");
        io.print("3. Edit a DVD");
        io.print("4. View a DVD");
        io.print("5. List All DVD's");
        io.print("6. Exit");

        return io.readInt("Select a menu item from above", 1, 6);

    }

    public String getDVDchoice() {
        return io.readString("Enter the DVD Title");
    }

    public DVD newDVDinfo() {
        String title = io.readString("Enter Title");
        String releaseDate = io.readString("Enter Release Date");
        String rating = io.readString("Enter MPAA Rating");
        String director = io.readString("Enter Director");
        String notes = io.readString("Enter any Personal Notes");
        DVD currentDVD = new DVD(title);
        currentDVD.setTitle(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setRating(rating);
        currentDVD.setDirector(director);
        currentDVD.setNotes(notes);
        return currentDVD;
    }

    public void showDVDlist(List<DVD> DVDList) {
        for (DVD currentDVD : DVDList) {
            io.print(currentDVD.getTitle() + " - "
                    + currentDVD.getReleaseDate() + " - "
                    + currentDVD.getDirector() + " - "
                    + currentDVD.getRating());
            io.print(currentDVD.getNotes());
        }
        io.readString("Press Enter to Continue");
    }

    public void viewDVD(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle() + " - "
                    + dvd.getReleaseDate() + " - "
                    + dvd.getDirector() + " - "
                    + dvd.getRating());
            io.print(dvd.getNotes());
        } else {
            io.print("No such DVD in library...");
        }
        io.readString("Press enter to continue");
    }

    public DVD editDVD(DVD dvd) {
        DVD editDVD = new DVD(dvd.getTitle());

        String title = io.readString("Enter Title( " + dvd.getTitle() + " ): ");
        editDVD.setTitle(title);

        String releaseDate = io.readString("Enter Release Date( " + dvd.getReleaseDate() + " ): ");
        editDVD.setReleaseDate(releaseDate);

        String rating = io.readString("Enter Rating( " + dvd.getRating() + " ): ");
        editDVD.setRating(rating);

        String director = io.readString("Enter Director( " + dvd.getDirector() + " ): ");
        editDVD.setDirector(director);

        String notes = io.readString("Enter Notes( " + dvd.getNotes() + " ): ");
        editDVD.setNotes(notes);

        return editDVD;
    }

    public void invalidEntry() {
        io.print("INVALID ENTRY (Select menu item 1-6)");
    }

    public void exitMessage() {
        io.print("EXITING...");
    }
    
    public void errorMessage(String errMsg){
        io.print("-ERROR-");
        io.print(errMsg);
    }

}
