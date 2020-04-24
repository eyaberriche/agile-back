package org.isamm.Agile.model.DTOs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.isamm.Agile.model.Role;

import java.util.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Comparable<UserDTO> {
   public UserDTO(Integer id, String username, String name, String lastname) {
      this.id = id;
      this.username = username;
      this.name = name;
      this.lastname = lastname;
   }

   private Integer id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private Long tel;
    private String mail;
    private Set<EvenementDTO> evenements = new HashSet<>();
    private Set<Role> roles = new HashSet<>();
    private Set<CompetenceDTO> competences = new HashSet<>();

    @Override
    public int compareTo(UserDTO o) {
        return name.compareToIgnoreCase(o.getName());
    }
}
