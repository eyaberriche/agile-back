package org.isamm.Agile.web;
import org.isamm.Agile.Exception.ResourceNotFoundException;
import org.isamm.Agile.Repository.CompetenceDao;
import org.isamm.Agile.Repository.RoleDao;
import org.isamm.Agile.Repository.UserDao;
import org.isamm.Agile.Security.payload.request.SignupRequest;
import org.isamm.Agile.Security.payload.response.MessageResponse;
import org.isamm.Agile.model.Competence;
import org.isamm.Agile.model.Role;
import org.isamm.Agile.model.RoleName;
import org.isamm.Agile.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserDao userdao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    CompetenceDao compdao;
    @GetMapping("/all/SM")
    public List<User> getUserListByRoleSM() {
        RoleName smrole= RoleName.ROLE_SM;
        return userdao.findByrole(smrole);
    }
    @GetMapping("/all/PO")
    public List<User> getUserListByRolePO() {
        RoleName porole= RoleName.ROLE_PO;
        return userdao.findByrole(porole);
    }
    @GetMapping("/all/user")
    public List<User> getUserList() {
        RoleName userrole= RoleName.ROLE_MEMBER;
        return userdao.findByrole(userrole);
    }
    @GetMapping("/all/client")
    public List<User> getClientList() {
        RoleName userrole= RoleName.ROLE_CLIENT;
        return userdao.findByrole(userrole);
    }
    @GetMapping("/all")
    public List<User> getAllUserList() {
        return userdao.findAll();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(value = "id") Long userId,
                                        @Valid @RequestBody SignupRequest userDetails)
            throws ResourceNotFoundException {
        User user = userdao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " +
                        userId));

        if ((userdao.existsByUsername(userDetails.getUsername())) &&  (!(user.getUsername().equals(userDetails.getUsername())))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: le nom d'utilisateur est déjà existe!"));
        }
        if ((userdao.existsByEmail(userDetails.getEmail())) &&  (!(user.getEmail().equals(userDetails.getEmail())))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: L'email d'utilisateur est déjà existe!"));
        }


        user.setEmail(userDetails.getEmail());
        user.setUsername(userDetails.getUsername());
        user.setLastname(userDetails.getLastname());
        user.setFirstname(userDetails.getFirstname());
        user.setTel(userDetails.getTel());
        user.setRoles(userDetails.getRoles());
        user.setCompetences(userDetails.getCompetences());
        user.setEntreprise(userDetails.getEntreprise());
        user.setSpecialite(userDetails.getSpecialite());
        if (userDetails.getPassword() == "")
        {
            user.setPassword(encoder.encode(user.getPassword()));
        }else{
        user.setPassword(encoder.encode(userDetails.getPassword()));}

     /*   Set<Role> roles = new HashSet<>();

        if (userDetails.getRoles() == null) {
            Role userRole = roleDao.findByName(RoleName.ROLE_CLIENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            roles.addAll(userDetails.getRoles());
       }
        user.setRoles(roles);*/

        userdao.save(user);
        return ResponseEntity.ok(new MessageResponse("mise à jour effectué avec succés!"));
 }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userdao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " +
                        userId));
        userdao.deleteById(userId);
        return ResponseEntity.ok(new MessageResponse("Utilisateur modifé avec succés !"));}
    @GetMapping("/byId/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userdao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/byEntreprise/{id}")
    public List<User> getUserByEntreprise(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {

        return userdao.findByEntreprise(id);
    }
    @GetMapping("/allCompetences")
    public List<Competence> getAllcompetence() {
        return compdao.findAll();
    }
    @PostMapping("/createCompetence")
    public ResponseEntity<?> createNewCompetence(@RequestBody Competence competencerequest) {

        if (compdao.existsByName(competencerequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur : le nom de compétence est déjà existe !"));
        }

        Competence competence = compdao.save(competencerequest) ;
        return ResponseEntity.ok(new MessageResponse("ajout de compétence réussi !"));
    }
    @DeleteMapping("/deleteCompetence/{id}")
    public ResponseEntity<?> deleteCompetence(@PathVariable(value = "id") Long competenceId)
            throws ResourceNotFoundException {
        Competence competence = compdao.findById(competenceId)
                .orElseThrow(() -> new ResourceNotFoundException("competence not found for this id :: " +
                        competenceId));
        compdao.deleteById(competenceId);
        return ResponseEntity.ok(new MessageResponse("compétence supprimeé !"));}

    @GetMapping("/Roleliste")
    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }
}
