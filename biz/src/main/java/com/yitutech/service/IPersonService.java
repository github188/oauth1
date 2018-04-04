package com.yitutech.service;

import com.yitutech.bo.PersonBO;
import java.util.List;

/**
 * Created by jarontang on 18-3-15.
 */
public interface IPersonService {

    PersonBO findPersonById(String id);

    void createPerson(PersonBO personBO);

    List<PersonBO> getPersonList(int page, int size);
}
