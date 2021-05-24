package jk.pp.engg.foundations.common.core.pubsub;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;

import jk.pp.engg.foundations.common.core.domain.IDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PubSubMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	static {
		PubSubContants.OBJECT_MAPPER.setSerializationInclusion(Include.NON_NULL);
	}

	private String topic;
	private Long pk;
	private IDomain domain;
	private Map<String, Object> addlData;

	public String generateMessageAsJson() {
		try {
			return PubSubContants.OBJECT_MAPPER.writeValueAsString(this);
		} catch (JsonProcessingException e) {

			throw new RuntimeException(e);
		}
	}

}
