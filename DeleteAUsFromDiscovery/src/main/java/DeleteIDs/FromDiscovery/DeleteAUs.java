package DeleteIDs.FromDiscovery;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import com.ibm.watson.developer_cloud.discovery.v1.Discovery;
import com.ibm.watson.developer_cloud.discovery.v1.model.document.DeleteDocumentRequest;
import com.ibm.watson.developer_cloud.discovery.v1.model.document.DeleteDocumentResponse;

public class DeleteAUs {

	public static void main(String[] args) {
		
		deleteDocumentInDiscovery();
	}

	private static void deleteDocumentInDiscovery() {
		
		String url= "";
		String username ="";
		String pswd = "";
		String eid= "";
		String cid = "";
		
		JSONObject credentials = getDiscoveryCredentials(url, username, pswd, eid, cid);
		String urlDiscovery = (String) credentials.get("url");
		String usernameDiscovery = (String) credentials.get("username");
		String pswdDiscovery = (String) credentials.get("pswd");
		String environmentId = (String) credentials.get("eid");
		String collectionId = (String) credentials.get("cid");
		
		Discovery discovery = new Discovery("2017-11-07");
		discovery.setEndPoint(urlDiscovery);
		discovery.setUsernameAndPassword(usernameDiscovery,pswdDiscovery);
	
		String csvContent = null;
		String fileName = ServiceCredentials.filePath;
		
		try {
			csvContent = FileUtils.readFileToString(new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] a = csvContent.split(",");
		
		for(int i = 0; i<=a.length;i++) {
		
			String documentId = a[i];
		//	String documentId = "78150ab8-c6c3-4a84-b9b5-23d9a3978ae5";
			
			DeleteDocumentRequest deleteRequest = new DeleteDocumentRequest.Builder(environmentId, collectionId, documentId).build();
			DeleteDocumentResponse deleteResponse = discovery.deleteDocument(deleteRequest).execute();
			
			System.out.println("Deleted ID from Discovery....."+deleteResponse.toString());
			System.out.println("Deleted Count: "+(i+1));
			
			
			}
	}

	@SuppressWarnings("unchecked")
	public static JSONObject getDiscoveryCredentials(String url, String username, String pswd,String eid, String cid){
		url = "https://gateway.watsonplatform.net/discovery/api";
		eid = "3b9303b4-0bf4-47d0-a195-4690bb560348";
		cid = "43d00f09-7d42-4ae0-96d4-cdf4d7394c5a";
		username = "2c55eec3-0199-43f5-9111-169e566dd2df";
		pswd = "byGVVbVPkU0g";
		
		JSONObject reply = new JSONObject();
			reply.put("url", url);
			reply.put("eid", eid);
			reply.put("cid", cid);
			reply.put("username", username);
			reply.put("pswd", pswd);
		
		return reply;
	}

}
