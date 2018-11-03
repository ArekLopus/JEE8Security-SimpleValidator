package simple_validator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;

@ApplicationScoped
public class SimpleValidator {
	
	@Inject
	IdentityStoreHandler ish;
	
	public CredentialValidationResult validateRequest(String name, String password) {
		
        CredentialValidationResult result = ish.validate(new UsernamePasswordCredential(name, password));
        return result;
        
    }
    

}
