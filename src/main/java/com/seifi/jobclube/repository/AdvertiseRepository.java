package com.seifi.jobclube.repository;

import com.seifi.jobclube.entity.AdvertiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AdvertiseRepository extends JpaRepository<AdvertiseEntity, Long> {

}
