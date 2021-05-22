package jk.pp.engg.foundations.common.core.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AppGlobalObjects {

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

}
