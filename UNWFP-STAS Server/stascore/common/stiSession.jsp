<%@page import="com.enterprisehorizons.magma.server.admin.AdminConfigUtils"%>
<%@page import="com.spacetimeinsight.security.bean.JAASConstants"%>
<%@page import="com.spacetimeinsight.security.bean.JAASAuthenticationTypeInitializer"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>

<script>

    // added to handle on browser close scneario to invalidate user session
	//window.onbeforeunload = invalidateSession;
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
		//Extending session
			validateAndExtendSession();	
		<% }else{ %>
			invalidateSession();
			//alert message is hardcoded here to show it in STAD also.
			alert("Your session has expired due to an extended period of inactivity. Any unsaved data would be lost. Please login again to access the requested information.");
			
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
    		timer =  setTimeout('sessionExpire()',sessionAlertTime*1000);
    		//parent.invalidateSession();
    	}
    }
    
    //function added here to invalidate the session in STAD also
    function invalidateSession(){
    	window.location = '<%=ServerUtils.getContextName(request)%>/logout.do?isSessionExpired=yes';
    }
    
    
    //use to trim the string for white space
    String.prototype.trim = function (){
    	return this.replace(/^\s*/, "").replace(/\s*$/, "");
	}

    
    
    //Display user Friendly or developer friendly messages as per the choice made in admin Manage configuration enable debug Alert
    function showAlert(msg) 
    {
    	var enableDebug = <%=AdminConfigUtils.isJSErrorDebugEnabled()%>; //returns boolean
    	var userDefinedMsg = '<%=AdminConfigUtils.getJSUserDefaultErrorMessage()%>'; //returns string
    	var defaultMsg = "There is an error in application, please try your last step as system could not responds.";
    	if(enableDebug)
    	{
    		alert(msg);
    	}
    	else if( userDefinedMsg.trim().length == 0 ) //if no message is defined in manage configuration.
    	{
    		alert(defaultMsg);
    	}
    	else
    	{
    		alert(userDefinedMsg);
    	}
    }
    
</script>