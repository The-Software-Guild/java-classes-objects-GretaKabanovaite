package dvdlibrary.dao;

import dvdlibrary.dto.DVD;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao{
    private Map<String, DVD> collectionDVDs = new HashMap<>();
    private final String DELIMITER = "::";
    private final String FILENAME = "DVDCollection.txt";

    @Override
    public void addDVD(DVD d) throws DVDLibraryDaoException {
        loadDVDs();
        collectionDVDs.put(d.getTitle(), d);
        writeDVDs();
    }

    @Override
    public void removeDVD(DVD d) throws DVDLibraryDaoException {
        loadDVDs();
        collectionDVDs.remove(d.getTitle());
        writeDVDs();
    }

    @Override
    public void editDVD(String title, DVD newDVD) throws DVDLibraryDaoException {
        loadDVDs();
        collectionDVDs.remove(title);
        collectionDVDs.put(title, newDVD);
        writeDVDs();
    }

    @Override
    public void editDVD(String title, String newTitle) throws DVDLibraryDaoException {
        loadDVDs();
        DVD temp = collectionDVDs.get(title);
        collectionDVDs.remove(title);
        temp.setTitle(newTitle);
        collectionDVDs.put(newTitle, temp);
        writeDVDs();
    }

    @Override
    public List<DVD> listAllDVDs() throws DVDLibraryDaoException {
        loadDVDs();
        return new ArrayList<>(collectionDVDs.values());
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryDaoException {
        loadDVDs();
        return collectionDVDs.get(title);
    }

    private DVD unmarshallDVD(String DVDString){
        String[] values = DVDString.split(DELIMITER);
        if(values.length == 6){
            return new DVD(values[0], LocalDate.parse(values[1]), values[2], values[3], values[4], values[5]);
        } else {
            return new DVD(values[0], LocalDate.parse(values[1]), values[2], values[3], values[4], "");
        }
    }

    private String marshallDVD(DVD d){
        if(!d.getNote().isEmpty()){
            return d.getTitle() + DELIMITER + d.getReleaseDate() + DELIMITER + d.getMPAARating()
                    + DELIMITER + d.getDirectorName() + DELIMITER + d.getStudio() + DELIMITER + d.getNote();
        } else {
            return d.getTitle() + DELIMITER + d.getReleaseDate() + DELIMITER + d.getMPAARating()
                    + DELIMITER + d.getDirectorName() + DELIMITER + d.getStudio();
        }
    }

    private void loadDVDs() throws DVDLibraryDaoException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));
            while (sc.hasNextLine()){
                String s = sc.nextLine();
                DVD temp = unmarshallDVD(s);
                collectionDVDs.put(temp.getTitle(), temp);
            }
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException("Could not find file.", e);
        }
        sc.close();
    }

    private void writeDVDs() throws DVDLibraryDaoException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(FILENAME));
            for(DVD d: collectionDVDs.values()){
                out.println(marshallDVD(d));
                out.flush();
            }
        } catch (IOException e) {
            throw new DVDLibraryDaoException("Could not save the data.", e);
        }
        out.close();
    }
}
