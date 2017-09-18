/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.mock;

import com.clashhorn.domain.model.account.ClanAccount;
import com.clashhorn.domain.model.account.ClanAccountRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.transformation.FilteredList;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

/**
 *
 * @author morgade
 */
@Primary
@Repository
@Profile("!live-mongo-db")
public class ClanAccountMockRepository implements ClanAccountRepository {
    private Map<String, ClanAccount> data = new HashMap<>();
    
    @Override
    public <S extends ClanAccount> S save(S entity) {
        data.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public ClanAccount findOne(String id) {
        return data.get(id);
    }
    
    
    @Override
    public boolean exists(String id) {
        return data.containsKey(id); 
    }

    
    @Override
    public List<ClanAccount> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<ClanAccount> findAll(Sort sort) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends ClanAccount> S insert(S entity) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends ClanAccount> List<S> insert(Iterable<S> entities) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Page<ClanAccount> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends ClanAccount> List<S> save(Iterable<S> entites) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Iterable<ClanAccount> findAll(Iterable<String> ids) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(ClanAccount entity) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(Iterable<? extends ClanAccount> entities) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends ClanAccount> S findOne(Example<S> example) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends ClanAccount> FilteredList<S> findAll(Example<S> arg0) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends ClanAccount> FilteredList<S> findAll(Example<S> arg0, Sort arg1) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends ClanAccount> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends ClanAccount> long count(Example<S> example) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends ClanAccount> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

        
}
