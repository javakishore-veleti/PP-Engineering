package jk.pp.engg.foundations.common.service.core.connect;

import jk.pp.engg.foundations.common.domain.core.connect.ServiceConnectReq;
import jk.pp.engg.foundations.common.domain.core.connect.ServiceConnectResult;

public interface ServiceConnectReceiver<REQ extends ServiceConnectReq, RES extends ServiceConnectResult> {

	RES command(REQ request) throws Exception;

}