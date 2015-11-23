package ufo.primomiglio.backend.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import ufo.primomiglio.backend.security.client.UserContext;
import ufo.primomiglio.common.jwt.JWTService;

@Component
public class UserContextResolver implements HandlerMethodArgumentResolver {

    public static String AUTHORIZATION_HEADER = "Authorization";
    public final Logger logger = LoggerFactory.getLogger(getClass());
    private final JWTService jwtService;

    @Autowired
    public UserContextResolver(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserContext.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        String authHeader = webRequest.getHeader(AUTHORIZATION_HEADER);
        logger.debug("Authorization token is: [{}]", authHeader);

        if (authHeader!=null) {
            try {
                return jwtService.parse(authHeader, UserContext.class);
            } catch (RuntimeException e) {
                logger.error("Not possible to decode authorization token: [{}]", authHeader, e);
            }
        }

        return new UserContext();
    }

}
