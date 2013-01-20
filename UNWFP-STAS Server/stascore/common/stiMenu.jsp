<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils,com.spacetimeinsight.security.bean.JAASConstants"%>
<%@page import="com.spacetimeinsight.security.bean.JAASAuthenticationTypeInitializer"%>
<% 
int i=0;
 
%> 
<% try { %>

	<logic:present name="stiUser" >
		<div class="mattblacktabs">
		<ul>
		<logic:iterate id="stiModulesList" name="stiUser"  property="modules" >
		 <logic:match name="stiModulesList" property="mobileSpecific" value="false">
		 <logic:notEmpty name="stiModulesList" property="url">
			<logic:match name="stiModulesList" property="url" value="myaction">
				 <logic:equal name="rasEnabled" value="true">
						<li id='<%=i %>' >
							<span id="<bean:write name="stiModulesList" property="name" />" onmouseover="changeStyle('<%=i %>')" onmouseout="resetStyle('<%=i %>')"  onclick="return loadToIFrameUrl('<bean:write name="stiModulesList" property="url"  />', '<%=i %>', 'true')" >
								<bean:write name="stiModulesList" property="name" />
							</span>
						</li>
				 </logic:equal>
			</logic:match>
			<logic:notMatch name="stiModulesList" property="url" value="myaction">
                   <li id='<%=i %>' >
                                <span id="<bean:write name="stiModulesList" property="name" />"  onmouseover="changeStyle('<%=i %>')" onmouseout="resetStyle('<%=i %>')" onclick="return loadToIFrameUrl('<bean:write name="stiModulesList" property="url"  />', '<%=i %>', 'true')" >
                                    <bean:write name="stiModulesList" property="name" />
                                </span>
                    </li>
            </logic:notMatch>
		</logic:notEmpty>
			<% if(i == 0){ %>
				<input type="hidden" id="menu2Load" name="menu2Load" value="<bean:write name="stiModulesList" property="url"  />" />
			<%	} %>
			<% i++;%>
			</logic:match>
		</logic:iterate>	
		<!--<li id="pwd">
				<span onmouseover="changeStyle('pwd')" onmouseout="resetStyle('pwd')" onclick="return loadToIFrameUrl('<%=ServerUtils.getContextName(request)%>/security/changePassword.jsp', 'pwd')" >
					Change Password
				</span>
		</li>-->	
			  <% if (!JAASConstants.KRB_LDAP_DATA_SOURCE.equals(JAASAuthenticationTypeInitializer.getInstance().getJAASAuthenticationType())) {%>
				<li id="logout">
				<span onclick="resetLogout();" onmouseover="changeStyle('logout')" onmouseout="resetStyle('logout')">
				<bean:message key='admin.menu.logout' bundle='admin'/>
				</span>
				</li>
				
				<% } %>	
	</ul>
	</div>		
	</logic:present>	
			

<%  }catch(Exception e) { %>
	<div class="mattblacktabs">
	<ul>
		<!--<li id="pwd">
				<a href="#" onclick="return loadToIFrameUrl('<%=ServerUtils.getContextName(request)%>/security/changePassword.jsp', 'pwd')" >
					Change Password
				</a>
		</li>	-->
		<li id="logout">
				<span onclick="resetLogout();" onmouseover="changeStyle('logout')" onmouseout="resetStyle('logout')">
					<bean:message key='admin.menu.logout' bundle='admin'/>
				</span>				
		</li>	
	</ul>
	</div>

<%}%>
<script>
var isLogoutClicked = false;
var selectedMenuId = 0;
var selectLayerIds = "";
var currentSelectedModule=""; // added to know in which module user is in now
var currentModuleURL = "";
var situationModuleId="";   //added to know the layer/window is related to a module in situation room is selected
var selectedRBRegion = "";
var selectedRBRegionModule = "";
	function resetLogout(){
		isLogoutClicked = true;
		
		if(currentModuleURL.indexOf("confirmBeforeLeave=true") > 0 ) {					
			var message = confirm('<bean:message key='admin.menu.confirm.msg' bundle='admin' />');				
			if (message){ 				
				return false;					
			}
		} 		
		invalidateSession();
	}
	
	function loadToIFrameUrl(url, menuId, isModuleClicked){
		var validateUrl = trimAll(url);
		if(validateUrl == ''){
			showEmptyDialog("Not a Valid URL", "Space Time Insight");
			return false;
		}		
		if(currentModuleURL == "") {			
			currentModuleURL = url;
		}
		if(isModuleClicked == 'true'){
			if(currentModuleURL.indexOf("confirmBeforeLeave=true") > 0 ) {					
				var message = confirm('<bean:message key='admin.menu.confirm.msg' bundle='admin' />');				
				if (message){ 				
					return false;					
				}else{
					currentModuleURL = url;
				}
			} else {
				currentModuleURL = url;
			}
		} 
		
		try{
			window.frames['windowIframe'].location = url;
		}catch(er){
			document.getElementById('windowIframe').src = url; 
		}
		resetButtonState();
		selectedMenuId = menuId;
		document.getElementById(menuId).className = "selected";
		try{
			parent.setIFrameProperties(false);
		}catch(err){
			//ERROR. when included the application under IFrame
		}
		return false;
	}

	function resetButtonState(){
		for(i=0; i < <%=i%>; i++){
			try{
				document.getElementById(i).className = "";			
			}catch(er){}
		}
		//document.getElementById('pwd').className = "";
	}
	
	function refreshSessionInvalid(){
		window.location = '<%=ServerUtils.getContextName(request)%>/loginAction.do';
	}

	function changeStyle(menuId){
		document.getElementById(menuId).className = "selected";
	}
	
	function resetStyle(menuId){
		if(selectedMenuId != menuId){
			document.getElementById(menuId).className = "";
		}
	}

	function trimAll(sString){
		while (sString.substring(0,1) == ' ')
		{
			sString = sString.substring(1, sString.length);
		}
		while (sString.substring(sString.length-1,  sString.length) == ' ')
		{
			sString = sString.substring(0,sString.length-1);
		}
		return sString;
	}
	
	function invalidateSession(){
		if(!isLogoutClicked){
			window.location = '<%=ServerUtils.getContextName(request)%>/logout.do?isSessionExpired=yes';
		}else{
			window.location = '<%=ServerUtils.getContextName(request)%>/logout.do';
		}
	}
	
	function loadGEFrame(url, menu,selectLayerIds1,selectedRBRegion1,isModuleLevel){
		loadToIFrameUrl(url,menu, 'false');
		var geFrameWindow = null;
		try{
			geFrameWindow = window.frames['windowIframe']
		}catch(er){
			geFrameWindow = document.getElementById('windowIframe') 
		}
		selectLayerIds = selectLayerIds1;
		if(isModuleLevel){
			selectedRBRegionModule = selectedRBRegion1;
		}else{			
			selectedRBRegion = selectedRBRegion1;
		}
	}
	/**
	opens a module 
	*/
	function openModule(moduleName){		
		try{
			var moduleSpanLink = document.getElementById(moduleName);
			moduleSpanLink.click();
		}catch(err){
			alert("No module found with "+moduleName);
		}		
	}
</script>