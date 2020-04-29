package org.isamm.Agile.model.DTOs;



import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO implements Comparable<ProjectDTO> {
    private Long id;
    private String nomP;
    private String description;
    private Date dateCreation;
    private String typeP;
    private Set<UserDTO> users =new HashSet<>() ;
    private DepartementDTO departement;
    private EntrepriseDTO entreprise;
    private ProductBacklogDTO backlog;
   // private Set<CompetenceDTO> competences = new HashSet<>();


    @Override
    public int compareTo(ProjectDTO o) {
        return nomP.compareToIgnoreCase(o.getNomP());
    }
}
