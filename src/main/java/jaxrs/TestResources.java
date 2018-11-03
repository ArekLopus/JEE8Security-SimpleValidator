package jaxrs;

import java.security.Principal;

import javax.inject.Inject;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import simple_validator.SimpleValidator;

//http://localhost:8080/JEE8Security-SimpleValidator/res/sec/valid1
@Path("sec")
@Produces(MediaType.TEXT_HTML)
public class TestResources {
	
	@Inject
	SimpleValidator val;
	
	@Path("valid1")
	@GET
	public String test() {
		CredentialValidationResult result = val.validateRequest("aa", "aa");
		return info(result);
	}
	
	@Path("valid2")
	@GET
	public String test1() {
		CredentialValidationResult result = val.validateRequest("bb", "bb");
		return info(result);
	}
	
	@Path("valid3")
	@GET
	public String test2() {
		CredentialValidationResult result = val.validateRequest("bbb", "bb");
		return info(result);
	}
	
	
	private String info(CredentialValidationResult result) {
		String info;
		Principal principal = result.getCallerPrincipal();
		if(principal != null) {
			info = "<br/>Principal not null, name: " + principal.getName()
				+"<br/>is caller in role 'admin' -> " + result.getCallerGroups().contains("admin")
				+"<br/>is caller in role 'user' -> " + result.getCallerGroups().contains("user");
			return info;
		} else {
			info = "<br/>Principal IS NULL";
			return info;
		}
	}
	
	
//	private void credInfo(CredentialValidationResult result) {
//		System.out.println(result.getStatus());						//VALID
//		System.out.println(result.getCallerDn());					//null
//		System.out.println(result.getCallerUniqueId());				//null
//		System.out.println(result.getIdentityStoreId());			//null
//		System.out.println(result.getCallerGroups());				//[admin, user]
//		System.out.println(result.getCallerPrincipal());			//[[javax.security.enterprise.CallerPrincipal@60a43c]]
//		System.out.println(result.getCallerPrincipal().getName());	//aa
//	}
	
}
