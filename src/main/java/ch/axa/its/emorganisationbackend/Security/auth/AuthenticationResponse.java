package ch.axa.its.emorganisationbackend.Security.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
  private String token;
}
