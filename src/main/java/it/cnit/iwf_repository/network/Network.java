package it.cnit.iwf_repository.network;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import it.cnit.iwf_repository.site.Site;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"vim_network_name", "site_id"})
})
public class Network {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  @Column(name = "vim_network_name")
  private String vim_network_name;

  @NotNull
  private boolean floating_ip;

  @NotNull
  private boolean mgmt_net;

  @NotNull
  private boolean external_net;

  @NotNull
  @Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])(/(\\d|[1-2]\\d|3[0-2]))$",
      message = "must be a valid IPv4 address range (ex. 192.168.1.0/24)")
  @Schema(description = "The address space for the network", example = "192.168.1.0/24")
  private String cidr;

  @Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])(/(\\d|[1-2]\\d|3[0-2]))$",
      message = "must be a valid IPv4 address range (ex. 172.17.40.0/24)")
  @Schema(description = "The address space to map this network to", example = "172.17.40.0/24")
  private String ip_mapping;

  @Hidden
  @ManyToOne
  private Site site;
}
