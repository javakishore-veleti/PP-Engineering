package jk.pp.engg.foundations.common.dao.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import jk.pp.engg.foundations.common.core.domain.IDomain;

@NoRepositoryBean
public interface AppPaginationRepo<T extends IDomain>
		extends JpaRepository<T, Long>, PagingAndSortingRepository<T, Long> {

	default Page<T> getPaginatedData(Pageable pageReq) throws Exception {
		return this.findAll(pageReq);
	}
}
