package br.com.mybooks.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "app", name = "books")
public class BookEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String title;

    private Integer edition;

    @Lob
    private byte[] cover;

    @Column(nullable = false)
    private Integer pages;

    @Lob
    @Column(nullable = false)
    private byte[] book;

    @Column(name = "shipping_date", nullable = false)
    private LocalDateTime shippingDate;

    @ManyToOne
    @JoinColumn(name = "id_publisher")
    private PublisherEntity publisher;

    @OneToOne
    @JoinColumn(name = "id_reading_data")
    private ReadingDataEntity readingData;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "app", name = "books_authors",
                joinColumns = @JoinColumn(name = "id_book"),
                inverseJoinColumns = @JoinColumn(name = "id_author"))
    private List<AuthorEntity> authors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public byte[] getBook() {
        return book;
    }

    public void setBook(byte[] book) {
        this.book = book;
    }

    public LocalDateTime getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDateTime shippingDate) {
        this.shippingDate = shippingDate;
    }

    public PublisherEntity getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherEntity publisher) {
        this.publisher = publisher;
    }

    public ReadingDataEntity getReadingData() {
        return readingData;
    }

    public void setReadingData(ReadingDataEntity readingData) {
        this.readingData = readingData;
    }

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((edition == null) ? 0 : edition.hashCode());
        result = prime * result + Arrays.hashCode(cover);
        result = prime * result + ((pages == null) ? 0 : pages.hashCode());
        result = prime * result + Arrays.hashCode(book);
        result = prime * result + ((shippingDate == null) ? 0 : shippingDate.hashCode());
        result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
        result = prime * result + ((readingData == null) ? 0 : readingData.hashCode());
        result = prime * result + ((authors == null) ? 0 : authors.hashCode());
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
        BookEntity other = (BookEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (edition == null) {
            if (other.edition != null)
                return false;
        } else if (!edition.equals(other.edition))
            return false;
        if (!Arrays.equals(cover, other.cover))
            return false;
        if (pages == null) {
            if (other.pages != null)
                return false;
        } else if (!pages.equals(other.pages))
            return false;
        if (!Arrays.equals(book, other.book))
            return false;
        if (shippingDate == null) {
            if (other.shippingDate != null)
                return false;
        } else if (!shippingDate.equals(other.shippingDate))
            return false;
        if (publisher == null) {
            if (other.publisher != null)
                return false;
        } else if (!publisher.equals(other.publisher))
            return false;
        if (readingData == null) {
            if (other.readingData != null)
                return false;
        } else if (!readingData.equals(other.readingData))
            return false;
        if (authors == null) {
            if (other.authors != null)
                return false;
        } else if (!authors.equals(other.authors))
            return false;
        return true;
    }

}
