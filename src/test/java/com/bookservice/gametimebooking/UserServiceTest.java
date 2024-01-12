package com.bookservice.gametimebooking;

import com.bookservice.gametimebooking.dto.UserDto;
import com.bookservice.gametimebooking.mapper.UserMapper;
import com.bookservice.gametimebooking.model.User;
import com.bookservice.gametimebooking.repository.UserRepository;
import com.bookservice.gametimebooking.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void createUserSuccess() {
        UserDto userDto = new UserDto();
        String mockPassword = "mockPassword";
        userDto.setPassword(mockPassword);
        User user = new User();

        when(userMapper.toEntity(Mockito.any(UserDto.class))).thenReturn(user);
        when(passwordEncoder.encode(Mockito.any(String.class))).thenReturn(mockPassword);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(null);

        userService.createUser(userDto);

        verify(userMapper, times(1)).toEntity(userDto);
        verify(passwordEncoder, times(1)).encode(mockPassword);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void loginSuccess() {
        User user = new User();
        UserDto userDto = new UserDto();
        String mockEmail = "mockEmail";
        String mockPassword = "mockPassword";
        userDto.setPassword(mockPassword);
        userDto.setEmail(mockEmail);


        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(Mockito.any(), Mockito.any())).thenReturn(true);

        String result = userService.login(userDto);

        verify(userRepository, times(1)).findByEmail(mockEmail);
        verify(passwordEncoder, times(1)).matches(mockPassword, null);

        assertThat(result).isNotBlank();
    }
}
