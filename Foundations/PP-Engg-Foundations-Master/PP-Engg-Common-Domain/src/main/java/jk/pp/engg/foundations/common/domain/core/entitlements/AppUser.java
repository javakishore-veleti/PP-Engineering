package jk.pp.engg.foundations.common.domain.core.entitlements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import jk.pp.engg.foundations.common.domain.core.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = "AppUser")
@Table(name = "app_user")
public class AppUser extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Column(name = "username", length = 25, unique = true)
	private String userName;

	@Column(name = "firstname", length = 50)
	private String firstName;

	@Column(name = "lastname", length = 50)
	private String lastName;

	@Column(name = "email", length = 50, unique = true)
	private String email;

	@Column(name = "gender", length = 7)
	private String gender;

	@Column(name = "phoneNo", length = 25)
	private String phoneNo;

	@Column(name = "password", length = 25)
	private String password;

	@Column(name = "member_user")
	private Boolean memberUser = Boolean.FALSE;

}
