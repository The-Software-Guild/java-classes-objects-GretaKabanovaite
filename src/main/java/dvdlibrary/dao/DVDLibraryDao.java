package dvdlibrary.dao;

import dvdlibrary.dto.DVD;

import java.util.List;

public interface DVDLibraryDao {
    void addDVD(DVD d) throws DVDLibraryDaoException;
    void removeDVD(DVD d) throws DVDLibraryDaoException;
    void editDVD(String title, DVD newDVD) throws DVDLibraryDaoException;
    void editDVD(String title, String newTitle) throws DVDLibraryDaoException;
    List<DVD> listAllDVDs() throws DVDLibraryDaoException;
    DVD getDVD(String title) throws DVDLibraryDaoException;
}
