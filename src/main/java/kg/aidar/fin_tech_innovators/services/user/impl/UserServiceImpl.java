package kg.aidar.fin_tech_innovators.services.user.impl;


import kg.aidar.fin_tech_innovators.dto.SignUpUserDto;
import kg.aidar.fin_tech_innovators.entities.UserEntity;
import kg.aidar.fin_tech_innovators.exceptions.ApiException;
import kg.aidar.fin_tech_innovators.models.AppUserDetails;
import kg.aidar.fin_tech_innovators.repositories.UserRepository;
import kg.aidar.fin_tech_innovators.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .map(u -> new AppUserDetails(u.getId(), u.getUsername(), u.getName(), u.getPassword()))
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    @Override
    public void saveUser(SignUpUserDto userDto) {
        log.info("Sign up user: {}", userDto.getUsername());
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setEnabled(true);
        try {
            userRepository.save(userEntity);
            log.info("User: {} is signed up", userDto.getUsername());
        } catch (DataIntegrityViolationException e) {
            log.error("Exception when user saving", e);
            throw new ApiException(e);
        } catch (Exception e) {
            log.error("Unknown exception when user saving", e);
            throw new ApiException(e);
        }
    }


    @Override
    public boolean isUserExists(String username) {
        return userRepository.isUserAlreadyExists(username) > 0;
    }
}
