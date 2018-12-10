package com.stavroula.postexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stavroula.postexample.entity.Photo;

@Repository("photoRepository")
public interface PhotoRepository extends JpaRepository<Photo, Long>{

}
