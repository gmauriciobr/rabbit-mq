package com.gmauricio.Service;

import org.springframework.stereotype.Service;

@Service
public interface ApiService {
	
	public void carga() throws Exception;
	
	public void send(String notificacao) throws Exception;

}
