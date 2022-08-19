package dvdlibrary.ui;


import dvdlibrary.dao.DVDLibraryDaoException;
import dvdlibrary.dto.DVD;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DVDLibraryView {
    UserIO io;
    public DVDLibraryView(UserIO io){
        this.io = io;
    }

    public int showMenuAndGetSelection(){
        io.print("=== DVD Library Menu ===");
        io.print("1. Add a new DVD");
        io.print("2. Remove a DVD");
        io.print("3. Edit an existing DVD");
        io.print("4. List all DVDs");
        io.print("5. Get a specific DVD");
        io.print("6. Exit");
        return io.readInt("Please choose one of the above options: ", 1, 6);
    }

    public void pressEnterPrompt(){
        io.readString("Please press enter to continue");
    }

    public void addBanner(){
        io.print("=== Add DVD ===");
    }
    public DVD enterDetails() throws DVDLibraryDaoException {
        String title = io.readString("Please enter the title: ");
        try {
            LocalDate date = LocalDate.parse(io.readString("Please enter the release date: "));
            String rating = io.readString("Please enter the MPAA rating: ");
            String dirName = io.readString("Please enter the director's name: ");
            String studio = io.readString("Please enter the studio name: ");
            return new DVD(title, date, rating, dirName, studio, "");
        } catch (DateTimeParseException e){
            throw new DVDLibraryDaoException("Date invalid, please use yyyy-mm-dd format.", e);
        }
    }
    public String askForNote(){
        if(io.readString("Would you like to add a personal note? Yes/No ").equalsIgnoreCase("Yes")){
            return getNote();
        } else {
            return "";
        }
    }
    public String getNote(){
        return io.readString("Please enter your personal note: ");
    }
    public void editBanner(){
        io.print("=== Edit DVD ===");
    }

    public void removeBanner(){
        io.print("=== Remove DVD ===");
    }

    public void listAllBanner(List<DVD> list) throws DVDLibraryDaoException {
        io.print("=== All DVDs ===");
        for(DVD d: list){
            showDVDBanner(d);
            io.print("");
        }
    }

    public void showDVDBanner(DVD d) throws DVDLibraryDaoException {
        try {
            io.print(d.getTitle());
            io.print(d.getReleaseDate().toString());
            io.print(d.getMPAARating());
            io.print(d.getDirectorName());
            io.print(d.getStudio());
            if(!d.getNote().isEmpty()){
                io.print(d.getNote());
            }
        } catch (NullPointerException e){
            throw  new DVDLibraryDaoException("Could not find the DVD.", e);
        }
    }

    public String askForTitle(){
        return io.readString("Please enter the title of the DVD");
    }

    public String askForNewTitle() {
        return io.readString("Please enter the new title of the DVD");
    }

    public void goodbye() {
        io.print("Goodbye!");
    }

    public void errorMsg() {
        io.print("Invalid!");
    }

    public void errorMsg(String e){
        io.print(e);
    }

    public int askEditMenu(){
        io.print("What would you like to edit?");
        io.print("1. Title");
        io.print("2. Release Date");
        io.print("3. MPAA Rating");
        io.print("4. Director");
        io.print("5. Studio");
        io.print("6. Personal Note");
        return io.readInt("Please choose one of the options", 1, 6);
    }

    public LocalDate askForNewDate() {
        return LocalDate.parse(io.readString("Please enter new release date: "));
    }

    public String getNewRating() {
        return io.readString("Please enter new MPAA Rating: ");
    }

    public String getNewDirector() {
        return io.readString("Please enter new director: ");
    }

    public String getNewStudio() {
        return io.readString("Please enter new studio: ");
    }
}
