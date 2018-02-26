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
package eu.vre4eic.evre.wfservice;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.SpringApplication;


import eu.vre4eic.evre.nodeservice.modules.metadata.MDModule;
import eu.vre4eic.evre.nodeservice.modules.monitor.AdvisoryModule;
import eu.vre4eic.evre.nodeservice.nodemanager.ZKServer;

//@Configuration
//@ComponentScan

//@EnableAutoConfiguration
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

public class WfServiceStart {

	
    //private static MDModule mdModule;

	public static void main(String[] args) {
		
		//ZKServer.init();
		
		 
        SpringApplication.run(WfServiceStart.class, args);
       
        AdvisoryModule.getInstance();
        MDModule mdModule= MDModule.getInstance();
        
        
		while (true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mdModule.listToken();
		}

    }
}
