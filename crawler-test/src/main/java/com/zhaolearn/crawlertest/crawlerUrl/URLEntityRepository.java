package com.zhaolearn.crawlertest.crawlerUrl;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface URLEntityRepository extends JpaRepository<URLEntity, Long>, JpaSpecificationExecutor<URLEntity> {
   
}