package com.seifi.jobclube.core.repository;

import com.seifi.jobclube.core.entitys.AdvertiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AdvertiseRepository extends JpaRepository<AdvertiseEntity, Long> {

}
