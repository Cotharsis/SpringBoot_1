package govno.govna.config;


import govno.govna.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
    @EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//        @Autowired
//        private DataSource dataSource;
        @Autowired
        private UserService userService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {//передает обьект
            http
                    .authorizeRequests()//авторизируем
                        .antMatchers("/","/registration").permitAll()//раздрешаем полный доступ
                        .anyRequest().authenticated()//на все остальные мы ребуем авторизацию
                    .and()
                         .formLogin()//вкл формлогин
                        .loginPage("/login")//находитьься на этом мепинге
                        .permitAll()//раздрешаем всем
                    .and()
                        .logout()//вкл логаут
                        .logoutSuccessUrl("/")//чтобы после логаута попасть на главную страницу,
                        .permitAll();//раздрешаем им пользоваться всем
        }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());}



    }
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.jdbcAuthentication()
//                    .dataSource(dataSource)//чтобы менеджер мог входить в бд и искать там пользователя и их роль
//                    .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                    //запорс чтобы система могла найти пользователя по его имени
//                    .usersByUsernameQuery("select username, password, active from person where username=?")
//                    //запос помогает спрингу получить списолк пользователей с их ролями(запорс из таблицы персон
//                    // присоедененной к ней таблице юзер_роль
//                   // соединенные через поля юзер ид и ид  выбираем поля юзернаме и имя роли)
//            .authoritiesByUsernameQuery("select u.username, " +
//                    "ur.roles from person u inner join user_role ur on u.id = ur.user_id where u.username=?");
//        }
        //        @Bean болще не нужен
//        @Override
//        public UserDetailsService userDetailsService() {
//            UserDetails user =
//                    User.withDefaultPasswordEncoder()//нужен толкьо для отладки?
//                            .username("user")
//                            .password("password")
//                            .roles("USER")
//                            .build();
//            return new InMemoryUserDetailsManager(user);//создает в апмяти менеджер который обслуживает запии учетные}


