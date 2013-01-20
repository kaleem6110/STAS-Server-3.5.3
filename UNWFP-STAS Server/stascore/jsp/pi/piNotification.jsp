<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ page import="com.enterprisehorizons.magma.config.beans.SecurityRoleBean" %>
<%@ page import="com.enterprisehorizons.magma.ecoweb.constants.IWebSessionContants"%>
<%@ page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@ page import="com.spacetimeinsight.security.bean.UserBean"%>
<%@page import="com.enterprisehorizons.magma.server.admin.AdminConfigUtils"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@ include file="/common/style.jsp" %> 
<script type="text/javascript" src="js/flexSessionTimeOut.js"></script>
<%--<%

    String appTheme = AdminConfigUtils.getApplicationTheme();
    if (appTheme == null || appTheme.equals("null")) {
        appTheme = "default";
    }
%>--%>

<html>
<head>
<%

    Object obj = request.getSession(false).getAttribute("stiUser");
    if(obj == null){ 
        response.sendRedirect(ServerUtils.getContextName(request)+"/");
        return;
    }   
    String serverUrl = ServerUtils.getServerContextBaseUrl(request);
    UserBean user = (UserBean)session.getAttribute(ServerUtils.USER_BEAN_NAME);
    String swfParams = "?serverUrl="+serverUrl+"&domainId="+user.getDomainId()+"&languageId="+user.getLanguageId()+"&theme="+appTheme;
%>
<script type="text/javascript" src="<%=serverUrl %>js/ge/flash_detect.js"></script>

<script language="javascript">
 var timer = 0;
 if(!FlashDetect.installed){
        alert("<bean:message key="flash.required" bundle="admin" />");       
    }
    function goBack(){
        clearTimer();
        window.location='<%=ServerUtils.getContextName(request)%>/pi.do';
    }
    function goHome(){
        clearTimer();
        window.location ='<%=ServerUtils.getContextName(request)%>/adminMain.do'
    }

     function sessionDetails(){
                
           var sessionMax = "<%=session.getMaxInactiveInterval()%>";
           var sessionAlert = "<%=AdminConfigUtils.getSessionAlert()%>";
           var sessionAlertTime = "<%=AdminConfigUtils.getSessionAlertTime()%>";
           var sessionMaxDiff = (sessionMax- sessionAlertTime);
           if(sessionAlert == 'true'){
                
                  clearTimeout(timer); 
                  timer = 0 ;
                  timer =  setTimeout("promptUser("+sessionAlertTime+")",sessionMaxDiff*1000); 
              }else{
                  clearTimeout(timer); 
                  timer = 0 ;
                  timer =  setTimeout("sessionExpire()",sessionMax*1000); 
                  }
                  
             
        }

     function sessionExpire(){
          alert(parent.SESSION_EXPIRE_ALERT);
         parent.invalidateSession();              
      }

     
   function clearTimer(){
       clearTimeout(timer); 
       timer = 0 ;

       } 
       
     function promptUser(sessionAlertTime){ 
            var message = confirm('Your login session will expire in ' +sessionAlertTime+ ' seconds.Would you like to extend the session with server?');
          if (message){ 
                 validateAndExtendSession();
                 sessionDetails();                       
           }else{
               parent.invalidateSession();
         }
 }   
    
</script>
</head>
<body style="margin:0px">
    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
            id="PINotifications" width="101.2%" height="100%">
            <param name="movie" value="swf/admin/PINotifications.swf<%=swfParams%>" />
            <param name="quality" value="high" />
            <param name="bgcolor" value="#FFFFFF" />
            <param name="allowScriptAccess" value="sameDomain" />
            <embed src="swf/admin/PINotifications.swf<%=swfParams%>" quality="high" bgcolor="#FFFFFF"
                width="100%" height="100%" name="PINotifications" align="middle"
                play="true"
                loop="false"
                quality="high"
                allowScriptAccess="sameDomain"
                type="application/x-shockwave-flash"
                pluginspage="http://www.adobe.com/go/getflashplayer">
            </embed>
    </object>
</body>
</html>