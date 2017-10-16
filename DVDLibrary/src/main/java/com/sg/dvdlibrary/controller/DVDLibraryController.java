/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import java.util.List;

/**
 *
 * @author apitz_000
 */
public class DVDLibraryController {

    DVDLibraryView view;
    DVDLibraryDao dao;

    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {

        this.view = view;
        this.dao = dao;
    }

    public void run() {

        boolean keepGoing = true;
        int menuSelector = 0;

        while (keepGoing) {
            try {

                menuSelector = getMenuSelection();

                switch (menuSelector) {
                    case 1:
                        addDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        editDVD();
                        break;
                    case 4:
                        viewDVD();
                        break;
                    case 5:
                        listDVDs();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        invalidEntry();
                }
            } catch (DVDLibraryDaoException e) {
                view.errorMessage(e.getMessage());

            } catch (NumberFormatException x) {
                view.errorMessage(x.getMessage());
                invalidEntry();
            }

        }
        exitMessage();

    }

    private int getMenuSelection() {
        return view.mainMenu();
    }

    private void addDVD() throws DVDLibraryDaoException {
        DVD newDVD = view.newDVDinfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
    }

    private void removeDVD() throws DVDLibraryDaoException {
        String removeDVD = view.getDVDchoice();
        dao.removeDVD(removeDVD);
    }

    private void editDVD() throws DVDLibraryDaoException {
        String editDVD = view.getDVDchoice();
        DVD dvd = dao.viewDVD(editDVD);
        DVD newDVD = view.editDVD(dvd);
        dao.editDVD(editDVD, newDVD);
    }

    private void viewDVD() throws DVDLibraryDaoException {
        String viewDVD = view.getDVDchoice();
        DVD dvd = dao.viewDVD(viewDVD);
        view.viewDVD(dvd);
    }

    private void listDVDs() throws DVDLibraryDaoException {
        List<DVD> DVDlist = dao.listDVDs();
        view.showDVDlist(DVDlist);
    }

    private void invalidEntry() {
        view.invalidEntry();
    }

    private void exitMessage() {
        view.exitMessage();
    }

}
