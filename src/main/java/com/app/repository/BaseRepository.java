package com.app.repository;

import com.app.model.entity.BaseEntityFile;
import com.app.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;


/**
 * Created by Damian on 2016-11-19.
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntityFile> extends JpaRepository<T, Long> {

    T findByToken(String token);
}
