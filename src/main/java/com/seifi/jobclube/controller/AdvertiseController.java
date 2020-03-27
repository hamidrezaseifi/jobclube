package com.seifi.jobclube.controller;

import com.seifi.jobclube.entity.AdvertiseEntity;
import com.seifi.jobclube.repository.AdvertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class AdvertiseController {

    @Autowired
    private AdvertiseRepository advertiseRepository;

    @GetMapping(value = "/adds/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdvertiseEntity> listAll(){

        List<Sort.Order> orders = Arrays.asList(Sort.Order.desc("createdAt"));

        return advertiseRepository.findAll(Sort.by(orders));
    }

    @GetMapping(value = "/adds/read/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<AdvertiseEntity> readById(@PathVariable Long id){

        return advertiseRepository.findById(id);
    }
}
