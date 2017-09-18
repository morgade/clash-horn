/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.model.account;

import com.clashhorn.domain.model.clan.ClanRef;
import static java.lang.String.format;
import java.util.Date;
import org.springframework.data.annotation.Id;

/**
 *
 * @author morgade
 */
public class ClanAccount {
    @Id
    private String id;
    private ClanRef clan;
    private Date creation;

    public ClanAccount(String id, ClanRef clan) {
        this.id = id;
        this.clan = clan;
        this.creation = new Date();
    }

    public String getId() {
        return id;
    }
    
    public Date getCreation() {
        return creation;
    }

    public ClanRef getClan() {
        return clan;
    }
    
    @Override
    public String toString() {
        return format("ClanAccount[=%s]", this.id);
    }
    
}
