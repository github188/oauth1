package com.yitutech.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.yitutech.bo.PersonBO;
import com.yitutech.common.log.MedicalLog;
import com.yitutech.common.log.MedicalLogFactory;
import com.yitutech.dao.PersonMapper;
import com.yitutech.model.Person;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jarontang on 18-3-15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceImplTest {
    MedicalLog log = MedicalLogFactory.getMedicalLog(PersonServiceImplTest.class);

    @Autowired
    @InjectMocks
    private PersonServiceImpl personService;

    @Autowired
    private ModelMapper modelMapper;

    @Mock
    private PersonMapper personMapper;
    private Person person;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        person = Person.builder()
            .id(1)
            .name("Jaron")
            .build();

        when(personMapper.selectByExample(any())).thenReturn(Arrays.asList(person, person, person));
        when(personMapper.insert(any())).thenReturn(1);
    }

    @Test
    public void createPerson() throws Exception {
        personService.createPerson(modelMapper.map(person, PersonBO.class));
    }

    @Test
    public void getPersonList() throws Exception {
        List<PersonBO> personBOList = personService.getPersonList(0, 5);
        Assert.assertSame(3, personBOList.size());
    }

    @Test
    public void findPersonById() throws Exception {
        PersonBO personBO = personService.findPersonById("1");
        Assert.assertNotNull(personBO);
        Assert.assertEquals(modelMapper.map(person, PersonBO.class), personBO);
        log.debug("personBO: " + personBO);
    }

}