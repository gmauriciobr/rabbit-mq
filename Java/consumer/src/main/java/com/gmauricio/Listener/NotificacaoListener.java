package com.gmauricio.Listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmauricio.Config.GloboAMQPConfig;
import com.gmauricio.Service.ApiService;

@Service
public class NotificacaoListener {
	
	private static final Log LOG = LogFactory.getLog(NotificacaoListener.class);
	
	@Autowired
	private ApiService apiservice;
	
	@RabbitListener(queues = GloboAMQPConfig.NOTIFICACAO_QUEUE)
	public void consumer(Message message) {
		try {
			LOG.info("Consumido: " + message);
			this.apiservice.registrar(message);
		} catch (Exception e) {
			LOG.info(e);
		}
	}

}
