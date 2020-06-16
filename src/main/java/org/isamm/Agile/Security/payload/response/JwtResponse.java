package org.isamm.Agile.Security.payload.response;

import org.isamm.Agile.model.Competence;
import org.isamm.Agile.model.Image;

import java.util.List;
import java.util.Set;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private String lastname ;
	private String firstname ;
	private String tel ;
	private String specialite ;
	private List<String> roles;
	private Image image ;
    private Set<Competence> competences ;
	public JwtResponse(String token, Long id, String username, String email, String lastname, String firstname, String tel, String specialite, List<String> roles , Image image , Set<Competence> competences) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.email = email;
		this.lastname = lastname;
		this.firstname = firstname;
		this.tel = tel;
		this.specialite = specialite;
		this.roles = roles;
		this.image = image;
		this.competences= competences;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastname() {
		return lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getTel() {
		return tel;
	}

	public String getSpecialite() {
		return specialite;
	}

	public List<String> getRoles() {
		return roles;
	}

	public Image getImage() {
		return image;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Set<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(Set<Competence> competences) {
		this.competences = competences;
	}
}
