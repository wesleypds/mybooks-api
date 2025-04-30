package br.com.mybooks.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ReadingData implements Serializable {

    private Long id;

    private Boolean open;

    private Boolean read;

    private LocalDateTime openingDate;

    private LocalDateTime endDateReading;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDateTime openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDateTime getEndDateReading() {
        return endDateReading;
    }

    public void setEndDateReading(LocalDateTime endDateReading) {
        this.endDateReading = endDateReading;
    }

}
