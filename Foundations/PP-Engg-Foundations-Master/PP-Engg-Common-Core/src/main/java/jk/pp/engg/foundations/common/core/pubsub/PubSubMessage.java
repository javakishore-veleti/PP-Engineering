package jk.pp.engg.foundations.common.core.pubsub;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;

import jk.pp.engg.foundations.common.core.domain.IDomain;
import jk.pp.engg.foundations.common.core.util.AppGlobalObjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PubSubMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	static {
		AppGlobalObjects.OBJECT_MAPPER.setSerializationInclusion(Include.NON_NULL);
	}

	private Long pk;
	private IDomain domain;
	private Map<String, Object> addlData;

	public String generateMessageAsJson() {
		try {
			return AppGlobalObjects.OBJECT_MAPPER.writeValueAsString(this);
		} catch (JsonProcessingException e) {

			throw new RuntimeException(e);
		}
	}

}
