package com.zhangrh.account.javaserver.service;

import java.util.List;

import com.zhangrh.account.javaserver.entity.RecordSort;
import com.zhangrh.account.javaserver.entity.User;

public interface RecordSortService {
  
  void add(User user, RecordSort recordSort);

  List<RecordSort> list(User user);

  void update(User user, RecordSort recordSort);

  void delete(User user, RecordSort recordSort);

  RecordSort getById(User user, long recordSortId);
}
