package com.yitutech.controller;

import static com.yitutech.common.result.ResultFactory.err;
import static com.yitutech.common.result.ResultFactory.ok;

import com.yitutech.bo.PersonBO;
import com.yitutech.common.log.MedicalLog;
import com.yitutech.common.log.MedicalLogFactory;
import com.yitutech.common.result.Result;
import com.yitutech.dto.PersonDTO;
import com.yitutech.error.Code;
import com.yitutech.service.IPersonService;
import com.yitutech.vo.PersonVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jarontang on 18-3-13.
 */
@RestController
public class PersonController {
    private static MedicalLog log = MedicalLogFactory.getMedicalLog(PersonController.class);

    @Autowired
    private IPersonService personService;
    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "查询 person", notes = "获取 person 信息")
    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public Result getPerson(
        @ApiParam(value = "需要查询的用户 id") @PathVariable(value = "id") String id) {

        log.debug("query id: " + id);
        PersonBO personBO = personService.findPersonById(id);
        if (personBO != null) {
            return ok(modelMapper.map(personBO, PersonVO.class));
        } else {
            return err(Code.RESOURCE_NOT_FOUND);
        }
    }

    @ApiOperation(value = "查询 person 列表", notes = "获取 person 列表信息")
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public Result getPersonList(
        @ApiParam(value = "页号，从1开始") @RequestParam(value = "page", defaultValue = "1") int page,
        @ApiParam(value = "页大小") @RequestParam(value = "size", defaultValue = "5") int size) {

        List<PersonVO> personVOList = personService.getPersonList(page, size).stream()
            .map(personBO -> modelMapper.map(personBO, PersonVO.class))
            .collect(Collectors.toList());
        return ok(personVOList);
    }


    @ApiOperation(value = "创建 person", notes = "创建一个 person")
    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public Result createPerson(
        @ApiParam(value = "需要查询的用户 id") @RequestBody PersonDTO personDTO) {

       personService.createPerson(modelMapper.map(personDTO, PersonBO.class));
       return ok();
    }
}
