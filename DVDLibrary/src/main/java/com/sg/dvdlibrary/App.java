/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DVDLibraryController;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author apitz_000
 */
public class App {

    public static void main(String[] args) {
        
        UserIO io = new UserIOConsoleImpl();
        DVDLibraryDao dao = new DVDLibraryDaoFileImpl();
        DVDLibraryView view = new DVDLibraryView(io);

        DVDLibraryController controller = new DVDLibraryController(dao, view);
        
        controller.run();
    }

}
