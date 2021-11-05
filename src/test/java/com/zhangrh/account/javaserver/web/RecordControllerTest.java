package com.zhangrh.account.javaserver.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.request.RecordAddRequest;
import com.zhangrh.account.javaserver.request.RecordDeleteRequest;
import com.zhangrh.account.javaserver.request.RecordUpdateRequest;
import com.zhangrh.account.javaserver.response.RecordDateGroupResponse;
import com.zhangrh.account.javaserver.utils.UserInfoUtil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RecordControllerTest {
  
  @Autowired
  RecordController controller;

  @Test
  void testAdd() {
    User user = new User();
    user.setUserId(1);
    UserInfoUtil.setUser(user);

    RecordAddRequest request = new RecordAddRequest();
    request.setRecordSortId(12);
    request.setAccountId(11);
    request.setRemark("打球");
    request.setSpendTimeStamp(1623468716);
    request.setCount("-11.22");
    CommonResult<String> result =  controller.doAdd(request);
    assertEquals(200, result.getCode());
    assertEquals("收支记录创建成功", result.getData());
  }

  @Test
  void testUpdate() {
    User user = new User();
    user.setUserId(1);
    UserInfoUtil.setUser(user);
    RecordUpdateRequest request = new RecordUpdateRequest();
    request.setRecordId(56);
    request.setAccountId(97);
    request.setRecordSortId(12);
    request.setAccountId(11);
    request.setRemark("打球update");
    request.setSpendTimeStamp(1623468716);
    request.setCount("0.22");
    CommonResult<String> result = controller.doUpdate(request);
    assertEquals(200, result.getCode());
    assertEquals("收支记录修改成功", result.getData());
  }

  @Test
  void testDelete() {
    User user = new User();
    user.setUserId(1);
    UserInfoUtil.setUser(user);

    RecordDeleteRequest request = new RecordDeleteRequest();
    request.setRecordId(56);
    CommonResult<String> result = controller.doDelete(request);
    assertEquals(200, result.getCode());
    assertEquals("收支记录删除成功", result.getData());
  }

  @Test
  void testGetListByUser() {
    User user = new User();
    user.setUserId(1);
    UserInfoUtil.setUser(user);
    CommonResult<List<RecordDateGroupResponse>> result = controller.doGetListByUser();
    assertEquals(200, result.getCode());
  }

  @Test
  void testDoGetListByAccount() {
    User user = new User();
    user.setUserId(1);
    UserInfoUtil.setUser(user);
    CommonResult<List<RecordDateGroupResponse>> result = controller.doGetListByAccount(97);
    assertEquals(200, result.getCode());
  }
}
