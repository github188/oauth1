package com.yitutech.dao;

import com.yitutech.model.Person;
import com.yitutech.model.PersonExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jarontang on 18-3-14.
 */
@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonMapperTest {


    @Autowired
    private PersonMapper personMapper;

    @Test
    public void countByExample() throws Exception {
        PersonExample personExample = new PersonExample();
        personExample.createCriteria().andNameLike("M%");
        personMapper.selectByExample(personExample);
    }

    @Test
    public void deleteByExample() throws Exception {
    }

    @Test
    public void insert() throws Exception {
        Person person = Person.builder()
            .name("Jack")
            .build();
        personMapper.insertSelective(person);
    }


    @Test
    public void selectByExample() throws Exception {
        PersonExample personExample = new PersonExample();
        personExample.createCriteria().andNameLike("M%");
        personMapper.selectByExample(personExample);
    }

    @Test
    public void updateByExampleSelective() throws Exception {
        Person person = Person.builder()
            .name("Jack")
            .build();
        personMapper.insert(person);

        PersonExample personExample = new PersonExample();
        personExample.createCriteria().andIdEqualTo(4);
        Person newPerson = Person.builder()
            .name("John")
            .build();
        personMapper.updateByExampleSelective(newPerson, personExample);
    }

    @Test
    public void updateByExample() throws Exception {
    }

}