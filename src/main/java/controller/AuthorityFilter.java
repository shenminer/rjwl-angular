package controller;

import domain.User;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.File;
import java.io.IOException;

/**
 * Created by hhx on 2017/4/2.
 */
@Provider
public class AuthorityFilter implements ContainerRequestFilter {
    private final static String TEACHER = "teacher";
    private final static String STUDENT = "student";
    private final static String ADMIN = "admin";
    private final static String TOKEN = "token";
    @Context
    private HttpServletRequest request;
    @Autowired
    private IUserService userService;
    @Autowired
    private Cache userCache;
    /*@Autowired
    private Cache accessCache;*/

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getAbsolutePath().getPath();
        String token = request.getHeader(TOKEN);
       /* if (accessCache.get(token) != null) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity("rate limited").build());
            return;
        }*/
        Element element = userCache.get(token);
        User user = null;
        if (element == null) {
            user = userService.getByWeChatId(token);
            userCache.put(new Element(token, user));
        } else {
            user = (User) element.getObjectValue();
        }

        if (path.contains(ADMIN)) {
            if (user == null || !user.getLevel().equals("1")) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                        .entity("forbid").build());
            }
        } else if (path.contains(TEACHER)) {
            if (user == null || !user.getRole().equals(TEACHER)) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                        .entity("forbid").build());
            }
        } else if (path.contains(STUDENT)) {
            if (user == null) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                        .entity("forbid").build());
            }
        }
    }
}
