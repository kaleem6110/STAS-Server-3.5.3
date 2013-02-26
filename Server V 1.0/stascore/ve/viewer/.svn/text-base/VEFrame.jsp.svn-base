<%@page import="com.enterprisehorizons.constants.CommonConstants"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.CommonConfigUtils" %>
<%@page import="com.enterprisehorizons.util.StringUtils"%>
<%
    String serverUrl = ServerUtils.getServerContextBaseUrl(request);
    String serverRootUrl = ServerUtils.getServerRootUrl(request);
    String groupId = request.getParameter("groupId");
    String domainId = request.getParameter("domainId");
    String moduleId = request.getParameter("moduleId");
    String mapMode = request.getParameter("mapMode");

    if(domainId == null) {
        domainId = "";
    }

    if(moduleId == null) {
        moduleId = "";
    }
    
    String coreServerUrl = serverRootUrl+CommonConstants.FORWARD_SLASH + CommonConfigUtils.getConfigValue("coreservercontext")+CommonConstants.FORWARD_SLASH;
    String adminServerUrl = serverRootUrl+CommonConstants.FORWARD_SLASH + CommonConfigUtils.getConfigValue("adminservercontext")+CommonConstants.FORWARD_SLASH;
%>

<html lang="en">


<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title></title>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/ve/viewer<%=ServerUtils.getContextName(request)%>_ajax.js"></script>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/swfobject.js"></script>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/ve/viewer/motionpack.js"></script>
<script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/ve/viewer/VEFrameToPlugin.js"></script>
<script src="AC_OETags.js" language="javascript"></script>

<script language="JavaScript" type="text/javascript">
var virtualEarthMapMode = "<%=mapMode%>";
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
    var divObj = document.getElementById(divId);
    if(!divObj){
        divObj = document.createElement('div');
        divObj.setAttribute('id', divId);
        divObj.style.position = "absolute"; 
        divObj.style.border = "0px"; 
        divObj.style.visibility="hidden";
        divObj.style.backgroundColor = "transparent";
        document.body.appendChild(divObj);
        swfObjectsArr.push(iframeId+"_swf");
    } 
    divObj.innerHTML = "<iframe id='"+iframeId+"' name='"+iframeId+"' src='" + url + "'frameborder='0'></iframe>";
    
}

function removeIFrame(divId){
//debugger;
    var divObj = document.getElementById(divId);
    divObj.parentNode.removeChild(divObj);
}
function refreshWindows() {
    for(i=0;i<swfObjectsArr.length;i++){
        sendToFlash(swfObjectsArr[i], "_refresh", "");
    }
    //sendToFlash("swfObject", "_refresh", "");
}

function openKml(url, name){
    try {
        window.frames["geIFrame"].openKml(url,name);
    } catch (e) {
        logAlert('Error ['+e.message+'] occurred while opening kml ['+url+']');
    }
}

function submitLocation( address ) {
    try {
        window.frames["geIFrame"].submitLocation(address);
    } catch (e) {
        logAlert('Error ['+e.message+'] occurred while submitting the location');
    }
}

function toggleLayer(layer,switchOn){
    try {
        window.frames["geIFrame"].toggleLayer(layer,switchOn);
    } catch (e) {
        logAlert('Error ['+e.message+'] occurred while submitting the location');
    }
}

function _refresh(){
    try {
        window.frames["geIFrame"]._refresh();
    } catch (e) {
        logAlert('Error ['+e.message+'] occurred while refreshing');
    }
}

function _refreshLink(name){
    try {
        window.frames["geIFrame"]._refreshLink(name);
    } catch (e) {
        logAlert('Error ['+e.message+'] occurred while refreshing link ['+name+']');
    }
}

function _lookat(coordStr) {
    try {
        window.frames["geIFrame"]._lookat(coordStr);
    } catch (e) {
        logAlert('Error ['+e.message+'] occurred while refreshing');
    }
}

function initAjax(startKmlUrl, name){
    try {
        window.frames["geIFrame"].initAjax(startKmlUrl, name);
    } catch (e) {
        logAlert('Error ['+e.message+'] occurred while in opening kml ['+startKmlUrl+']');
    }
}

function removeNetworkLink(name)  {
    try {
        window.frames["geIFrame"].removeNetworkLink(name);
    } catch (e) {
        logAlert('Error ['+e.message+'] occurred while removing networklink ['+name+']');
    }
}
function logAlert(msg){
	//alert(msg);
}
</script>
<!--- ===================================================================================================================== -->
<script>

    function updateMouseNavigationGEMap(mode){
        if(mode =="activate"){
            window.frames["geIFrame"].activate('activate');     
        }else if(mode =="deActivate"){
            window.frames["geIFrame"].activate('deActivate');       
        }else if(mode == "polygon"){
            window.frames["geIFrame"].drawPolygon();
        }else if(mode == "save"){
            window.frames["geIFrame"].save2AnalysisBasket();
        }
    }

    function invokeDialogBox(){     
        sendToFlash("geFrame", "_popUp", "");
    }
    
    function getJobName(jobNameValue){      
        sendToFlash("geFrame", "_showGE", "true");
        window.frames["geIFrame"].createTable(jobNameValue)
    }

    function showGE(){      
        sendToFlash("geFrame", "_showGE", "true");
    }
    
    function addGEPlacemark(itemCount, jobName, polygonType){       
        sendToFlash("geFrame", "_savePlacemark", itemCount+","+jobName+","+polygonType);        
    }

    function drawRectPlacemarkOnGE(id){
        window.frames["geIFrame"].showRectangle(id, true);
    }

    function drawPolyPlacemarkOnGE(id){
        window.frames["geIFrame"].showPolygon(id, true);
    }

    function hideRectPlacemarkOnGE(id){
        window.frames["geIFrame"].showRectangle(id, false);
    }

    function hidePolyPlacemarkOnGE(id){
        window.frames["geIFrame"].showPolygon(id, false);
    }

    function updatePlacemarksOnGE(placemarkCount){
        sendToFlash("geFrame", "_updatePlacemarkOnGE", placemarkCount);     
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
    var flashMovie = getFlashMovieObject(swfObjectName);
    if(flashMovie)
        flashMovie.callFlashFromJS(method, data);       
}

function setSessionId(name, sessionId) {
    sendToFlash("geFrame", "_setSessionId", name+","+sessionId);
}

 function openInfoframe(urlToOpen){
     sendToFlash("geFrame", "_loadInfoframe", urlToOpen);
 }

function createGEFrameObject(){
        var flashvars = {wmode: "transparent"};
        var params = {serverUrl: "<%=serverUrl%>",
                            coreServerUrl: "<%=coreServerUrl%>",
                            adminServerUrl: "<%=adminServerUrl%>",
                            groupId: "<%=groupId%>",
                            domainId: "<%=domainId%>",
                            moduleId: "<%=moduleId%>",
                            wmode: "transparent"};
        var attributes = {
            id: "GEFrame",
            name: "GEFrame",
            style: "border: 1px solid silver; height: 100%; width: 100%; position:absolute; left:0px;top:0px;",
            wmode: "transparent"
        };
    
        var geFrameUrl = "<%=ServerUtils.getContextName(request)%>/ve/viewer/VEFrame.swf";
        swfobject.embedSWF(geFrameUrl, "geFrame", "100%", "100%", "9.0.0",flashvars, params, attributes);
    }
    createGEFrameObject();
</script>
<div id='geFrame'></div>
<div id="geFrameDiv" style="position:absolute;background-color:transparent;border:0px;visibility:hidden;"></div>
<div id="dashboardFrameDiv" style="position:absolute;background-color:transparent;border:0px;visibility:hidden;"></div>
</body>
</html>
