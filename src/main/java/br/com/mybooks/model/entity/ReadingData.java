package br.com.mybooks.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "app", name = "reading_datas")
public class ReadingData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean open;

    private Boolean read;

    @Column(name = "opening_date")
    private LocalDateTime openingDate;

    @Column(name = "end_date_reading")
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((open == null) ? 0 : open.hashCode());
        result = prime * result + ((read == null) ? 0 : read.hashCode());
        result = prime * result + ((openingDate == null) ? 0 : openingDate.hashCode());
        result = prime * result + ((endDateReading == null) ? 0 : endDateReading.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReadingData other = (ReadingData) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (open == null) {
            if (other.open != null)
                return false;
        } else if (!open.equals(other.open))
            return false;
        if (read == null) {
            if (other.read != null)
                return false;
        } else if (!read.equals(other.read))
            return false;
        if (openingDate == null) {
            if (other.openingDate != null)
                return false;
        } else if (!openingDate.equals(other.openingDate))
            return false;
        if (endDateReading == null) {
            if (other.endDateReading != null)
                return false;
        } else if (!endDateReading.equals(other.endDateReading))
            return false;
        return true;
    }

}
