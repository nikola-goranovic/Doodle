package com.tx.doodle.utils;

import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.test.web.servlet.setup.MockMvcConfigurer;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

public class MockMvcUtils {

	private MockMvcUtils() {
	}

	public static MockMvcConfigurer defaultSecurity() {
		SecurityFilterChain security = new DefaultSecurityFilterChain(AnyRequestMatcher.INSTANCE,
				(request, response, chain) -> chain.doFilter(request, response));
		return springSecurity(new FilterChainProxy(security));
	}
}
