package dvdlibrary.controller;

import dvdlibrary.dao.DVDLibraryDao;
import dvdlibrary.dao.DVDLibraryDaoException;
import dvdlibrary.dto.DVD;
import dvdlibrary.ui.DVDLibraryView;

import java.time.LocalDate;

public class Controller {
    DVDLibraryView view;
    DVDLibraryDao dao;
    public Controller(DVDLibraryView view, DVDLibraryDao dao){
        this.view = view;
        this.dao = dao;
    }

    public void run(){
        boolean isRunning = true;
        while(isRunning){
            int selection = showMenu();
            switch (selection) {
                case 1 -> addDVD();
                case 2 -> removeDVD();
                case 3 -> editDVD();
                case 4 -> listDVD();
                case 5 -> searchDVD();
                case 6 -> {
                    exit();
                    isRunning = false;
                }
                default -> error();
            }
        }
    }

    private void error() {
        view.errorMsg();
    }

    private void exit() {
        view.goodbye();
    }

    private void searchDVD()  {
        String title = view.askForTitle();
        try {
            view.showDVDBanner(dao.getDVD(title));
        } catch (DVDLibraryDaoException e) {
            view.errorMsg(e.getMessage());
        }
        view.pressEnterPrompt();
    }

    private void listDVD() {
        try {
            view.listAllBanner(dao.listAllDVDs());
        } catch (DVDLibraryDaoException e) {
            view.errorMsg(e.getMessage());
        }
        view.pressEnterPrompt();
    }

    private int showMenu() {
        return view.showMenuAndGetSelection();
    }

    private void addDVD() {
        view.addBanner();
        DVD temp;
        try {
            temp = view.enterDetails();
            temp.setNote(view.askForNote());
            try {
                dao.addDVD(temp);
            } catch (DVDLibraryDaoException e) {
                view.errorMsg(e.getMessage());
            }
        } catch (DVDLibraryDaoException e) {
            view.errorMsg(e.getMessage());
        }
        view.pressEnterPrompt();
    }

    private void removeDVD(){
        view.removeBanner();
        String title = view.askForTitle();
        try {
            dao.removeDVD(dao.getDVD(title));
        } catch (DVDLibraryDaoException e) {
            view.errorMsg(e.getMessage());
        }
        view.pressEnterPrompt();
    }

    private void editDVD() {
        view.editBanner();
        String title = view.askForTitle();
        try{
            DVD temp = dao.getDVD(title);
            if(temp == null){
                throw new DVDLibraryDaoException("Couldn't find the DVD");
            } else {
                switch (view.askEditMenu()) {
                    case 1 -> dao.editDVD(title, view.askForNewTitle());
                    case 2 -> {
                        LocalDate ld = view.askForNewDate();
                        temp.setReleaseDate(ld);
                        dao.editDVD(title, temp);
                    }
                    case 3 -> {
                        String rating = view.getNewRating();
                        temp.setMPAARating(rating);
                        dao.editDVD(title, temp);
                    }
                    case 4 -> {
                        String dir = view.getNewDirector();
                        temp.setDirectorName(dir);
                        dao.editDVD(title, temp);
                    }
                    case 5 -> {
                        String studio = view.getNewStudio();
                        temp.setStudio(studio);
                        dao.editDVD(title, temp);
                    }
                    case 6 -> {
                        String note = view.getNote();
                        temp.setNote(note);
                        dao.editDVD(title, temp);
                    }
                    default -> view.errorMsg();
                }
            }
        } catch (DVDLibraryDaoException e){
            view.errorMsg(e.getMessage());
        }
        view.pressEnterPrompt();
    }
}
