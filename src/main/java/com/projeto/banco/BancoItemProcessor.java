package com.projeto.banco;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class BancoItemProcessor implements ItemProcessor<BancoDTO, BancoDTO>{
	
	private static final Logger log = LoggerFactory.getLogger(BancoItemProcessor.class);

	  @Override
	  public BancoDTO process(final BancoDTO bancoDTO) throws Exception {
		final String id = bancoDTO.getId().toUpperCase();
		final String nome = bancoDTO.getNome().toUpperCase();
	    final String agencia = bancoDTO.getAgencia().toUpperCase();
	    final String conta = bancoDTO.getConta().toUpperCase();

	    final BancoDTO transformedBanco = new BancoDTO(id, nome, agencia, conta);

	    log.info("Converting (" + bancoDTO + ") into (" + transformedBanco + ")");

	    return transformedBanco;
	    
	  }

}
