package com.seifi.jobclube.core.controller;

import com.seifi.jobclube.common.models.edo.PostEdo;
import com.seifi.jobclube.core.entitys.PostEntity;
import com.seifi.jobclube.core.models.EntityEdoMapper;
import com.seifi.jobclube.core.repository.PostRepository;
import com.seifi.jobclube.core.services.ITokenProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ITokenProcessor tokenProcessor;

    @GetMapping(value = "/posts/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostEdo> listAll(@RequestHeader(value = "APP-TOKEN", defaultValue = "") String appToken)
            throws InvocationTargetException, IllegalAccessException {

        tokenProcessor.validateAppToken(appToken);

        List<Sort.Order> orders = Arrays.asList(Sort.Order.desc("createdAt"));

        List<PostEntity> modelList = postRepository.findAll(Sort.by(orders));
        List<PostEdo> edoList = EntityEdoMapper.toPostEdoList(modelList);

        return edoList;
    }

    @GetMapping(value = "/posts/read/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<PostEdo> readById(@PathVariable Long id, @RequestHeader(value = "APP-TOKEN", defaultValue = "") String appToken)
            throws InvocationTargetException, IllegalAccessException {

        tokenProcessor.validateAppToken(appToken);

        Optional<PostEdo> edoOptional = EntityEdoMapper.toPostEdoOptional(postRepository.findById(id));

        return edoOptional;
    }

    @PostMapping(value = "/posts/save" , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Optional<PostEdo> save(@RequestBody PostEdo PostEdo, @RequestHeader(value = "APP-TOKEN", defaultValue = "") String appToken)
            throws InvocationTargetException, IllegalAccessException {

        tokenProcessor.validateAppToken(appToken);

        PostEntity PostEntity = EntityEdoMapper.fromEdo(PostEdo);
        PostEntity savedReviewEntity = postRepository.save(PostEntity);
        Optional<PostEdo> edoOptional = EntityEdoMapper.toPostEdoOptional(Optional.of(savedReviewEntity) );

        return edoOptional;
    }

    @PostMapping(value = "/posts/delete" , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody PostEdo PostEdo, @RequestHeader(value = "APP-TOKEN", defaultValue = "") String appToken)
            throws InvocationTargetException, IllegalAccessException {

        tokenProcessor.validateAppToken(appToken);

        postRepository.delete(EntityEdoMapper.fromEdo(PostEdo));

    }

}
