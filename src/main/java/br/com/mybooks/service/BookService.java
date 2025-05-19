package br.com.mybooks.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.mybooks.model.entity.BookEntity;
import br.com.mybooks.repository.BookRepository;

@Service
public class BookService {

    @Value("${livros.upload.dir}")
    private String uploadDir;

    @Autowired
    private BookRepository repository;

    public BookEntity addBook(BookEntity book, MultipartFile file, String username) throws IOException {
        Path pathFile = creatPathFile(file, username);
        Files.copy(file.getInputStream(), pathFile, StandardCopyOption.REPLACE_EXISTING);
        book.setPathFile(pathFile.toString());
        return repository.save(book);
    }

    private Path creatPathFile(MultipartFile file, String username) throws IOException {
        Path root = Paths.get("").toAbsolutePath();
        Path pathUpload = root.resolve(uploadDir + File.separator + username);
        Files.createDirectories(pathUpload);
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        return pathUpload.resolve(fileName);
    }

}
