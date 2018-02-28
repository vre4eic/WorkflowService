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

import org.springframework.http.MediaType;
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

@RestController
public class WorkflowConfigController {

	Properties property = new Properties();
	public WorkflowConfigController()  {
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

	 @ApiOperation(value = "Returns the list of  Web Service descriptions", 
		        notes = "A client with a valid identifier can invoke this web service to obtain a list of descriptions of Web Services stored in e-VRE catalogue", 
		        response = String.class)
	    @RequestMapping(value="/wfservice/getservicedescs", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 public String getServiceDescriptions(@RequestParam(value="evresid") String evresid) {
		
		 return null;
	 }
	    
	    @ApiOperation(value = "Search for Web Service descriptions", 
		        notes = "A client with a valid identifier can invoke this web service to search the e-VRE catalogue for Web Services descriptions", 
		        response = String.class)
	    @RequestMapping(value="/wfservice/searchservicedescs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public String searchServiceDescriptions(@RequestParam(value="evresid") String evresid, @RequestParam(value="query") String query){

			return null;
		}
	    
	    @ApiOperation(value = "Save a Workflow descriptions", 
		        notes = "A client with a valid identifier can invoke this web service to save the description of a Workflow in the e-VRE Catalogue", 
		        response = String.class)
	    @RequestMapping(value="/wfservice/savewfdesc", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public String saveWfDescriptions(@RequestParam(value="evresid") String evresid, @RequestParam(value="query") String description){

			return null;
		}
	    

}
