package com.gmauricio.Service;

import org.springframework.amqp.core.Message;

public interface ApiService extends PersistenceService {
	
	public void registrar(Message message);
	
}
