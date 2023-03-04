//package com.twitter.service;
//
//import com.twitter.persistence.InitializerService;
//import com.twitter.persistence.JPAUtil;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//
//import javax.persistence.EntityManager;
//
//public abstract class TwitterServiceTest {
//        InitializerService dataHelper;
//        protected EntityManager em;
//
//        public TwitterServiceTest() {
//            super();
//        }
//
//        protected void beforeDatabasePreparation(){}
//
//        protected void afterDatabasePreparation(){}
//
//        @BeforeEach
//        public final void setUp() {
//            beforeDatabasePreparation();
//            dataHelper = new InitializerService();
//            dataHelper.prepareData();
//            em = JPAUtil.getCurrentEntityManager();
//            afterDatabasePreparation();
//        }
//
//        protected void afterTearDown(){}
//
//        @AfterEach
//        public final void tearDown() {
//            em.close();
//            afterTearDown();
//        }
//}
//
