package com.seifi.jobclube.core.controller;

import com.seifi.jobclube.common.models.edo.AdvertiseEdo;
import com.seifi.jobclube.core.entitys.AdvertiseEntity;
import com.seifi.jobclube.core.models.EntityEdoMapper;
import com.seifi.jobclube.core.repository.AdvertiseRepository;
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
public class AdvertiseController {

    @Autowired
    private AdvertiseRepository advertiseRepository;

    @Autowired
    private ITokenProcessor tokenProcessor;

    @GetMapping(value = "/adds/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdvertiseEdo> listAll(@RequestHeader(value = "APP-TOKEN", defaultValue = "") String appToken)
            throws InvocationTargetException, IllegalAccessException {

        tokenProcessor.validateAppToken(appToken);

        List<Sort.Order> orders = Arrays.asList(Sort.Order.desc("createdAt"));

        List<AdvertiseEntity> advertiseList = advertiseRepository.findAll(Sort.by(orders));
        List<AdvertiseEdo> edoList = EntityEdoMapper.toAdvertiseEdoList(advertiseList);

        return edoList;
    }

    @GetMapping(value = "/adds/read/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<AdvertiseEdo> readById(@PathVariable Long id, @RequestHeader(value = "APP-TOKEN", defaultValue = "") String appToken)
            throws InvocationTargetException, IllegalAccessException {

        tokenProcessor.validateAppToken(appToken);

        Optional<AdvertiseEdo> edoOptional = EntityEdoMapper.toAdvertiseEdoOptional(advertiseRepository.findById(id));

        return edoOptional;
    }

    @PostMapping(value = "/adds/save" , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Optional<AdvertiseEdo> save(@RequestBody AdvertiseEdo advertiseEdo, @RequestHeader(value = "APP-TOKEN", defaultValue = "") String appToken)
            throws InvocationTargetException, IllegalAccessException {

        tokenProcessor.validateAppToken(appToken);

        AdvertiseEntity advertiseEntity = EntityEdoMapper.fromEdo(advertiseEdo);
        AdvertiseEntity savedAdvertiseEntity = advertiseRepository.save(advertiseEntity);
        Optional<AdvertiseEdo> edoOptional = EntityEdoMapper.toAdvertiseEdoOptional(Optional.of(savedAdvertiseEntity) );

        return edoOptional;
    }

    @PostMapping(value = "/adds/delete" , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody AdvertiseEdo advertiseEdo, @RequestHeader(value = "APP-TOKEN", defaultValue = "") String appToken)
            throws InvocationTargetException, IllegalAccessException {

        tokenProcessor.validateAppToken(appToken);

        advertiseRepository.delete(EntityEdoMapper.fromEdo(advertiseEdo));

    }

}
