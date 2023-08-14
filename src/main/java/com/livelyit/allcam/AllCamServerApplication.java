package com.livelyit.allcam;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.livelyit.allcam.common.Utils;

//@EnableResourceServer
//@EnableAuthorizationServer
@SpringBootApplication
public class AllCamServerApplication extends SpringBootServletInitializer {
	int fileMaxSize = 20 * 1024 * 1024;
	
	public static void main(String[] args) {
		Utils.getFBA();

		SpringApplicationBuilder builder = new SpringApplicationBuilder(AllCamServerApplication.class);
	    builder.headless(false).run(args);
//		SpringApplication.run(AllCamServerApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(AllCamServerApplication.class);
	}
	
	@Bean
	EmbeddedServletContainerCustomizer containerCustomizer() throws Exception {
		return (ConfigurableEmbeddedServletContainer container) -> {
	          if (container instanceof TomcatEmbeddedServletContainerFactory) {
	              TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
	              tomcat.addConnectorCustomizers(
	                      (connector) -> {
	                    	  connector.setMaxPostSize(fileMaxSize); //파일 크기 20MB
	                      }
	              );
	          }
	      };
	}
}