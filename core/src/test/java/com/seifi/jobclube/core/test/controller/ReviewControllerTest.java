package com.seifi.jobclube.core.test.controller;

import com.seifi.jobclube.common.models.edo.ReviewEdo;
import com.seifi.jobclube.core.entitys.ReviewEntity;
import com.seifi.jobclube.core.models.EntityEdoMapper;
import com.seifi.jobclube.core.repository.ReviewRepository;
import com.seifi.jobclube.core.test.TestDataProducer;
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

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest extends TestDataProducer {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MappingJackson2HttpMessageConverter jsonConverter;

    @MockBean
    private ReviewRepository reviewRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testListAll() throws Exception {

        List<ReviewEntity> modelList = generaletTestReviewEntityList();
        List<ReviewEdo> edoList = EntityEdoMapper.toReviewEdoList(modelList);

        when(this.reviewRepository.findAll(any(Sort.class))).thenReturn(modelList);

        final String responseAsJson = this.jsonConverter.getObjectMapper().writeValueAsString(edoList);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/reviews/all")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(responseAsJson));

        verify(this.reviewRepository, times(1)).findAll(any(Sort.class));

    }

    @Test
    public void testReadById() throws Exception {

        ReviewEntity model = generaletTestReviewEntity(1);
        ReviewEdo edo = EntityEdoMapper.toEdo(model);

        when(this.reviewRepository.findById(any(Long.class))).thenReturn(Optional.of(model));

        final String responseAsJson = this.jsonConverter.getObjectMapper().writeValueAsString(edo);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/reviews/read/1")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(responseAsJson));

        verify(this.reviewRepository, times(1)).findById(any(Long.class));

    }

    @Test
    public void testSave() throws Exception {

        ReviewEntity model = generaletTestReviewEntity(1);
        ReviewEdo edo = EntityEdoMapper.toEdo(model);

        when(this.reviewRepository.save(any(ReviewEntity.class))).thenReturn(model);

        final String requestAsJson = this.jsonConverter.getObjectMapper().writeValueAsString(edo);
        final String responseAsJson = this.jsonConverter.getObjectMapper().writeValueAsString(edo);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/reviews/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestAsJson)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(responseAsJson));

        verify(this.reviewRepository, times(1)).save(any(ReviewEntity.class));

    }

    @Test
    public void testDelete() throws Exception {

        ReviewEntity model = generaletTestReviewEntity(1);
        ReviewEdo edo = EntityEdoMapper.toEdo(model);

        final String requestAsJson = this.jsonConverter.getObjectMapper().writeValueAsString(edo);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/reviews/delete").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestAsJson)
                )
                .andExpect(status().isOk());

        verify(this.reviewRepository, times(1)).delete(any(ReviewEntity.class));

    }

}
