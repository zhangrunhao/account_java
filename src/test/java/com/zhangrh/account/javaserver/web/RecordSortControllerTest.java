package com.zhangrh.account.javaserver.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.request.RecordSortAddRequest;
import com.zhangrh.account.javaserver.request.RecordSortDeleteRequest;
import com.zhangrh.account.javaserver.request.RecordSortUpdateRequest;
import com.zhangrh.account.javaserver.response.RecordSortResponse;
import com.zhangrh.account.javaserver.utils.UserInfoUtil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RecordSortControllerTest {

  @Autowired
  RecordSortController controller;

  @Test
  void test() {
    // 测试列表
    UserInfoUtil.setDefaultTestUser(1);
    CommonResult<List<RecordSortResponse>> listResult1 = controller.doList();
    assertEquals(200, listResult1.getCode());

    // 测试添加
    RecordSortAddRequest addRequest = new RecordSortAddRequest();
    addRequest.setName("工资");
    addRequest.setIcon("http://aa.com/jiaotong.jpg");
    addRequest.setType("income");
    CommonResult<String> addResult = controller.doAdd(addRequest);
    assertNotNull(addResult);
    assertEquals(200, addResult.getCode());
    assertEquals("收支记录种类创建成功", addResult.getData());

    CommonResult<List<RecordSortResponse>> listResult2 = controller.doList();
    int length1 = listResult1.getData().size();
    int length2 = listResult2.getData().size();
    assertEquals(length1, length2 - 1);

    // 测试获取
    long addRecordSortId = listResult2.getData().get(length1).getRecordSortId();
    CommonResult<RecordSortResponse> getResult =  controller.doGetRecordSort(addRecordSortId);
    assertEquals(addRequest.getIcon(), getResult.getData().getIcon());
    assertEquals(addRequest.getName(), getResult.getData().getName());
    assertEquals(addRequest.getType(), getResult.getData().getType());

    // 测试更新
    String updateName = "updateName";
    RecordSortUpdateRequest updateRequest = new RecordSortUpdateRequest();
    BeanUtils.copyProperties(getResult.getData(), updateRequest);
    updateRequest.setName(updateName);
    CommonResult<String> updateResult = controller.doUpdate(updateRequest);
    assertEquals(200, updateResult.getCode());
    assertEquals("用户收支种类更新成功", updateResult.getData());
    CommonResult<RecordSortResponse> getUpdateResult = controller.doGetRecordSort(addRecordSortId);
    assertEquals(updateName, getUpdateResult.getData().getName());

    // 测试删除
    RecordSortDeleteRequest deleteRequest = new RecordSortDeleteRequest();
    deleteRequest.setRecordSortId(getResult.getData().getRecordSortId());
    CommonResult<String> deleteResult = controller.doDelete(deleteRequest);
    assertEquals(200, deleteResult.getCode());
    assertEquals("收支类型删除成功", deleteResult.getData());
    CommonResult<RecordSortResponse> getDeleResult = controller.doGetRecordSort(getResult.getData().getRecordSortId());
    assertNotEquals(200, getDeleResult.getCode());
  }
}
