package org.isamm.Agile.Security.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.isamm.Agile.model.Competence;
import org.isamm.Agile.model.Entreprise;

@Getter
@Setter
@AllArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(min = 4, max = 20)
    private String username;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    @Size(max = 20)
    @Email
    private String email;
    private long tel ;
    private Set<String> roles;
    
   @NotBlank
    @Size(min = 4, max = 40)
    private String password;
    @NotBlank
    @Size(min = 4, max = 40)
    private String confirmpassword;
    private String specialite ;
    private Set<Competence> competences ;

  
 
}
