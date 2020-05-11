package it.cnit.siteinventory.vim;

import it.cnit.siteinventory.nfvo.NfvOrchestrator;
import it.cnit.siteinventory.zone.AvailabilityZone;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class VimAccount {

  @Id
  @GeneratedValue
  private long id;

  // This is the id used by the NVFO for this VIM account
  @NotNull
  private UUID vimAccountId;

  @NotNull
  private String name;

  @NotNull
  private String type;

  private String uri;

  private String tenant;

  @OneToOne(mappedBy = "vimAccount")
  private AvailabilityZone availabilityZone;

  @ManyToMany
  @JoinTable(name = "vim_nfvo",
      joinColumns = @JoinColumn(name = "vim_id"),
      inverseJoinColumns = @JoinColumn(name = "nfvo_id"))
  private List<NfvOrchestrator> nfvos;

}