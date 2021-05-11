package com.zhangrh.account.javaserver.config;

import com.zhangrh.account.javaserver.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserService userService;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }

  // public PasswordEncoder passwordEncoder() {
  //   return new BCryptPasswordEncoder();
  // }

  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // 用户登录信息校验使用自定义 userService
    // 还需要注意密码加密与验证需要使用同一种方式
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
  }

  /**
   * 自定义处理登录处理
   *
   * @param http
   * @throws Exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

      http.authorizeRequests((authorize) -> authorize
              .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // 静态资源，比如 css,js 无需登录鉴权
              .anyRequest().permitAll() // 其他页面需要登录鉴权
      ).formLogin((formLogin) -> formLogin  // 自定义登录页面
              .loginPage("/login") // 登录页
              .loginProcessingUrl("/auth/login") // 自定义登录请求地址
              .permitAll()// 登录页当然无需鉴权了，不然不就套娃了吗？
      ).logout(LogoutConfigurer::permitAll // 登出页面
      ).rememberMe(rememberMe -> rememberMe
              .rememberMeCookieName("test-remember") // 自定义记住我 cookie 名
              .key("test") // 盐值
              .tokenValiditySeconds(3600 * 12)) // 记住我，本地生成 cookie 包含用户信息

      ;
  }
}
