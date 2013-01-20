package com.wfp.jobs;

import com.enterprisehorizons.util.Logger;
import com.spacetimeinsight.config.scheduler.Parameters;
import com.spacetimeinsight.magma.job.CustomJobTask;
import com.wfp.offline.synchronize.terminal.TerminalService;


public class CustomSyncJob implements CustomJobTask {

	public synchronized boolean executeCustomTask(Parameters parameters) {
		//System.out.println("");
		Logger.info("***********START SYNCHRONIZATION*************************************", TerminalService.class);
		TerminalService tService = new TerminalService();
		 
		tService.synchronizeData();
		
		Logger.info("*************END SYNCHRONIZATION***********************************", TerminalService.class);
		
		//TestTiboJMS.sendMessage();
		return true;
	}
}

