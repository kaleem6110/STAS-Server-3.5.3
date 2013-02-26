<html>
<head>
	<%@ include file="/mobile/common/style.jsp"%>
</head>
<body>
				
<script>

	try{
		if(parent.document.getElementById('menu2Load').value != ""){
			parent.loadToIFrameUrl(parent.document.getElementById('menu2Load').value, '0')
		}
	}catch(er){
	}
</script>
</body>
</html>