package com.zhangrh.account.javaserver.service.Bo;

import com.zhangrh.account.javaserver.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBo extends AbstractBo{
  private long id;
  private String email;
  private String password;

  public UserBo() {
  }

  public UserBo(long id) {
    this.id = id;
  }

  public UserBo(User user) {
    setId(user.getId());
    setEmail(user.getEmail());
    setPassword(user.getPassword());
  }

  public User toUser() {
    User user = new User();
    user.setId(this.getId());
    user.setEmail(this.getEmail());
    user.setPassword(this.getEmail());
    user.setCreateAt(this.getCreateAt());
    user.setUpdateAt(this.getUpdateAt());
    user.setUpdateAt(this.getDeleteAt());
    return user;
  }
}
