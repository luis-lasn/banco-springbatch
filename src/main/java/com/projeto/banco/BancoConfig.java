package com.projeto.banco;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.client.RestTemplate;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;


@Configuration
public class BancoConfig {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
    ItemReader<BancoDTO> BancoReader(Environment environment, RestTemplate restTemplate) {
        return new BancoReader("http://localhost:8080/findallbancos", restTemplate);
    }
	
	@Bean
    public Step exampleJobStep(ItemReader<BancoDTO> reader,
                               ItemWriter<BancoDTO> writer,
                               StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("exampleJobStep")
                .<BancoDTO, BancoDTO>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }
	
	@Bean
    public Job exampleJob(Step exampleJobStep,
                          JobBuilderFactory jobBuilderFactory) {
        return jobBuilderFactory.get("exampleJob")
                .incrementer(new RunIdIncrementer())
                .flow(exampleJobStep)
                .end()
                .build();
    }
	
	@Bean
    ItemWriter<BancoDTO> BancoCsvItemWriter() {
        FlatFileItemWriter<BancoDTO> csvFileWriter = new FlatFileItemWriter<>();
 
        String exportFileHeader = "Id;nome;agencia;conta";
        StringHeaderWriter headerWriter = new StringHeaderWriter(exportFileHeader);
        csvFileWriter.setHeaderCallback(headerWriter);
 
        String exportFilePath = "/tmp/banco.csv";
        csvFileWriter.setResource(new FileSystemResource(exportFilePath));
 
        LineAggregator<BancoDTO> lineAggregator = createStudentLineAggregator();
        csvFileWriter.setLineAggregator(lineAggregator);
 
        return csvFileWriter;
    }
	
	private LineAggregator<BancoDTO> createStudentLineAggregator() {
        DelimitedLineAggregator<BancoDTO> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(";");
 
        FieldExtractor<BancoDTO> fieldExtractor = createBancoFieldExtractor();
        lineAggregator.setFieldExtractor(fieldExtractor);
 
        return lineAggregator;
    }
	
	private FieldExtractor<BancoDTO> createBancoFieldExtractor() {
        BeanWrapperFieldExtractor<BancoDTO> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[] {"id", "nome", "agencia", "conta"});
        return extractor;
    }

}
