// package com.zhangrh.account.javaserver.mapper;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.math.BigDecimal;
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.util.List;

// import com.zhangrh.account.javaserver.entity.Trade;
// import com.zhangrh.account.javaserver.entity.User;
// import com.zhangrh.account.javaserver.enums.TradeOperation;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest
// public class TradeMapperTest {
  
//   @Autowired
//   TradeMapper tradeMapper;

//   @Test
//   void testInsert() {
//     Trade trade = new Trade();
//     trade.setUserId(1);
//     trade.setAccountId(1);
//     trade.setTradeCateId(1);
//     trade.setMoney(new BigDecimal(10));
//     trade.setRemark("一条支出备注");
//     trade.setSpendDate(LocalDate.now());
//     trade.setOperate(TradeOperation.Expend);
//     trade.setCreateAt(LocalDateTime.now());
//     int r = tradeMapper.insert(trade);
//     assertEquals(1, r);
//   }

//   @Test
//   void testUpdate() {
//     Trade trade = new Trade();
//     trade.setId(2);
//     trade.setUserId(1);
//     trade.setAccountId(2);
//     trade.setTradeCateId(2);
//     trade.setMoney(new BigDecimal(10));
//     trade.setRemark("一条支出备注update");
//     trade.setSpendDate(LocalDate.of(2021, 02, 11));
//     trade.setOperate(TradeOperation.Income);
//     trade.setUpdateAt(LocalDateTime.now());
//     int r = tradeMapper.update(trade);
//     assertEquals(1, r);
//   }

//   @Test
//   void testQueryUser() {
//     User user = new User();
//     user.setId(1);
//     List<Trade> result = tradeMapper.query(user);
//     assertEquals(2, result.size());
//   }

//   @Test
//   void testDelete() {
//     Trade trade = new Trade();
//     trade.setId(1);
//     trade.setDeleteAt(LocalDateTime.now());
//     int r = tradeMapper.delete(trade);
//     assertEquals(1, r);
//   }
  
// }
