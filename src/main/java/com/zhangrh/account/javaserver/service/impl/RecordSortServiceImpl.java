package com.zhangrh.account.javaserver.service.impl;

import java.util.Date;
import java.util.List;

import com.zhangrh.account.javaserver.entity.RecordSort;
import com.zhangrh.account.javaserver.entity.User;
import com.zhangrh.account.javaserver.exception.Asserts;
import com.zhangrh.account.javaserver.mapper.RecordSortMapper;
import com.zhangrh.account.javaserver.service.RecordSortService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordSortServiceImpl implements RecordSortService {
  private static final Logger LOGGER = LoggerFactory.getLogger(RecordSortServiceImpl.class);

  @Autowired
  RecordSortMapper recordSortMapper;

  @Override
  public void add(User user, RecordSort recordSort) {
    try {
      recordSort.setCreateAt(new Date().getTime());
      recordSortMapper.insert(recordSort);
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("用户创建收支记录种类失败");
    }
  }

  @Override
  public List<RecordSort> list(User user) {
    List<RecordSort> list = null;
    try {
      list = recordSortMapper.selectList(user);
      if (list == null) throw new Exception("record sort list is null");
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("查询收支记录种类列表出错");
    }
    return list;
  }

  @Override
  public void update(User user, RecordSort recordSort) {
    try {
      recordSort.setUpdateAt(new Date().getTime());
      int size = recordSortMapper.update(recordSort, user);
      if (size != 1) throw new Exception("update record sort row size: " + size);
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("收支记录种类更新失败");
    }
  }

  @Override
  public void delete(User user, RecordSort recordSort) {
    try {
      recordSort.setDeleteAt(new Date().getTime());
      recordSortMapper.delete(recordSort, user);
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("收支记录种类删除失败");
    }
  }

  @Override
  public RecordSort getById(User user, long recordSortId) {
    RecordSort recordSort = null;
    try {
      recordSort = recordSortMapper.getRecordSortById(recordSortId, user);
      if (recordSort == null) throw new Exception("get record sort by id is null");
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("收支记录种类查询失败");
    }
    return recordSort;
  }
  
}
