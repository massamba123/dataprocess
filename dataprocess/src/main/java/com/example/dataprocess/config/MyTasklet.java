package com.example.dataprocess.config;

import com.example.dataprocess.entity.Personne;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class MyTasklet implements Tasklet {

    private ItemReader<Personne> itemReader;

    public MyTasklet(ItemReader<Personne> itemReader) {
        this.itemReader = itemReader;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        Personne personne = itemReader.read();
        if (null != personne){
            System.out.println("Hello from MyTasklet!");
            System.out.println(personne);
        }
        return RepeatStatus.FINISHED;
    }
}