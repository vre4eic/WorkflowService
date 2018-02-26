/*******************************************************************************
 * Copyright (c) 2018 VRE4EIC Consortium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package eu.vre4eic.evre.wfservice;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import static com.google.common.base.Predicates.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
//@SpringBootApplication
@EnableSwagger2
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SwaggerConfig {    
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Bean
	public Docket api() { 
		return new Docket(DocumentationType.SWAGGER_2) 
				.groupName("workflowservice-api")
				.apiInfo(apiInfo())
				.select()   
				//.apis(!(RequestHandlerSelectors.withClassAnnotation(JsonIgnore.class))
				.apis(RequestHandlerSelectors.any())              
				//.paths(PathSelectors.any()) 
				//.paths(PathSelectors.regex("/user.*"))
				.paths(paths())
				.build()
				.pathMapping("/");                                           
	}

	private Predicate<String> paths() {
		//return or(PathSelectors.regex("/wfservice.*"),
		//		PathSelectors.regex("/wfservice.*"));
		return (PathSelectors.regex("/wfservice.*"));
	}
	private ApiInfo apiInfo() {
		InputStream in;
		Properties property = new Properties();
		try {
			in = this.getClass().getClassLoader()
					.getResourceAsStream("Settings.properties");
			property.load(in);
			in.close();
		} catch (FileNotFoundException e) {

			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return new ApiInfoBuilder()
				.title("e-VRE WorkflowService")
				.description("This page shows the Web Services entry points for the e-VRE WorkflowService building block.")
				.version(property.getProperty("Version_default"))
				.termsOfServiceUrl("http://terms-of-services.evre")
				.license("Apache License, Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.build();
	}
}