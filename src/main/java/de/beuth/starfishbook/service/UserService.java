package de.beuth.starfishbook.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.lang.reflect.Field;
import de.beuth.starfishbook.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import de.beuth.starfishbook.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean delete(Long id) {
        this.userRepository.deleteById(id);
        return this.userRepository.existsById(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User updateWithMap(Long id, Map<Object, Object> objectMap) {

        Optional<User> user = userRepository.findById(id);

        objectMap.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(User.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, user.get(), value);
        });

        return userRepository.save(user.get());// todoService.updateTodo(todos);
    }
}
