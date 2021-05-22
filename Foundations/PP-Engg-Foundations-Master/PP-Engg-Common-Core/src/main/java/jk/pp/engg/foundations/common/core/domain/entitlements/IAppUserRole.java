package jk.pp.engg.foundations.common.core.domain.entitlements;

import jk.pp.engg.foundations.common.core.domain.IDomain;

public interface IAppUserRole extends IDomain {

	Long getUserPK();

	void setUserPK(Long userPK);

	Long getRolePK();

	void setRolePK(Long rolePK);

}
