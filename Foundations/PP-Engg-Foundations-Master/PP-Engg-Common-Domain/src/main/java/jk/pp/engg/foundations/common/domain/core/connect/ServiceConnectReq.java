package jk.pp.engg.foundations.common.domain.core.connect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jk.pp.engg.foundations.common.domain.core.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceConnectReq {

	private Long pk;
	private String domainType;
	private BaseDomain domain;
	private List<BaseDomain> domains;

	private String commandName;
	private String jwtToken;

	private Map<String, Object> ctxData;

	public void addCtxData(String key, Object value) {
		if (this.ctxData == null) {
			this.ctxData = new HashMap<>();
		}
		this.ctxData.put(key, value);
	}
}
