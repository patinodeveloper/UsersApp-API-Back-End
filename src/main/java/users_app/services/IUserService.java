package users_app.services;

import users_app.models.request.UserRequest;
import users_app.models.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    Optional<User> update(UserRequest user, Long id);

    void remove(Long id);
}
