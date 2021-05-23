package jk.pp.engg.foundations.common.core.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppGlobalProperties {

	public static final String[] commonProfiles = new String[] { "microservices-db", "microservices-svcreg-client" };

	@Value("${pp.platform.serviceaccount.user:PPSvcActUser}")
	private String appSvcAcctUser = "PPSvcActUser";

	@Value("${pp.platform.serviceaccount.userpassword:password}")
	private String appSvcAcctUserPwd = "password";

	@Value("${pp.platform.microservices.security.enabled:false}")
	private Boolean securityEnabledVal = Boolean.FALSE;

	public boolean isSecurityEnabled() {
		return this.securityEnabledVal;
	}

}
