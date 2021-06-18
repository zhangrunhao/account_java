package com.zhangrh.account.javaserver.web;

import java.util.ArrayList;
import java.util.List;

import com.zhangrh.account.javaserver.api.CommonResult;
import com.zhangrh.account.javaserver.entity.RecordSort;
import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.request.RecordSortAddRequest;
import com.zhangrh.account.javaserver.request.RecordSortDeleteRequest;
import com.zhangrh.account.javaserver.request.RecordSortUpdateRequest;
import com.zhangrh.account.javaserver.response.RecordSortResponse;
import com.zhangrh.account.javaserver.service.RecordSortService;
import com.zhangrh.account.javaserver.utils.UserInfoUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/record_sort")
public class RecordSortController {
  static final Logger LOGGER = LoggerFactory.getLogger(RecordSortController.class);

  @Autowired
  RecordSortService recordSortService;

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doAdd(
    @Validated @RequestBody RecordSortAddRequest recordSortAddRequest
  ) {
    try {
      User user = UserInfoUtil.getUser();
      RecordSort recordSort = new RecordSort();
      BeanUtils.copyProperties(recordSortAddRequest, recordSort);
      recordSort.setUserId(user.getUserId());
      recordSortService.add(user, recordSort);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("收支记录种类创建成功");
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<List<RecordSortResponse>> doList() {
    User user = UserInfoUtil.getUser();
    List<RecordSortResponse> recordSortResponses = new ArrayList<>();
    try {
      List<RecordSort> recordSorts = recordSortService.list(user);
      for (RecordSort recordSort: recordSorts) {
        RecordSortResponse recordSortResponse = new RecordSortResponse();
        BeanUtils.copyProperties(recordSort, recordSortResponse);
        recordSortResponses.add(recordSortResponse);
      }
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success(recordSortResponses);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doUpdate(
    @Validated @RequestBody RecordSortUpdateRequest recordSortUpdateRequest
  ) {
    try {
      User user = UserInfoUtil.getUser();
      RecordSort recordSort = new RecordSort();
      BeanUtils.copyProperties(recordSortUpdateRequest, recordSort);
      recordSort.setUserId(user.getUserId());
      recordSortService.update(user, recordSort);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("用户收支种类更新成功");
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public CommonResult<String> doDelete(
    @Validated @RequestBody RecordSortDeleteRequest recordSortDeleteRequest
  ) {
    try {
      User user = UserInfoUtil.getUser();
      RecordSort recordSort = new RecordSort();
      recordSort.setRecordSortId(recordSortDeleteRequest.getRecordSortId());
      recordSortService.delete(user, recordSort);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success("收支类型删除成功");
  }


  @RequestMapping(value = "/getRecordSort", method = RequestMethod.GET)
  @ResponseBody
  public CommonResult<RecordSortResponse> doGetRecordSort(
    @RequestParam long recordSortId
  ) {
    User user = UserInfoUtil.getUser();
    RecordSortResponse recordSortResponse = null;
    RecordSort recordSort = null;
    try {
      recordSort = recordSortService.getById(user, recordSortId);
      recordSortResponse = new RecordSortResponse();
      BeanUtils.copyProperties(recordSort, recordSortResponse);
    } catch (Exception e) {
      return CommonResult.failed(e.getMessage());
    }
    return CommonResult.success(recordSortResponse);
  }
}
