package hello;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	   private static final Logger logger = LoggerFactory
	            .getLogger(WebSecurityConfig.class);

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().fullyAuthenticated()
				.and()
			.formLogin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.ldapAuthentication()
				.userDnPatterns("uid={0},ou=people")
				.groupSearchBase("ou=groups")
				.contextSource(contextSource())
                .passwordCompare()
                	.passwordEncoder(new LdapShaPasswordEncoder())
					.passwordAttribute("userPassword");
	}

    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:9389/"), "dc=springframework,dc=org");
    }
    
//    @Bean
//    public FilterBasedLdapUserSearch filterBasedLdapUserSearch(){
//        return new FilterBasedLdapUserSearch("", //user-search-base
//                "(uid={0})", //user-search-filter
//                contextSource()); //ldapServer
//    }
//    
//    @Bean
//    public LdapAuthoritiesPopulator authoritiesPopulator(UserDetailsService userDetailsService){
//        return new UserDetailsServiceLdapAuthoritiesPopulator(userDetailsService);
//    }
//    @Bean
//    public LdapAuthenticationProvider authenticationProvider(BindAuthenticator ba,
//                                                             LdapAuthoritiesPopulator lap,
//                                                             UserDetailsContextMapper cm){
//        return new LdapAuthenticationProvider(ba, lap){{
//            setUserDetailsContextMapper(cm);
//        }};
//    }
//
//    @Bean
//    public BindAuthenticator bindAuthenticator(FilterBasedLdapUserSearch userSearch){
//        return new BindAuthenticator(contextSource()){{
//            setUserSearch(userSearch);
//
//        }};
//    }
    
 
}