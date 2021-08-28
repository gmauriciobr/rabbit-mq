package com.gmauricio.Service.Impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gmauricio.Service.PersistenceService;

@Transactional
public class PersistenceServiceImpl implements PersistenceService {
	
	@PersistenceContext	
	public EntityManager entityManager;
	
	public void save(Object object) {
		entityManager.persist(object);
		entityManager.flush();
	}
	
}
