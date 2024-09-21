package server.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import server.user.model.FleetUser;

import java.security.Principal;

public class ProjectUtil {
    public static int DEFAULT_PAGE_SIZE=10;
    public static int DEFAULT_PAGE_NUMBER=0;

    public static FleetUser getUser(Principal principal) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken)principal;
        FleetUser user = (FleetUser) usernamePasswordAuthenticationToken.getDetails();
        return user;
    }
}
