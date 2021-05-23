package jk.pp.engg.foundations.common.dao.core;

import org.springframework.data.repository.NoRepositoryBean;

import jk.pp.engg.foundations.common.domain.core.BaseDomain;

@NoRepositoryBean
public interface AppDomainNameDescCrudDAO<T extends BaseDomain> extends AppCrudDAO<T> {

}
