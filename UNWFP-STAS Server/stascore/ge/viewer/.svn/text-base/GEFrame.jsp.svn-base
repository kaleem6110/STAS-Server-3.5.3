<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@page import="com.enterprisehorizons.constants.CommonConstants"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.CommonConfigUtils" %>
<%@page import="com.enterprisehorizons.util.StringUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.AdminConfigUtils"%>
<%@page import="com.spacetimeinsight.stas.config.ConfigurationConstants"%>
<%@page import="com.spacetimeinsight.stas.config.GenericConfigurationManager"%>
<%@page import="com.spacetimeinsight.security.bean.UserBean"%>
<%@page import="com.enterprisehorizons.magma.artifact.search.GlobalSearchController"%>

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
    String geKey = request.getParameter(ServerUtils.PARAM_GE_KEY);
    String geClientId = request.getParameter(ServerUtils.PARAM_GE_CLIENT_ID);
    String rasEnabled = request.getParameter(ServerUtils.PARAM_RAS_ENABLED);
    String rasInobxUrl = request.getParameter(ServerUtils.PARAM_RAS_INBOX_URL);
	String userName = request.getParameter("userName");
    String viewMode = request.getParameter("viewmode");
    String clientName = request.getParameter("clientname");
    String title = request.getParameter("title");
    String expandTree = request.getParameter("expandtree");
    String defaultLat = request.getParameter("defaultlat");
    String defaultLon = request.getParameter("defaultlon");
    String defaultRange = request.getParameter("defaultrange");
    //longitude,latitude,range,tilt,heading,altitude,altitudemode
    String defaultView = request.getParameter("defaultview");
    String selectedLayers = request.getParameter("selectedLayers");
    String selectFavorite = request.getParameter("selectFavorite");
    String swfName = request.getParameter("swf");
    String ecoexpml = request.getParameter("ecoexpml");
    int sessionStatusTimerInterval = AdminConfigUtils.getSessionStatusUpdateTimerInterval();
    String disableTour = request.getParameter("disableTour");
    String disableSound = request.getParameter("disableSound");
    String alertOffset = request.getParameter("alertOffset");
	String alarmModeId = null;
	String tickerModeId = null;
	String tourModeId = null;
	String mmTickerModeId = null;
	String autoAckSnoozeInterval = null;
	String messageScrollSpeed = null;
	String messageScrollCount = null;
	String tourRepeatCount = null;
	String maxTourAlerts = null;
    String appTheme = AdminConfigUtils.getApplicationTheme();
    String isHighlightPlacemark = AdminConfigUtils.getHighlightPlacemark();
	String idColumn = request.getParameter("idColumn");
	String autoFitColumns = request.getParameter("autoFitColumns");
	String helpUrl = request.getParameter("helpUrl");
	String mappingType = request.getParameter("mappingType");
    if(appTheme == null || appTheme.equals("null")) {
        appTheme = "default";
    }
    String jsessionId = "";
    if(request.getSession(false) != null) {
        jsessionId = request.getSession(false).getId();
    }

    String gsComponentUrl = AdminConfigUtils.getGSSearchComponentUrl();
    boolean enableGS = GlobalSearchController.getInstance().isEnableGlobalSearch();
    if(gsComponentUrl == null) {
        gsComponentUrl = "";
    }
    boolean gsLaunchInWindow = AdminConfigUtils.getGSLaunchInExternalWindow();

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

    //System.out.println("jsessionId = "+jsessionId);

   // System.out.println("rasEnabled = "+rasEnabled);
	if(StringUtils.isNull(geKey)) {
        geKey = AdminConfigUtils.getGEKey();
    }

    if(geKey == null) {
        geKey = "";
    }

    if(expandTree == null) {
        expandTree = "";
    }

    if(defaultLat == null) {
        defaultLat = "";
    }

    if(defaultLon == null) {
        defaultLon = "";
    }

    if(defaultRange == null) {
        defaultRange = "";
    }

    if(defaultView == null) {
        defaultView = "";
    }

    if(title == null) {
        title = "";
    }

    if(StringUtils.isNull(geClientId)) {
        geClientId = AdminConfigUtils.getGEClientId();
    }
    if(geClientId == null) {
        geClientId = "";
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
	if(StringUtils.isNull(userName)) {
        userName = String.valueOf(userBean.getFirstName())+" "+String.valueOf(userBean.getLastName());
	}
    if(userName == null) {
        userName = "";
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

    if(rasEnabled == null) {
        rasEnabled = "false";
        rasInobxUrl="";
    }

    if(rasInobxUrl!=null){
        rasInobxUrl=serverRootUrl+rasInobxUrl;
    } else{
        rasInobxUrl="";
    }

    if(clientName == null) {
        clientName = "";
    }

    if(alarmModeId == null){
    	alarmModeId = "ALARM";
    }

    if(tickerModeId == null){
    	tickerModeId = "TICKER";
    }

	if(tourModeId == null){
    	tourModeId = "TOUR";
    }
	if(mmTickerModeId == null){
    	mmTickerModeId = "MMTicker";
    }

	if(messageScrollSpeed == null){
		messageScrollSpeed = AdminConfigUtils.getMessageScrollSpeed();
    }
	if(messageScrollCount == null){
		messageScrollCount = AdminConfigUtils.getMessageScrollCount();
    }
	if(tourRepeatCount == null) {
		tourRepeatCount = AdminConfigUtils.getTourRepeatCount();
	}
	if(maxTourAlerts == null) {
		maxTourAlerts = AdminConfigUtils.getMaxTourAlerts();
	}

	if(autoAckSnoozeInterval==null){
		autoAckSnoozeInterval = AdminConfigUtils.getAutoAckSnoozeInterval();
	}
	
	if(mappingType == null) {
		mappingType = "";
	}

    // events or notifications properties
    GenericConfigurationManager gcManager = GenericConfigurationManager.getInstance();
    String noOfMessages=gcManager.getProperty(ConfigurationConstants.CONFIGURATION_NOTIFICATIONS, ServerUtils.PARAM_ALERT_MAX_MSGS);
    String alertFile=gcManager.getProperty(ConfigurationConstants.CONFIGURATION_NOTIFICATIONS, ServerUtils.PARAM_ALERT_FILE);
    String defaultPriority=gcManager.getProperty(ConfigurationConstants.CONFIGURATION_NOTIFICATIONS, ServerUtils.PARAM_ALERT_DEFAULT_PRIORITY);
    String zoomRange=gcManager.getProperty(ConfigurationConstants.CONFIGURATION_NOTIFICATIONS, ServerUtils.PARAM_ALERT_ZOOM);

    if(disableTour==null)
        disableTour=gcManager.getProperty(ConfigurationConstants.CONFIGURATION_NOTIFICATIONS, ServerUtils.PARAM_ALERT_DISABLE_TOUR);
    if(disableSound==null)
        disableSound=gcManager.getProperty(ConfigurationConstants.CONFIGURATION_NOTIFICATIONS, ServerUtils.PARAM_ALERT_DISABLE_SOUND);
    if(alertOffset==null)
        alertOffset=gcManager.getProperty(ConfigurationConstants.CONFIGURATION_NOTIFICATIONS, ServerUtils.PARAM_ALERT_OFFSET);
    if(StringUtils.isNull(swfName)) {
        swfName = "GEFrame.swf";
    }


   String moduleJSUrl = request.getParameter(ServerUtils.PARAM_CUSTOM_JS_URL_MODULE);
   String pluginJSUrl = request.getParameter(ServerUtils.PARAM_CUSTOM_JS_URL_MAP_PLUGIN);

    //String coreServerUrl = serverRootUrl+CommonConstants.FORWARD_SLASH + CommonConfigUtils.getConfigValue("coreservercontext")+CommonConstants.FORWARD_SLASH;
    //String adminServerUrl = coreServerUrl;//serverRootUrl+CommonConstants.FORWARD_SLASH + CommonConfigUtils.getConfigValue("adminservercontext")+CommonConstants.FORWARD_SLASH;
%>
<jsp:include page="/common/commonheader.jsp"/>

<jsp:include page="/common/stiSession.jsp"/>


<%@page import="com.spacetimeinsight.alerts.util.IAlertConstants"%><html lang="en">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title><%=title%></title>
<script type="text/javascript" src="<%=serverUrl %>js/ge/flash_detect.js"></script>
<script type="text/javascript" src="<%=serverUrl%>js/flexSessionTimeOut.js"></script>
<script type="text/javascript">
	if(!FlashDetect.installed || !FlashDetect.versionAtLeast(9)){
        alert("<bean:message key="flash.required" bundle="admin" />");
    }

var msgs="";
function showMessages(messages){
   msgs = messages;
}

function getMessage(){
   return msgs;
}
function clearAckMessages(_headers){
    for(i=0;i<swfObjectsArr.length;i++){
        if("_AlertMessagingEvents" == swfObjectsArr[i]){
            var iframeObj = document.getElementById(swfObjectsArr[i]);
             if(iframeObj.src.indexOf("xcelcius")!=-1){
                iframeObj.src=iframeObj.src;
            }
            sendJSObjectToFlash(getSWFId(swfObjectsArr[i]), "_refreshDataGrid", _headers);
        }

     }

}
// if any user add/update artifact then it will display alert to other user for updating artifact.
function callMMMessagebox(message){
	alert(message);
}
// for refreshing acknowledge message for Manual mapping.
function clearAckMessagesMM(_headers){

   sendJSObjectToFlash("geFrame", "_refreshDataGrid", _headers);
}
function ackAllEventMessages(_selectedEventId){
 	sendJSObjectToFlash("geFrame", "_ackAllEventMessages", _selectedEventId);
}
/*
			method to change view from java script
			params :viewMode :Object
			0(GE View only)
			1(Dashboard View Only)
			2(both GE& dashboard View)
*/
function changeView(_selectedView){
 	sendJSObjectToFlash("geFrame", "_changeView", _selectedView);
}
</script>
<script type="text/javascript" src="<%=serverUrl %>ge/viewer/magma_ajax.js"></script>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/swfobject.js"></script>
<script type="text/javascript" src="<%=serverUrl %>ge/viewer/motionpack.js"></script>
<script type="text/javascript" src="<%=serverUrl %>ge/viewer/AC_OETags.js" ></script>
<script type="text/javascript" src="<%=serverUrl %>js/ge/AlertTourFunctions.js"></script>
<%
 if(!StringUtils.isNull(moduleJSUrl)) {
%>
<script type="text/javascript" src="<%=serverUrl+moduleJSUrl.trim() %>" ></script>
<%
    }
%>
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

function alertMe(test) {
    alert('alert me'+test);
}

function hideIFrame(divId){
    document.getElementById(divId).style.visibility="hidden";
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
        //swfObjectsArr.push(iframeId+"_swf");
        swfObjectsArr.push(iframeId);
        //set the zindex for the div id
        bringDivToBack(divId);
    }
    divObj.innerHTML = "<iframe id='"+iframeId+"' name='"+iframeId+"' src='" + url + "'frameborder='0'></iframe>";

}
// For getting event log message
function getEventLogMessage(messages){
			sendToFlash("geFrame", "_refreshDataGrid", messages);
}
//var childWindowsArr = new Array();

function openWindow(title, iframeId, url) {
    //swfObjectsArr.push(iframeId);
    var childWindow = open(url,iframeId, 'resizable=yes,width=500,height=400');
    if (childWindow.opener == null) {
        childWindow.opener = self;
    }

    //childWindowsArr.push(childWindow);
}

function existsIFrame(iframeId) {
    return swfObjectsArr.indexOf(iframeId) >= 0;
}

function removeIFrame(divId,iframeId){
    var divObj = document.getElementById(divId);
    //var index = swfObjectsArr.indexOf(iframeId+"_swf");
    var index = swfObjectsArr.indexOf(iframeId);
    sendToFlash(getSWFId(iframeId), "_closeWindow", "");
    swfObjectsArr.remove(index);
    divObj.parentNode.removeChild(divObj);
}

function getSWFId(iframeId) {
    return "swf_"+iframeId.replaceAll(" ","_");
}

function refreshWindows() {
    refreshDashboards('');
}

function refreshDashboards(layerName, dashboardIdToExlcude) {
    try{
        for(i=0;i<swfObjectsArr.length;i++){
			if(dashboardIdToExlcude && dashboardIdToExlcude == swfObjectsArr[i]) {
				continue;
			}
			refreshDashboard(layerName, swfObjectsArr[i]);
			/*
            //var iframeId=swfObjectsArr[i].substring(0,(swfObjectsArr[i].length-4));
            var iframeObj = document.getElementById(swfObjectsArr[i]);
            // check for xcelsius type in the dashboard url for xmldatatype property
            //if its xcelsius referesh frame otherwise skip.
            if(iframeObj.src.indexOf("xcelcius")!=-1){
                var url = iframeObj.src;
				var index = url.indexOf("random");
				if(index > 0) {
					url = url.substring(0,(index+6))+"%3D"+Math.random();

				}
              iframeObj.src=url;
            }


            if(layerName == '' || swfObjectsArr[i].indexOf(layerName+"_") == 0) {
                sendToFlash(getSWFId(swfObjectsArr[i]), "_refresh", "");
            }
			*/
        }

    } catch (e){
        //alert('Error ['+e.message+'] occurred while refreshing the windows');
    }
}

function refreshDashboard(layerName, dashboardId) {
	try {
		//alert(layerName+":"+dashboardId);
		var iframeObj = document.getElementById(dashboardId);
		if(iframeObj == null) {
		   //prefix the layerid to the dashboard if the dashboardid does not start with layer id
			dashboardId = layerName+"_"+dashboardId;
			iframeObj = document.getElementById(dashboardId);
		}
		if(iframeObj != null) {
			// check for xcelsius type in the dashboard url for xmldatatype property
			//if its xcelsius referesh frame otherwise skip.
			if(iframeObj.src.indexOf("xcelcius")!=-1) {
				var url = iframeObj.src;
				var index = url.indexOf("random");
				if(index > 0) {
					url = url.substring(0,(index+6))+"%3D"+Math.random();

				}
			  iframeObj.src=url;
			} else if(layerName == '' || dashboardId.indexOf(layerName+"_") == 0) {
				sendToFlash(getSWFId(dashboardId), "_refresh", "");
			}
		}
	} catch (e) {
	}
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

function fireViewChanged() {
    try{
        for(i=0;i<swfObjectsArr.length;i++){
            //var iframeId=swfObjectsArr[i].substring(0,(swfObjectsArr[i].length-4));
            var iframeObj = document.getElementById(swfObjectsArr[i]);
            // check for xcelsius type in the dashboard url for xmldatatype property
            //if its xcelsius referesh frame otherwise skip.
            if(iframeObj.src.indexOf("xcelcius")!=-1){
                var url = iframeObj.src;
				var index = url.indexOf("random");
				if(index > 0) {
					url = url.substring(0,(index+6))+"%3D"+Math.random();

				}
              iframeObj.src=url;
            }

            sendToFlash(getSWFId(swfObjectsArr[i]), "_viewchanged", "");
        }

    } catch (e){
        //alert('Error ['+e.message+'] occurred while refreshing the windows');
    }
    //sendToFlash("swfObject", "_refresh", "");
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

function openKml(url, name){
    try {
        window.frames["geIFrame"].openKml(url,name);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while opening kml ['+url+']');
    }
}

function _startAlertTour(){
    try {
        window.frames["geIFrame"]._startAlertTour();
    } catch (e) {
        alert('Error ['+e.message+'] occurred while Starting tour ');
    }

}
function _stopAlertTour(){
    try {
        window.frames["geIFrame"]._stopAlertTour();
    } catch (e) {
        alert('Error ['+e.message+'] occurred while Stopping tour ');
    }

}


function _selectLayers(layers) {
    sendToFlash("geFrame", "_selectLayers", layers);
}

function _selectLayersById(layerIds) {
    sendToFlash("geFrame", "_selectLayersById", layerIds);
}

function _deSelectLayers(layers) {
    sendToFlash("geFrame", "_deSelectLayers", layers);
}

function submitLocation( address ) {
    try {
        window.frames["geIFrame"].submitLocation(address);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while submitting the location');
    }
}

function toggleLayer(layer,switchOn){
    try {
        window.frames["geIFrame"].toggleLayer(layer,switchOn);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while toggling the layer');
    }
}

function updateOptions(option,switchOn){
    try {
        window.frames["geIFrame"].updateOptions(option,switchOn);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while updating the options');
    }
}

function getCurrentView() {
    try {
        return window.frames["geIFrame"].getCurrentView();
    } catch (e) {
        alert('Error ['+e.message+'] occurred while getting the view');
    }
}

function setCurrentView(lookatStr) {
    try {
        //alert(lookatStr);
        return window.frames["geIFrame"].setCurrentView(lookatStr);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while setting the current view');
    }
}

function _refresh(){
    try {
        window.frames["geIFrame"]._refresh();
    } catch (e) {
        alert('Error ['+e.message+'] occurred while refreshing');
    }
}

function _refreshLink(name){
    try {
        window.frames["geIFrame"]._refreshLink(name);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while refreshing link ['+name+']');
    }
}

function _refreshLinkWithParameters(name, params){
    try {
        window.frames["geIFrame"]._refreshLinkWithParameters(name, params);
    } catch (e) {
       alert('Error ['+e.message+'] occurred while refreshing link ['+name+'] with parameters ['+params+']');
    }
}

function getLinkUrl(name){
    try {
		return window.frames["geIFrame"].getLinkUrl(name);
    } catch (e) {
       alert('Error ['+e.message+'] occurred while refreshing link ['+name+'] with parameters ['+params+']');
    }
}

/*
function _lookat(coordStr) {
    try {
        window.frames["geIFrame"]._lookat(coordStr);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while calling look at');
    }
}
*/

function _lookat(coordStr,rangeStr) {
    try {

        window.frames["geIFrame"]._lookat(coordStr,rangeStr);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while calling look at');
    }
}


function _highlight(coordStr) {
    try {
        window.frames["geIFrame"]._highlight(coordStr);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while highlighting the coordinate');
    }
}

function _setHighlightPlacemarkUrl(url) {
    try {
        window.frames["geIFrame"]._setHighlightPlacemarkUrl(url);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while setting the  highlight placemark url');
    }
}

function _setHighlightPlacemarkScale(size) {
    try {
        window.frames["geIFrame"]._setHighlightPlacemarkScale(size);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while setting the  highlight placemark scale');
    }
}

function _setHighlightPlacemarkStyle(style) {
    try {
        window.frames["geIFrame"]._setHighlightPlacemarkStyle(style);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while setting the  highlight placemark style');
    }
}

function initAjax(startKmlUrl, name){
    try {
        window.frames["geIFrame"].initAjax(startKmlUrl, name);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while in opening kml ['+startKmlUrl+']');
    }
}

function fetchKml(startKmlUrl, name){
    try {
        window.frames["geIFrame"].fetchKml(startKmlUrl, name);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while in fetching kml ['+startKmlUrl+']');
    }
}

 function distanceCalculation(mode){
    try {
        window.frames["geIFrame"].distnaceCalculation(mode);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while calculating the distance ');
    }
}

function removeNetworkLink(name)  {
    try {
        window.frames["geIFrame"].removeNetworkLink(name);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while removing networklink ['+name+']');
    }
}

function addSmartArtOverlay(layerName,smartArtName,url,xPos,yPos,xUnit,yUnit){
    try {
        window.frames["geIFrame"].addSmartArtOverlay(layerName,smartArtName,url,xPos,yPos,xUnit,yUnit)
    } catch (e) {
        alert('Error ['+e.message+'] occurred while adding extended data ['+name+','+dataName+','+dataValue+']');
    }
}

function getViewFormatParams(){
    try {
        return window.frames["geIFrame"].getViewFormatParams();
    } catch (e) {
        alert('Error ['+e.message+'] occurred while getting the view format parameters');
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

function bringGEDivToFront() {
    document.getElementById('geFrameDiv').style.zIndex = 2;
}

function bringGEDivToBack() {
    document.getElementById('geFrameDiv').style.zIndex = -1;
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

function hideBalloon() {
    try {
        window.frames["geIFrame"].hideBalloon();
    } catch (e) {
        alert('Error ['+e.message+'] occurred while hiding the balloon');
    }
}

function _clearDOMFeatures(){
    try {
        window.frames["geIFrame"]._clearDOMFeatures();
    } catch (e) {
        alert('Error ['+e.message+'] occurred while refreshing');
    }
}

/* google maps specific functions - start */
function showTrafficLayer(flag) {
    try {
        window.frames["geIFrame"].showTrafficLayer(flag);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while showing the traffic layer');
    }
}

function showStreetViewLayer(flag) {
    try {
        window.frames["geIFrame"].showStreetViewLayer(flag);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while showing the street view layer');
    }
}

function showLocalSearchControl(flag) {
      try {
        window.frames["geIFrame"].showLocalSearchControl(flag);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while showing the Local Search Control');
    }
}
function _showRoute(wayPoints){
    //alert("geframe jsp :_showRoute :"+wayPoints);
     try {
        window.frames["geIFrame"]._showRoute(wayPoints);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while _showRoute');
    }
}

function _clearRoute(){
    //alert("geframe jsp :_clearRoute :");
     try {
        window.frames["geIFrame"]._clearRoute();
       // alert("geframe jsp :_clearRoute1 :");
    } catch (e) {
        alert('Error ['+e.message+'] occurred while _clearRoute geframe');
    }
}
/* google maps specific functions - end*/

function redirectToLoginPage()
{
    parent.refreshSessionInvalid();
}

function saveRubberbandingInEditMode(){

    window.frames["geIFrame"].saveRBRegionsEditMode();
}

function getSelectedRubberBandDetails() {
    window.frames["geIFrame"].getCurrentRubberBandEditingDetails();
}
function getSelectedCircleRubberBandDetails() {
    window.frames["geIFrame"].getSelectedCircleRubberBandDetails();
}

function drawRBPolygonFinished() {
    window.frames["geIFrame"].drawRBPolygonFinished();
}

function drawRBCircleFinished() {
    window.frames["geIFrame"].drawRBCircleFinished();
}

function showEditConfirmation(rubberBandXML) {
    sendToFlash("geFrame", "_showEditConfirmation",rubberBandXML);
}

function renamePolygonId(xmlData) {
    sendToFlash("geFrame", "_renamePolygonId",xmlData);
}

function setCurrentSelectedRegion(featureId) {
     sendToFlash("geFrame", "_setCurrentSelectedRegion", featureId);
}

function cancelSaveRubberbandingInEditMode(){
    window.frames["geIFrame"].cancelSaveRubberbanding();
}

function addLayer(name, url, isSelect, id) {
    sendToFlash("geFrame", "_addLayer", name+","+url+","+isSelect+","+id);
}
</script>
<!--- ===================================================================================================================== -->
<script>
function showGE(){
    sendToFlash("geFrame", "_showGE", "true");
}

function enableFrame(flag){
    sendToFlash("geFrame", "_enableFrame", flag);
    //this is not called from geframe for a reason,
    //as this needs to be only done for Google Earth Enterprise Server
    //and can be commented out if there are any issues by calling this method.
    showGE();
}

function _refreshTimeLayers(){
    sendToFlash("geFrame", "_refreshTimeLayers");
}

function setRubberbandingEditMode(editModeFlag){

    window.frames["geIFrame"].changeRubberBandModeEditMode(editModeFlag);
 }


 function setCircleRubberbandingEditMode(editModeFlag){
    window.frames["geIFrame"].setCircleRubberbandingEditMode(editModeFlag);
 }

function drawRBFeature(type) {

    try {
        window.frames["geIFrame"].drawRBFeature(type);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while drawing ['+type+'] for rubberbanding');
    }
}

function createRBPolygon(id, coordStr, flag) {
    try {
        window.frames["geIFrame"].createRBPolygon(id, coordStr, flag);
    } catch (e) {
        //do not show the error
        //alert('Error ['+e.message+'] occurred while creating polygon feature by id = ['+id+'] for rubberbanding');
    }
}

function createRBCircle(id, coordStr, flag, viewPort,centerCoordinates, circumCoordinates, radius, operation) {
    try {
        window.frames["geIFrame"].createRBCircle(id, coordStr, flag,viewPort, centerCoordinates, circumCoordinates, radius, operation);
    } catch (e) {
        //do not show the error
        //alert('Error ['+e.message+'] occurred while creating polygon feature by id = ['+id+'] for rubberbanding');
    }
}

function removeRBFeature(id) {
    try {
        window.frames["geIFrame"].removeRBFeature(id);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while removing feature by id = ['+id+'] for rubberbanding');
    }
}

function showRBFeature(id, viewportStr, flag) {

    try {
        window.frames["geIFrame"].showRBFeature(id, viewportStr, flag);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while showing/hiding feature id = ['+id+'] for rubberbanding');
    }
}

function getSerializedViewPort() {
    return window.frames["geIFrame"].getSerializedViewPort();
}

function setSerializedViewPort(viewPortStr) {
    return window.frames["geIFrame"].setSerializedViewPort(viewPortStr);
}

function activateListener(eventType, listenerType) {
    try {
        window.frames["geIFrame"].activateListener(eventType, listenerType);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while activating the ['+eventType+'] listener for ['+listenerType+']');
    }
}

function deActivateListener(eventType, listenerType) {
    try {
        window.frames["geIFrame"].deActivateListener(eventType, listenerType);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while deActivating the ['+eventType+'] listener for ['+listenerType+']');
    }
}

function setMarkerMode(markerMode) {
    try {
        window.frames["geIFrame"].setMarkerMode(markerMode);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while setting the marker mode ['+markerMode+']');
    }
}

function initializeMarkerData() {
    try {
        window.frames["geIFrame"].initializeMarkerData();
    } catch (e) {
        alert('Error ['+e.message+'] occurred while initializing the marker data');
    }
}


function drawMarkerFeature(type) {
    try {
        window.frames["geIFrame"].drawMarkerFeature(type);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while drawing ['+type+'] for marker');
    }
}

function deleteSelectedMarkerFeature() {
    try {
        window.frames["geIFrame"].deleteSelectedMarkerFeature();
    } catch (e) {
        alert('Error ['+e.message+'] occurred while deleting feature for marker');
    }
}

function setSelectedMarkerFeatureById(id) {
    try {
        window.frames["geIFrame"].setSelectedMarkerFeatureById(''+id);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while setting selected marker feature by id = ['+id+']');
    }
}

function resetMarkerFeature(type) {
    try {
        window.frames["geIFrame"].resetMarkerFeature(type);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while drawing ['+type+'] for marker');
    }
}

function updateCoordinates(id, coordsStr){
    sendToFlash("geFrame", "_updateCoordinates", id+":"+coordsStr);
}

function selectRow(id){
    sendToFlash("geFrame", "_selectRow", id);
}

function addFeature(id, coordsStr, featuretype,count){
    sendToFlash("geFrame", "_addFeature", featuretype+":"+id+":"+coordsStr+":"+count);
}

function updateFeature(id, coordsStr, featuretype){
    sendToFlash("geFrame", "_updateFeature", featuretype+":"+id+":"+coordsStr);
}

function updateFeatureAll(xmlData){
    sendToFlash("geFrame", "_updateFeatureAll",xmlData);
}

function _getSelectedSessionIds() {
    try {
        return sendToFlash("geFrame", "_getSelectedSessionIds");
        //return window.frames["geIFrame"]._getCurrentBoundingBox();
    } catch (e) {
        alert('Error ['+e.message+'] occurred while getting the selected session ids');
    }

    return "";
}


function _getSelectedArtifactNames() {
    try {
        return sendToFlash("geFrame", "_getSelectedArtifactNames");
    } catch (e) {
        alert('Error ['+e.message+'] occurred while getting the selected artifact names');
    }

    return "";
}

function _getLayerIdByName(layerName) {
    try {
        return sendToFlash("geFrame", "_getLayerIdByName", layerName);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while getting the layer id for ['+layerName+']');
    }

    return "";
}

function _showControls(dashboardDivId, flag) {
	try {
        return sendToFlash("geFrame", "_showControls", dashboardDivId + "," + flag);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while getting the show/hide the dashboardcontrolbar dashboardDivI/flag');
    }

    return "";
}

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

function sendToFlash(swfObjectName, method, data) {
    try {
        var flashMovie = getFlashMovieObject(swfObjectName);
        //alert(method+":"+flashMovie+":"+swfObjectName+":"+data);
        return flashMovie.callFlashFromJS(method, data);
    }
    catch (e) {
        //alert('Error ['+e.message+'] occurred while calling a function on the flash object');
    }
}

function sendJSObjectToFlash(swfObjectName, method, data) {
    try {
        var flashMovie = getFlashMovieObject(swfObjectName);
        //alert(method+":"+flashMovie+":"+swfObjectName+":"+data);
        return flashMovie.sendJSObjectToFlash(method, data);
    }
    catch (e) {
        //alert('Error ['+e.message+'] occurred while calling a function on the flash object');
    }
}

function setSessionId(name, sessionId, artifactName) {
    sendToFlash("geFrame", "_setSessionId", name+","+sessionId+","+artifactName);
}


function viewChanged(viewStr) {
    sendToFlash("geFrame", "_viewChanged", viewStr);
}

function loadDashboards(name, nLink) {
    sendToFlash("geFrame", "_loadDashboards", name+","+nLink);
}

/**
 * @param params:Array - holds
 *  params[0] = All event details comma seperated
 *  params[1] = ecosystem session Id
 */
function registerEcosessionEvents(params) {
    sendJSObjectToFlash("geFrame", "_registerRefreshEcosessionOnEvents", params);
}

/**
 * @param params:Array - holds
 *  params[0] = All event details semicolan & space seperated
 *  params[1] = ecosystem session Id
 *  params[2] = dashbaord id
 */
function registerDashboardForEvents(params) {
    sendJSObjectToFlash("geFrame", "_registerDashboardRefreshOnEvents", params);
}

function fetchKmlLoaded(ecoexpml) {
    sendToFlash("geFrame", "_fetchKmlLoaded", ecoexpml);
}

function _getCurrentBoundingBox() {
    try {
        return window.frames["geIFrame"]._getCurrentBoundingBox();
    } catch (e) {
        alert('Error ['+e.message+'] occurred while getting the current boundingbox');
    }

    return "";
}

 function openInfoframe(urlToOpen){
     sendToFlash("geFrame", "_loadInfoframe", urlToOpen);
 }

 function openInfoframeById(id, urlToOpen){
     sendToFlash("geFrame", "_loadInfoframeById", id+","+urlToOpen);
 }

 function openDashboardFrameById(id, urlToOpen,layerId,layerName, windowId){
     sendToFlash("geFrame", "_loadDashboardFrameById", id+","+urlToOpen+","+layerId+","+layerName+","+windowId);
 }

 function openLayerDashboard(layerId, layerName, dashboardName) {
	sendToFlash("geFrame", "_openLayerDashboard", layerId+","+layerName+","+dashboardName);
 }


 function openGMaps(){
   openInfoframeById('GMaps','<%=ServerUtils.getContextName(request)%>/ge/viewer/GMaps.jsp?key=<%=geKey%>');
 }

 function openGoogleStreetview(){
    openInfoframeById('StreetView','<%=ServerUtils.getContextName(request)%>/ge/viewer/GMapsStreetView.jsp?key=<%=geKey%>');
 }

 function _setTimeRange(timeStr) {
    sendToFlash("geFrame", "_setTimeRange", timeStr);
 }

  function _setTime(startTime, endTime) {
	var timeRange = [];
	timeRange.push(startTime);
	timeRange.push(endTime);
    sendToFlash("geFrame", "_setTime", timeRange);
 }

 function gotoHome(){
    document.geLinkForm.action = '/magmage/wfts/stihomepage.jsp';
    document.geLinkForm.submit();
}

function openHelpWindow(){
    window.open('serverresources/<%=AdminConfigUtils.getPortalUserGuide() %>', 'portalUserGuide', 'width='+screen.width/2+',height='+screen.height+', resizable=yes, toolbar=no, location=no, directories=no, status=no, menubar=no,scrollbars=yes');
}
function showFileInHelpWindow(fileName){
	window.open('serverresources/'+fileName, 'portalUserGuide', 'width='+screen.width/2+',height='+screen.height+', resizable=yes, toolbar=no, location=no, directories=no, status=no, menubar=no,scrollbars=yes');
}
function changeGEDivStyle(flag) {
    geFrameDiv.style.zIndex = flag ? "100" : -1;
}


function fireGEEvent(listenerType, eventType, eventMode) {
    sendToFlash("geFrame", "_fireGEEvent", listenerType+","+eventType+","+eventMode);
}

function layerSelected(layerName, isSelected) {
    try {
        window.frames["geIFrame"].layerSelected(layerName, isSelected)
    } catch (e) {
        alert('Error ['+e.message+'] occurred while in calling select layer for '+layername);
    }
}

function callCurrentState(param){
    sendToFlash("geFrame", "_currentState", param);
}
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
/**
 * incase there is a change in naming convention for SWF id for dashbaords, need to change accordingly
 * ecosystem event handler
 * eventName - name of the event triggered from ecosystem
 * layerId - source of event
 * data - any extended data
 */
function handleEvent(eventName, layerId, data){
	try{
		for(i=0;i<swfObjectsArr.length;i++){
			// check whether this dashboard is related to this layer or not
			if(swfObjectsArr[i].indexOf(layerId) == 0){
				//add layerId & extended data to the data object
				// & send the same to Dashboard control bar
				var extData = [];
				extData[0] = eventName;
				extData[1] = data;

	            sendToFlash(getSWFId(swfObjectsArr[i]), "_handleEvent", extData);
	        }
	     }
	}catch(err){
		//alert(err);
	}


}

window.onload=function(){
    sessionDetails();
}
function createGEFrameObject(){
        var tmpSelectLayerIds = "";
        var tmpSelectedRBRegion = "";
        var tmpSelectedRBRegionModule = "";
        if(parent.selectLayerIds){
            tmpSelectLayerIds = parent.selectLayerIds;
            parent.selectLayerIds="";
        }

        if(parent.selectedRBRegion){
            tmpSelectedRBRegion = parent.selectedRBRegion;
            parent.selectedRBRegion="";
        }

        if(parent.selectedRBRegionModule){
            tmpSelectedRBRegionModule = parent.selectedRBRegionModule;
            parent.selectedRBRegionModule="";
        }

        var flashvars = {};
        var params = {serverUrl: "<%=serverUrl%>",
                            coreServerUrl: "<%=serverUrl%>",
                            adminServerUrl: "<%=serverUrl%>",
                            jsessionId: "<%=jsessionId%>",
                            gekey: "<%=geKey%>",
                            viewmode: "<%=viewMode%>",
                            clientname: "<%=clientName%>",
                            geclientid: "<%=geClientId%>",
                            userId: "<%=userId%>",
							userName: "<%=userName%>",
                            groupId: "<%=groupId%>",
                            domainId: "<%=domainId%>",
                            moduleId: "<%=moduleId%>",
                            languageId: "<%=languageId%>",
                            languageCd: "<%=languageCd%>",
                            localeChain: "<%=languageCd%>",
                            expandtree: "<%=expandTree%>",
                            selectedLayers: "<%=selectedLayers%>",
                            selectFavorite: "<%=selectFavorite%>",
                            defaultlat: "<%=defaultLat%>",
                            defaultlon: "<%=defaultLon%>",
                            defaultrange: "<%=defaultRange%>",
                            isHighlightPlacemark:"<%=isHighlightPlacemark %>",
                            defaultview: "<%=defaultView%>",
                            rasEnabled: "<%=rasEnabled%>",
                            rasInobxUrl: "<%=rasInobxUrl%>",
                            selectLayerIds: tmpSelectLayerIds,
                            selectedRBRegion: tmpSelectedRBRegion,
                            selectedRBRegionModule: tmpSelectedRBRegionModule,
                            sessionStatusTimerInterval:"<%=sessionStatusTimerInterval%>",
                            theme: "<%=appTheme%>",
							<%if(!StringUtils.isNull(ecoexpml)) {%>ecoexpml:"<%=ecoexpml%>",<%}%>
                            wmode:"transparent",
                            noOfMessages:"<%=noOfMessages%>",
                            zoomRange:"<%=zoomRange%>",
                            disableTour:"<%=disableTour%>",
                            disableSound:"<%=disableSound%>",
                            alertOffset:"<%=alertOffset%>",
                            defaultPriority:"<%=defaultPriority%>",
                            enablegs:"<%=enableGS%>",
                            <%if(enableGS) {%>gscomponenturl:"<%=gsComponentUrl%>",<%}%>
                            <%if(gsLaunchInWindow) {%>gslaunchinwindow:"<%=gsLaunchInWindow%>",<%}%>
							<%if(!StringUtils.isNull(pluginJSUrl)) {%>customJSUrlMapPlugin:"<%=pluginJSUrl%>",<%}%>
                            alertFile:"<%=alertFile%>",
                            tickerModeId:"<%=tickerModeId%>",
							mmTickerModeId:"<%=mmTickerModeId%>",
                            alarmModeId:"<%=alarmModeId%>",
							tourModeId:"<%=tourModeId%>",
                            messageScrollSpeed:"<%=messageScrollSpeed%>",
							messageScrollCount:"<%=messageScrollCount%>",
							tourRepeatCount:"<%=tourRepeatCount%>",
							maxTourAlerts:"<%=maxTourAlerts%>",
							dashboardsLayout:"<%=dashboardsLayout%>",
							dashboardsLayoutNumCols:"<%=dashboardsLayoutNumCols%>",
							dashboardsLayoutNumRows:"<%=dashboardsLayoutNumRows%>",
							autoAckSnoozeInterval:"<%=autoAckSnoozeInterval%>",
							idColumn:"<%=idColumn%>",
							autoFitColumns:"<%=autoFitColumns%>",
							helpUrl:"<%=helpUrl%>",
							mappingType:"<%=mappingType%>"
							};
       var attributes = {
        id: "GEFrame",
        name: "GEFrame",
        style: "z-index:1;border: 1px solid silver; height: 100%; width: 100%; position:absolute; left:0px;top:0px;",
        wmode:"transparent"
       };


        var geFrameUrl = "<%=serverUrl%>ge/viewer/<%=swfName%>";
        swfobject.embedSWF(geFrameUrl, "geFrame", "100%", "100%", "9.0.0",flashvars, params, attributes);
    }
    createGEFrameObject();
</script>
<div id='geFrame' style="z-index:1"></div>
<div id="geFrameDiv" style="position:absolute;background-color:transparent;border:0px;visibility:hidden"></div>
<div id="dashboardFrameDiv" style="position:absolute;background-color:transparent;border:0px;visibility:hidden;z-index:1"></div>
</body>
</html>