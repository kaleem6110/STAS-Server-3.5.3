<%@page import="com.spacetimeinsight.stas.config.* , com.enterprisehorizons.magma.server.util.*"%>{<%String baseURL = ServerUtils.getServerContextBaseUrl(request);%>
	items = (
		{
			assets = (
				{
					kind = "software-package";
					url = "<%=baseURL%>tablet/ios/stasmobile.ipa";
				},
				{
					kind = "display-image";
					"needs-shine" = YES;
					url = "<%=baseURL%>tablet/ios/appicon.png";
				},
				{
					kind = "full-size-image";
					"needs-shine" = YES;
					url = "<%=baseURL%>tablet/ios/appiconlarge.png";
				},
			);
			metadata = {
				"bundle-identifier" = "<%=GenericConfigurationManager.getInstance().getProperty("mobile","APP_ID")%>";
				"bundle-version" = "<%=GenericConfigurationManager.getInstance().getProperty("mobile","APP_VERSION")%>";
				kind = software;
				subtitle = "Space-Time Insight";
				title = "Situational Intelligence for Smart Devices";
			};
		},
	);
}