package ch.axa.its.emorganisationbackend.Security.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
  private String username;
  private String password;
}
