/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apitz_000
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {
    private Map<String, DVD> dvdLibrary = new HashMap<>();
    public static final String DVD_LIBRARY = "dvdLibrary.txt";
    public static final String DELIMITER = "::";
    

    private void loadLibrary() throws DVDLibraryDaoException {
        Scanner sc;

        try {
            sc = new Scanner(new BufferedReader(new FileReader(DVD_LIBRARY)));

        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException("Could not load library into memory", e);
        }
        String currentLine;
        String[] currentTokens;

        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            DVD currentDVD = new DVD(currentTokens[0]);
            currentDVD.setReleaseDate(currentTokens[1]);
            currentDVD.setRating(currentTokens[2]);
            currentDVD.setDirector(currentTokens[3]);
            currentDVD.setNotes(currentTokens[4]);

            dvdLibrary.put(currentDVD.getTitle(), currentDVD);
        }
        sc.close();
    }

    private void writeLibrary() throws DVDLibraryDaoException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_LIBRARY));
        } catch (IOException e) {
            throw new DVDLibraryDaoException("Could not save Library", e);
        }
        List<DVD> dvdList = new ArrayList<>(dvdLibrary.values());
        for (DVD currentDVD : dvdList) {
            out.println(currentDVD.getTitle() + DELIMITER
                    + currentDVD.getReleaseDate() + DELIMITER
                    + currentDVD.getRating() + DELIMITER
                    + currentDVD.getDirector() + DELIMITER
                    + currentDVD.getNotes() + DELIMITER);
            out.flush();
        }
        out.close();
    }

    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        DVD newDVD = dvdLibrary.put(title, dvd);
        writeLibrary();
        return newDVD;
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException {
        DVD removeDVD = dvdLibrary.remove(title);
        writeLibrary();
        return removeDVD;
    }

    @Override
    public DVD editDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        dvdLibrary.remove(title);
        DVD newDVD = dvdLibrary.put(dvd.getTitle(), dvd);
        writeLibrary();
        return  newDVD;

    }

    @Override
    public DVD viewDVD(String title) throws DVDLibraryDaoException {
        loadLibrary();
        return dvdLibrary.get(title);

    }

    @Override
    public List<DVD> listDVDs() throws DVDLibraryDaoException {
        loadLibrary();
        return new ArrayList<>(dvdLibrary.values());
    }

}
