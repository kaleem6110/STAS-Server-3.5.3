package com.wfp.custom.datasource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lu.hitec.pss.soap.ds.out._15_x.DtoMission;
import lu.hitec.pss.soap.ds.out._15_x.PssuFence;

import org.jgrapht.graph.ListenableDirectedWeightedGraph;

import com.enterprisehorizons.exception.EHRuntimeException;
import com.enterprisehorizons.magma.datamashup.BaseGeoDataDriver;
import com.enterprisehorizons.magma.datamashup.IScriptableDataDriver;
import com.enterprisehorizons.magma.designtime.artifact.IScripter;
import com.enterprisehorizons.util.Logger;
import com.enterprisehorizons.util.StringUtils;
import com.spacetimeinsight.db.config.model.Rubberband;
import com.wfp.utils.EventServiceUtils;
import com.wfp.utils.IEPICConstants;
import com.wfp.utils.LDAPWSUtils;
import com.wfp.utils.MissionFenceWrapper;
import com.wfp.utils.RBRegionsUtils;

/**
 * allows to retrieve information about sensor devices, and the location and/or
 * probe measurements which are known for them.
 * 
 * @author sti-user
 * 
 */
public class MiddlewareGeofencesDataDriver extends BaseGeoDataDriver implements
		IScriptableDataDriver, IEPICConstants {

	private GeofenceDataSource dataSource = null;
	// private Object dataList[][] = null;
	private IScripter scripter;
	private Object response = null;

	/*
	 * Constructor initializes datasource by invoking a local call to initialise
	 * method.
	 */
	public MiddlewareGeofencesDataDriver(GeofenceDataSource datasource) {
		super(datasource);
		this.dataSource = datasource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.enterprisehorizons.magma.datamashup.IDataDriver#getData()
	 */
	@SuppressWarnings("unchecked")
	public List getData() {
		try {
			List<Rubberband> rbRegions = null;
			Rubberband r = new Rubberband();
			// setCustomSearchCriteria();
			String[] allFenceTypes = dataSource.getFenceType() != null ? dataSource
					.getFenceType().split(",")
					: null;

			String token = EventServiceUtils.getLDAPToken();

			List<PssuFence> fenceList = LDAPWSUtils.getAllFences(token);
			if (allFenceTypes != null) 
			{

				rbRegions = new ArrayList<Rubberband>();
				for (String fenceType : allFenceTypes) 
				{
					  
					
					  for( PssuFence f : fenceList )
					  {
						  if( f.getType().equals( fenceType ) )
						  {
							   
						  }
					  }
					
					/*
					 * Rubberband[] rubberbandregions =
					 * RBRegionsUtils.getUserModulePreferences(dataSource.getDomainId(),
					 * dataSource.getLanguageId() ,
					 * dataSource.getUserId(),dataSource.getUserUniqueId(),
					 * StringUtils .getLong(module));
					 * rbRegions.addAll(Arrays.asList(rubberbandregions));
					 */
				}
			}

			return rbRegions;

		} catch (Exception exception) {

			Logger.error("Exception during webservice invokation ",
					"WebServiceDataDriver", exception);
			throw new EHRuntimeException(
					"Exception during webservice invokation "
							+ exception.getMessage(), exception);
		}

	}

	@Override
	public boolean supportsSearch() {
		// TODO Auto-generated method stub
		return true;
	}

	public IScripter getScripter() {
		return scripter;
	}

	public void setScripter(IScripter scripter) {
		this.scripter = scripter;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public static void main(String args[]) {

	}

}
