package jk.pp.engg.foundations.common.core.domain.entitlements;

import jk.pp.engg.foundations.common.core.domain.IDomain;

public interface IAppUser extends IDomain {

	String getUserName();

	void setUserName(String userName);

	String getFirstName();

	void setFirstName(String firstName);

	String getLastName();

	void setLastName(String lastName);

	String getEmail();

	void setEmail(String email);

	String getPassword();

	void setPassword(String password);
}
