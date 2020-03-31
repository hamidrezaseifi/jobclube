package com.seifi.jobclube.core.controller;

import com.seifi.jobclube.common.models.edo.ReviewEdo;
import com.seifi.jobclube.core.entitys.ReviewEntity;
import com.seifi.jobclube.core.models.EntityEdoMapper;
import com.seifi.jobclube.core.repository.ReviewRepository;
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
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ITokenProcessor tokenProcessor;

    @GetMapping(value = "/reviews/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReviewEdo> listAll(@RequestHeader(value = "APP-TOKEN", defaultValue = "") String appToken)
            throws InvocationTargetException, IllegalAccessException {

        tokenProcessor.validateAppToken(appToken);

        List<Sort.Order> orders = Arrays.asList(Sort.Order.desc("createdAt"));

        List<ReviewEntity> modelList = reviewRepository.findAll(Sort.by(orders));
        List<ReviewEdo> edoList = EntityEdoMapper.toReviewEdoList(modelList);

        return edoList;
    }

    @GetMapping(value = "/reviews/read/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<ReviewEdo> readById(@PathVariable Long id, @RequestHeader(value = "APP-TOKEN", defaultValue = "") String appToken)
            throws InvocationTargetException, IllegalAccessException {

        tokenProcessor.validateAppToken(appToken);

        Optional<ReviewEdo> edoOptional = EntityEdoMapper.toReviewEdoOptional(reviewRepository.findById(id));

        return edoOptional;
    }

    @PostMapping(value = "/reviews/save" , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Optional<ReviewEdo> save(@RequestBody ReviewEdo ReviewEdo, @RequestHeader(value = "APP-TOKEN", defaultValue = "") String appToken)
            throws InvocationTargetException, IllegalAccessException {

        tokenProcessor.validateAppToken(appToken);

        ReviewEntity ReviewEntity = EntityEdoMapper.fromEdo(ReviewEdo);
        ReviewEntity savedReviewEntity = reviewRepository.save(ReviewEntity);
        Optional<ReviewEdo> edoOptional = EntityEdoMapper.toReviewEdoOptional(Optional.of(savedReviewEntity) );

        return edoOptional;
    }

    @PostMapping(value = "/reviews/delete" , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody ReviewEdo ReviewEdo, @RequestHeader(value = "APP-TOKEN", defaultValue = "") String appToken)
            throws InvocationTargetException, IllegalAccessException {

        tokenProcessor.validateAppToken(appToken);

        reviewRepository.delete(EntityEdoMapper.fromEdo(ReviewEdo));

    }

}
