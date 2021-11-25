package com.zhangrh.account.javaserver.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.zhangrh.account.javaserver.entity.TradeCate;
import com.zhangrh.account.javaserver.entity.UserToTradeCate;
import com.zhangrh.account.javaserver.enums.TradeCateType;
import com.zhangrh.account.javaserver.exception.Asserts;
import com.zhangrh.account.javaserver.mapper.TradeCateMapper;
import com.zhangrh.account.javaserver.mapper.UserToTradeCateMapper;
import com.zhangrh.account.javaserver.service.TradeCateService;
import com.zhangrh.account.javaserver.service.Bo.TradeCateBo;
import com.zhangrh.account.javaserver.service.Bo.UserBo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TradeCateServiceImpl implements TradeCateService {
  private static final Logger LOGGER = LoggerFactory.getLogger(TradeCateServiceImpl.class);

  @Autowired
  TradeCateMapper tradeCateMapper;

  @Autowired
  UserToTradeCateMapper userToTradeCateMapper;

  @Override
  @Transactional
  public void add(TradeCateBo tradeCateBo) {
    try {
      // 新建
      tradeCateBo.setCreateAt(LocalDateTime.now());
      tradeCateBo.setType(TradeCateType.Extra);
      TradeCate tradeCate = tradeCateBo.toTradeCate();
      tradeCateMapper.insert(tradeCate); // trade_cate 添加记录
      tradeCateBo.setTradeCateId(tradeCate.getId());
      userToTradeCateMapper.insert(tradeCateBo.toUserToTradeCate()); // user_to_trade_cate 添加记录
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("用户创建交易种类失败");
    }
  }

  @Override
  @Transactional
  public void addDefault(UserBo userBo) {
    try {
      List<TradeCate> defaultList = tradeCateMapper.queryType(TradeCateType.Default.getCode());
      UserToTradeCate userToTradeCate = new UserToTradeCate();
      userToTradeCate.setUserId(userBo.getId());
      userToTradeCate.setCreateAt(LocalDateTime.now());
      for (TradeCate tradeCate : defaultList) {
        userToTradeCate.setTradeCateId(tradeCate.getId());
        userToTradeCateMapper.insert(userToTradeCate);
      }
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("创建默认交易种类失败");
    }
  }

  @Override
  public List<TradeCateBo> list(UserBo userBo) {
    List<TradeCateBo> tradeCateBos = new ArrayList<>();
    try {
      List<UserToTradeCate> userToTradeCates = userToTradeCateMapper.queryUser(userBo.toUser());
      for (UserToTradeCate userToTradeCate : userToTradeCates) { // 遍历所有关系
        if (userToTradeCate.getDeleteAt() == null) { // 关系未删除
          TradeCate tradeCate = new TradeCate();
          tradeCate.setId(userToTradeCate.getTradeCateId()); // 找到tradeCat的详细信息
          tradeCate = tradeCateMapper.queryId(tradeCate);
          if (tradeCate != null && tradeCate.getDeleteAt() == null) { // tradeCat未删除
            TradeCateBo tradeCateBo = new TradeCateBo(tradeCate);
            tradeCateBo.setUserId(userBo.getId());
            tradeCateBos.add(tradeCateBo);
          }
        }
      }
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("查询用户交易种类列表失败");
    }
    return tradeCateBos;
  }

  @Override
  @Transactional
  public void update(TradeCateBo tradeCateBo) {
    // 根据id, 查出类型
    TradeCate tradeCate =  tradeCateMapper.queryId(tradeCateBo.toTradeCate());
    tradeCateBo.setType(tradeCate.getType());
    try {
      switch (tradeCateBo.getType()) {
      case Default:
        // 删除关系
        tradeCateBo.setDeleteAt(LocalDateTime.now());
        UserToTradeCate userToTradeCate = tradeCateBo.toUserToTradeCate();
        userToTradeCateMapper.delete(userToTradeCate);
        // 添加一个类型为extra的新种类
        tradeCateBo.setCreateAt(LocalDateTime.now());
        tradeCateBo.setType(TradeCateType.Extra);
        tradeCate = tradeCateBo.toTradeCate();
        tradeCateMapper.insert(tradeCate);
        // 用户和新种类建立关系
        tradeCateBo.setTradeCateId(tradeCate.getId());
        userToTradeCateMapper.insert(tradeCateBo.toUserToTradeCate());
        break;
      case Extra:
        // 直接更新
        tradeCateBo.setUpdateAt(LocalDateTime.now());
        tradeCateMapper.update(tradeCateBo.toTradeCate());
        break;
      case System:
        throw new Error("TradeCate type is system. don't update");
      }
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("用户交易种类更新失败");
    }
  }

  @Override
  public void delete(TradeCateBo tradeCateBo) {
    try {
      UserToTradeCate userToTradeCate = tradeCateBo.toUserToTradeCate();
      userToTradeCate.setDeleteAt(LocalDateTime.now());
      userToTradeCateMapper.delete(userToTradeCate);
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("用户交易种类删除失败");
    }
  }

  @Override
  public TradeCateBo get(TradeCateBo tradeCateBo) {
    TradeCate tradeCate = null;
    try {
      tradeCate = tradeCateBo.toTradeCate();
      tradeCate = tradeCateMapper.queryId(tradeCate);
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("详情获取失败");
    }
    return new TradeCateBo(tradeCate);
  }
}
