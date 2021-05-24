package jk.pp.engg.foundations.common.service.core.connect;

import jk.pp.engg.foundations.common.domain.core.connect.ServiceConnectReq;
import jk.pp.engg.foundations.common.domain.core.connect.ServiceConnectResult;

public interface ServiceConnector<REQ extends ServiceConnectReq, RESP extends ServiceConnectResult> {

	RESP command(REQ request) throws Exception;

}
