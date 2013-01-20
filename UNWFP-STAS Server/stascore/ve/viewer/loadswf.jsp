<%
    String url = request.getParameter("url");
    String swfObject = request.getParameter("swfobject");
%>
<HTML>
<HEAD>
<TITLE>Space-Time Insight SWF Plugin</TITLE>
<script type="text/javascript">
function _refresh(){
    debugger;
    parent._refresh();
}

function _refreshLink(name){
    parent._refreshLink(name);
}

function _lookat(coordStr) {
    parent._lookat(coordStr);
}
</script>

<style>
    body { margin: 0px; overflow:hidden }
</style>

</HEAD>
<BODY>
<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
codebase="http://fpdownload.adobe.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0" 
WIDTH="100%" HEIGHT="100%" id="<%=swfObject%>">
<PARAM NAME="movie" VALUE="<%=url%>&theme=blue"> 
<PARAM NAME="quality" VALUE="high">
<PARAM NAME="play" VALUE="true">
<PARAM NAME="loop" VALUE="true">
<EMBED src="<%=url%>&theme=blue" quality=high WIDTH="100%" HEIGHT="100%" 
NAME="<%=swfObject%>" ALIGN="" TYPE="application/x-shockwave-flash" 
play="true" loop="true" 
PLUGINSPAGE="http://www.adobe.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash">
</EMBED>
</OBJECT>
</BODY>
</HTML>
