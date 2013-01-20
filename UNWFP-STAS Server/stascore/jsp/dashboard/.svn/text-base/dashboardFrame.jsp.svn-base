<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@page import="com.enterprisehorizons.constants.CommonConstants"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.CommonConfigUtils" %>
<%@page import="com.enterprisehorizons.util.StringUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.AdminConfigUtils"%>

<%@page import="com.spacetimeinsight.security.bean.UserBean"%>

<%
    if(session == null || session.getAttribute(ServerUtils.USER_BEAN_NAME) == null){
        response.sendRedirect("/");
        return;
    }
    UserBean userBean = (UserBean) session.getAttribute(ServerUtils.USER_BEAN_NAME);

    String serverUrl = ServerUtils.getServerContextBaseUrl(request);
    String serverRootUrl = ServerUtils.getServerRootUrl(request);
    String groupId = request.getParameter(ServerUtils.PARAM_GROUP_ID);
    String domainId = request.getParameter(ServerUtils.PARAM_DOMAIN_ID);
    String userId = request.getParameter(ServerUtils.PARAM_USER_ID);
    String moduleId = request.getParameter(ServerUtils.PARAM_MODULE_ID);
    String languageId = request.getParameter(ServerUtils.PARAM_LANGUAGE_ID);
    String languageCd = request.getParameter(ServerUtils.PARAM_LANGUAGE_CODE);
    String expandTree = request.getParameter("expandtree");
    String selectedLayers = request.getParameter("selectedLayers");
    String swfName = request.getParameter("swf");
	String dashboardsLayout = request.getParameter("dashboardsLayout");
	 if(StringUtils.isNull(dashboardsLayout)) {
       dashboardsLayout = "";
   }

	String dashboardsLayoutNumCols = request.getParameter("dashboardsLayoutNumCols");
	 if(StringUtils.isNull(dashboardsLayoutNumCols)) {
        dashboardsLayoutNumCols = "";
    }

	String dashboardsLayoutNumRows = request.getParameter("dashboardsLayoutNumRows");
	 if(StringUtils.isNull(dashboardsLayoutNumRows)) {
        dashboardsLayoutNumRows = "";
    }


    String appTheme = AdminConfigUtils.getApplicationTheme();
    if (appTheme == null || appTheme.equals("null")) {
        appTheme = "default";
    }

    String jsessionId = "";
    if(request.getSession(false) != null) {
        jsessionId = request.getSession(false).getId();
    }

    if(expandTree == null) {
        expandTree = "";
    }


    if(StringUtils.isNull(domainId)) {
        domainId = String.valueOf(userBean.getDomainId());
    }
    if(domainId == null) {
        domainId = "";
    }

    if(StringUtils.isNull(userId)) {
        userId = String.valueOf(userBean.getUserId());
    }
    if(userId == null) {
        userId = "";
    }

    if(StringUtils.isNull(groupId)) {
        groupId = StringUtils.tokenize(userBean.getGroupIds());
    }
     if(groupId == null) {
        groupId = "";
    }

    if(moduleId == null) {
        moduleId = "";
    }

    if(StringUtils.isNull(languageId)) {
        languageId = String.valueOf(userBean.getLanguageId());
    }
    if(languageId == null) {
        languageId = "";
    }

    if(StringUtils.isNull(languageCd)) {
        languageCd = userBean.getLanguageCd();
    }
    if(languageCd == null) {
        languageCd = "";
    }

    if(StringUtils.isNull(swfName)) {
        swfName = "DashboardsFrame.swf";
    }
%>
<jsp:include page="/common/commonheader.jsp"/>

<jsp:include page="/common/stiSession.jsp"/>

<html lang="en">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Dashboard Frame</title>
<script type="text/javascript" src="<%=serverUrl %>js/ge/flash_detect.js"></script>
<script type="text/javascript">
	if(!FlashDetect.installed || !FlashDetect.versionAtLeast(9)){
        alert("<bean:message key="flash.required" bundle="admin" />"); 
    }
</script>
<script type="text/javascript" src="<%=serverUrl %>ge/viewer/magma_ajax.js"></script>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/swfobject.js"></script>
<script type="text/javascript" src="<%=serverUrl %>ge/viewer/motionpack.js"></script>
<script type="text/javascript" src="<%=serverUrl %>ge/viewer/AC_OETags.js" ></script>
<script type="text/javascript" src="<%=serverUrl %>js/flexSessionTimeOut.js" ></script>
<script language="JavaScript" type="text/javascript">


String.prototype.replaceAll = function(stringToFind,stringToReplace){
    var temp = this;
    var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);
        }
        return temp;
    }

// Array Remove support on JS Array
Array.prototype.remove = function(from, to) {
  var rest = this.slice((to || from) + 1 || this.length);
  this.length = from < 0 ? this.length + from : from;
  return this.push.apply(this, rest);
}

//Array indexof support on JS Array
if(!Array.indexOf){
  Array.prototype.indexOf = function(obj){
   for(var i=0; i<this.length; i++){
    if(this[i]==obj){
     return i;
    }
   }
   return -1;
  }
}

// ends here for the indexof support on JS Array.

function moveIFrame(divId, iframeId, x,y,w,h) {
    var frameRef=document.getElementById(divId);
    frameRef.style.left=x;
    frameRef.style.top=y;
    var iFrameRef=document.getElementById(iframeId);
    iFrameRef.width=w;
    iFrameRef.height=h;
}

function hideIFrame(divId){
    document.getElementById(divId).style.visibility="hidden";
}
function openInfoframeById(id, urlToOpen){
     sendToFlash("dashboardFrame", "_loadInfoframeById", id+","+urlToOpen);
 }
function showIFrame(divId){
    document.getElementById(divId).style.visibility="visible";
}
var swfObjectsArr = new Array();
function loadIFrame(divId, iframeId, url){
    if(url == null || url.indexOf("null") == 0) {
        //alert('loading.. '+url);
        return;
    }
    //alert(divId+':'+iframeId+'loading.. '+url);
    var divObj = document.getElementById(divId);
    if(!divObj){
        divObj = document.createElement('div');
        divObj.setAttribute('id', divId);
        divObj.style.position = "absolute";
        divObj.style.border = "0px";
        divObj.style.visibility="hidden";
        divObj.style.backgroundColor = "transparent";
        document.body.appendChild(divObj);
        //swfObjectsArr.push("swf_"+iframeId);
        swfObjectsArr.push(iframeId);
        //set the zindex for the div id
        bringDivToBack(divId);
    }
    if(url.indexOf('?') >= 0) {
        url += "&"+Math.random();
    } else {
        url += "?"+Math.random();
    }
    divObj.innerHTML = "<iframe id='"+iframeId+"' name='"+iframeId+"' src='" + url + "'frameborder='0'></iframe>";

}

function bringDivToFront(divId){
    try {
        var div = document.getElementById(divId);
        if(div) {
            div.style.zIndex = 4;
        }
    } catch (e) {
        alert('Error ['+e.message+'] occurred while bringing the div to front');
    }
}

function bringDivToBack(divId){
    try {
        var div = document.getElementById(divId);
        if(div) {
            div.style.zIndex = 3;
        }
    } catch (e) {
        alert('Error ['+e.message+'] occurred while bringing the div to front');
    }
}

function removeIFrame(divId,iframeId){
    var divObj = document.getElementById(divId);
    //var index = swfObjectsArr.indexOf("swf_"+iframeId);
    var index = swfObjectsArr.indexOf(iframeId);
    sendToFlash(getSWFId(iframeId), "_closeWindow", "");
    swfObjectsArr.remove(index);
    divObj.parentNode.removeChild(divObj);
}

function getSWFId(iframeId) {
    return "swf_"+iframeId.replaceAll(" ","_");
}

function openLayerDashboard(layerId, layerName, dashboardName) {
	invokeFlashMethod("_openLayerDashboard", layerId+","+layerName+","+dashboardName);
}

function invokeDashboardMethod(layerId, dashboardId, methodName, methodParams) {
	var swfId = getDashboardSWFId(layerId, dashboardId);
	if(swfId != null && swfId != "") {
		if(!methodParams || methodParams == null) {
			methodParams = "";
		}
		sendToFlash(swfId, methodName, methodParams);
	} else {
		alert('dashboard not found for ['+layerId+'], ['+dashboardId+']');
	}
}

function getDashboardSWFId(layerId, dashboardId) {
	var iframeObj = document.getElementById(dashboardId);
	if(iframeObj == null) {
	   //prefix the layerid to the dashboard if the dashboardid does not start with layer id
		dashboardId = layerId+"_"+dashboardId;
		iframeObj = document.getElementById(dashboardId);
	}

	if(iframeObj != null) {
		return getSWFId(dashboardId);
	}

	return "";
}


function refreshWindows() {
    refreshDashboards('');
}
function openHelpWindow(){
    window.open('serverresources/<%=AdminConfigUtils.getDashboardFrameManual() %>', 'dashboardFrameManual', 'width='+screen.width/2+',height='+screen.height+',resizable=yes, toolbar=no, location=no, directories=no, status=no, menubar=no,scrollbars=yes');
}
function refreshDashboards(layerName) {
    try{
        for(i=0;i<swfObjectsArr.length;i++){
            //var iframeId=swfObjectsArr[i].substring(0,(swfObjectsArr[i].length-4));
            var iframeObj = document.getElementById(swfObjectsArr[i]);
            // check for xcelsius type in the dashboard url for xmldatatype property
            //if its xcelsius referesh frame otherwise skip.
            if(iframeObj.src.indexOf("xcelcius")!=-1){
                iframeObj.src=iframeObj.src;
            }

            if(layerName == '' || swfObjectsArr[i].indexOf(layerName+"_") == 0) {
                sendToFlash(getSWFId(swfObjectsArr[i]), "_refresh", "");
            }
        }

    } catch (e){
        //alert('Error ['+e.message+'] occurred while refreshing the windows');
    }
}

function enableAutoRefresh(flag) {
    try {
        window.frames["geIFrame"].enableAutoRefresh(flag);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while enabling autorefresh');
    }
}

function enableMouseListeners(flag) {
    try {
        window.frames["geIFrame"].enableMouseListeners(flag);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while enabling mouselisteners');
    }
}

function invokeFlashMethod(methodName, methodArgs) {
    sendToFlash("dashboardFrame", methodName, methodArgs);
}

function _selectLayers(layers) {
    invokeFlashMethod("_selectLayers", layers);
}

function _selectLayersById(layerIds) {
    invokeFlashMethod("_selectLayersById", layerIds);
}

function _deSelectLayers(layers) {
    invokeFlashMethod("_deSelectLayers", layers);
}

function enableFrame(flag){
    invokeFlashMethod("_enableFrame", flag);
}

function _refreshTimeLayers(){
    invokeFlashMethod("_refreshTimeLayers");
}

function selectRow(id){
    invokeFlashMethod("_selectRow", id);
}

function addFeature(id, coordsStr, featuretype){
    invokeFlashMethod("_addFeature", featuretype+":"+id+":"+coordsStr);
}

function _setTimeRange(timeStr){
    invokeFlashMethod("_setTimeRange", timeStr);
}

/* empty stub methods - start*/
function _getCurrentBoundingBox() {
    return '';
}

function _highlight(coordStr) {
}

function _setHighlightPlacemarkUrl(url) {
}

function _setHighlightPlacemarkStyle(style) {
}

function _lookat(coordStr) {
}

function getViewFormatParams() {
    return '';
}

function _refreshLink(name){
    invokeFlashMethod("_refreshLayer", name);
}

function _showControls(dashboardDivId, flag) {
	try {
        return sendToFlash("dashboardFrame", "_showControls", dashboardDivId + "," + flag);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while getting the show/hide the dashboardcontrolbar flag');
    }

    return "";
}

/* empty stub methods - end*/
</script>
<!--- ===================================================================================================================== -->

<style>
body { margin: 0px; overflow:hidden; bgcolor:#000000; width:100%; height:100%}
</style>
</head>

<body scroll="no">
<script language="JavaScript" type="text/javascript">

function getFlashMovieObject(movieName){
    if (window.document[movieName]){
        return window.document[movieName];
    }
    if (navigator.appName.indexOf("Microsoft Internet")==-1){
      if (document.embeds && document.embeds[movieName])
        return document.embeds[movieName];
    } else{
      var obj = document.getElementById(movieName);
      if(!obj) {
        var frames = window.frames; // or // var frames = window.parent.frames;
        for (var i = 0; i < frames.length; i++) {
           obj = frames[i].document.getElementById(movieName);
           if(obj) {
               return obj;
           }
        }
      }
      return obj;
    }
}
function bringDivToFront(divId){
    try {
        var div = document.getElementById(divId);
        if(div) {
            div.style.zIndex = 4;
        }
    } catch (e) {
        alert('Error ['+e.message+'] occurred while bringing the div to front');
    }
}

function bringDivToBack(divId){
    try {
        var div = document.getElementById(divId);
        if(div) {
            div.style.zIndex = 3;
        }
    } catch (e) {
        alert('Error ['+e.message+'] occurred while bringing the div to front');
    }
}

function sendToFlash(swfObjectName, method, data) {
    try {
        var flashMovie = getFlashMovieObject(swfObjectName);
        return flashMovie.callFlashFromJS(method, data);
    }
    catch (e) {
        //alert('Error ['+e.message+'] occurred while calling a function on the flash object');
    }
    return "";
}

function showOnlyPrimaryControls(iframeId, show){
	try {
		sendToFlash(getSWFId(iframeId), "_showOnlyPrimaryControls", show);
	} catch (e) {
		//ignore
	}
}

function redirectToLoginPage() {
    parent.refreshSessionInvalid();
}

function createFrameObject(){
	var tmpSelectLayerIds = "";
	var tmpSelectedRBRegion = "";
	var tmpSelectedRBRegionModule = "";
	if(parent.selectLayerIds){
	    tmpSelectLayerIds = parent.selectLayerIds;
	}
	
	if(parent.selectedRBRegion){
	    tmpSelectedRBRegion = parent.selectedRBRegion;
	}
	
	if(parent.selectedRBRegionModule){
	    tmpSelectedRBRegionModule = parent.selectedRBRegionModule;
	}
	
	var flashvars = {};
    var params = {serverUrl: "<%=serverUrl%>",
                            coreServerUrl: "<%=serverUrl%>",
                            adminServerUrl: "<%=serverUrl%>",
                            jsessionId: "<%=jsessionId%>",
                            userId: "<%=userId%>",
                            groupId: "<%=groupId%>",
                            domainId: "<%=domainId%>",
                            moduleId: "<%=moduleId%>",
                            languageId: "<%=languageId%>",
                            languageCd: "<%=languageCd%>",
                            localeChain: "<%=languageCd%>",
                            expandtree: "<%=expandTree%>",
                            selectedLayers: "<%=selectedLayers%>",
                            selectLayerIds: tmpSelectLayerIds,
                            theme: "<%=appTheme%>",
							dashboardsLayout:"<%=dashboardsLayout%>",
							dashboardsLayoutNumCols:"<%=dashboardsLayoutNumCols%>",
							dashboardsLayoutNumRows:"<%=dashboardsLayoutNumRows%>",
                            wmode:"transparent"};
    var attributes = {
    			id: "DashboardsFrame",
    			name: "DashboardsFrame",
    			style: "z-index:1;border: 1px solid silver; height: 100%; width: 100%; position:absolute; left:0px;top:0px;",
    			wmode:"transparent"
    };
    var geFrameUrl = "<%=serverUrl%>swf/<%=swfName%>";
    swfobject.embedSWF(geFrameUrl, "dashboardFrame", "100%", "100%", "9.0.0",flashvars, params, attributes);
}
createFrameObject();
/*
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
	alert(parent.SESSION_EXPIRE_ALERT);
    parent.invalidateSession();
}

function promptUser(sessionAlertTime){ 
	var message = confirm('Your login session will expire in ' +sessionAlertTime+ ' seconds. Click Ok to extend the session with server');
	if (message){
		validateAndExtendSession();
	    sessionDetails();
	}else{
		parent.invalidateSession();
	}
}
*/
</script>
<div id='dashboardFrame' style="z-index:1"></div>
<div id="dashboardFrameDiv" style="position:absolute;background-color:transparent;border:0px;visibility:hidden;z-index:1"></div>
</body>
</html>