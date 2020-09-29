package it.cnit.iwf_repository.site;

import io.swagger.v3.oas.annotations.media.Schema;
import it.cnit.iwf_repository.datashipper.DataShipper;
import it.cnit.iwf_repository.network.Network;
import it.cnit.iwf_repository.nfvo.NfvOrchestrator;
import it.cnit.iwf_repository.rano.RanOrchestrator;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Site {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  @Column(unique = true)
  private String name;

  @NotNull
  private String location;

  @NotNull
  @Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$",
      message = "must be a valid IPv4 address")
  private String kafkaIpAddress;

  @Schema(hidden = true)
  @OneToMany(mappedBy = "site")
  private List<NfvOrchestrator> nfvOrchestrators;

  @Schema(hidden = true)
  @OneToMany(mappedBy = "site")
  private List<RanOrchestrator> ranOrchestrators;

  @Schema(hidden = true)
  @OneToMany(mappedBy = "site")
  private List<DataShipper> dataShippers;

  @Schema(hidden = true)
  @OneToMany(mappedBy = "site")
  private List<Network> networks;
}
