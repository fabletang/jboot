package org.lenic.jboot.servlet;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Component
	public static class CustomizationBean implements EmbeddedServletContainerCustomizer {
		@Override
		public void customize(ConfigurableEmbeddedServletContainer container) {
			container.setPort(9000);
			container.setContextPath("/ctx");
			container.setSessionTimeout(30, TimeUnit.MINUTES);
		}
	}

	@Bean(name = "characterEncodingFilter")
	public FilterRegistrationBean characterEncodingFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.addInitParameter("encoding", "UTF-8");
		bean.addInitParameter("forceEncoding", "true");
		bean.setFilter(new CharacterEncodingFilter());
		bean.addUrlPatterns("/*");
		return bean;
	}

	@Bean(name = "indexServlet")
	public ServletRegistrationBean indexServlet() {
		ServletRegistrationBean bean = new ServletRegistrationBean();
		bean.setServlet(new IndexServlet());
		bean.addUrlMappings("/index");
		return bean;
	}

	public static class IndexServlet extends HttpServlet {
		private static final long	serialVersionUID	= -8944347685885474860L;

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doPost(req, resp);
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.getWriter().write("hi, spring-boot, this is a servlet");
			resp.getWriter().write("req.getCharacterEncoding() = " + req.getCharacterEncoding());
			resp.getWriter().write("resp.getCharacterEncoding() = " + resp.getCharacterEncoding());
			resp.getWriter().flush();
		}
	}
}