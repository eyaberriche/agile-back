package org.isamm.Agile.Security.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.isamm.Agile.model.Competence;
import org.isamm.Agile.model.Entreprise;
import org.isamm.Agile.model.Role;

@Getter
@Setter
@AllArgsConstructor
public class SignupRequest {
    //@NotBlank
    @Size(min = 4, max = 20)
    private String username;
    //@NotBlank
    private String firstname;
    //@NotBlank
    private String lastname;
    //@NotBlank
    @Size(max = 20)

    private String email;
    private String  tel ;
    private Set<Role> roles;
    

   // @Size(min = 6, max = 20)
    private String password;
    private String specialite ;
    private Entreprise entreprise ;
    private Set<Competence> competences ;

  
 
}
