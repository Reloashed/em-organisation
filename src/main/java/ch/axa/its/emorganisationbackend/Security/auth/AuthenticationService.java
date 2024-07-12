package ch.axa.its.emorganisationbackend.Security.auth;

import ch.axa.its.emorganisationbackend.Security.Role;
import ch.axa.its.emorganisationbackend.Security.User;
import ch.axa.its.emorganisationbackend.Security.UserRepository;
import ch.axa.its.emorganisationbackend.Security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authManager;

  public AuthenticationResponse register(RegisterRequest registerRequest) {
    User user = new User();
    user.setFirstname(registerRequest.getFirstname());
    user.setLastname(registerRequest.getLastname());
    user.setUsername(registerRequest.getUsername());
    user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
    user.setRole(Role.USER);
    userRepository.save(user);

    var jwtToken = jwtService.generateToken(user);

    AuthenticationResponse authResponse = new AuthenticationResponse();
    authResponse.setToken(jwtToken);

    return authResponse;
  }

  public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
    authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(), authRequest.getPassword()
            )
    );

    var user = userRepository.findByUsername(authRequest.getUsername()).orElseThrow();

    var jwtToken = jwtService.generateToken(user);

    AuthenticationResponse authResponse = new AuthenticationResponse();
    authResponse.setToken(jwtToken);

    return authResponse;
  }
}
