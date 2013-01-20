<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@page import="com.enterprisehorizons.constants.CommonConstants"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.CommonConfigUtils" %>
<%@page import="com.spacetimeinsight.stas.config.GenericConfigurationManager"%>
<%@page import="com.spacetimeinsight.stas.config.ConfigurationConstants"%>
<%@page import="com.enterprisehorizons.util.StringUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.AdminConfigUtils"%>
<%@page import="com.enterprisehorizons.magma.artifact.search.GlobalSearchController"%>

<%
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
    String viewMode = request.getParameter("viewmode");
    String clientName = request.getParameter("clientname");
    String title = request.getParameter("title");
    String expandTree = request.getParameter("expandtree");
    String defaultLat = request.getParameter("defaultlat");
    String defaultLon = request.getParameter("defaultlon");
    String defaultRange = request.getParameter("defaultrange");
    String selectedLayers = request.getParameter("selectedLayers");
    String swfName = request.getParameter("swf");
    String ecoexpml = request.getParameter("ecoexpml");
	String disableTour = request.getParameter("disableTour");
    String disableSound = request.getParameter("disableSound");
    String alertOffset = request.getParameter("alertOffset");

    String appTheme = AdminConfigUtils.getApplicationTheme();
    if (appTheme == null || appTheme.equals("null")) {
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

    if(title == null) {
        title = "";
    }

    if(geClientId == null) {
        geClientId = "";
    }


    if(domainId == null) {
        domainId = "";
    }

    if(userId == null) {
        userId = "";
    }

    if(moduleId == null) {
        moduleId = "";
    }

    if(languageId == null) {
        languageId = "";
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
        swfName = "ArcGISFrame.swf";
    }

    //String coreServerUrl = serverRootUrl+CommonConstants.FORWARD_SLASH + CommonConfigUtils.getConfigValue("coreservercontext")+CommonConstants.FORWARD_SLASH;
    //String adminServerUrl = coreServerUrl;//serverRootUrl+CommonConstants.FORWARD_SLASH + CommonConfigUtils.getConfigValue("adminservercontext")+CommonConstants.FORWARD_SLASH;
%>
<jsp:include page="/common/commonheader.jsp"/>
<html lang="en">


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

function clearAllEventMessages(_selectedEventId){
    sendToFlash("geFrame", "_clearAllEventMessages", _selectedEventId);
}
/*
			method to change view from java script 
			params :_selectedView :Object 
			0(GE View only)
			1(Dashboard View Only)
			2(both GE& dashboard View)
*/
function changeView(_selectedView){
 	sendToFlash("geFrame", "_changeView", _selectedView);
}
</script>
<script type="text/javascript" src="<%=serverUrl %>ge/viewer/magma_ajax.js"></script>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/swfobject.js"></script>
<script type="text/javascript" src="<%=serverUrl %>ge/viewer/motionpack.js"></script>
<script type="text/javascript" src="<%=serverUrl %>ge/viewer/AC_OETags.js" ></script>

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

function removeIFrame(divId,iframeId){
    var divObj = document.getElementById(divId);
    //var index = swfObjectsArr.indexOf(iframeId+"_swf");
    var index = swfObjectsArr.indexOf(iframeId);
    sendToFlash(getSWFId(iframeId), "_closeWindow", "");
    swfObjectsArr.remove(index);
    divObj.parentNode.removeChild(divObj);
}

function getSWFId(swfObjectsId) {
	return "swf_"+swfObjectsId.replaceAll(" ","_");
}

function refreshWindows(autoRefreshOn) {
    refreshDashboards('',autoRefreshOn);
}

function refreshDashboards(layerName,autoRefreshOn) {
    try{
        for(i=0;i<swfObjectsArr.length;i++){
            if(layerName == '' || swfObjectsArr[i].indexOf(layerName+"_") == 0) {
				if(autoRefreshOn){
					sendToFlash(getSWFId(swfObjectsArr[i]), "_viewchanged", "");
				} else{
					sendToFlash(getSWFId(swfObjectsArr[i]), "_refresh", "");
				}
			}
        }
    } catch (e){
        alert('Error ['+e.message+'] occurred while refreshing the windows');
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

function openKml(url, name){
    try {
        window.frames["geIFrame"].openKml(url,name);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while opening kml ['+url+']');
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
		sendToFlash("geFrame", "_refreshLink", name);
        //window.frames["geIFrame"]._refreshLink(name);
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

function _lookat(coordStr) {
    try {
		sendToFlash("geFrame", "_lookat", coordStr);
        //window.frames["geIFrame"]._lookat(coordStr);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while calling look at');
    }
}

function _highlight(coordStr) {
    try {
		sendToFlash("geFrame", "_highlight", coordStr);
        //window.frames["geIFrame"]._highlight(coordStr);
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
		sendToFlash("geFrame", "_setHighlightPlacemarkStyle", style);
		//window.frames["geIFrame"]._setHighlightPlacemarkStyle(style);
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
}

function _refreshTimeLayers(){
    sendToFlash("geFrame", "_refreshTimeLayers");
}

/*
var designerMode = "GEFRAME";
var operation = "insert";
    function updateMouseNavigationGEMap(mode){
        if(mode =="activate"){
            window.frames["geIFrame"].activate('activate');
        }else if(mode =="deActivate"){
            window.frames["geIFrame"].activate('deActivate');
        }else if(mode =="draggable"){
            window.frames["geIFrame"].activate('draggable');
        }else if(mode == "polygon"){
            window.frames["geIFrame"].drawPolygon();
        }else if(mode == "save"){
            window.frames["geIFrame"].save2AnalysisBasket();
        }else if(mode == "lineString"){
            window.frames["geIFrame"].drawLineStringPlacemark();
        }
    }

    function invokeDialogBox(){
        sendToFlash("geFrame", "_popUp", "");
    }

    function getJobName(jobNameValue, startDateValue, endDateValue, coordinatesValue){
        sendToFlash("geFrame", "_showGE", "true");
        window.frames["geIFrame"].createTable(jobNameValue, startDateValue, endDateValue, coordinatesValue);
    }



    function addGEPlacemark(jobName, jobName, polygonType, startDateValue, endDateValue, coordinatesValue, viewPort){
        //alert(jobName+","+polygonType+","+startDateValue+","+ endDateValue+","+ coordinatesValue+","+viewPort);
        sendToFlash("geFrame", "_savePlacemark", jobName+","+jobName+","+polygonType+","+startDateValue+","+endDateValue+","+coordinatesValue+","+viewPort+","+operation);
    }

    function drawPlacemarkOnGE(id){
        //window.frames["geIFrame"].showPlacemark(id, true);
    }

    function drawPolyPlacemarkOnGE(id){
        //window.frames["geIFrame"].showPolygon(id, true);
    }

    function hidePlacemarkOnGE(id){
        //window.frames["geIFrame"].showPlacemark(id, false);
    }

    function hidePolyPlacemarkOnGE(id){
        //window.frames["geIFrame"].showPolygon(id, false);
    }

    function updatePlacemarksOnGE(placemarkCount){
        sendToFlash("geFrame", "_updatePlacemarkOnGE", placemarkCount);
    }


    function deletePlacemarksOnGE(placemarkIds){
        try{
           // window.frames["geIFrame"].deleteRubberbandedPlacemark(placemarkIds);
        }catch(err){
        }

        try{
           // window.frames["geIFrame"].deletePolygonPlacemark(placemarkIds);
        }catch(er){}
    }

    function deleteAllPlacemarks(){
        //window.frames["geIFrame"].deleteRubberbandedFeatures(0);
        changeButtonState("save", "true");
    }

    function resetGoogleEarth(placemarkCount){
        sendToFlash("geFrame", "_resetGoogleEarth", placemarkCount);
    }

    function showAllRBRegions(allRegionsArrayWithRegionId){
        var allRegionsArray = allRegionsArrayWithRegionId[0].allRegions;
        var selectedRegionId = allRegionsArrayWithRegionId[0].selectedRegionId;
        var polygonType = allRegionsArrayWithRegionId[0].polygonType;

        var i=0;
        for(i=0; i<allRegionsArray.length;i++){
             //window.frames["geIFrame"].document.getElementById('savedData').value = allRegionsArray[i].regionId +"\n " +allRegionsArray[i].regionName+"\n"+allRegionsArray[i].startDate+"\n"+allRegionsArray[i].endDate+"\n"+allRegionsArray[i].coordinates;
            if(selectedRegionId == allRegionsArray[i].regionId && polygonType == 'polygon'){
                window.frames["geIFrame"].showPolygon(allRegionsArray[i].coordinates, selectedRegionId);
                window.frames["geIFrame"].gex.util.deserializeView(allRegionsArray[i].viewPort);
            }else if(selectedRegionId == allRegionsArray[i].regionName && polygonType == 'lineString'){
                window.frames["geIFrame"].showLineString(allRegionsArray[i].coordinates, selectedRegionId);
                window.frames["geIFrame"].gex.util.deserializeView(allRegionsArray[i].viewPort);
            }else if(selectedRegionId == allRegionsArray[i].regionId && polygonType == 'dragPlacemark'){
                window.frames["geIFrame"].showDragPlacemark(allRegionsArray[i].coordinates, selectedRegionId);
                window.frames["geIFrame"].gex.util.deserializeView(allRegionsArray[i].viewPort);
            } else if(selectedRegionId == allRegionsArray[i].regionId && polygonType == 'rectangle'){
                window.frames["geIFrame"].showPolygon(allRegionsArray[i].coordinates, selectedRegionId);
                window.frames["geIFrame"].gex.util.deserializeView(allRegionsArray[i].viewPort);
            }
        }
    }

    function removeRegion(regionIdToRemove){
        window.frames["geIFrame"].removePolygon(regionIdToRemove);
    }

    function changeButtonState(buttonName, status){
        sendToFlash("geFrame", "_changeButtonState", buttonName+","+status);
    }

    function editRBRegions(allRBRegionsWithSelectedRegionIdToEdit){
        var allRegionsArray = allRBRegionsWithSelectedRegionIdToEdit[0].allRegions;
        var selectedRegionIdToEdit = allRBRegionsWithSelectedRegionIdToEdit[0].selectedRegionId;
        var polygonType = allRBRegionsWithSelectedRegionIdToEdit[0].polygonType;
        operation = 'update';
        var i=0;

        for(i=0; i<allRegionsArray.length;i++){
             //window.frames["geIFrame"].document.getElementById('savedData').value = allRegionsArray[i].regionId +"\n " +allRegionsArray[i].regionName+"\n"+allRegionsArray[i].startDate+"\n"+allRegionsArray[i].endDate+"\n"+allRegionsArray[i].coordinates;

            if(selectedRegionIdToEdit == allRegionsArray[i].regionId && polygonType == 'polygon'){
                window.frames["geIFrame"].editPolygon(allRegionsArray[i].coordinates, selectedRegionIdToEdit);
                window.frames["geIFrame"].gex.util.deserializeView(allRegionsArray[i].viewPort);
            }else if(selectedRegionIdToEdit == allRegionsArray[i].regionName && polygonType == 'lineString'){
                window.frames["geIFrame"].editPolyLineString(allRegionsArray[i].coordinates, selectedRegionIdToEdit);
                window.frames["geIFrame"].gex.util.deserializeView(allRegionsArray[i].viewPort);
            }else if(selectedRegionIdToEdit == allRegionsArray[i].regionId && polygonType == 'dragPlacemark'){
                window.frames["geIFrame"].showDragPlacemark(allRegionsArray[i].coordinates, selectedRegionIdToEdit);
                window.frames["geIFrame"].gex.util.deserializeView(allRegionsArray[i].viewPort);
            } else if(selectedRegionIdToEdit == allRegionsArray[i].regionId && polygonType == 'rectangle'){
                window.frames["geIFrame"].showPolygon(allRegionsArray[i].coordinates, selectedRegionIdToEdit);
                window.frames["geIFrame"].gex.util.deserializeView(allRegionsArray[i].viewPort);
            }

        }
    }

    function getDataForUpdatePlacemark(allRBRegionsWithSelectedRegionIdToUpdate){
        var allRegionsArray = allRBRegionsWithSelectedRegionIdToUpdate[0].allRegions;
        var selectedRegionIdToEdit = allRBRegionsWithSelectedRegionIdToUpdate[0].selectedRegionId;
        var polygonType = allRBRegionsWithSelectedRegionIdToUpdate[0].polygonType;
        operation = 'update';
        var i=0;
        for(i=0; i<allRegionsArray.length;i++){
            //window.frames["geIFrame"].document.getElementById('savedData').value = allRegionsArray[i].regionId +"\n " +allRegionsArray[i].regionName+"\n"+allRegionsArray[i].startDate+"\n"+allRegionsArray[i].endDate+"\n"+allRegionsArray[i].coordinates;
            try{
                window.frames["geIFrame"].setFinalCoordinatesString(window.frames["geIFrame"].globalPlacemarkObject.getGeometry().getCoordinates());
            }catch(er){}
            if(selectedRegionIdToEdit== allRegionsArray[i].regionId && polygonType == 'polygon'){
                window.frames["geIFrame"].setFinalCoordinatesString(window.frames["geIFrame"].globalPlacemarkObject.getGeometry().getOuterBoundary().getCoordinates());
                var cord = window.frames["geIFrame"].document.getElementById('coordinates').value;
                sendToFlash("geFrame", "_savePlacemark", selectedRegionIdToEdit+","+selectedRegionIdToEdit+","+allRegionsArray[i].polygonType+","+allRegionsArray[i].startDate+","+allRegionsArray[i].endDate+","+cord+","+allRegionsArray[i].viewPort+","+operation);
            }else if(selectedRegionIdToEdit == allRegionsArray[i].regionId && polygonType == 'lineString'){
                var cord = window.frames["geIFrame"].document.getElementById('coordinates').value;
                sendToFlash("geFrame", "_savePlacemark", selectedRegionIdToEdit+","+selectedRegionIdToEdit+","+allRegionsArray[i].polygonType+","+allRegionsArray[i].startDate+","+allRegionsArray[i].endDate+","+cord+","+allRegionsArray[i].viewPort+","+operation);
            }
        }

        window.frames["geIFrame"].isEditEnabled = false;
    }

function removeAllPlacemarks(regionIdToRemove){
    var totalRegions = window.frames["geIFrame"].ge.getFeatures().getChildNodes().getLength();
        for(k=0;k<totalRegions;k++){
            var region = window.frames["geIFrame"].ge.getFeatures().getChildNodes().item(k);
            if(region.getName() == null || region.getName() == ""){
                window.frames["geIFrame"].ge.getFeatures().removeChild(window.frames["geIFrame"].ge.getFeatures().getChildNodes().item(k));
            }
        }
}
*/

function drawRBFeature(type) {
    try {
        window.frames["geIFrame"].drawRBFeature(type);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while drawing ['+type+'] for rubberbanding');
    }
}

function drawRBFeatureModule(type) {
    try {
        window.frames["geIFrame"].drawRBFeatureModule(type);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while drawing module ['+type+'] for rubberbanding');
    }
}

function resetRBFeature(type) {
    try {
        window.frames["geIFrame"].resetRBFeature(type);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while drawing ['+type+'] for rubberbanding');
    }
}


function createRBPolygon(id, coordStr, flag) {
    try {
        window.frames["geIFrame"].createRBPolygon(id, coordStr, flag);
    } catch (e) {
        alert('Error ['+e.message+'] occurred while creating polygon feature by id = ['+id+'] for rubberbanding');
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

function showRBFeatureModule(id, viewportStr, flag) {
    try {
        window.frames["geIFrame"].showRBFeatureModule(id, viewportStr, flag);
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

function setStreetViewLocation(frameId, coords) {
    try {
		window.frames[frameId+"_"+frameId].setStreetViewLocation(coords);
    } catch (e) {
       // alert('Error ['+e.message+'] occurred setting the streetview location');
    }
}

function updateCoordinates(id, coordsStr){
    sendToFlash("geFrame", "_updateCoordinates", id+":"+coordsStr);
}

function selectRow(id){
    sendToFlash("geFrame", "_selectRow", id);
}

function addFeature(id, coordsStr, featuretype){
    sendToFlash("geFrame", "_addFeature", featuretype+":"+id+":"+coordsStr);
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
        return flashMovie.callFlashFromJS(method, data);
    }
    catch (e) {
        //alert('Error ['+e.message+'] occurred while calling a function on the flash object');
    }
    return "";
}

function setSessionId(name, sessionId, artifactName) {
    sendToFlash("geFrame", "_setSessionId", name+","+sessionId+","+artifactName);
}

function fetchKmlLoaded(ecoexpml) {
    sendToFlash("geFrame", "_fetchKmlLoaded", ecoexpml);
}

function _getCurrentBoundingBox() {
    try {
        return sendToFlash("geFrame", "_getCurrentBoundingBox");
        //return window.frames["geIFrame"]._getCurrentBoundingBox();
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
function openGMaps(){
   openInfoframeById('GMaps','<%=ServerUtils.getContextName(request)%>/ge/viewer/GMaps.jsp?key=<%=geKey%>');
}
 function _setTimeRange(timeStr) {
    sendToFlash("geFrame", "_setTimeRange", timeStr);
 }

 function gotoHome(){
    document.geLinkForm.action = '/magmage/wfts/stihomepage.jsp';
    document.geLinkForm.submit();
}

function openHelpWindow(){
    window.open('serverresources<%=AdminConfigUtils.getPortalUserGuide() %>', 'ESRIUserGuide', 'width='+screen.width/2+',height='+screen.height+', resizable=yes, toolbar=no, location=no, directories=no, status=no, menubar=no,scrollbars=yes');
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

function openDashboardFrameById(id, urlToOpen,layerId,layerName, windowId){
    sendToFlash("geFrame", "_loadDashboardFrameById", id+","+urlToOpen+","+layerId+","+layerName+","+windowId);
}

function getViewFormatParams() {
	/* please add required functionality for focus @Honey
	var latLonBox = ge.getView().getViewportGlobeBounds();
	var lookAt =  ge.getView().copyAsLookAt(ge.ALTITUDE_ABSOLUTE);
	return  "&bboxWest="+latLonBox.getWest()+"&bboxSouth="+latLonBox.getSouth()+"&bboxEast="+latLonBox.getEast()+"&bboxNorth="+latLonBox.getNorth()+
		"&lookatLon="+lookAt.getLongitude()+"&lookatLat=="+lookAt.getLatitude()+"&lookatRange="+lookAt.getRange()+"&lookatTilt="+lookAt.getTilt()+
		"&lookatHeading="+lookAt.getHeading();
	*/
}

function _zoom(xMin, yMin, xMax, yMax){
	sendToFlash("geFrame", "_zoom", xMin+","+yMin+","+xMax+","+yMax);
}

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
	parent.invalidateSession();   
	alert(parent.SESSION_EXPIRE_ALERT);
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

function createGEFrameObject(){
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
                            gekey: "<%=geKey%>",
                            viewmode: "<%=viewMode%>",
                            clientname: "<%=clientName%>",
                            geclientid: "<%=geClientId%>",
                            userId: "<%=userId%>",
                            groupId: "<%=groupId%>",
                            domainId: "<%=domainId%>",
                            moduleId: "<%=moduleId%>",
                            languageId: "<%=languageId%>",
                            languageCd: "<%=languageCd%>",
                            localeChain: "<%=languageCd%>",
                            expandtree: "<%=expandTree%>",
                            selectedLayers: "<%=selectedLayers%>",
                            defaultlat: "<%=defaultLat%>",
                            defaultlon: "<%=defaultLon%>",
                            defaultrange: "<%=defaultRange%>",
                            rasEnabled: "<%=rasEnabled%>",
                            rasInobxUrl: "<%=rasInobxUrl%>",
                            selectLayerIds: tmpSelectLayerIds,
                            selectedRBRegion: tmpSelectedRBRegion,
                            selectedRBRegionModule: tmpSelectedRBRegionModule,
							basemapurl:"<%=AdminConfigUtils.getESRIArcviewerBaseMapUrl()%>",
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
                            alertFile:"<%=alertFile%>"
                            };
       var attributes = {
        id: "GEFrame",
        name: "GEFrame",
        style: "z-index:1;border: 1px solid silver; height: 100%; width: 100%; position:absolute; left:0px;top:0px;",
        wmode:"transparent"
       };


        var geFrameUrl = "<%=serverUrl%>esri/viewer/<%=swfName%>";
        swfobject.embedSWF(geFrameUrl, "geFrame", "100%", "100%", "9.0.0",flashvars, params, attributes);
    }
    createGEFrameObject();
</script>
<div id='geFrame' style="z-index:1"></div>
<div id="geFrameDiv" style="position:absolute;background-color:transparent;border:0px;visibility:hidden"></div>
<div id="dashboardFrameDiv" style="position:absolute;background-color:transparent;border:0px;visibility:hidden;z-index:1"></div>
</body>
<form name="geLinkForm" method="post">
    <input type="hidden" name="navSource" value="ge">
</form>
</html>