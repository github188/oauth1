package com.yitutech.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yitutech.App;
import com.yitutech.bo.PersonBO;
import com.yitutech.common.log.MedicalLog;
import com.yitutech.common.log.MedicalLogFactory;
import com.yitutech.model.Person;
import com.yitutech.service.IPersonService;
import com.yitutech.vo.PersonVO;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by jarontang on 18-3-13.
 */
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PersonControllerTest {
    MedicalLog log = MedicalLogFactory.getMedicalLog(PersonControllerTest.class);

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private IPersonService personService;
    @Autowired
    @InjectMocks
    private PersonController personController;
    private PersonVO personVO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders
            .standaloneSetup(personController)
            .build();

        Person person = Person.builder()
            .id(1)
            .name("Jaron")
            .build();

        personVO = modelMapper.map(person, PersonVO.class);

        List<PersonBO> personBOList = Arrays.asList(person, person, person).stream()
            .map(p -> modelMapper.map(p, PersonBO.class))
            .collect(Collectors.toList());
        when(personService.findPersonById(String.valueOf(1))).thenReturn(modelMapper.map(person, PersonBO.class));
        when(personService.getPersonList(1, 3)).thenReturn(personBOList);
        doNothing().when(personService).createPerson(isA(PersonBO.class));
    }

    @Test
    public void getPerson() throws Exception {
        mvc
            .perform(get("/person/1"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath(("$.data.id")).exists());
    }

    @Test
    public void getPersonList() throws Exception {
        mvc
            .perform(get("/person?page=1&size=3"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.length()", is(3)));
    }

    @Test
    public void createPerson() throws Exception {
        String content = objectMapper.writeValueAsString(personVO);
        log.debug("content: " + content);
        mvc
            .perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(200)));
    }
}