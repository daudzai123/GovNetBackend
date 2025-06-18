//package com.project.DocumentMIS.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class CustomAccessDeniedHandler implements AuthenticationEntryPoint {
//    private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
//            throws IOException, ServletException {
//        logger.error("Unauthorized error: {}", authException.getMessage());
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        final Map<String, Object> body = new HashMap<>();
//        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
//        body.put("error", "Unauthorized");
//        body.put("message", authException.getMessage());
//        body.put("path", request.getServletPath());
//        final ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(response.getOutputStream(), body);
//
//
//    }
//}


// package com.project.DocumentMIS.ExceptionHandlingFiles;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.access.AccessDeniedException;
// import org.springframework.security.web.AuthenticationEntryPoint;
// import org.springframework.security.web.access.AccessDeniedHandler;
// import org.springframework.stereotype.Component;
// import org.springframework.web.servlet.HandlerExceptionResolver;
// import java.io.IOException;

// @Component
// public class CustomAccessDeniedHandler implements AuthenticationEntryPoint, AccessDeniedHandler {
    
//     private final HandlerExceptionResolver resolver;

//     @Autowired
//     public CustomAccessDeniedHandler(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver){
//         this.resolver= resolver;
//     }

//     @Override
//     public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
//             throws IOException {
//        resolver.resolveException(request, response, null, authException);
//     }

//     @Override
//     public void handle(final HttpServletRequest request, final HttpServletResponse response,
//             final AccessDeniedException exception) {
//         resolver.resolveException(request, response, null, exception);
//     }
    
// }


