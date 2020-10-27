package com.projeto.banco;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

public class BancoWriter implements ItemWriter<BancoDTO>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BancoWriter.class);

    @Override
    public void write(List<? extends BancoDTO> list) throws Exception {
        LOGGER.info("Writing Bancos: {}", list);
        System.out.println("Printou " + list.get(0).getId());
        System.out.println("Printou " + list.get(0).getBanco());
        System.out.println("Printou " + list.get(0).getAgencia());
        System.out.println("Printou " + list.get(0).getConta());
    }

}
