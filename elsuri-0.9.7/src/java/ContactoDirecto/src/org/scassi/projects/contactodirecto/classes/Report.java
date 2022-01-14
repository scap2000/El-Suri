package org.scassi.projects.contactodirecto.classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Random;

import org.digitall.lib.geo.coordinatesystems.LatLongCoord;
import org.digitall.lib.sql.LibSQL;

public class Report {

    public static final int TYPE_CONTROL = 0;
    public static final int TYPE_COMPLAINT = 1;
    public static final int TYPE_WORKS = 2;
    public static final int TYPE_HEALTH = 3;
    public static final int TYPE_SOCIALNET = 4;
    public static final int TYPE_SURVEY = 5;
    public static final int TYPE_INVENTORY = 6;
    public static final int TYPE_CUSTOM = 7;
    public static final int TYPE_DEFAULT = TYPE_SOCIALNET;
    public static int[] report_types = { Report.TYPE_CONTROL, Report.TYPE_COMPLAINT, Report.TYPE_WORKS, Report.TYPE_HEALTH, Report.TYPE_SOCIALNET, Report.TYPE_SURVEY, Report.TYPE_INVENTORY, Report.TYPE_CUSTOM };

    private int[] gpsLocation;
    private String address;
    private int[] mapLocation;
    private String email;
    private long id;
    private long idReport = -1;
    private long idParent = -1;
    private String uid;
    private String url = "";
    private String type = "";
    private String description = "";
    private String picturePath;
    private String audioPath;
    private boolean finished = false;
    private boolean dataSaved = false;
    private boolean pictureSaved = false;
    private boolean audioSaved = false;
    private boolean uploading = false;
    private int rotation;
    private String myLocation;
    private boolean savePOI;
    private boolean hdPicture = false;
    private boolean HDPictureSaved;
    private String remotePictureFilename;
    private String color;
    private int idType;
    private String error;
    private int errorCount = 0;
    private boolean isPublic = false;
    private boolean saveGPSLocation = true;
    private boolean deleted;
    private String groupsArray = "{-1}";
    private String date;
    private String time;
    private String event_datetime;
    private long idGroup;
    private boolean owner = false;
    private String creatorNameEmail;
    private String ownerNameEmail;
    private boolean hasSubReports;
    private boolean hasTransfers;
    private boolean canPublish;
    private String thumbnailBase64;
    private boolean remote;
    private int subReports;
    private String HDPicturePath;
    private double value = 0.0;
    private double subReportsValue;
    private String permaLink;

    public Report() {
        //TODO set random ID and get users uid to set
        //setUid=user.uid
        //set id random
        //set event_datetime to now() in constructor
        id = (new Random()).nextLong();
        Date _date = new Date();
        time = new SimpleDateFormat("HH:mm:ss").format(_date);
        date = new SimpleDateFormat("yyyy/MM/dd").format(_date);
        event_datetime = date + " " + time;
    }

    public Report(long _idReport) {
        idReport = _idReport;
    }

    public boolean fetchData() {
        ResultSet rs = LibSQL.exFunction("cdirecto.getreport", "'no_uid'," + idReport);
        try {
            if (rs.next()) {
                setIdReport(rs.getLong("idreport"));
                setIdParent(rs.getLong("idparent"));
                String gpsLocation = rs.getString("gpslocation");
                if (gpsLocation != null) {
                    if (!gpsLocation.equals("") && !gpsLocation.equals("null")) {
                        String[] latlong = gpsLocation.split(" ");
                        setGpsLocation(new LatLongCoord(Double.parseDouble(latlong[0]), Double.parseDouble(latlong[1])));
                    }
                }
                String mapLocation = rs.getString("maplocation");
                if (mapLocation != null) {
                    if (!mapLocation.equals("") && !mapLocation.equals("null") && !mapLocation.equals("0 0")) {
                        String[] latlong = mapLocation.split(" ");
                        int lat = (int)(Double.parseDouble(latlong[0]) * 1E6);
                        int lng = (int)(Double.parseDouble(latlong[1]) * 1E6);
                        setMapLocation(lat, lng);
                    }
                }
                //report.setStatus(_jsonObject.getString("report_status"));
                setEvent_datetime(rs.getString("event_datetime"));
                setAddress(rs.getString("address"));
                setMyLocation(rs.getString("mylocation"));
                setUrl(rs.getString("url"));
                setType(rs.getString("type"));
                setDescription(rs.getString("description"));
                if (rs.getString("picturefilename") != null) {
                    setPicturePath(rs.getString("picturefilename"));
                }
                if (rs.getString("audiofilename") != null) {
                    setAudioPath(rs.getString("audiofilename"));
                }
                if (rs.getString("hdpicturefilename") != null) {
                    setHDPicturePath(rs.getString("hdpicturefilename"));
                }
                setColor(rs.getString("color"));
                setPublic(rs.getString("ispublic").equals("t"));
                setIdType(rs.getInt("idtype"));
                setGroupsArray(rs.getString("groupsarray"));
                if (rs.getString("isowner") != null) {
                    setOwner(rs.getString("isowner").equals("t"));
                }
                setCreatorNameEmail(rs.getString("creatornameemail"));
                setOwnerNameEmail(rs.getString("ownernameemail"));
                if (rs.getString("hassubreports") != null) {
                    setHasSubReports(rs.getString("hassubreports").equals("t"));
                }
                if (rs.getString("hastransfers") != null) {
                    setHasTransfers(rs.getString("hastransfers").equals("t"));
                }
                if (rs.getString("canpublish") != null) {
                    setCanPublish(rs.getString("canpublish").equals("t"));
                }
                setValue(rs.getDouble("value"));
                setSubReportsValue(rs.getDouble("subreportsvalue"));
                setPermaLink(rs.getString("permalink"));
            }
            return true;
        } catch (SQLException x) {
            x.printStackTrace();
            return false;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.trim().replaceAll("  ", " ");
    }

    public LatLongCoord getGpsLocation() {
        if (gpsLocation != null) {
            return new LatLongCoord(gpsLocation[0]/1E6, gpsLocation[1]/1E6);
        } else {
            return null;
        }
    }

    public void setGpsLocation(LatLongCoord gpsLocation) {
        this.gpsLocation = new int[2];
        this.gpsLocation[0] = gpsLocation.getLatitudeE6();
        this.gpsLocation[1] = gpsLocation.getLongitudeE6();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LatLongCoord getMapLocation() {
        if (mapLocation != null) {
            return new LatLongCoord(mapLocation[0]/1E6, mapLocation[1]/1E6);
        } else {
            return null;
        }
    }

    public void setMapLocation(int lat, int lng) {
        this.mapLocation = new int[2];
        this.mapLocation[0] = lat;
        this.mapLocation[1] = lng;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String _picturePath) {
        picturePath = _picturePath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public boolean isDataSaved() {
        return dataSaved;
    }

    public void setDataSaved(boolean saved) {
        this.dataSaved = saved;
    }

    public boolean isPictureSaved() {
        return pictureSaved;
    }

    public void setPictureSaved(boolean saved) {
        this.pictureSaved = saved;
    }

    public boolean isAudioSaved() {
        return audioSaved;
    }

    public void setAudioSaved(boolean saved) {
        this.audioSaved = saved;
    }

    public void release() {
        if (dataSaved && pictureSaved && audioSaved) {
            //TODO delete from SQLite and free space
        }
    }

    public long getID() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public void unsetMapLocation() {
        mapLocation = null;
    }

    public void rotatePicture() {
        if (picturePath != null) {
            rotation = (rotation + 90) % 360;
        }
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getRotation() {
        return rotation;
    }

    public void unsetGpsLocation() {
        gpsLocation = null;
    }

    public long getIdReport() {
        return idReport;
    }

    public void setIdReport(long idReport) {
        this.idReport = idReport;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setMyLocation(String myLocation) {
        this.myLocation = myLocation;
    }

    public String getMyLocation() {
        return myLocation;
    }

    public void setSavePOI(boolean savePOI) {
        this.savePOI = savePOI;
    }

    public boolean isSavePOI() {
        return savePOI;
    }

    public long getIdParent() {
        return idParent;
    }

    public void setIdParent(long idParent) {
        this.idParent = idParent;
    }

    public boolean isUploading() {
        return uploading;
    }

    public void setUploading(boolean uploading) {
        this.uploading = uploading;
    }

    public void setHdPicture(boolean hdPicture) {
        this.hdPicture = hdPicture;
        this.setHDPictureSaved(!hdPicture);
    }

    public boolean isHdPicture() {
        return hdPicture;
    }

    public void setHDPictureSaved(boolean HDPictureSaved) {
        this.HDPictureSaved = HDPictureSaved;
    }

    public boolean isHDPictureSaved() {
        return HDPictureSaved;
    }

    public void setRemoteFilename(String remotePictureFilename) {
        this.remotePictureFilename = remotePictureFilename;
    }

    public String getRemotePictureFilename() {
        return remotePictureFilename;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = String.format("#%06X", (0xFFFFFF & color));
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public int getIdType() {
        return idType;
    }

    public void setError(String error) {
        this.error = error;
        if (this.error != null) {
            errorCount++;
        }
    }

    public String getError() {
        return error;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public boolean saveGPSLocation() {
        return saveGPSLocation;
    }

    public void setSaveGPSLocation(boolean saveGPSLocation) {
        this.saveGPSLocation = saveGPSLocation;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setGroupsArray(String groupsArray) {
        if (groupsArray != null) {
            this.groupsArray = groupsArray;
        }
    }

    public String getGroupsArrayString() {
        return groupsArray;
    }

    public String[] getGroupsArray() {
        return groupsArray.replaceAll("[\\[\\](){}]", "").split(",");
    }

    public void setDate(String date) {
        this.date = date;
        event_datetime = this.date + " " + this.time;
    }

    public String getDate() {
        return date;
    }

    public void setTime(String time) {
        this.time = time;
        event_datetime = this.date + " " + this.time;
    }

    public String getTime() {
        return time;
    }

    public void setEvent_datetime(String event_datetime) {
        this.event_datetime = event_datetime;
    }

    public String getEvent_datetime() {
        return event_datetime;
    }

    public void setIdGroup(long idGroup) {
        this.idGroup = idGroup;
    }

    public long getIdGroup() {
        return idGroup;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public void setCreatorNameEmail(String creatorNameEmail) {
        this.creatorNameEmail = creatorNameEmail;
    }

    public String getCreatorNameEmail() {
        return creatorNameEmail;
    }

    public void setOwnerNameEmail(String ownerNameEmail) {
        this.ownerNameEmail = ownerNameEmail;
    }

    public String getOwnerNameEmail() {
        return ownerNameEmail;
    }

    public void setHasSubReports(boolean hasSubReports) {
        this.hasSubReports = hasSubReports;
    }

    public void setHasTransfers(boolean hasTransfers) {
        this.hasTransfers = hasTransfers;
    }

    public boolean hasSubReports() {
        return hasSubReports;
    }

    public boolean hasTransfers() {
        return hasTransfers;
    }

    public void setCanPublish(boolean canPublish) {
        this.canPublish = canPublish;
    }

    public boolean canPublish() {
        return canPublish;
    }

    public void setThumbnailBase64(String thumbnailBase64) {
        this.thumbnailBase64 = thumbnailBase64;
    }

    public String getThumbnailBase64() {
        return thumbnailBase64;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    public boolean isRemote() {
        return remote;
    }

    public void setSubReports(int subReports) {
        this.subReports = subReports;
    }

    public int getSubReports() {
        return subReports;
    }

    public void setHDPicturePath(String HDPicturePath) {
        this.HDPicturePath = HDPicturePath;
    }

    public String getHDPicturePath() {
        return HDPicturePath;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setSubReportsValue(double subReportsValue) {
        this.subReportsValue = subReportsValue;
    }

    public double getSubReportsValue() {
        return subReportsValue;
    }

    public void setPermaLink(String permaLink) {
        this.permaLink = permaLink;
    }

    public String getPermaLink() {
        return permaLink;
    }
}
