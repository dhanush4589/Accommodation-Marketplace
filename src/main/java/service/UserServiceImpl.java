package service;

import User.UserEntity;
import Request.UserRegisterRequest;
import repository.UserRepository;
import exception.UserAlreadyExistsByException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(UserRegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsByException("User account already exists for the given email");
        }

        UserEntity user = toUser(request);
        userRepository.save(user);
    }

    private UserEntity toUser(UserRegisterRequest request) {
        return UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();
    }
}
