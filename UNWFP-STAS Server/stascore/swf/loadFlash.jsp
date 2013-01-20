<%@page import="java.util.*"%>
<%@page import="com.enterprisehorizons.magma.server.util.ServerUtils"%>
<%
    Enumeration paramsEnum = request.getParameterNames();
    HashMap   paramsMap = new HashMap();
    while(paramsEnum.hasMoreElements()){
        String keyName = (String)paramsEnum.nextElement();
        String value = request.getParameter(keyName);
        paramsMap.put(keyName, value);
    }
    String[] paramsKey = (String[])paramsMap.keySet().toArray(new String[0]);
    String swf = (String)paramsMap.get("swf");
    String width = (String)paramsMap.get("width");
    String height = (String)paramsMap.get("height");
    if(width == null) {
        width = "100%";
    }
    if(height == null) {
        height = "100%";
    }

%>
<html>
<head>
    <script type="text/javascript" src="<%=ServerUtils.getContextName(request)%>/js/swfobject.js"></script>
    <script>
    function createSWFObject(){
        var flashvars = {};
        var params = {
<%      for(int i=0;i<paramsKey.length;i++){
            out.println(paramsKey[i]+":\""+paramsMap.get(paramsKey[i])+"\"");
            if(i!=paramsKey.length-1)out.println(",");}
%>
        };
        var attributes = {
            id: "searchWin",
            name: "search",
            style: "border: 1px solid silver; height: <%=height%>; width: <%=width%>; position:absolute; left:10px;top:215px;"
        };
    
        swfobject.embedSWF("<%=swf%>", "swfDiv", "<%=width%>", "<%=height%>", "9.0.0",flashvars, params, attributes);
    }
    </script>
</head>
<body onload="createSWFObject();">
    <div id="swfDiv" align="bottom"></div>
</body>
</html>