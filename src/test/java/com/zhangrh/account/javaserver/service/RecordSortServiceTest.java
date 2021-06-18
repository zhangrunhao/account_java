package com.zhangrh.account.javaserver.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import com.zhangrh.account.javaserver.entity.RecordSort;
import com.zhangrh.account.javaserver.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RecordSortServiceTest {
  
  @Autowired
  RecordSortService recordSortService;

  @Test
  void test() {
    User user = new User();
    user.setUserId(1);

    RecordSort recordSort = new RecordSort();
    recordSort.setUserId(user.getUserId());
    recordSort.setIcon("http://baidu.com/1.png");
    recordSort.setName("餐饮");
    recordSort.setType("expend");
    recordSortService.add(user, recordSort); // add()

    List<RecordSort> list = recordSortService.list(user); // list()
    RecordSort recordSort1 =  list.get(list.size() - 1); // 这里才有id
    assertEquals(recordSort.getName(), recordSort1.getName()); // 只能对比name


    String updateName = "updateSortName";
    recordSort1.setName(updateName);
    recordSortService.update(user, recordSort1); // update()
    RecordSort recordSort2 = recordSortService.getById(user, recordSort1.getRecordSortId()); // getById()
    assertEquals(updateName, recordSort2.getName());

    recordSortService.delete(user, recordSort1); // delete()
    RecordSort recordSort3 = recordSortService.getById(user, recordSort1.getRecordSortId());
    assertNull(recordSort3);
  }
}
