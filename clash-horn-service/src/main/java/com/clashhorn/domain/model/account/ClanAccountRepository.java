/*
 * Clash Horn - MIT License
 */
package com.clashhorn.domain.model.account;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author morgade
 */
public interface ClanAccountRepository  extends MongoRepository<ClanAccount, String> {

}
