package com.zhangrh.account.javaserver.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhangrh.account.javaserver.entity.Account;
import com.zhangrh.account.javaserver.entity.BorrowLend;
import com.zhangrh.account.javaserver.entity.Trade;
import com.zhangrh.account.javaserver.entity.Transfer;
import com.zhangrh.account.javaserver.entity.ViewTradeCateAccount;
import com.zhangrh.account.javaserver.enums.TradeOperation;
import com.zhangrh.account.javaserver.exception.Asserts;
import com.zhangrh.account.javaserver.mapper.AccountMapper;
import com.zhangrh.account.javaserver.mapper.BorrowLendMapper;
import com.zhangrh.account.javaserver.mapper.TradeCateMapper;
import com.zhangrh.account.javaserver.mapper.TradeMapper;
import com.zhangrh.account.javaserver.mapper.TransferMapper;
import com.zhangrh.account.javaserver.mapper.ViewTradeCateAccountMapper;
import com.zhangrh.account.javaserver.service.TradeService;
import com.zhangrh.account.javaserver.service.Bo.AccountBo;
import com.zhangrh.account.javaserver.service.Bo.TradeBo;
import com.zhangrh.account.javaserver.service.Bo.UserBo;
import com.zhangrh.account.javaserver.utils.UserInfoUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TradeServiceImpl implements TradeService {
  private static final Logger LOGGER = LoggerFactory.getLogger(TradeServiceImpl.class);

  @Autowired
  TradeMapper tradeMapper;

  @Autowired
  TradeCateMapper tradeCateMapper;

  @Autowired
  TransferMapper transferMapper;

  @Autowired
  ViewTradeCateAccountMapper viewTradeCateAccountMapper;

  @Autowired
  AccountMapper accountMapper;

  @Autowired
  BorrowLendMapper borrowLendMapper;

  @Override
  public void add(TradeBo tradeBo) {
    try {
      tradeBo.setCreateAt(LocalDateTime.now());
      tradeMapper.insert(tradeBo.toTrade());
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("交易记录添加失败");
    }
  }

  @Override
  @Transactional
  public void transfer(TradeBo outTradeBo, TradeBo inTradeBo) {
    try {
      // outTrade
      outTradeBo.setCreateAt(LocalDateTime.now());
      long outTradeCateId = tradeCateMapper.queryOperate(TradeOperation.Transfer_Out.getCode()).getId();
      outTradeBo.setTradeCateId(outTradeCateId);
      outTradeBo.setOperate(TradeOperation.Transfer_Out);
      Trade outTrade = outTradeBo.toTrade();
      Account outAccount = accountMapper.queryId(outTrade.getAccountId());

      // inTrade
      inTradeBo.setCreateAt(LocalDateTime.now());
      long inTradeCateId = tradeCateMapper.queryOperate(TradeOperation.Transfer_In.getCode()).getId();
      inTradeBo.setTradeCateId(inTradeCateId);
      inTradeBo.setOperate(TradeOperation.Transfer_In);
      Trade inTrade = inTradeBo.toTrade();
      Account inAccount = accountMapper.queryId(inTrade.getAccountId());

      // remark
      String remark = outAccount.getName() + " -> " + inAccount.getName();
      outTrade.setRemark(remark);
      inTrade.setRemark(remark);

      tradeMapper.insert(outTrade);
      tradeMapper.insert(inTrade);

      // transfer
      Transfer transfer = new Transfer();
      transfer.setCreateAt(LocalDateTime.now());
      transfer.setOutTradeId(outTrade.getId());
      transfer.setInTradeId(inTrade.getId());
      transferMapper.insert(transfer);
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("转账交易记录添加失败");
    }
  }

  @Override
  public void update(TradeBo tradeBo) {
    try {
      tradeBo.setUpdateAt(LocalDateTime.now());
      tradeMapper.update(tradeBo.toTrade());
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("交易记录更新失败");
    }
  }

  @Override
  @Transactional
  public void delete(TradeBo tradeBo) {
    try {
      Trade trade = tradeMapper.queryById(tradeBo.toTrade());
      TradeOperation operation = trade.getOperate();
      if (operation == TradeOperation.Expend || operation == TradeOperation.Income) { // 收入 支出
        trade.setDeleteAt(LocalDateTime.now());
        tradeMapper.delete(trade);
      } else if (operation == TradeOperation.Transfer_In || operation == TradeOperation.Transfer_Out) { // 转入 转出
        // 查询转账关联信息
        Transfer transfer = null;
        if (operation == TradeOperation.Transfer_In) {
          transfer = transferMapper.queryByInTradeId(trade.getId());
        } else {
          transfer = transferMapper.queryByOutTradeId(trade.getId());
        }
        // 删除两条记录
        Trade deleTrade = new Trade();
        deleTrade.setDeleteAt(LocalDateTime.now());
        deleTrade.setId(transfer.getInTradeId());
        tradeMapper.delete(deleTrade);
        deleTrade.setId(transfer.getOutTradeId());
        tradeMapper.delete(deleTrade);
        // 删除转账关联信息
        transfer.setDeleteAt(LocalDateTime.now());
        transferMapper.delete(transfer);
      } else if (operation == TradeOperation.Borrow || operation == TradeOperation.Lend) { // 借入 借出
        // TODO: 如果删除 借出借出, 就删除所有相关的还款收款, 并删除下所有关联记录
      } else if (operation == TradeOperation.Repayment || operation == TradeOperation.Receive) { // 还款 收款
        // TODO: 如果删除 还款收款, 就顺带删除下关联记录
      }
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("交易记录删除失败");
    }
  }

  @Override
  public TradeBo query(TradeBo tradeBo) {
    ViewTradeCateAccount viewTradeCateAccount = null;
    try {
      viewTradeCateAccount = viewTradeCateAccountMapper.queryByTradeId(tradeBo.getTradeId());
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("单条交易记录查询失败");
    }
    return new TradeBo(viewTradeCateAccount);
  }

  @Override
  public List<TradeBo> list(UserBo userBo) {
    List<TradeBo> tradeBos = new ArrayList<>();
    try {
      List<ViewTradeCateAccount> trades = viewTradeCateAccountMapper.queryByUserId(userBo.getId());
      for (ViewTradeCateAccount trade : trades) {
        if (trade.getDeleteAt() == null
            && (trade.getOperate() == TradeOperation.Expend || trade.getOperate() == TradeOperation.Income)) {
          tradeBos.add(new TradeBo(trade));
        }
      }
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("交易记录按用户查询失败");
    }
    return tradeBos;
  }

  @Override
  public List<TradeBo> list(AccountBo accountBo) {
    List<TradeBo> tradeBos = new ArrayList<>();
    try {
      List<ViewTradeCateAccount> trades = viewTradeCateAccountMapper.queryByAccountId(accountBo.getId());
      for (ViewTradeCateAccount trade : trades) {
        if (trade.getDeleteAt() == null) {
          tradeBos.add(new TradeBo(trade));
        }
      }
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("交易记录按账户查询失败");
    }
    return tradeBos;
  }

  @Override
  public List<TradeBo> list(TradeOperation tradeOperation) {
    List<TradeBo> tradeBos = new ArrayList<>();
    try {
      List<ViewTradeCateAccount> trades = viewTradeCateAccountMapper.queryByOperation(UserInfoUtil.getUser().getId(), tradeOperation.getCode());
      for (ViewTradeCateAccount trade : trades) {
        if (trade.getDeleteAt() == null) {
          tradeBos.add(new TradeBo(trade));
        }
      }
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("交易记录按账户查询失败");
    }
    return tradeBos;
  }


  @Override
  public Map<String, List<TradeBo>> listSortByDate(TradeOperation tradeOperation) {
    return sortTradeByDate(list(tradeOperation));
  }

  @Override
  public Map<String, List<TradeBo>> listSortByDate(AccountBo accountBo) {
    return sortTradeByDate(list(accountBo));
  }

  @Override
  public Map<String, List<TradeBo>> listSortByDate(UserBo userBo) {
    return sortTradeByDate(list(userBo));
  }

  private Map<String, List<TradeBo>> sortTradeByDate(List<TradeBo> tradeBos) {
    Map<String, List<TradeBo>> result = new HashMap<>();
    for (TradeBo tradeBo : tradeBos) {
      LocalDate spendDate = tradeBo.getSpendDate();
      String dateStr = spendDate.toString();
      List<TradeBo> tradeBoDateList = result.get(dateStr);
      if (tradeBoDateList == null) {
        tradeBoDateList = new ArrayList<>();
        tradeBoDateList.add(tradeBo);
        result.put(dateStr, tradeBoDateList);
      } else {
        tradeBoDateList.add(tradeBo);
      }
    }
    return result;
  }

  @Override
  @Transactional
  public void addBorrowLend(TradeBo tradeBo) {
    try {
      long tradeCateId = tradeCateMapper.queryOperate(tradeBo.getOperate().getCode()).getId();
      tradeBo.setCreateAt(LocalDateTime.now());
      tradeBo.setTradeCateId(tradeCateId);
      tradeMapper.insert(tradeBo.toTrade());
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("借入借出记录添加失败");
    }
  }

  @Override
  @Transactional
  public void addRepaymentReceive(TradeBo tradeBo, long targetTradeId) {
    try {
      long tradeCateId = tradeCateMapper.queryOperate(tradeBo.getOperate().getCode()).getId();
      tradeBo.setCreateAt(LocalDateTime.now());
      tradeBo.setTradeCateId(tradeCateId);
      Trade trade = tradeBo.toTrade();
      tradeMapper.insert(trade);

      // TODO: 应该统计当前目标的金额, 再对比下提供了多少钱, 是否超出, 导致不合理
      BorrowLend borrowLend = new BorrowLend();
      borrowLend.setCreateAt(LocalDateTime.now());
      borrowLend.setBorrowLendTradeId(targetTradeId);
      borrowLend.setRepaymentReceiveTradeId(trade.getId());
      borrowLendMapper.insert(borrowLend);
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("借入借出记录添加失败");
    }
  }

  @Override
  public void updateBorrowLend(TradeBo tradeBo) {
    try {
      long tradeCateId = tradeCateMapper.queryOperate(tradeBo.getOperate().getCode()).getId();
      tradeBo.setTradeCateId(tradeCateId);
      tradeBo.setUpdateAt(LocalDateTime.now());
      tradeMapper.update(tradeBo.toTrade());
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("借入借出记录更新失败");
    }
  }

  @Override
  @Transactional
  public Map<String, List<TradeBo>> listByBorrowLendId(long borrowLendId) {
    List<TradeBo> trades = new ArrayList<>();
    try {
      BorrowLend borrowLend = new BorrowLend();
      borrowLend.setBorrowLendTradeId(borrowLendId);
      List<BorrowLend> list = borrowLendMapper.queryBorrowLend(borrowLend);

      for (BorrowLend borrowLend2 : list) {
        ViewTradeCateAccount trade =  viewTradeCateAccountMapper.queryByTradeId(borrowLend2.getRepaymentReceiveTradeId());
        trades.add(new TradeBo(trade));
      }
    } catch (Exception e) {
      LOGGER.warn(e.getMessage());
      Asserts.fail("查询借入借出记录列表失败");
    }
    return sortTradeByDate(trades);
  }
}
