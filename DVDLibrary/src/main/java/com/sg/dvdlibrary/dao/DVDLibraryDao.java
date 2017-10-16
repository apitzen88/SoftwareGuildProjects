/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author apitz_000
 */
public interface DVDLibraryDao {
    
    //adds dvd to library and associates it with the name
    DVD addDVD(String name, DVD dvd) throws DVDLibraryDaoException;
    
    //removes a dvd from the library by its name
    DVD removeDVD(String name) throws DVDLibraryDaoException;
    
    //allows to edit an existing dvd in the library
    DVD editDVD(String name, DVD dvd) throws DVDLibraryDaoException;
    
    //allows to search for a single dvd in the library
    DVD viewDVD(String name) throws DVDLibraryDaoException;
    
    //lists all dvd's in the library
    List<DVD> listDVDs() throws DVDLibraryDaoException;
    
    
    
}
