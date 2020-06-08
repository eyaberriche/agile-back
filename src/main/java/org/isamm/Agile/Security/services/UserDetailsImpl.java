package org.isamm.Agile.Security.services;

	import java.util.Collection;
	import java.util.List;
	import java.util.Objects;
	import java.util.stream.Collectors;
	import com.fasterxml.jackson.annotation.JsonIgnore;
    import org.isamm.Agile.model.User;
    import org.springframework.security.core.GrantedAuthority;
	import org.springframework.security.core.authority.SimpleGrantedAuthority;
	import org.springframework.security.core.userdetails.UserDetails;

	public class UserDetailsImpl implements UserDetails {
		private static final long serialVersionUID = 1L;

		   private Long id;
		   private String username;
		   private String email;
		   private String lastname ;
		   private String firstname ;
		   private String tel ;
		   private String specialite ;


		@JsonIgnore
		private String password;

		private Collection<? extends GrantedAuthority> authorities;

		public UserDetailsImpl(Long id, String username, String email, String lastname, String firstname, String tel, String specialite, String password, Collection<? extends GrantedAuthority> authorities) {
			this.id = id;
			this.username = username;
			this.email = email;
			this.lastname = lastname;
			this.firstname = firstname;
			this.tel = tel;
			this.specialite = specialite;
			this.password = password;
			this.authorities = authorities;
		}

		public static UserDetailsImpl build(User user) {
			List<GrantedAuthority> authorities = user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getName().name()))
					.collect(Collectors.toList());


			return new UserDetailsImpl(
					user.getId(),
					user.getUsername(),
					user.getEmail(),
					user.getLastname(),
					user.getFirstname(),
					user.getTel(),
					user.getSpecialite(),
					user.getPassword(),
					authorities


					);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return authorities;
		}
		public Long getId() {
			return id;
		}

		public String getEmail() {
			return email;
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

		@Override
		public String getPassword() {
			return password;
		}

		@Override
		public String getUsername() {
			return username;
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			UserDetailsImpl user = (UserDetailsImpl) o;
			return Objects.equals(id, user.id);
		}
		}


		

