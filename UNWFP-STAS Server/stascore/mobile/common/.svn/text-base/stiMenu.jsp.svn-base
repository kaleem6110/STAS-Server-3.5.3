<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ page import="com.enterprisehorizons.magma.admin.processmanager.ProcessManagerHelper "%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils,com.spacetimeinsight.security.bean.JAASConstants"%>
<%@page import="com.spacetimeinsight.security.bean.JAASAuthenticationTypeInitializer"%>

<% 
int i=0;
%> 
<% try { %>
	<logic:present name="stiUser" >
        <div class="headerMenu">
        <ul>                
        	<logic:iterate id="stiModulesList" name="stiUser"  property="modules" >
         		<logic:notEmpty name="stiModulesList" property="url">
            		<logic:notMatch name="stiModulesList" property="url" value="myaction">
                        <logic:match name="stiModulesList" property="mobileSpecific" value="true">
                            <li id='<%=i %>' >
                                <span onmouseover="changeStyle('<%=i %>')" onmouseout="resetStyle('<%=i %>')"
                                    onclick="return loadToIFrameUrl('<bean:write name="stiModulesList" property="url"  />', '<%=i %>')" >
                                    <bean:write name="stiModulesList" property="name" />
                                </span>
                            </li>
                        </logic:match>
                    </logic:notMatch>
        		</logic:notEmpty>
            	<% if(i == 0){ %>
            	    <input type="hidden" id="menu2Load" name="menu2Load" value="<bean:write name="stiModulesList" property="url"  />" />
            	<%  } %>
            	<% i++;%>
    		</logic:iterate>    
        
			<% if (!JAASConstants.KRB_LDAP_DATA_SOURCE.equals(JAASAuthenticationTypeInitializer.getInstance().getJAASAuthenticationType())) {%>  
                 
     		<li id="logout">
        		<span onclick="invalidateSession()" onmouseover="changeStyle('logout')" onmouseout="resetStyle('logout')">
            		<bean:message key='admin.menu.logout' bundle='admin'/>
            	</span>
     		</li>
               
        	<% } %> 
   		</ul>
		</div>      
	</logic:present>    

<%  }catch(Exception e) { %>
    <div class="headerMenu">
    <ul>
        <li id="logout">
        	<span onclick="invalidateSession()" onmouseover="changeStyle('logout')" onmouseout="resetStyle('logout')">
                    <bean:message key='admin.menu.logout' bundle='admin'/>
          	</span>             
        </li>   
    </ul>
    </div>

<%}%>
<script>
var selectedMenuId = 0;
var selectLayerIds = "";
var selectedRBRegion = "";
var selectedRBRegionModule = "";
    function loadToIFrameUrl(url, menuId){
        var validateUrl = trimAll(url);
        if(validateUrl == ''){
            showEmptyDialog("Not a Valid URL", "Space Time Insight");
            return false;
        }
      
        try{
            window.frames['windowIframe'].location = url;
        }catch(er){
            document.getElementById('windowIframe').src = url; 
        }
        resetButtonState();
        selectedMenuId = menuId;
        document.getElementById(menuId).className = "selected";
       // parent.setIFrameProperties(false);
        return false;
    }
    function resetButtonState(){
        for(i=0; i < <%=i%>; i++){
            try{
                document.getElementById(i).className = "";          
            }catch(er){}
        }
	}
    
    function refreshSessionInvalid(){
        window.location = '<%=ServerUtils.getContextName(request)%>/mobile/loginAction.do';
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
        window.location = '<%=ServerUtils.getContextName(request)%>/mobile/logout.do';
    }
    
    function loadGEFrame(url, menu,selectLayerIds1,selectedRBRegion1,isModuleLevel){
        loadToIFrameUrl(url,menu);
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
</script>