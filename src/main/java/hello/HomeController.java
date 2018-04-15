package hello;

import java.security.Permission;
import java.security.Permissions;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
//    private LdapTemplate template;
//    
//    @Autowired
// 	public void getBean(LdapTemplate template) {
//     	this.template = template;
// 	}

    @GetMapping("/")
    public String index() {
    	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    	
    	SecurityContext context = SecurityContextHolder.getContext();
    	String currentUserName = null;
    	Associate associate = new Associate();
    	Set grantedAuthorities = null;
    	if (!(auth instanceof AnonymousAuthenticationToken)) {
    	    currentUserName = auth.getName();
    	    LdapUserDetailsImpl principal =  (LdapUserDetailsImpl) auth.getCredentials();
    	    associate.setLastName(currentUserName);
    	    grantedAuthorities = new HashSet<>();
    	    for (GrantedAuthority role : auth.getAuthorities())
    	    {
    	        grantedAuthorities.add(role.getAuthority());
    	    }
    	    associate.setRoles(grantedAuthorities);
    	}
    	
    	PermissionsContextMapper pr = new PermissionsContextMapper();
    	
    	
        return "Welcome to the home page!" + currentUserName + " " + grantedAuthorities+ " " + auth;
    }
   
//    @Autowired
//    DirContextOperations ctx;
//    private Associate AssociateContextMapper(DirContextOperations ctx) {
//        Associate associate = new Associate();
//        associate.setFirstName( ctx.getStringAttribute( LdapAttribute.GIVEN_NAME.value ) );
//        associate.setLastName( ctx.getStringAttribute( LdapAttribute.SURNAME.value ) );
//        associate.setEmail( ctx.getStringAttribute( LdapAttribute.EMAIL.value ) );
//        associate.setRole( ctx.getStringAttribute( LdapAttribute.MEMBER_OF.value ) );
//        return associate;
//	}

	@GetMapping("/user")
    public String user(Principal principal) {
       // Get authenticated user name from Principal
       System.out.println(principal.getName());
       return "user";
    }
    
    @GetMapping("/admin")
    public String admin() {
       // Get authenticated user name from SecurityContext
       SecurityContext context = SecurityContextHolder.getContext();
       System.out.println(context.getAuthentication().getName());
       return "admin";
    }

    
    
    private static class PermissionsContextMapper extends AbstractContextMapper<Permissions>
    {
        @Override
        protected Permissions doMapFromContext( DirContextOperations ctx )
        {
            
            Permissions permission = new Permissions();
            
            permission.add((Permission) ctx.getAttributes());
            return permission;
        }
        
    }   
    
}
