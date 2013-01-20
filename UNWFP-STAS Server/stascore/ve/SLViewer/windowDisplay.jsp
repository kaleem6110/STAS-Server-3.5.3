<%@page import="com.enterprisehorizons.constants.CommonConstants"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%@page import="com.enterprisehorizons.magma.server.admin.CommonConfigUtils" %>
<%@page import="com.enterprisehorizons.util.StringUtils, java.net.*"%>
<%
    String url = request.getParameter("url").replace("lookatfunction=", "lookatfunction=parent.");
    String winName = request.getParameter("Name");
    String winWidth = request.getParameter("width");
    String winHeight = request.getParameter("height");
    String IsCameraSensitive = "false";
    if (request.getParameter("isCameraSensitive")!=null)
    	IsCameraSensitive=request.getParameter("isCameraSensitive");
     
%>

<% 
    URLDecoder urlDecode = new URLDecoder();
%>
             
             
             
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
     <title></title>
     <style type="text/css">
         html, body
         {
             margin: 0;
             padding: 0;
             height: 100%;
             width: 100%;
             overflow: hidden;
         }
         #myContent
         {
             height: 100%;
             width: 100%;
             overflow: hidden;
         }
     </style>
 
     <script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/swfobject.js"></script>
 
     <script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/ve/SLViewer/VEFunctions.js"></script>
 
     <script type="text/javascript">
 		var winWidth = "<%=winWidth %>";
		var winHeight =  "<%=winHeight %>";
		
         function loadDashboard() {
         if (winWidth.indexOf("%") != -1) {
	                 if (parseInt(winWidth.substring(0, winWidth.indexOf("%"))) > 100 || parseInt(winHeight.substring(0, winHeight.indexOf("%"))) > 100) {
	                     document.getElementById("Body").style.overflow = "auto";
	                 }
            	}
             var flashvars = { allowScriptAccess: "always", allowNetworking: "all" };
             var params = { allowScriptAccess: "always", allowNetworking: "all" };
             var attributes = { allowScriptAccess: "always", allowNetworking: "all" };
             
             url= '<%=urlDecode.decode(urlDecode.decode(url)) %>';
             swfobject.embedSWF(url, "myContent", winWidth, winHeight, "9.0.0", flashvars, params, attributes);
             
             document.getElementById("hdnName").value = '<%=winName %>';
             document.getElementById("isCameraSensitive").value ='<%=IsCameraSensitive %>';
             
         }
         function getWindowName() {
             return document.getElementById("hdnName").value;
         }
 
 		function checkIsCameraSensitive() {
            return document.getElementById("isCameraSensitive").value;
        }
         function clearSwfObject() {
             swfobject.removeSWF("myContent");
         }
     </script>
 
 </head>
 <body id="Body" style="background-color: #D6E5F0" runat="server" onload=loadDashboard();>
     <div id="myContent" style="position: relative">
         <p>
             Alternative contents 
         </p>
     </div>
     <input type="hidden" id="hdnName" value="" />
     <input type="hidden" id="isCameraSensitive" value="" />
 </body>
</html>