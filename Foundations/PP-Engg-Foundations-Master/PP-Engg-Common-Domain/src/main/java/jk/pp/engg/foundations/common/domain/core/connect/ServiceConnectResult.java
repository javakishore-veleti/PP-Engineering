package jk.pp.engg.foundations.common.domain.core.connect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jk.pp.engg.foundations.common.domain.core.BaseDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceConnectResult {

	private Boolean success = Boolean.FALSE;
	private String reasonCode;
	private String reasonDescription;

	private Map<String, String> reasonCodeAndDescs;

	private Long pk;
	private BaseDomain domain;
	private List<BaseDomain> domains;

	private String jwtToken;

	private Map<String, Object> ctxData;

	public void addCtxData(String key, Object value) {
		if (this.ctxData == null) {
			this.ctxData = new HashMap<>();
		}
		this.ctxData.put(key, value);
	}

	public void addReasonCodeAndDesc(String code, String description) {
		if (this.reasonCodeAndDescs == null) {
			this.reasonCodeAndDescs = new HashMap<>();
		}
		this.reasonCodeAndDescs.put(code, description);
	}
}
