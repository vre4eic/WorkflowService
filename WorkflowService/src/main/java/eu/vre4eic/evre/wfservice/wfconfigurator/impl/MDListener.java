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
package eu.vre4eic.evre.wfservice.wfconfigurator.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.vre4eic.evre.core.messages.AuthenticationMessage;
import eu.vre4eic.evre.core.messages.MetadataMessage;
import eu.vre4eic.evre.nodeservice.modules.metadata.MDModule;
import eu.vre4eic.evre.core.comm.MessageListener;

/**
 * 
 * Class used to receive asynchronous messages related to users authenticated by  the system.
 * The authentication message can represent  Login or Logout operations and contains token which must be used by users for each service invocation.
 * @author francesco
 *
 */
public class MDListener implements MessageListener<MetadataMessage>{
	
	private MDModule module;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	public  MDListener(MDModule mdModule) {
		this.module = mdModule;
	}
	
	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(MetadataMessage message) {

		log.info("##### metadata message arrived #####");
		//log.info("token: " + am.getToken());
		//log.info("time limit: "+ am.getTimeLimit());

		log.info(message.getToken()+" "+message.getMessage());           	


	}



}
