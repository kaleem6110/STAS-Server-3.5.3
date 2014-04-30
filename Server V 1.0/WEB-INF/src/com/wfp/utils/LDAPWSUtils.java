/**
 * 
 */
package com.wfp.utils;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import com.enterprisehorizons.util.Logger;
import com.wfp.security.form.DeviceBean;

import lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
import lu.hitec.pss.soap.ds.out._15_x.AuthorizationException;
import lu.hitec.pss.soap.ds.out._15_x.CrudEnum;
import lu.hitec.pss.soap.ds.out._15_x.DirectoryServiceOutInterface_PortType;
import lu.hitec.pss.soap.ds.out._15_x.DirectoryServiceOutInterface_ServiceLocator;
import lu.hitec.pss.soap.ds.out._15_x.DtoMission;
import lu.hitec.pss.soap.ds.out._15_x.PssuUnit;
import lu.hitec.pss.soap.ds.out._15_x.PssuUser;
import lu.hitec.pss.soap.ds.out._15_x.PssuVehicle;
import lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
import lu.hitec.pss.soap.ds.out._15_x.UnitId;
import lu.hitec.pss.soap.ds.out._15_x.UnitType;

/**
 * @author kaleem.mohammed
 *
 */
public class LDAPWSUtils implements IEPICConstants {
	
	static DirectoryServiceOutInterface_PortType ldapStub;
	
	public static DirectoryServiceOutInterface_PortType getLDAPStub()
	{
		if( ldapStub==null  )
			try {
				ldapStub= new DirectoryServiceOutInterface_ServiceLocator().getDirectoryServiceOutInterfacePort( );
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return ldapStub;
	}
	

	
	public static PssuUnit getUnitByDeviceId(String token , String deviceId ) throws AuthorizationException, AuthenticationException, RemoteException, IllegalArgumentException
	{
		return getLDAPStub().getUnitAssignedToDeviceForCrud(token, deviceId, CrudEnum.fromString("READ") );	
	}
	public static PssuUser getUserFromDevice(String token, String deviceId) throws AuthorizationException, AuthenticationException, RemoteException, IllegalArgumentException, ServiceException
	{	
		PssuUnit  unit = getUnitByDeviceId(token, deviceId  );	 
		PssuUser user = null;
		if( unit!=null) user= getLDAPStub().getUser(token, unit.getUid() ); 
		
		return user;
	}
	public static PssuVehicle getVehicleFromDevice(String token, String deviceId ) throws AuthorizationException, AuthenticationException, RemoteException, IllegalArgumentException, ServiceException
	{	
		PssuUnit  unit = getUnitByDeviceId(token, deviceId  );	 
		PssuVehicle vehicle = null;
		if( unit!=null) vehicle= getLDAPStub().getVehicle(token,  unit.getUid() ); 
		
		return vehicle;
	}
	//unitType - USER, VEHICLE
	public static DtoMission[] getMissionsForPlace(String token, String placeId,   UnitType unitType) throws AuthorizationException, AuthenticationException, ResourceNotFoundException, RemoteException, IllegalArgumentException
	{
		 return getLDAPStub().getMissionsAssignedToUnitForCrud(token, new UnitId( placeId, unitType )
				, CrudEnum.fromString("READ") );
	}
	public static DtoMission[] getMissionsForDevice(String token, String deviceId,   UnitType unitType) throws AuthorizationException, AuthenticationException, ResourceNotFoundException, RemoteException, IllegalArgumentException
	{
		 return getLDAPStub().getMissionsAssignedToUnitForCrud(token, 
				new UnitId( getUnitByDeviceId(token, deviceId).getUid(), unitType ), CrudEnum.fromString("READ") );
	}
	public static DtoMission[] getMissionsForUser(String token, String uid,   UnitType unitType) throws AuthorizationException, AuthenticationException, ResourceNotFoundException, RemoteException, IllegalArgumentException
	{
		 return getLDAPStub().getMissionsAssignedToUnitForCrud(token, new UnitId( uid, UnitType.fromValue(UnitType._USER)), CrudEnum.fromString("READ") );
	}
	public static void getUserByUID(String token, String uid ,  DeviceBean deviceBean ) throws AuthorizationException, AuthenticationException, RemoteException, IllegalArgumentException
	{
		PssuUser user =  getLDAPStub().getUser(token, uid ) ;
		deviceBean.setPersonalTitle( user.getPersonalTitle() );
		deviceBean.setOrganization( user.getOrganizationalUnitName() );
		deviceBean.setCn( user.getFirstname() );
		deviceBean.setSn( user.getLastname() );
		
	}
	public static String getDeviceByUID( String token, String unitId, String unitType ) throws AuthorizationException, AuthenticationException, ResourceNotFoundException, RemoteException, IllegalArgumentException
	{
			String[] deviceId = getLDAPStub().getDeviceIdsAssignedToUnitForCrud(token, new UnitId(  unitId, UnitType.fromString(unitType  ) ),
					CrudEnum.fromValue( CrudEnum._READ ) );			
			return deviceId!=null?deviceId[0]:null;
	}
	public static String[] getUserProfilesByUid(  String uid  ) throws AuthorizationException, AuthenticationException, RemoteException, IllegalArgumentException 
	{	
			return getLDAPStub().getProfileIdsAssignedToUserForCrud( EventServiceUtils.getLDAPToken(), uid, CrudEnum.fromValue( CrudEnum._READ ) );	
	}
	public static DtoMission[] getMissionAssignedToPlace( String token, String placeId  ) throws AuthorizationException, AuthenticationException, RemoteException, IllegalArgumentException 
	{	if( token==null ) token = EventServiceUtils.getLDAPToken();
			return getLDAPStub().getMissionsAssignedToUnitForCrud( token, new UnitId(  placeId, UnitType.fromString(UnitType._PLACE )  ), CrudEnum.fromValue( CrudEnum._READ ) );	
	}
	public static boolean validatePlaceByUserMissions(String placeId, List<String> userMissionList)
	{
		Logger.info("Validating Places ["+placeId+"] by user missionsList ["+userMissionList+"]", LDAPWSUtils.class);
		boolean isValid = false;
		try
		{
			if(userMissionList != null&&userMissionList.size()>0 )
			{
				DtoMission[] missionArray = getMissionAssignedToPlace(null, placeId);
				if(missionArray != null && missionArray.length > 0)
				{
					for( DtoMission dto : missionArray )
					{
						if( userMissionList.contains( dto.getUniqueId() )  ) return true;
					}
				}
			}
		}catch(Exception e) { Logger.error(" Exception in validatePlaceByUserMissions()", LDAPWSUtils.class ); }
		
		return isValid;
	}
	
	public static List<String>  getUserPlacesList(  List<String> userMissionList )
	{
		List<String> userPlacesList =null;
		try
		{	
			if( userMissionList!=null&& userMissionList.size()>0 )
			{	String token = EventServiceUtils.getLDAPToken();
				userPlacesList = new ArrayList<String>();
				for(String missionId: userMissionList )
				{
					PssuUnit[] pssunit = getLDAPStub().getUnitsAssignedToMissionForCrud( token, missionId, UnitType.fromValue(UnitType._PLACE),
							CrudEnum.fromValue( CrudEnum._READ ) );
					if(pssunit!=null&&pssunit.length>0)
					{
						for(PssuUnit unit: pssunit)
						{
							userPlacesList.add( unit.getUid() );
						}
					}
				}			
			}
		}catch(Exception e){}
		return userPlacesList;
		
	}
	public static DtoMission[] getAllMissions( String token ) throws AuthorizationException, AuthenticationException, RemoteException, IllegalArgumentException
	{
		return getLDAPStub().getMissionsForCrud(token, CrudEnum.fromValue( CrudEnum._READ ) );
		
	}
	public static String[]  getUnitIdsByMission(String token,String missionId) throws AuthorizationException, AuthenticationException, ResourceNotFoundException, RemoteException, IllegalArgumentException
	{
		return  getLDAPStub().getUnitIdsAssignedToMissionForCrud(token, missionId, UnitType.fromValue(UnitType._PLACE ), CrudEnum.fromValue( CrudEnum._READ ) );
		
	}
	
	public static void main(String a[])
	{		
		
		 try {
			System.out.println( getMissionUnitsMap( EventServiceUtils.getLDAPToken() ) );
		} catch (AuthorizationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static Map<DtoMission, List<String>> getMissionUnitsMap( String token) throws AuthorizationException, AuthenticationException, RemoteException, IllegalArgumentException
	{
		Map<DtoMission, List<String>> missionUnitsMap = new HashMap<DtoMission, List<String>>();
		
		DtoMission[] allMissions = getAllMissions( token );
		
		//System.out.println(" allMissions length: "+allMissions.length );
		if( allMissions!=null && allMissions.length>0 )
		{
			for(DtoMission m: allMissions)
			{ 
				System.out.println(" m: "+m.getUniqueId() + CommonUtils.getUTCdatetimeAsString() );
				String[] unitIds = getUnitIdsByMission(token, m.getUniqueId() );	
				//System.out.println(m.getCn()+":  unitIds : "+unitIds );
				if( unitIds!=null &&  unitIds.length>0 )
				{
					List<String> unitsList = new ArrayList<String>();
					for(String unit : unitIds )
					{
						unitsList.add( unit );
					}
					missionUnitsMap.put( m , unitsList );
				}
			}			
		}				
		return missionUnitsMap;
		
	}
	public static void setMissionToPlaces( Map placesMap )
	{
		System.out.println( " ## START LDAPWSUtils.setMissionToPlaces :"+CommonUtils.getUTCdatetimeAsString() );
		// TODO Auto-generated method stub	
		String token = EventServiceUtils.getLDAPToken();
	
		Map<DtoMission, List<String>> missionUnitsMap=null;
		try {
			missionUnitsMap = getMissionUnitsMap(  token);
		} catch (AuthorizationException e1) {
			// TODO Auto-generated catch block
			System.out.println( e1.getMessage() );
		} catch (AuthenticationException e1) {
			// TODO Auto-generated catch block
			System.out.println( e1.getMessage() );
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			System.out.println( e1.getMessage() );
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			System.out.println( e1.getMessage() );
		}		
		for (Object entry : placesMap.entrySet()) 
		{
			Map.Entry<String, Map<String,String>>	e = (Map.Entry<String, Map<String,String>>)entry;
			if( missionUnitsMap!=null )
			{
				for (Object entry2 : missionUnitsMap.entrySet()) 
				{ 
					Map.Entry<DtoMission, List<String>>	e2 = (Map.Entry<DtoMission, List<String>>)entry2;					
					if( e2.getValue().contains( e.getKey() ) )
					{
						if( e.getValue().get("mission" )==null )
							e.getValue().put("mission", e2.getKey().getUniqueId()  );
						else e.getValue().put("mission",e.getValue().get("mission" )+","+ e2.getKey().getUniqueId() );
						//System.out.println( "inner :" +e.getKey() +": "+e2.getKey() );
					}
				}				
			}
		}
		System.out.println( " ## END LDAPWSUtils.setMissionToPlaces :"+CommonUtils.getUTCdatetimeAsString() );
		
	}
}
