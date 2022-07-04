package de.beuth.starfishbook.service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import de.beuth.starfishbook.model.FileDB;
import de.beuth.starfishbook.model.Notes;
import de.beuth.starfishbook.repository.FileDBRepository;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    public List<FileDB> findByNotesId(Long notesId) {
        return fileDBRepository.findByNotesId(notesId);
    }

    public FileDB store(MultipartFile file, Notes notes) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), notes);
        return fileDBRepository.save(FileDB);
    }

    public FileDB store2(MultipartFile file) throws IOException {

        Notes notes = new Notes();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), notes);
        return fileDBRepository.save(FileDB);
    }


    public FileDB save(FileDB request) {
        return this.fileDBRepository.save(request);
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    public FileDB getFile(String id){
        return fileDBRepository.findById(id).get();
    }

    /*public void deleteFile(String id){
        Optional<FileDB> file = fileDBRepository.findById(id);
        file.ifPresent(fileDBRepository::delete);


        //return fileDBRepository.findById(id).isPresent(fileDBRepository::delete);
    }*/

    public Boolean deleteFile(String id){
        if(!this.fileDBRepository.existsById(id)){

        }
        this.fileDBRepository.deleteById(id);
        return this.fileDBRepository.existsById(id);
    }


}
