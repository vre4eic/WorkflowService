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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import eu.vre4eic.evre.core.Common;
import eu.vre4eic.evre.core.Common.MetadataOperationType;
import eu.vre4eic.evre.core.comm.NodeLinker;
import eu.vre4eic.evre.core.comm.Publisher;
import eu.vre4eic.evre.core.comm.PublisherFactory;
import eu.vre4eic.evre.core.comm.Subscriber;
import eu.vre4eic.evre.core.comm.SubscriberFactory;
import eu.vre4eic.evre.core.messages.AuthenticationMessage;
import eu.vre4eic.evre.core.messages.Message;
import eu.vre4eic.evre.core.messages.MetadataMessage;
import eu.vre4eic.evre.core.messages.MultiFactorMessage;
import eu.vre4eic.evre.core.messages.impl.MessageImpl;
import eu.vre4eic.evre.core.messages.impl.MetadataMessageImpl;
import eu.vre4eic.evre.nodeservice.modules.authentication.AuthModule;
import eu.vre4eic.evre.nodeservice.modules.metadata.MDModule;


public class WorkflowConfiguratorImpl {
	private static AuthModule module;
	private static MDModule mdModule;
	
	public WorkflowConfiguratorImpl(){
		NodeLinker node = NodeLinker.init("v4e-lab.isti.cnr.it:2181");
		String brokerURL =  node.getMessageBrokerURL();

		
		module = AuthModule.getInstance(brokerURL);
		mdModule = MDModule.getInstance(brokerURL);
	    Subscriber<MetadataMessage> subscriber = SubscriberFactory.getMetadataSubscriber();
		//subscriber.setListener(new MDListener(this));

	}
	
	public MetadataMessageImpl getServiceDescriptions(String id, String token){
		JSONArray resultObject= new JSONArray();
		MetadataMessageImpl mmi=new MetadataMessageImpl("Operation completed!", Common.ResponseStatus.SUCCEED);
		
		URL myUrl;
     	//String myQuery="select%20distinct%20%3FservName%20%3FservMedium%20%3FservUri%20%3FservDescr%20GROUP_CONCAT(%3FservKeywords%20%3B%20separator%3D%22%2C%20%22)%20as%20%3FservParams%20(%3Fserv%20as%20%3Furi)%20from%20%3Chttp%3A%2F%2Fprofile%2Fcesare%3E%20where%20%7B%0A%3Fserv%20a%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23WebService%3E.%0A%3Fserv%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23has_URI%3E%20%20%20%20%20%20%20%20%20%3FservUri%20.%0A%3Fserv%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23has_description%3E%20%3FservDescr%20.%0A%3Fserv%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23has_keywords%3E%20%20%20%20%3FservKeywords%20.%0A%3Fserv%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23has_name%3E%20%20%20%20%20%20%20%20%3FservName%20.%0A%3Fserv%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23is_source_of%3E%20%3FservmediumLE%20.%0A%3FservmediumLE%20%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23has_destination%3E%20%3FservMedium.%0A%3FservMedium%20a%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23Medium%3E.%20%0A%7D%0A";
		//String myQuery="select%20distinct%20%3FservName%20%3FservMedium%20%3FservUri%20%3FservDescr%20GROUP_CONCAT(%3FservKeywords%20%3B%20separator%3D%22%2C%20%22)%20as%20%3FservParams%20(%3Fserv%20as%20%3Furi)%20from%20%3Chttp%3A%2F%2Fprofile%2F"+id+"%3E%20where%20%7B%0A%3Fserv%20a%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23WebService%3E.%0A%3Fserv%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23has_URI%3E%20%20%20%20%20%20%20%20%20%3FservUri%20.%0A%3Fserv%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23has_description%3E%20%3FservDescr%20.%0A%3Fserv%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23has_keywords%3E%20%20%20%20%3FservKeywords%20.%0A%3Fserv%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23has_name%3E%20%20%20%20%20%20%20%20%3FservName%20.%0A%3Fserv%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23is_source_of%3E%20%3FservmediumLE%20.%0A%3FservmediumLE%20%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23has_destination%3E%20%3FservMedium.%0A%3FservMedium%20a%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23Medium%3E.%20%0A%7D%0A";
        String myQuery="select%20distinct%20%3FservName%20%3FservMedium%20%3FservUri%20%3FservDescr%20(%3Fserv%20as%20%3Furi)%20from%20%3Chttp%3A%2F%2Fprofile%2F"+id+"%3E%20where%20%7B%0A%3Fserv%20a%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23WebService%3E.%0A%3Fserv%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23has_URI%3E%20%20%20%20%20%20%20%20%20%3FservUri%20.%0A%3Fserv%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23has_description%3E%20%3FservDescr%20.%0A%3Fserv%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23has_name%3E%20%20%20%20%20%20%20%20%3FservName%20.%0A%3Fserv%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23is_source_of%3E%20%3FservmediumLE%20.%0A%3FservmediumLE%20%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23has_destination%3E%20%3FservMedium.%0A%3FservMedium%20a%20%3Chttp%3A%2F%2Feurocris.org%2Fontology%2Fcerif%23Medium%3E.%20%0A%7D";
		try {
			mmi.setOperation(MetadataOperationType.QUERY);
			mmi.setToken(token);
			if (!module.checkToken(token)){
				mmi.setStatus(Common.ResponseStatus.FAILED);
				mmi.setMessage("Error, please contact server admin");
				mmi.setJsonMessage(new JSONObject().put("services", resultObject));
				return mmi;
			}
		//	myUrl = new URL("https://v4e-lab.isti.cnr.it:2443/rest-admin/v1/entity/identity/userName/fava?credentialRequirement=");
			myUrl = new URL("http://139.91.183.97:8080/EVREMetadataServices-1.0-SNAPSHOT/query/virtuoso?format=application/json&query="+ myQuery+"&token="+token);
			//myUrl = new URL("http://139.91.183.97:8099/EVREMetadataServices-1.0-SNAPSHOT/query/virtuoso?query="+myQuery+"&token="+token);

			HttpURLConnection conn = (HttpURLConnection) myUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			InputStream inputStream = conn.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String line = "";
			
			String jsonres="";
			JSONObject obj=null;
			
			while ((line = bufferedReader.readLine()) != null) {
				if (!line.trim().isEmpty()){
					jsonres=jsonres+line;
					//System.out.println("pp "+line);
					
					
				}
			}
			if (!jsonres.trim().isEmpty()){
				obj = new JSONObject(jsonres);
				JSONObject results=obj.getJSONObject("results");
				
				System.out.println("results "+results.toString());
				JSONArray bindings=results.getJSONArray("bindings");
				System.out.println("bindings "+bindings.toString());
				for (int i=0; i<bindings.length();i++){
					JSONObject wadl=bindings.getJSONObject(i);
					//System.out.println("servName "+wadl.getJSONObject("servName"));
					//System.out.println("servMedium "+wadl.getJSONObject("servMedium"));
					//JSONArray item=new JSONArray();
				    JSONObject el1=new JSONObject();
				   // JSONObject el2=new JSONObject();
				    
				    el1.put("name", wadl.getJSONObject("servName").get("value"));
				    el1.put("ref", wadl.getJSONObject("servMedium").get("value"));
					//item.put(el1);
					//item.put(el2);
					resultObject.put(el1);
				}
				
				
				mmi.setJsonMessage(new JSONObject().put("services", resultObject));
				System.out.println("res "+mmi.getJsonMessage().toString());
			};
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//conn.setRequestProperty("Authorization", "Basic "+encoding );



		
		return mmi;
	}
	public MetadataMessageImpl saveWfDescriptions(String userId, String description, String token){
		System.out.println (description);
		MetadataMessageImpl mmi=new MetadataMessageImpl("Workflow created!", Common.ResponseStatus.SUCCEED);
		try{
		mmi.setOperation(MetadataOperationType.INSERT);
		mmi.setToken(token);
		if (!module.checkToken(token)){
			mmi.setStatus(Common.ResponseStatus.FAILED);
			mmi.setMessage("Error, please contact server admin");
			mmi.setJsonMessage(new JSONObject().put("workflow_description", ""));
			

			return mmi;
		}
		mmi.setJsonMessage(new JSONObject().put("workflow_description", description));
		}
		catch (Exception e){
			e.printStackTrace();
		}
		Publisher<MetadataMessage> p =  PublisherFactory.getMetatdaPublisher();
		p.publish(mmi);
		
		return mmi;
	}

}
