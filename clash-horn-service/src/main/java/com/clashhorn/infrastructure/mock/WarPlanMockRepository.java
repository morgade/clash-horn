/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.mock;

import com.clashhorn.domain.model.clan.ClanRef;
import com.clashhorn.domain.model.war.WarPlan;
import com.clashhorn.domain.model.war.WarPlanRepository;
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
public class WarPlanMockRepository implements WarPlanRepository {
    
    @Override
    public <S extends WarPlan> S save(S entity) {
        return entity;
    }

    @Override
    public WarPlan findOne(String id) {
        WarPlan w = new WarPlan(id, id, new ClanRef("#FHGFDD", "TJF"), new ClanRef("#KJHGEKJ", "OS OUTROS"));
        return w;
    }
    
    @Override
    public <S extends WarPlan> S findOne(Example<S> example) {
        WarPlan w = new WarPlan("id", "id", new ClanRef("#FHGFDD", "TJF"), new ClanRef("#KJHGEKJ", "OS OUTROS"));
        return (S) w;
    }
    
    @Override
    public boolean exists(String id) {
        return true; 
    }

    
    @Override
    public List<WarPlan> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<WarPlan> findAll(Sort sort) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends WarPlan> S insert(S entity) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends WarPlan> List<S> insert(Iterable<S> entities) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Page<WarPlan> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends WarPlan> List<S> save(Iterable<S> entites) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Iterable<WarPlan> findAll(Iterable<String> ids) {
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
    public void delete(WarPlan entity) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(Iterable<? extends WarPlan> entities) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends WarPlan> FilteredList<S> findAll(Example<S> arg0) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends WarPlan> FilteredList<S> findAll(Example<S> arg0, Sort arg1) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends WarPlan> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends WarPlan> long count(Example<S> example) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends WarPlan> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

        
}
