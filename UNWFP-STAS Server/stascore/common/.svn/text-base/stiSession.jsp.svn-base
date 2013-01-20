<%@page import="com.enterprisehorizons.magma.server.admin.AdminConfigUtils"%>
<%@page import="com.spacetimeinsight.security.bean.JAASConstants"%>
<%@page import="com.spacetimeinsight.security.bean.JAASAuthenticationTypeInitializer"%>

<script>
	var timer = 0;
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
		<% if (JAASConstants.KRB_LDAP_DATA_SOURCE.equals(JAASAuthenticationTypeInitializer.getInstance().getJAASAuthenticationType())) {%>
		// Extending session
			validateAndExtendSession();	
		<% }else{ %>
		// Invalidating session
			alert(parent.SESSION_EXPIRE_ALERT);
			parent.invalidateSession();
		<% } %>
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