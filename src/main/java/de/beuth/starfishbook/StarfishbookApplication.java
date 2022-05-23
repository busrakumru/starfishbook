package de.beuth.starfishbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.beuth.starfishbook.model.Notes;
import de.beuth.starfishbook.repository.NotesRepository;


@SpringBootApplication

public class StarfishbookApplication {


	public static void main(String[] args) {
		SpringApplication.run(StarfishbookApplication.class, args);
	}

	@Autowired
	private NotesRepository notesRepository;

	/**@Override
	public void run(String... args) throws Exception{
		this.notesRepository.save(new Notes("Test1", "Hallo", "Blau"));
		this.notesRepository.save(new Notes("Test2", "Welt", "Grün"));
		this.notesRepository.save(new Notes("Test3", "Wie", "Rot"));
		this.notesRepository.save(new Notes("Test4", "Gehts?", "Schwarz"));
        
	}**/
}
