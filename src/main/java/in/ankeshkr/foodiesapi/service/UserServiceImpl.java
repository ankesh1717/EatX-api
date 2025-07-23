package in.ankeshkr.foodiesapi.service;

import in.ankeshkr.foodiesapi.entity.UserEntity;
import in.ankeshkr.foodiesapi.io.UserRequest;
import in.ankeshkr.foodiesapi.io.UserResponse;
import in.ankeshkr.foodiesapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private  final AuttheneticationFacade auttheneticationFacade;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuttheneticationFacade auttheneticationFacade) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.auttheneticationFacade = auttheneticationFacade;
    }


    @Override
    public UserResponse registerUser(UserRequest request) {
        UserEntity newUser = convertToEntity(request);
        newUser = userRepository.save(newUser);
        return convertToResponse(newUser);
    }

    @Override
    public String findByUserId() {
        String loggedInUserEmail = auttheneticationFacade.getAuthentication().getName();
        UserEntity loggedInUser = userRepository.findByEmail(loggedInUserEmail).orElseThrow(() ->new UsernameNotFoundException("User not found"));
        return loggedInUser.getId();
    }

    private UserEntity convertToEntity(UserRequest request){
        return UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .build();
    }

    private  UserResponse convertToResponse(UserEntity registeredUser){
        return UserResponse.builder()
                .id(registeredUser.getId())
                .name(registeredUser.getName())
                .email(registeredUser.getEmail())
                .build();
    }
}
