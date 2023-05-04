
package com.company.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private ResourceServerTokenServices tokenServices;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId("resumeapi")
                .tokenServices(tokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .and()
                .authorizeRequests().antMatchers("/admins").hasAnyAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/admin").permitAll()
                .and()
                .authorizeRequests().antMatchers("/doctors").hasAnyAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/doctor").hasAnyAuthority("USER","ADMIN")
                .and()
                .authorizeRequests().antMatchers("/patients").hasAnyAuthority("USER","ADMIN")
                .and()
                .authorizeRequests().antMatchers("/patient").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/actuator/**", "/api-docs/**").permitAll()
                .antMatchers("/**" ).authenticated();
    }
}
