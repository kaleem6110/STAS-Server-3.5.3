/**
 * DirectoryServiceOutInterface_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package lu.hitec.pss.soap.ds.out._15_x;

public interface DirectoryServiceOutInterface_PortType extends java.rmi.Remote {
    public lu.hitec.pss.soap.ds.out._15_x.DtoThreshold getThreshold(java.lang.String token, java.lang.String thresholdId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoProfile[] getProfilesForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public java.lang.String[] getProfileIdsForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public java.lang.String authenticate(java.lang.String uid, java.lang.String credential, java.lang.String sessionId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoCasualty[] getCasualtiesByMission(java.lang.String token, java.lang.String missionId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public java.lang.String[] getDeviceCapabilitiesByUnitId(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.UnitId unitId, lu.hitec.pss.soap.ds.out._15_x.DeviceCapabilityEnum[] excludeDeviceCapabilities) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuDevice[] getDevicesAssignedToUnitForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.UnitId unitId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public java.lang.String[] getEquipmentIdsAssignedToKitForCrud(java.lang.String token, java.lang.String kitId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public java.lang.String authenticateWithSurfaceId(java.lang.String surfaceId, java.lang.String credential, java.lang.String sessionId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public java.lang.String[] getDeviceIdsAssignedToUnitForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.UnitId unitId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuDeviceWithAssignment[] getDevicesWithAssignmentForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuDevice getPrimaryLocalisationDevice(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.UnitId unitId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuUnit[] getUnitsAssignedToMissionForCrud(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.ds.out._15_x.UnitType unitType, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public java.lang.String[] getUserIdsAssignedToProfileForCrud(java.lang.String token, java.lang.String profileId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoMission[] getMissionsAssignedToUnitForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.UnitId unitId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public java.lang.String getKitIdAssignedToEquipmentForCrud(java.lang.String token, java.lang.String equipmentId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public java.lang.String[] getFenceIdsAssignedToMissionForCrud(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.InternalId[] getInternalIdsAssignedToKit(java.lang.String token, java.lang.String kitId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuFence[] getFencesAssignedToMissionForCrud(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public byte[] getPictureAssignedToEquipment(java.lang.String token, java.lang.String equipmentId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public java.lang.String[] getUnitIdsAssignedToMissionForCrud(java.lang.String token, java.lang.String missionId, lu.hitec.pss.soap.ds.out._15_x.UnitType unitType, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public byte[] getPictureAssignedToMission(java.lang.String token, java.lang.String missionId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.InternalId[] getInternalIdsAssignedToUnit(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.UnitId unitId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuUser[] getUsersAssignedToProfileForCrud(java.lang.String token, java.lang.String profileId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public java.lang.String[] getProfileIdsAssignedToUserForCrud(java.lang.String token, java.lang.String userId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.UnitId getUnitIdAssignedToDeviceForCrud(java.lang.String token, java.lang.String deviceId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoEquipment[] getEquipmentsAssignedToKitForCrud(java.lang.String token, java.lang.String kitId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoMission[] getMissionsAssignedToFenceForCrud(java.lang.String token, java.lang.String fenceId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoProfile[] getProfilesAssignedToUserForCrud(java.lang.String token, java.lang.String userId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public java.lang.String[] getMissionIdsAssignedToUnitForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.UnitId unitId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoKit getKitAssignedToEquipmentForCrud(java.lang.String token, java.lang.String equipmentId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoMission[] getMissionsAssignedToProfileForCrud(java.lang.String token, java.lang.String profileId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public byte[] getPictureAssignedToUser(java.lang.String token, java.lang.String userId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuUnit getUnitAssignedToDeviceForCrud(java.lang.String token, java.lang.String deviceId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public java.lang.String[] getKitIdsAssignedToUnitForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.UnitId arg1, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.UnitId getUnitIdAssignedToKitForCrud(java.lang.String token, java.lang.String kitId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public java.lang.String[] getMissionIdsAssignedToFenceForCrud(java.lang.String token, java.lang.String fenceId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuFence getFence(java.lang.String token, java.lang.String fenceId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoKit getKit(java.lang.String token, java.lang.String kitId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoEquipment getEquipment(java.lang.String token, java.lang.String equipmentId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoCasualty getCasualty(java.lang.String token, java.lang.String casualtyId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoEquipment[] getEquipmentsForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoKit[] getKitsForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuDevice[] getDevicesForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public java.lang.String[] getMissionIdsForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuDevice getDevice(java.lang.String token, java.lang.String deviceId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoMission getMission(java.lang.String token, java.lang.String missionId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuPlace[] getPlacesForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoMission[] getMissionsForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuUser[] getUsersForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoWidget getWidget(java.lang.String token, java.lang.String widgetId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuVehicle getVehicle(java.lang.String token, java.lang.String vehicleId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoRefugee getRefugee(java.lang.String token, java.lang.String refugeeId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuUser getUser(java.lang.String token, java.lang.String userId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuDevice[] searchDevices(java.lang.String token, java.lang.String searchString) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuUser[] searchUsers(java.lang.String token, java.lang.String searchString) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuVehicle[] getVehiclesForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoProfile getProfile(java.lang.String token, java.lang.String profileId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public byte[] getMyPicture(java.lang.String token) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuPlace getPlace(java.lang.String token, java.lang.String placeId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoThreshold[] getThresholdsForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public void logout(java.lang.String token) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoWidget[] getWidgetsForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuFence[] searchFences(java.lang.String token, java.lang.String searchString) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuPlace[] searchPlaces(java.lang.String token, java.lang.String searchString) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuUser getMyDetails(java.lang.String token) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.InternalId[] getInternalIdsAssignedToEquipment(java.lang.String token, java.lang.String equipmentId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public java.lang.String[] getMissionIdsByMiddlewareMember(java.lang.String token, java.lang.String mwId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public java.lang.String[] getOrganizationIdsAssignedToProfileForCrud(java.lang.String token, java.lang.String profileId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public java.lang.String[] getOrganizationIdsAssignedToUnitForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.UnitId unitId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public java.lang.String[] getMissionIdsAssignedToProfileForCrud(java.lang.String token, java.lang.String profileId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoCertification[] getCertificationsAssignedToUser(java.lang.String token, java.lang.String userId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuUser[] getUsersAssignedToCertification(java.lang.String token, java.lang.String certificationId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuDevice[] getDevicesAssignedToUnitForCrudAndCapabilities(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.UnitId unitId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud, lu.hitec.pss.soap.ds.out._15_x.DeviceCapabilityEnum[] deviceCapabilites) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoCertification[] getCertifications(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuCircularFence[] getCircularFencesForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoKit[] getKitsAssignedToUnitForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.UnitId unitId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoMiddleware[] getMiddlewaresForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public java.lang.String[] getMissionIdsForCrudFiltered(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud, lu.hitec.pss.soap.ds.out._15_x.WorkflowStatus status) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoMission[] getMissionsForCrudFiltered(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud, lu.hitec.pss.soap.ds.out._15_x.WorkflowStatus status) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoCertification[] getMyCertifications(java.lang.String token) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public java.lang.String getMyDashboardWidgets(java.lang.String token) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public java.lang.String[] getMyPermissions(java.lang.String token) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public java.lang.String[] getOrganizationIdsForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuPolygonalFence[] getPolygonalFencesForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoCertification getCertification(java.lang.String token, java.lang.String certificationId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuUnit getUnitAssignedToKitForCrud(java.lang.String token, java.lang.String kitId, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoMission[] searchMissions(java.lang.String token, java.lang.String searchString) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuPlace[] getUnAssignedPlacesForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuVehicle[] searchVehicles(java.lang.String token, java.lang.String searchString) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.DtoRefugee[] getRefugeesByMission(java.lang.String token, java.lang.String missionId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuResourceType getResourceType(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.ResourcesTypesEnum typeType, java.lang.String resourceTypeId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException, lu.hitec.pss.soap.ds.out._15_x.ResourceNotFoundException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuUser[] getUnAssignedUsersForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.Project getProjectDetails() throws java.rmi.RemoteException;
    public java.lang.String getUidBySurfaceId(java.lang.String surfaceId) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuResourceType[] getResourceTypesForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.ResourcesTypesEnum typeType, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
    public lu.hitec.pss.soap.ds.out._15_x.PssuVehicle[] getUnAssignedVehiclesForCrud(java.lang.String token, lu.hitec.pss.soap.ds.out._15_x.CrudEnum crud) throws java.rmi.RemoteException, lu.hitec.pss.soap.ds.out._15_x.AuthorizationException, lu.hitec.pss.soap.ds.out._15_x.AuthenticationException;
}
