package controller;

import domain.User;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * Created by hhx on 2017/4/2.
 */
public abstract class BaseCtrl {
    @Autowired
    private Cache userCache;
    @Autowired
    private IUserService userService;
    @Context
    private HttpHeaders httpHeaders;
    @Context
    private HttpServletRequest request;

    private final static String TOKEN = "token";


    protected String getToken() {
        return httpHeaders.getHeaderString(TOKEN);
    }

    protected User getUser() {
        return getUser(getToken());
    }

    protected User getUser(String token) {
        Element element = userCache.get(token);
        if (element == null) {
            return userService.getByWeChatId(token);
        } else {
            return (User) element.getObjectValue();
        }

    }
}
