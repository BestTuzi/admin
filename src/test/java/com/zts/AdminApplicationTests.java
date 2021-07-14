package com.zts;

import com.zts.entity.Depart;
import com.zts.entity.Teachers;
import com.zts.mapper.DepartMapper;
import com.zts.mapper.TeachersMapper;
import com.zts.service.DepartService;
import com.zts.service.TeachersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AdminApplicationTests {
    @Autowired
    private TeachersService teachersService;
    @Autowired
    private DepartMapper departMapper;
    @Autowired
    private DepartService departService;
    @Autowired
    private TeachersMapper teachersMapper;

    @Test
    void contextLoads() {
     teachersService.updateDepartByDepartId(5,"中文系" );
    }
    @Test
    void commit01(){
        System.out.println("第二次提交");
    }

}
