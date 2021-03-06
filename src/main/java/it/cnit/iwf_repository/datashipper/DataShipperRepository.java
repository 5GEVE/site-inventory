package it.cnit.iwf_repository.datashipper;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.cnit.iwf_repository.datashipper.DataShipper.InfrastructureMetricType;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@SuppressWarnings("unused")
@RepositoryRestResource(collectionResourceRel = "dataShippers", path = "dataShippers")
@Tag(name = "Data Shippers")
public interface DataShipperRepository extends
    PagingAndSortingRepository<DataShipper, Long> {

  List<DataShipper> findByMetricType(@Param("metricType") InfrastructureMetricType metricType);

  List<DataShipper> findByDataShipperId(@Param("dataShipperId") String dataShipperId);
}
