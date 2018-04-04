package com.yitutech.service.impl;

import com.github.pagehelper.PageHelper;
import com.yitutech.bo.PersonBO;
import com.yitutech.common.log.MedicalLog;
import com.yitutech.common.log.MedicalLogFactory;
import com.yitutech.dao.PersonMapper;
import com.yitutech.model.Person;
import com.yitutech.model.PersonExample;
import com.yitutech.service.IPersonService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jarontang on 18-3-15.
 */
@Component
public class PersonServiceImpl implements IPersonService {

    MedicalLog log = MedicalLogFactory.getMedicalLog(PersonServiceImpl.class);

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PersonBO findPersonById(String id) {
        PersonExample personExample = new PersonExample();
        personExample.createCriteria().andIdEqualTo(Integer.valueOf(id));
        log.debug("modelMapper: " + modelMapper);
        List<PersonBO> personBOList = personMapper.selectByExample(personExample)
            .stream()
            .map(src -> modelMapper.map(src, PersonBO.class))
            .collect(Collectors.toList());
        return personBOList.size() == 0 ? null : personBOList.get(0);
    }

    @Override
    public void createPerson(PersonBO personBO) {
        personMapper.insert(modelMapper.map(personBO, Person.class));
    }

    @Override
    public List<PersonBO> getPersonList(int page, int size) {
        PageHelper.startPage(page, size);
        List<PersonBO> personBOList = personMapper.selectByExample(null)
            .stream()
            .map(src -> modelMapper.map(src, PersonBO.class))
            .collect(Collectors.toList());
        return personBOList;
    }
}
