package com.seifi.jobclube.core.config;

import com.seifi.jobclube.core.entitys.AdvertiseEntity;
import com.seifi.jobclube.core.entitys.PostEntity;
import com.seifi.jobclube.core.entitys.ReviewEntity;
import com.seifi.jobclube.core.entitys.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
@Transactional
//@ConditionalOnProperty(value ="app.db" , havingValue = "hsq", matchIfMissing = false)
public class InMemoryDatabaseCreator {

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("classpath:database/tables.sql")
    private Resource resource;

    //@PersistenceUnit
    //private SessionFactory sessionFactory;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    @Transactional
    public void start() throws IOException {

        InputStream inputStream = resource.getInputStream();
        InputStreamReader isReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(isReader);
        StringBuffer sb = new StringBuffer();
        String str;
        while((str = reader.readLine())!= null){
            sb.append(str);
        }
        isReader.close();

        String totalSql = sb.toString();
        //System.out.println(totalSql);

        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        generateAdvetise(em);
        generateReview(em);
        generateUser(em);
        generatePost(em);

        transaction.commit();

        em.close();
    }

    private void generateAdvetise(EntityManager em) {
        for(int i=1; i<11 ; i++){
            AdvertiseEntity model = new AdvertiseEntity();
            model.setDescription("desc:" + i);
            model.setPhoto("photo:" + i);
            model.setStatus(1);
            model.setVersion(1);
            em.persist(model);
        }
    }

    private void generateReview(EntityManager em) {
        for(int i=1; i<11 ; i++){
            ReviewEntity model = new ReviewEntity();
            model.setDescription("desc:" + i);
            model.setUserId(1L);
            model.setStatus(1);
            model.setPostId(1L);
            model.setVersion(1);
            em.persist(model);
        }
    }

    private void generateUser(EntityManager em) {
        for(int i=1; i< 11 ; i++){
            UserEntity model = new UserEntity();
            model.setAddress("addr");
            model.setFirstName("fn:" + i);
            model.setLastName("ln:" + i);
            model.setUserName("un:" + i);
            model.setPhoto("photo:" + i);
            em.persist(model);
        }
    }

    private void generatePost(EntityManager em) {
        for(int i=1; i<11 ; i++){
            PostEntity model = new PostEntity();
            model.setDescription("desc:" + i);
            model.setPhoto("photo:" + i);
            model.setTitle("title:" + 1);

            em.persist(model);
        }
    }
}
