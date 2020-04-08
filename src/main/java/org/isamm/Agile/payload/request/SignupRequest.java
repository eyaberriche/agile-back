package org.isamm.Agile.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    private String typeuser;
    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @NotBlank
    @Size(max = 50)
    @Email
    private String mail;
    private long tel ;
    private Set<String> roles;
    
    @NotBlank
    @Size(min = 4, max = 40)
    private String password;
    private String specialite ; 
  
 
}
