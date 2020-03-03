package it.cnit.nfvoservice;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "vims", path = "vims")
public interface VimRepository extends PagingAndSortingRepository<Vim, Long> {
    List<Vim> findByNfvo(@Param("nfvo") Nfvo nfvo);
}
