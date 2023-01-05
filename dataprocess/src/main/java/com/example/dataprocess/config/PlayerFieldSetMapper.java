package com.example.dataprocess.config;

import com.example.dataprocess.entity.Personne;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

class PlayerFieldSetMapper implements FieldSetMapper<Personne> {
    public Personne mapFieldSet(FieldSet fieldSet) {
        Personne player = new Personne();

        player.setId(fieldSet.readInt(0));
        player.setPrenom(fieldSet.readString(1));
        player.setNom(fieldSet.readString(2));
        player.setAge(fieldSet.readInt(3));
        return player;
    }
}
