package com.wfp.custom.datasource;

import com.enterprisehorizons.magma.datamashup.BaseGeoDataSource;

/**
 * Soap Datasource having name & time properties
 * 
 * @author sti-user
 * 
 */
public class GeofenceDataSource extends BaseGeoDataSource {

	private static final long serialVersionUID = 0L;

	private String fenceType;
	

	public GeofenceDataSource() {
	}


	/**
	 * @return the fenceType
	 */
	public String getFenceType() {
		return fenceType;
	}


	/**
	 * @param fenceType the fenceType to set
	 */
	public void setFenceType(String fenceType) {
		this.fenceType = fenceType;
	}


	

	

}
