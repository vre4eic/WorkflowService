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

package eu.vre4eic.evre.wfservice.wfconfigurator.impl;

import eu.vre4eic.evre.core.Common;
import eu.vre4eic.evre.core.comm.NodeLinker;
import eu.vre4eic.evre.core.messages.Message;
import eu.vre4eic.evre.core.messages.impl.MessageImpl;
import eu.vre4eic.evre.nodeservice.modules.authentication.AuthModule;

public class WorkflowConfiguratorImpl {
	private static AuthModule module;
	
	public WorkflowConfiguratorImpl(){
		NodeLinker node = NodeLinker.init("v4e-lab.isti.cnr.it:2181");
		String brokerURL =  node.getMessageBrokerURL();

		module = AuthModule.getInstance(brokerURL);

	}
	
	public Message getServiceDescriptions(String token){
		if (module.checkToken(token))
			return( new MessageImpl("Error, please contact server admin", Common.ResponseStatus.FAILED));
		
		return null;
	}

}
