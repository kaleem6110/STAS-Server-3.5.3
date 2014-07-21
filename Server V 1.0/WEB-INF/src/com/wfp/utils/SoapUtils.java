/**
 * 
 */
package com.wfp.utils;

import java.rmi.RemoteException;
import java.util.Calendar;

import lu.hitec.pss.soap.sensor.client._12_x.LocationRange;
import lu.hitec.pss.soap.sensor.client._12_x.RangeLimit;
import lu.hitec.pss.soap.sensor.client._12_x.SubRangeType;
import lu.hitec.pss.soap.sensor.client._12_x.UnitId;
import lu.hitec.pss.soap.sensor.client._12_x.UnitType;

import com.wfp.jobs.SoapTrackingJob;

/**
 * @author kaleem.mohammed
 * 
 */
public class SoapUtils implements IEPICConstants {
	

	public static String getAllWaypointsCount(String unitId, String type,
			String mission, int lpCount) 
	{
		String count = "0";
		try 
		{

			RangeLimit rl = SensorServiceUtils.getRangeLimit(SoapTrackingJob.getStartDateTime(), SoapTrackingJob.getEndDateTime(),
															SubRangeType.CONTINUOUS_LATEST, lpCount);
			LocationRange locationRange = null;
			if (type.equalsIgnoreCase("VEHICLE"))
				locationRange = SensorServiceUtils.getServiceLocatorStub()
						.getUnitLocationRange(EventServiceUtils.getLDAPToken(),new UnitId(unitId, UnitType.VEHICLE), mission,rl);
			else
				locationRange = SensorServiceUtils.getServiceLocatorStub()
						.getUnitLocationRange(EventServiceUtils.getLDAPToken(),new UnitId(unitId, UnitType.USER), mission, rl);
			System.out.println( locationRange +" lr : val : "+ locationRange.getVal() );
			if (locationRange != null && locationRange.getVal() != null) 
			{
				count = locationRange.getVal().length + "";
				Calendar start = locationRange.getVal()[locationRange.getVal().length - 1]
						.getTime();
				Calendar end = locationRange.getVal()[0].getTime();
				Double timeinMilli = Math.ceil((end.getTime().getTime() - start.getTime().getTime())/ (1000 * 60 * 60 * 24.0));

				return CommonUtils.formatDate(start.getTime())+"DELIM"+ count + "DELIM" + timeinMilli.intValue();
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;

	}

	public static void main(String a[]) {
		System.out.println(getAllWaypointsCount("PK-UN-67-1704", "VEHICLE",
				"Pakistan_support", 10000));
	}

}
