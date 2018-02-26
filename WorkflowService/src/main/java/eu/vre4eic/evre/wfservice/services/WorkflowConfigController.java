/*******************************************************************************
 * Copyright (c) 2017 VRE4EIC Consortium
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
		
		
	}

	 @ApiOperation(value = "Returns the list of REST Web Services", 
		        notes = "A service with a valid identifier can invoke this web service to obtain a list of descriptions of web services", 
		        response = String.class)
	    @RequestMapping(value="/wfservice/getservices", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 public String getServiceDescriptions(@RequestParam(value="evresid") String evresid) {
		
		 return null;
	 }
	    
	    @ApiOperation(value = "Search for Web Service descriptions", 
		        notes = "A service with a valid identifier can invoke this web service to search the catalogue for Web Services descriptions", 
		        response = String.class)
	    @RequestMapping(value="/wfservice/searchservices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public String searchServiceDescriptions(@RequestParam(value="evresid") String evresid, @RequestParam(value="query") String query){

			return null;
		}
	    

}
