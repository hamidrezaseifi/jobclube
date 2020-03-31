package com.seifi.jobclube.core.test.controller;

import com.seifi.jobclube.common.models.edo.AdvertiseEdo;
import com.seifi.jobclube.core.test.TestDataProducer;
import com.seifi.jobclube.core.entitys.AdvertiseEntity;
import com.seifi.jobclube.core.models.EntityEdoMapper;
import com.seifi.jobclube.core.repository.AdvertiseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdvertiseControllerTest extends TestDataProducer {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MappingJackson2HttpMessageConverter jsonConverter;

    @MockBean
    private AdvertiseRepository advertiseRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testListAll() throws Exception {

        List<AdvertiseEntity> modelList = generaletTestAdvertiseEntityList();
        List<AdvertiseEdo> edoList = EntityEdoMapper.toAdvertiseEdoList(modelList);

        when(this.advertiseRepository.findAll(any(Sort.class))).thenReturn(modelList);

        final String responseAsJson = this.jsonConverter.getObjectMapper().writeValueAsString(edoList);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/adds/all")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(responseAsJson));

        verify(this.advertiseRepository, times(1)).findAll(any(Sort.class));

    }

    @Test
    public void testReadById() throws Exception {

        AdvertiseEntity model = generaletTestAdvertiseEntity(1);
        AdvertiseEdo edo = EntityEdoMapper.toEdo(model);

        when(this.advertiseRepository.findById(any(Long.class))).thenReturn(Optional.of(model));

        final String responseAsJson = this.jsonConverter.getObjectMapper().writeValueAsString(edo);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/adds/read/1")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(responseAsJson));

        verify(this.advertiseRepository, times(1)).findById(any(Long.class));

    }

    @Test
    public void testSave() throws Exception {

        AdvertiseEntity model = generaletTestAdvertiseEntity(1);
        AdvertiseEdo edo = EntityEdoMapper.toEdo(model);

        when(this.advertiseRepository.save(any(AdvertiseEntity.class))).thenReturn(model);

        final String requestAsJson = this.jsonConverter.getObjectMapper().writeValueAsString(edo);
        final String responseAsJson = this.jsonConverter.getObjectMapper().writeValueAsString(edo);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/adds/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestAsJson)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(responseAsJson));

        verify(this.advertiseRepository, times(1)).save(any(AdvertiseEntity.class));

    }

    @Test
    public void testDelete() throws Exception {

        AdvertiseEntity model = generaletTestAdvertiseEntity(1);
        AdvertiseEdo edo = EntityEdoMapper.toEdo(model);

        final String requestAsJson = this.jsonConverter.getObjectMapper().writeValueAsString(edo);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/adds/delete").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestAsJson)
                )
                .andExpect(status().isOk());

        verify(this.advertiseRepository, times(1)).delete(any(AdvertiseEntity.class));

    }

}
