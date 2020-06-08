package dev.solocoding.entitylistener.repo;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dev.solocoding.entitylistener.entities.Post;
import dev.solocoding.entitylistener.entities.PostBck;
import lombok.RequiredArgsConstructor;

@Component // generic purpose annotation for DI
@RequiredArgsConstructor
public class PostListener {

    private final PostBckRepo bckRepo;
    
    @PrePersist //to be called before presist // flushing
    public void onPrePrist(final Post toSave){
        toSave.setVersion(1L);
    }

    @PostPersist // to be called after flushing, from here we can recover the id
    @PostUpdate // after update
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void afterPresist(final Post saved){
        PostBck backup = new PostBck(saved);
        bckRepo.save(backup);
    }

    @PreUpdate
    public void onUpdate(final Post toUpdate){
        toUpdate.setVersion(toUpdate.getVersion()+1);
    }

}
