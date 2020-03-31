package com.seifi.jobclube.core.repository;

import com.seifi.jobclube.core.entitys.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

}
