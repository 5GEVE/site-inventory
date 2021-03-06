package it.cnit.iwf_repository.rano;

import io.swagger.v3.oas.annotations.Hidden;
import it.cnit.iwf_repository.cred.Credentials;
import it.cnit.iwf_repository.site.Site;
import it.cnit.iwf_repository.area.CoverageArea;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Data
public class RanOrchestrator {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  @Column(unique = true)
  private String name;

  @NotNull
  private String type;

  private String uri;

  @OneToOne(cascade = CascadeType.ALL)
  @RestResource(exported = false)
  private Credentials credentials;

  @Hidden
  @ManyToOne
  private Site site;

  @Hidden
  @OneToMany(mappedBy = "ranOrchestrator")
  private List<CoverageArea> coverageAreas;
}
