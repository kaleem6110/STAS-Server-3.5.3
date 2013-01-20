<%@page import="com.spacetimeinsight.security.bean.JAASConstants"%>
<%@page import="com.spacetimeinsight.security.bean.JAASAuthenticationTypeInitializer"%>
<%@ taglib prefix="logic" uri="/tags/struts-logic" %>
<%
//System.out.println("Type: "+JAASConstants.JAAS_AUTHENTICATION_TYPE);
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
String userAgent = request.getHeader("user-agent");
if(userAgent.contains("Windows")){   
   if (JAASConstants.KRB_LDAP_DATA_SOURCE.equals(JAASAuthenticationTypeInitializer.getInstance().getJAASAuthenticationType())) { %>
                <logic:redirect action="/ssoLoginAction" />
        <%  } else { %>
                <logic:redirect action="/welcome" />
        <%  }
    }
    else { %>   
            <jsp:forward page="mobile/loginAction.do"/>
      <% } %>
