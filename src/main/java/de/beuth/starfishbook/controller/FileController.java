package de.beuth.starfishbook.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import de.beuth.starfishbook.message.ResponseFile;
import de.beuth.starfishbook.message.ResponseMessage;
import de.beuth.starfishbook.model.FileDB;
import de.beuth.starfishbook.model.Notes;
import de.beuth.starfishbook.repository.FileDBRepository;
import de.beuth.starfishbook.repository.NotesRepository;
import de.beuth.starfishbook.service.FileStorageService;
import de.beuth.starfishbook.service.NotesService;

@CrossOrigin(origins = "https://localhost:8100")
@RestController
@RequestMapping("auth/users/")
public class FileController {

    private FileDBRepository filesRepository;
    private NotesRepository notesRepository;

  @Autowired
  public FileController(FileDBRepository filesRepository, NotesRepository notesRepository) {
    this.filesRepository = filesRepository;
    this.notesRepository = notesRepository;
  }

    @Autowired
    private FileStorageService storageService;

    @GetMapping("notes/{notesId}/files")
    public ResponseEntity<List<FileDB>> getFilesByNotesId(@PathVariable(value = "notesId") Long notesId) {
        List<FileDB> files = storageService.findByNotesId(notesId);
        return new ResponseEntity<>(files, HttpStatus.OK);
    }
  
    /*@PostMapping("notes/{notesId}/files")
    public ResponseEntity<ResponseMessage> createFileByNotesId(@PathVariable(value = "notesId") Long notesId,
            @RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            Notes notes = this.notesService.findNotesById(notesId);
            // request.setNotes(notes);
            // this.storageService.save(request);

            storageService.store(file, notes);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }*/

    @PostMapping("files")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            
            storageService.store(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    /*@PostMapping("files")
    public ResponseEntity<FileDB> uploadFile(@RequestBody @Valid FileDB files) {
        Optional<Notes> optionalNotes = notesRepository.findById(files.getNotes().getId());
        if (!optionalNotes.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        files.setNotes(optionalNotes.get());

        FileDB savedfile = filesRepository.save(files);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedfile.getId()).toUri();

        return ResponseEntity.created(location).body(savedfile);
    }
    @PostMapping("files")
    public ResponseEntity<FileDB> create(@RequestBody @Valid FileDB files) {
        Optional<Notes> optionalNotes = notesRepository.findById(files.getNotes().getId());
        if (!optionalNotes.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        files.setNotes(optionalNotes.get());

        FileDB savedFiles = filesRepository.save(files);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(savedFiles.getId()).toUri();

        return ResponseEntity.created(location).body(savedFiles);
    }*/
    /*
     * public FileDB createFileByNotesId(@PathVariable(value = "notesId") Long
     * notesId, @RequestBody FileDB request, @RequestParam("file") MultipartFile
     * file) {
     * 
     * Notes notes = this.notesService.findNotesById(notesId);
     * request.setNotes(notes);
     * this.uploadFile(file);
     * return this.storageService.save(request);
     * 
     * }
     */

    @GetMapping("files")
    public ResponseEntity<Page<FileDB>> getAll(Pageable pageable) {
        return ResponseEntity.ok(filesRepository.findAll(pageable));
    }


    /*@GetMapping("files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("auth/users/files/")
                    .path(dbFile.getId())
                    .toUriString();
            return new ResponseFile(
                    dbFile.getId(),
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length,
                    dbFile.getNotes());
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }*/

    /*@PostMapping("files")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store2(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }*/

    @GetMapping("files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = storageService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @DeleteMapping("files/{id}")
    public Boolean deleteFile(@PathVariable String id) {
        return this.storageService.deleteFile(id);
    }

}