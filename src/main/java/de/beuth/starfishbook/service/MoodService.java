package de.beuth.starfishbook.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.beuth.starfishbook.model.Mood;
import de.beuth.starfishbook.repository.MoodRepository;

@Service
public class MoodService {

    private final MoodRepository moodRepository;

    @Autowired
    public MoodService(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    public List<Mood> getMood() {
        return this.moodRepository.findAll();
    }

    public Mood findById(Long id) {
        return moodRepository.findMoodById(id);
    }


    public Mood addMood ( Mood request){  
        Mood mood = new Mood();
          mood.setName(request.getName());
          mood.setCreatedAt(request.getCreatedAt());
          mood.setImg(request.getImg());
          mood.setDay(request.getDay());
          mood.setDaily(request.getDaily());
          mood.setColor(request.getColor());
          return this.moodRepository.save(mood);
      }

      public Boolean delete(Long id) {
        this.moodRepository.deleteById(id);
        return this.moodRepository.existsById(id);
    }
}


