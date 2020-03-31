package com.seifi.jobclube.core.test.controller;

import com.seifi.jobclube.common.models.edo.PostEdo;
import com.seifi.jobclube.core.entitys.PostEntity;
import com.seifi.jobclube.core.models.EntityEdoMapper;
import com.seifi.jobclube.core.repository.PostRepository;
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
public class PostControllerTest extends TestDataProducer {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MappingJackson2HttpMessageConverter jsonConverter;

    @MockBean
    private PostRepository postRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testListAll() throws Exception {

        List<PostEntity> modelList = generaletTestPostEntityList();
        List<PostEdo> edoList = EntityEdoMapper.toPostEdoList(modelList);

        when(this.postRepository.findAll(any(Sort.class))).thenReturn(modelList);

        final String responseAsJson = this.jsonConverter.getObjectMapper().writeValueAsString(edoList);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/posts/all")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(responseAsJson));

        verify(this.postRepository, times(1)).findAll(any(Sort.class));

    }

    @Test
    public void testReadById() throws Exception {

        PostEntity model = generaletTestPostEntity(1);
        PostEdo edo = EntityEdoMapper.toEdo(model);

        when(this.postRepository.findById(any(Long.class))).thenReturn(Optional.of(model));

        final String responseAsJson = this.jsonConverter.getObjectMapper().writeValueAsString(edo);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/posts/read/1")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(responseAsJson));

        verify(this.postRepository, times(1)).findById(any(Long.class));

    }

    @Test
    public void testSave() throws Exception {

        PostEntity model = generaletTestPostEntity(1);
        PostEdo edo = EntityEdoMapper.toEdo(model);

        when(this.postRepository.save(any(PostEntity.class))).thenReturn(model);

        final String requestAsJson = this.jsonConverter.getObjectMapper().writeValueAsString(edo);
        final String responseAsJson = this.jsonConverter.getObjectMapper().writeValueAsString(edo);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/posts/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestAsJson)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(responseAsJson));

        verify(this.postRepository, times(1)).save(any(PostEntity.class));

    }

    @Test
    public void testDelete() throws Exception {

        PostEntity model = generaletTestPostEntity(1);
        PostEdo edo = EntityEdoMapper.toEdo(model);

        final String requestAsJson = this.jsonConverter.getObjectMapper().writeValueAsString(edo);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/posts/delete").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestAsJson)
                )
                .andExpect(status().isOk());

        verify(this.postRepository, times(1)).delete(any(PostEntity.class));

    }

}
