package org.isamm.Agile.Security.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.isamm.Agile.model.Competence;

@Getter
@Setter
@AllArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
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
    private Set<Competence> competences ;
  
 
}