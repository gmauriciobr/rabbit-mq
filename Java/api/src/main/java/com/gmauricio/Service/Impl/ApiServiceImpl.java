package com.gmauricio.Service.Impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmauricio.Config.GloboAMQPConfig;
import com.gmauricio.Service.ApiService;

@Service
public class ApiServiceImpl implements ApiService {
	
	private static final Log LOG = LogFactory.getLog(ApiServiceImpl.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void carga() throws Exception {
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream("notificacoes.txt");
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(reader);
			
	        String linha = br.readLine();
	        while(linha != null) {
	        	LOG.info(linha);
	        	if(!linha.equals("")) {
	        		this.send(linha);
	        	}
	            linha = br.readLine();
	        }
		} catch (Exception e) {
			LOG.info(e);
			throw new Exception("Erro ao obter JSON");
		}
	}
	
	public void send(String notificacao) throws Exception {
		try {
			this.enviarFila(notificacao);
		} catch (Exception e) {
			LOG.info(e);
			throw new Exception("Erro ao enviar para fila!");
		}
	}
	
	private void enviarFila(String notificacao) {
		rabbitTemplate.convertAndSend(GloboAMQPConfig.EXCHANGE_NAME, "", notificacao);
	}
	
}
