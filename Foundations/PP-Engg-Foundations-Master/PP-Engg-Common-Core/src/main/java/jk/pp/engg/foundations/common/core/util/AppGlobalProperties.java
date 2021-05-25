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
	public String appSvcAcctUser = "PPSvcActUser";

	@Value("${pp.platform.serviceaccount.userpassword:password}")
	private String appSvcAcctUserPwd = "password";

	@Value("${pp.platform.microservices.security.enabled:false}")
	public Boolean securityEnabledVal = Boolean.FALSE;

	@Value("${pp.ta.pubsub.crud.global.enabled: false}")
	public Boolean pubSubToipcsGlobalEnabled = Boolean.FALSE;

	@Value("${pp.ta.pubsub.crud.global.producer.enabled: false}")
	public Boolean pubSubToipcsGlobalProducerEnabled = Boolean.FALSE;

	@Value("${pp.ta.pubsub.crud.global.consumer.enabled: false}")
	public Boolean pubSubToipcsGlobalConsumerEnabled = Boolean.FALSE;

	@Value("${pp.ta.pubsub.crud.global.jsonfile: ''}")
	public String pubSubToipcsGlobalJson;

	@Value("${pp.ta.pubsub.crud.global.jsonkey: ''}")
	public String pubSubToipcsGlobalJsonKey;

	public boolean isSecurityEnabled() {
		return this.securityEnabledVal;
	}

}
