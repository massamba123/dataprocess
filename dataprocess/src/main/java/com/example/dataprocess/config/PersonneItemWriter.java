package com.example.dataprocess.config;

import com.example.dataprocess.entity.Personne;
import com.example.dataprocess.entity.PersonneRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class PersonneItemWriter implements ItemWriter<Personne> {
    private final PersonneRepository personneRepository;
    public PersonneItemWriter(PersonneRepository personneRepository){
        this.personneRepository = personneRepository;
    }
    @Override
    public void write(Chunk<? extends Personne> chunk) throws Exception {
        personneRepository.saveAll(chunk);
    }
}
