package com.gmauricio.Controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gmauricio.Service.ApiService;

@RestController
@RequestMapping("api")
public class ApiController {
	
	private static final Log LOG = LogFactory.getLog(ApiController.class);
	
	@Autowired
	private ApiService apiService;
	
	@RequestMapping(value = "/carga", method = RequestMethod.POST)
    public ResponseEntity<String> carga() throws Exception {
    	try{
	    	this.apiService.carga();
	    	return new ResponseEntity<>(HttpStatus.OK);
    	}catch(Exception e){
    		LOG.error(e);
    		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
    public ResponseEntity<String> send(@RequestBody String notificacao) throws Exception {
    	try{
	    	this.apiService.send(notificacao);
	    	return new ResponseEntity<>(HttpStatus.OK);
    	}catch(Exception e){
    		LOG.error(e);
    		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
}
