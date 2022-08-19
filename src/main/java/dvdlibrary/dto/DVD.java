package dvdlibrary.dto;

import java.time.LocalDate;

public class DVD {
    private String title;
    private LocalDate releaseDate;
    private String MPAARating;
    private String directorName;
    private String studio;
    private String note;

    public DVD(String title, LocalDate date, String rating, String director, String studio, String note){
        this.title = title;
        releaseDate = date;
        MPAARating = rating;
        directorName = director;
        this.studio = studio;
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getMPAARating() {
        return MPAARating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public String getStudio() {
        return studio;
    }

    public String getNote() {
        return note;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setMPAARating(String MPAARating) {
        this.MPAARating = MPAARating;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
