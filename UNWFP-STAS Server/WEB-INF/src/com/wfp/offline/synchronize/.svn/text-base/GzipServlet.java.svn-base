package com.wfp.offline.synchronize;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprisehorizons.util.Logger;
import com.enterprisehorizons.util.StringUtils;
import com.enterprisehorizons.util.URLUtils;
import com.wfp.offline.synchronize.central.CentralService;
import com.wfp.utils.WFPConfigUtils;

public class GzipServlet extends HttpServlet {


	private static final long serialVersionUID = -960180572411211474L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Date d = new Date(); 
		long time = d.getTime();
		//System.out.println("Received the request for processing @"+ d);
		doPost(req, res);
		d = new Date();
		//System.out.println("Completed processing the request at @"+ d);
		//System.out.println("Time taken to process the request"+(d.getTime() - time));
	}

	public synchronized void doPost(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, IOException {
		Date d = new Date();
		long time = d.getTime();

		String syncXMLString = req.getParameter("syncXML");
		String terminalResponse = req.getParameter("terminalResponse");
		String encryptedLoginId = URLUtils.decode(req.getParameter("loginId"));
		String encryptedPassword = URLUtils.decode(req.getParameter("password"));
		Logger.info("Central Server:Start *********************************", GzipServlet.class);
		Logger.info("Validating request....in progress", GzipServlet.class);
		if(WFPConfigUtils.isRequestValid(encryptedLoginId, encryptedPassword)){
			Logger.info("Request validation [success]", GzipServlet.class);
			Logger.info("Checking whther the received request is a new request or a acknowledgment response from terminal server...", GzipServlet.class);
			if(StringUtils.isNull(terminalResponse)){
				Logger.info("Start: New Request for Synchronization...", GzipServlet.class);
				//System.out.println("XML Received for processing : "+URLUtils
				//		.decode(syncXMLString));
				CentralService cService = new CentralService(URLUtils
						.decode(syncXMLString));
				//System.out.println("Received the request for processing @ ["+cService.getTerminalServerName()+"]"+ d);
				res.setHeader("Content-Encoding", "gzip");
				PrintWriter out = res.getWriter();
				String content = cService.synchronizeData();
				//System.out.println("Center Server Processed XML: "+content);
				out.write(content);
				out.flush();
				out.close();
				d = new Date();
				Logger.info("End: New Request for Synchronization...", GzipServlet.class);
				//System.out.println("Completed processing the request at @"+ d);
				//System.out.println("Time taken to process the request ["+cService.getTerminalServerName()+"] "+(d.getTime() - time));
			}else if ("success".equalsIgnoreCase(terminalResponse)){
				Logger.info("Start: Acknowledgment response ["+terminalResponse+"] ", GzipServlet.class);
				CentralService cService = new CentralService(URLUtils
						.decode(syncXMLString), true);
				cService.updateTraceLogs();
				
				Logger.info("End: Acknowledgment response ["+terminalResponse+"] ", GzipServlet.class);
			}
		}else {
			Logger.info("End: Not  a valid synchronization request. please contact administrator for security token values ", GzipServlet.class);
		}
		
		Logger.info("Central Server:End *********************************", GzipServlet.class);
	}

}