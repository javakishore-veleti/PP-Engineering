package jk.pp.engg.foundations.common.service.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import jk.pp.engg.foundations.common.core.domain.IDomain;
import jk.pp.engg.foundations.common.core.dto.DomainCrudDTO;
import jk.pp.engg.foundations.common.core.util.AppDBConstants;
import jk.pp.engg.foundations.common.dao.core.AppPaginationRepo;

public abstract class AppPaginationServiceImpl<T extends IDomain, DTO extends DomainCrudDTO<T>>
		implements AppPaginationService<T, DTO> {

	private AppPaginationRepo<T> daoRepo;

	public void setPaginiationRepo(AppPaginationRepo<T> daoRepo) {
		this.daoRepo = daoRepo;
	}

	@Override
	public List<T> getPaginatedData(int page, int size, String sortDir, String sort) throws Exception {

		if (sortDir == null || sortDir.trim().length() == 0) {
			sortDir = Sort.DEFAULT_DIRECTION.name();
		}

		if (sort == null || sort.trim().length() == 0) {
			sort = AppDBConstants.PK_COLUMN_NAME;
		}

		PageRequest pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
		Page<T> posts = daoRepo.getPaginatedData(pageReq);

		return posts.getContent();
	}

}
