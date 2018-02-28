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

package eu.vre4eic.evre.wfservice.services;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.ApiOperation;



/**
 * This class contains methods for managing users. 
 * @author Cesare
 *
 */

@Controller
public class WorkflowServiceController {

	private static final String RELEASE = "release";
	private static final String WELCOME_PAGE = "welcome";
	Properties property = new Properties();
	public WorkflowServiceController()  {
		super();
		
		InputStream in;
		
		try {
			in = this.getClass().getClassLoader()
					.getResourceAsStream("Settings.properties");
			property.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
	}

	//@JsonIgnore
		@RequestMapping(value="/", method=RequestMethod.GET)
		public String WelcomePage(ModelMap model, HttpSession session) {

			session.setAttribute(RELEASE, property.getProperty("Version_default"));
			
			return WELCOME_PAGE;
		}

}
