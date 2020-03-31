package it.cnit.siteinventory.nfvo;

import it.cnit.siteinventory.site.Site;
import it.cnit.siteinventory.vim.Vim;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Entity
@Data
public class Nfvo {
    @Id
    @GeneratedValue
    private long id;

    private @NotNull String name;
    private @NotNull String nfvoType;
    private String uri;
    private Date createdAt;
    private Date updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credentials")
    @RestResource(exported = false)
    private NfvoCredentials credentials;

    @OneToMany(mappedBy = "nfvo")
    private List<Vim> vims;

    @ManyToOne
    private Site site;
}