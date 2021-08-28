package com.gmauricio.Service.Impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmauricio.DTO.NotificacaoDTO;
import com.gmauricio.Entity.EventHistory;
import com.gmauricio.Entity.Status;
import com.gmauricio.Entity.Subscription;
import com.gmauricio.Service.ApiService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ApiServiceImpl extends PersistenceServiceImpl implements ApiService {
	
	private static final Log LOG = LogFactory.getLog(ApiServiceImpl.class);
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void registrar(Message message) {
		try {
			String response = this.parseToJson(new String(message.getBody(), "UTF-8"));
			
			if(response.equalsIgnoreCase("")) {
				return;
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			NotificacaoDTO dto = objectMapper.readValue(response, NotificacaoDTO.class);
			
			this.saveSubscription(dto);
		} catch (Exception e) {
			LOG.error("Erro ao registrar notificacao: " + e);
		}
	}
	
	private void saveSubscription(NotificacaoDTO dto) throws Exception {
		try {
			Subscription subscription = this.entityManager.find(Subscription.class, dto.getSubscription());
			if(subscription == null || subscription.getId() == null) {
				subscription = new Subscription(dto.getSubscription());
				subscription.setCreatedAt(new Date());
				subscription.setUpdatedAt(new Date());
				this.getStatus(dto, subscription);
				this.entityManager.persist(subscription);
			} else {
				this.saveEventHistory(subscription);
				this.getStatus(dto, subscription);
				subscription.setUpdatedAt(new Date());
				this.entityManager.merge(subscription);
			}
			this.entityManager.flush();
		} catch (Exception e) {
			LOG.error(e);
			throw new Exception("Erro ao salvar Subscription: " + e);
		}
	}
	
	private void saveEventHistory(Subscription subscription) throws Exception {
		try {
			EventHistory eventHistory = new EventHistory();
			eventHistory.setType(subscription.getStatus().getName());
			eventHistory.setCreatedAt(new Date());
			eventHistory.setSubscription(subscription);
			this.entityManager.persist(eventHistory);
			this.entityManager.flush();
		} catch (Exception e) {
			LOG.error(e);
			throw new Exception("Erro ao salvar EventHistory: " + e);
		}
	}
	
	private void getStatus(NotificacaoDTO dto, Subscription subscription){
		if(Status.SUBSCRIPTION_STATUS_ATIVO.contains(dto.getNotificationType())){
			subscription.setStatus(new Status(Status.ATIVO));
		} else {
			subscription.setStatus(new Status(Status.INATIVO));
		}
	}
	
	private String parseToJson(String a) {
		return a.replaceAll("'", "\"");
	}
	
}
