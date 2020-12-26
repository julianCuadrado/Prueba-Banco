package co.com.abc.controllers;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Service
public class ServicoRest {

	public ResponseEntity<?> ejecutarMetodoGet(String url, Map<String, String> mapaHeaders) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		if (mapaHeaders != null) {
			mapaHeaders.forEach((k, v) -> headers.set(k, v));
		}
		HttpEntity<String> request = new HttpEntity<>(null, headers);
		ResponseEntity<?> resp = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
		return resp;
	}

	public <T> ResponseEntity<?> ejecutarMetodoPost(String url, T body, Map<String, String> mapaHeaders) {
		RestTemplate restTemplate = new RestTemplate();
		String json = new Gson().toJson(body);
		HttpHeaders headers = new HttpHeaders();
		if (mapaHeaders != null) {
			mapaHeaders.forEach((k, v) -> headers.set(k, v));
		}
		HttpEntity<String> request = new HttpEntity<>(json, headers);
		ResponseEntity<?> resp = restTemplate.postForEntity(url, request, String.class);
		return resp;
	}
	
	public <T> ResponseEntity<?> ejecutarMetodoDelete(String url, T body, Map<String, String> mapaHeaders) {
		RestTemplate restTemplate = new RestTemplate();
		String json = new Gson().toJson(body);
		HttpHeaders headers = new HttpHeaders();
		if (mapaHeaders != null) {
			mapaHeaders.forEach((k, v) -> headers.set(k, v));
		}
		HttpEntity<String> request = new HttpEntity<>(json, headers);
		ResponseEntity<?> resp = restTemplate.exchange(url, HttpMethod.DELETE,request, String.class);
		return resp;
	}

}
