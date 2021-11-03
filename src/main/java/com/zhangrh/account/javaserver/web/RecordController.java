package com.zhangrh.account.javaserver.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.Record;
import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.request.RecordAddRequest;
import com.zhangrh.account.javaserver.request.RecordDeleteRequest;
import com.zhangrh.account.javaserver.request.RecordUpdateRequest;
import com.zhangrh.account.javaserver.response.RecordDateGroupResponse;
import com.zhangrh.account.javaserver.response.RecordResponse;
import com.zhangrh.account.javaserver.service.RecordService;
import com.zhangrh.account.javaserver.utils.DateTimeUtil;
import com.zhangrh.account.javaserver.utils.UserInfoUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/record")
public class RecordController {
  static final Logger LOGGER = LoggerFactory.getLogger(RecordController.class);

  @Autowired
  RecordService recordService;

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doAdd(
    @Validated @RequestBody RecordAddRequest request
  ) {
    try {
      User user = UserInfoUtil.getUser();
      Record record = new Record();
      record.setUserId(user.getUserId());
      record.setAccountId(request.getAccountId());
      record.setRecordSortId(request.getRecordSortId());
      record.setRemark(request.getRemark());
      record.setSpendTime(DateTimeUtil.MillToLocalDate(request.getSpendTimeStamp()));
      record.setCount(new BigDecimal(request.getCount()));
      recordService.add(record);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("收支记录创建成功");
  }
  
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doUpdate(
    @Validated @RequestBody RecordUpdateRequest request
    ) {
      try {
        Record record = new Record();
        record.setUserId(UserInfoUtil.getUser().getUserId());
        record.setAccountId(request.getAccountId());
        record.setCount(new BigDecimal(request.getCount()));
        record.setRecordSortId(request.getRecordSortId());
        record.setRemark(request.getRemark());
        record.setSpendTime(DateTimeUtil.MillToLocalDate(request.getSpendTimeStamp()));
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("收支记录修改成功");
  }

  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  @ResponseBody
  public CommonResult<String> doDelete(
    @Validated @RequestBody RecordDeleteRequest request
  ) {
    try {
      Record record = new Record();
      record.setUserId(UserInfoUtil.getUser().getUserId());
      record.setRecordId(request.getRecordId());
      recordService.delete(record);
    } catch (Exception e) {
      CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("收支记录删除成功");
  }

  @RequestMapping(value = "/getListByUser", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<List<RecordDateGroupResponse>> doGetListByUser() {
    List<RecordDateGroupResponse> responses = new ArrayList<>();
    try {
      User user = UserInfoUtil.getUser();
      Map<String, List<Record>>  rMap = recordService.getDateGroupRecordByUser(user);
      rMap.forEach((key, value) -> {
        List<RecordResponse> rList = new ArrayList<>();
        for (Record record : value) {
          rList.add(RecordResponse.recordEntityToRecordResponse(record));
        }
        responses.add(new RecordDateGroupResponse(key, rList));
      });
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success(responses);
  }

  @RequestMapping(value = "/getListByAccount", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<List<RecordResponse>> doGetListByAccount(
    @RequestParam long accountId
  ) {
    List<RecordResponse> responses = new ArrayList<>();
    try {
      Account account = new Account();
      account.setUserId(UserInfoUtil.getUser().getUserId());
      account.setAccountId(accountId);
      List<Record> list = recordService.getListByAccount(account);
      for (Record record: list) {
        responses.add(RecordResponse.recordEntityToRecordResponse(record));
      }
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    // TODO: 如何直接通过两张表一起查询返回recordSort的相关信息
    return CommonResult.success(responses);
  }
}
