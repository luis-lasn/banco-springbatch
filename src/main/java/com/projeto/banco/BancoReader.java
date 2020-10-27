package com.projeto.banco;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class BancoReader implements ItemReader<BancoDTO>{
	
	private final String apiUrl;
    private final RestTemplate restTemplate;
 
    private int nextBancoIndex;
    private List<BancoDTO> bancoData;
    
    public BancoReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        nextBancoIndex = 0;
    }

    @Override
    public BancoDTO read() throws Exception {
        if (bancoDataIsNotInitialized()) {
            bancoData = fetchBancoDataFromAPI();
        }
 
        BancoDTO nextBanco = null;
 
        if (nextBancoIndex < bancoData.size()) {
            nextBanco = bancoData.get(nextBancoIndex);
            nextBancoIndex++;
        }
 
        return nextBanco;
    }
    
    private boolean bancoDataIsNotInitialized() {
        return this.bancoData == null;
    }
    
    private List<BancoDTO> fetchBancoDataFromAPI() {
        ResponseEntity<BancoDTO[]> response = restTemplate.getForEntity(
            apiUrl, 
            BancoDTO[].class
        );
        BancoDTO[] bancoData = response.getBody();
        System.out.println("Registro Descarregado: " + response.getBody());
        return Arrays.asList(bancoData);
    }

}
