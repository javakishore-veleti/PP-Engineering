package jk.pp.engg.foundations.common.dao.core;

import org.springframework.data.repository.NoRepositoryBean;

import jk.pp.engg.foundations.common.core.domain.IDomainNameDesc;

@NoRepositoryBean
public interface AppDomainNameDescCrudDAO<T extends IDomainNameDesc> extends AppCrudDAO<T> {

}
