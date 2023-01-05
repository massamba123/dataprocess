package com.example.dataprocess.config;

import com.example.dataprocess.entity.Personne;
import com.example.dataprocess.entity.PersonneRepository;
import org.springframework.batch.item.ItemProcessor;

public class PersonneItemProcessor implements ItemProcessor<Personne,Personne> {

    @Override
    public Personne process(Personne item) throws Exception {
        item.setPrenom(item.getPrenom()+"-"+item.getNom());
        return item;
    }
}
