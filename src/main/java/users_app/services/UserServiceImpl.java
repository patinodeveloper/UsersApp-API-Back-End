package users_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import users_app.models.request.UserRequest;
import users_app.models.entities.User;
import users_app.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Optional<User> update(UserRequest userRequest, Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        User optionalUser = null;
        if (existingUser.isPresent()) {
            User userDB = existingUser.get();
            userDB.setUsername(userRequest.getUsername());
            userDB.setEmail(userRequest.getEmail());

            optionalUser = this.save(userDB);
        }
        return Optional.ofNullable(optionalUser);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
}
