package com.xiaoan.obd.obdproject.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "OBD_TT".
*/
public class ObdTTDao extends AbstractDao<ObdTT, Long> {

    public static final String TABLENAME = "OBD_TT";

    /**
     * Properties of entity ObdTT.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Conten = new Property(1, String.class, "conten", false, "CONTEN");
        public final static Property ObdType = new Property(2, String.class, "obdType", false, "OBD_TYPE");
        public final static Property Sncode = new Property(3, int.class, "sncode", false, "SNCODE");
        public final static Property StrokeId = new Property(4, int.class, "strokeId", false, "STROKE_ID");
        public final static Property DataBytes = new Property(5, int.class, "dataBytes", false, "DATA_BYTES");
        public final static Property OccTime = new Property(6, int.class, "occTime", false, "OCC_TIME");
        public final static Property HotCarTime = new Property(7, int.class, "hotCarTime", false, "HOT_CAR_TIME");
        public final static Property TravelTime = new Property(8, int.class, "travelTime", false, "TRAVEL_TIME");
        public final static Property IdleSpeedTime = new Property(9, int.class, "idleSpeedTime", false, "IDLE_SPEED_TIME");
        public final static Property TravelMileage = new Property(10, double.class, "travelMileage", false, "TRAVEL_MILEAGE");
        public final static Property IdleFuel = new Property(11, double.class, "idleFuel", false, "IDLE_FUEL");
        public final static Property DrivingFuel = new Property(12, double.class, "drivingFuel", false, "DRIVING_FUEL");
        public final static Property AverageFuel = new Property(13, double.class, "averageFuel", false, "AVERAGE_FUEL");
        public final static Property MaxRpm = new Property(14, int.class, "maxRpm", false, "MAX_RPM");
        public final static Property MaxSpeed = new Property(15, int.class, "maxSpeed", false, "MAX_SPEED");
        public final static Property HighestTemperature = new Property(16, int.class, "highestTemperature", false, "HIGHEST_TEMPERATURE");
        public final static Property LowestTemperature = new Property(17, int.class, "lowestTemperature", false, "LOWEST_TEMPERATURE");
        public final static Property MaxAcceleration = new Property(18, double.class, "maxAcceleration", false, "MAX_ACCELERATION");
        public final static Property MinAcceleration = new Property(19, double.class, "minAcceleration", false, "MIN_ACCELERATION");
        public final static Property SolarTermDoor = new Property(20, double.class, "solarTermDoor", false, "SOLAR_TERM_DOOR");
        public final static Property AccelerationTimes = new Property(21, int.class, "accelerationTimes", false, "ACCELERATION_TIMES");
        public final static Property DecelerationTimes = new Property(22, int.class, "decelerationTimes", false, "DECELERATION_TIMES");
        public final static Property FaultCodeNum = new Property(23, int.class, "faultCodeNum", false, "FAULT_CODE_NUM");
        public final static Property SpeedingTime = new Property(24, int.class, "speedingTime", false, "SPEEDING_TIME");
        public final static Property OneMinutesIdleTimes = new Property(25, int.class, "oneMinutesIdleTimes", false, "ONE_MINUTES_IDLE_TIMES");
        public final static Property MaxTirePressure = new Property(26, String.class, "maxTirePressure", false, "MAX_TIRE_PRESSURE");
        public final static Property MinTirePressure = new Property(27, String.class, "minTirePressure", false, "MIN_TIRE_PRESSURE");
        public final static Property MaxTireTemperature = new Property(28, String.class, "maxTireTemperature", false, "MAX_TIRE_TEMPERATURE");
        public final static Property MinTireTemperature = new Property(29, String.class, "minTireTemperature", false, "MIN_TIRE_TEMPERATURE");
        public final static Property TireState = new Property(30, String.class, "tireState", false, "TIRE_STATE");
        public final static Property StopTime = new Property(31, long.class, "stopTime", false, "STOP_TIME");
        public final static Property StartTime = new Property(32, long.class, "startTime", false, "START_TIME");
        public final static Property CreateAtTime = new Property(33, String.class, "createAtTime", false, "CREATE_AT_TIME");
    };


    public ObdTTDao(DaoConfig config) {
        super(config);
    }
    
    public ObdTTDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"OBD_TT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE ," + // 0: id
                "\"CONTEN\" TEXT," + // 1: conten
                "\"OBD_TYPE\" TEXT," + // 2: obdType
                "\"SNCODE\" INTEGER NOT NULL ," + // 3: sncode
                "\"STROKE_ID\" INTEGER NOT NULL ," + // 4: strokeId
                "\"DATA_BYTES\" INTEGER NOT NULL ," + // 5: dataBytes
                "\"OCC_TIME\" INTEGER NOT NULL ," + // 6: occTime
                "\"HOT_CAR_TIME\" INTEGER NOT NULL ," + // 7: hotCarTime
                "\"TRAVEL_TIME\" INTEGER NOT NULL ," + // 8: travelTime
                "\"IDLE_SPEED_TIME\" INTEGER NOT NULL ," + // 9: idleSpeedTime
                "\"TRAVEL_MILEAGE\" REAL NOT NULL ," + // 10: travelMileage
                "\"IDLE_FUEL\" REAL NOT NULL ," + // 11: idleFuel
                "\"DRIVING_FUEL\" REAL NOT NULL ," + // 12: drivingFuel
                "\"AVERAGE_FUEL\" REAL NOT NULL ," + // 13: averageFuel
                "\"MAX_RPM\" INTEGER NOT NULL ," + // 14: maxRpm
                "\"MAX_SPEED\" INTEGER NOT NULL ," + // 15: maxSpeed
                "\"HIGHEST_TEMPERATURE\" INTEGER NOT NULL ," + // 16: highestTemperature
                "\"LOWEST_TEMPERATURE\" INTEGER NOT NULL ," + // 17: lowestTemperature
                "\"MAX_ACCELERATION\" REAL NOT NULL ," + // 18: maxAcceleration
                "\"MIN_ACCELERATION\" REAL NOT NULL ," + // 19: minAcceleration
                "\"SOLAR_TERM_DOOR\" REAL NOT NULL ," + // 20: solarTermDoor
                "\"ACCELERATION_TIMES\" INTEGER NOT NULL ," + // 21: accelerationTimes
                "\"DECELERATION_TIMES\" INTEGER NOT NULL ," + // 22: decelerationTimes
                "\"FAULT_CODE_NUM\" INTEGER NOT NULL ," + // 23: faultCodeNum
                "\"SPEEDING_TIME\" INTEGER NOT NULL ," + // 24: speedingTime
                "\"ONE_MINUTES_IDLE_TIMES\" INTEGER NOT NULL ," + // 25: oneMinutesIdleTimes
                "\"MAX_TIRE_PRESSURE\" TEXT," + // 26: maxTirePressure
                "\"MIN_TIRE_PRESSURE\" TEXT," + // 27: minTirePressure
                "\"MAX_TIRE_TEMPERATURE\" TEXT," + // 28: maxTireTemperature
                "\"MIN_TIRE_TEMPERATURE\" TEXT," + // 29: minTireTemperature
                "\"TIRE_STATE\" TEXT," + // 30: tireState
                "\"STOP_TIME\" INTEGER NOT NULL ," + // 31: stopTime
                "\"START_TIME\" INTEGER NOT NULL ," + // 32: startTime
                "\"CREATE_AT_TIME\" TEXT);"); // 33: createAtTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"OBD_TT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ObdTT entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String conten = entity.getConten();
        if (conten != null) {
            stmt.bindString(2, conten);
        }
 
        String obdType = entity.getObdType();
        if (obdType != null) {
            stmt.bindString(3, obdType);
        }
        stmt.bindLong(4, entity.getSncode());
        stmt.bindLong(5, entity.getStrokeId());
        stmt.bindLong(6, entity.getDataBytes());
        stmt.bindLong(7, entity.getOccTime());
        stmt.bindLong(8, entity.getHotCarTime());
        stmt.bindLong(9, entity.getTravelTime());
        stmt.bindLong(10, entity.getIdleSpeedTime());
        stmt.bindDouble(11, entity.getTravelMileage());
        stmt.bindDouble(12, entity.getIdleFuel());
        stmt.bindDouble(13, entity.getDrivingFuel());
        stmt.bindDouble(14, entity.getAverageFuel());
        stmt.bindLong(15, entity.getMaxRpm());
        stmt.bindLong(16, entity.getMaxSpeed());
        stmt.bindLong(17, entity.getHighestTemperature());
        stmt.bindLong(18, entity.getLowestTemperature());
        stmt.bindDouble(19, entity.getMaxAcceleration());
        stmt.bindDouble(20, entity.getMinAcceleration());
        stmt.bindDouble(21, entity.getSolarTermDoor());
        stmt.bindLong(22, entity.getAccelerationTimes());
        stmt.bindLong(23, entity.getDecelerationTimes());
        stmt.bindLong(24, entity.getFaultCodeNum());
        stmt.bindLong(25, entity.getSpeedingTime());
        stmt.bindLong(26, entity.getOneMinutesIdleTimes());
 
        String maxTirePressure = entity.getMaxTirePressure();
        if (maxTirePressure != null) {
            stmt.bindString(27, maxTirePressure);
        }
 
        String minTirePressure = entity.getMinTirePressure();
        if (minTirePressure != null) {
            stmt.bindString(28, minTirePressure);
        }
 
        String maxTireTemperature = entity.getMaxTireTemperature();
        if (maxTireTemperature != null) {
            stmt.bindString(29, maxTireTemperature);
        }
 
        String minTireTemperature = entity.getMinTireTemperature();
        if (minTireTemperature != null) {
            stmt.bindString(30, minTireTemperature);
        }
 
        String tireState = entity.getTireState();
        if (tireState != null) {
            stmt.bindString(31, tireState);
        }
        stmt.bindLong(32, entity.getStopTime());
        stmt.bindLong(33, entity.getStartTime());
 
        String createAtTime = entity.getCreateAtTime();
        if (createAtTime != null) {
            stmt.bindString(34, createAtTime);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ObdTT entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String conten = entity.getConten();
        if (conten != null) {
            stmt.bindString(2, conten);
        }
 
        String obdType = entity.getObdType();
        if (obdType != null) {
            stmt.bindString(3, obdType);
        }
        stmt.bindLong(4, entity.getSncode());
        stmt.bindLong(5, entity.getStrokeId());
        stmt.bindLong(6, entity.getDataBytes());
        stmt.bindLong(7, entity.getOccTime());
        stmt.bindLong(8, entity.getHotCarTime());
        stmt.bindLong(9, entity.getTravelTime());
        stmt.bindLong(10, entity.getIdleSpeedTime());
        stmt.bindDouble(11, entity.getTravelMileage());
        stmt.bindDouble(12, entity.getIdleFuel());
        stmt.bindDouble(13, entity.getDrivingFuel());
        stmt.bindDouble(14, entity.getAverageFuel());
        stmt.bindLong(15, entity.getMaxRpm());
        stmt.bindLong(16, entity.getMaxSpeed());
        stmt.bindLong(17, entity.getHighestTemperature());
        stmt.bindLong(18, entity.getLowestTemperature());
        stmt.bindDouble(19, entity.getMaxAcceleration());
        stmt.bindDouble(20, entity.getMinAcceleration());
        stmt.bindDouble(21, entity.getSolarTermDoor());
        stmt.bindLong(22, entity.getAccelerationTimes());
        stmt.bindLong(23, entity.getDecelerationTimes());
        stmt.bindLong(24, entity.getFaultCodeNum());
        stmt.bindLong(25, entity.getSpeedingTime());
        stmt.bindLong(26, entity.getOneMinutesIdleTimes());
 
        String maxTirePressure = entity.getMaxTirePressure();
        if (maxTirePressure != null) {
            stmt.bindString(27, maxTirePressure);
        }
 
        String minTirePressure = entity.getMinTirePressure();
        if (minTirePressure != null) {
            stmt.bindString(28, minTirePressure);
        }
 
        String maxTireTemperature = entity.getMaxTireTemperature();
        if (maxTireTemperature != null) {
            stmt.bindString(29, maxTireTemperature);
        }
 
        String minTireTemperature = entity.getMinTireTemperature();
        if (minTireTemperature != null) {
            stmt.bindString(30, minTireTemperature);
        }
 
        String tireState = entity.getTireState();
        if (tireState != null) {
            stmt.bindString(31, tireState);
        }
        stmt.bindLong(32, entity.getStopTime());
        stmt.bindLong(33, entity.getStartTime());
 
        String createAtTime = entity.getCreateAtTime();
        if (createAtTime != null) {
            stmt.bindString(34, createAtTime);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ObdTT readEntity(Cursor cursor, int offset) {
        ObdTT entity = new ObdTT( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // conten
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // obdType
            cursor.getInt(offset + 3), // sncode
            cursor.getInt(offset + 4), // strokeId
            cursor.getInt(offset + 5), // dataBytes
            cursor.getInt(offset + 6), // occTime
            cursor.getInt(offset + 7), // hotCarTime
            cursor.getInt(offset + 8), // travelTime
            cursor.getInt(offset + 9), // idleSpeedTime
            cursor.getDouble(offset + 10), // travelMileage
            cursor.getDouble(offset + 11), // idleFuel
            cursor.getDouble(offset + 12), // drivingFuel
            cursor.getDouble(offset + 13), // averageFuel
            cursor.getInt(offset + 14), // maxRpm
            cursor.getInt(offset + 15), // maxSpeed
            cursor.getInt(offset + 16), // highestTemperature
            cursor.getInt(offset + 17), // lowestTemperature
            cursor.getDouble(offset + 18), // maxAcceleration
            cursor.getDouble(offset + 19), // minAcceleration
            cursor.getDouble(offset + 20), // solarTermDoor
            cursor.getInt(offset + 21), // accelerationTimes
            cursor.getInt(offset + 22), // decelerationTimes
            cursor.getInt(offset + 23), // faultCodeNum
            cursor.getInt(offset + 24), // speedingTime
            cursor.getInt(offset + 25), // oneMinutesIdleTimes
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // maxTirePressure
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // minTirePressure
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // maxTireTemperature
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // minTireTemperature
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // tireState
            cursor.getLong(offset + 31), // stopTime
            cursor.getLong(offset + 32), // startTime
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33) // createAtTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ObdTT entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setConten(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setObdType(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSncode(cursor.getInt(offset + 3));
        entity.setStrokeId(cursor.getInt(offset + 4));
        entity.setDataBytes(cursor.getInt(offset + 5));
        entity.setOccTime(cursor.getInt(offset + 6));
        entity.setHotCarTime(cursor.getInt(offset + 7));
        entity.setTravelTime(cursor.getInt(offset + 8));
        entity.setIdleSpeedTime(cursor.getInt(offset + 9));
        entity.setTravelMileage(cursor.getDouble(offset + 10));
        entity.setIdleFuel(cursor.getDouble(offset + 11));
        entity.setDrivingFuel(cursor.getDouble(offset + 12));
        entity.setAverageFuel(cursor.getDouble(offset + 13));
        entity.setMaxRpm(cursor.getInt(offset + 14));
        entity.setMaxSpeed(cursor.getInt(offset + 15));
        entity.setHighestTemperature(cursor.getInt(offset + 16));
        entity.setLowestTemperature(cursor.getInt(offset + 17));
        entity.setMaxAcceleration(cursor.getDouble(offset + 18));
        entity.setMinAcceleration(cursor.getDouble(offset + 19));
        entity.setSolarTermDoor(cursor.getDouble(offset + 20));
        entity.setAccelerationTimes(cursor.getInt(offset + 21));
        entity.setDecelerationTimes(cursor.getInt(offset + 22));
        entity.setFaultCodeNum(cursor.getInt(offset + 23));
        entity.setSpeedingTime(cursor.getInt(offset + 24));
        entity.setOneMinutesIdleTimes(cursor.getInt(offset + 25));
        entity.setMaxTirePressure(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setMinTirePressure(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setMaxTireTemperature(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setMinTireTemperature(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setTireState(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setStopTime(cursor.getLong(offset + 31));
        entity.setStartTime(cursor.getLong(offset + 32));
        entity.setCreateAtTime(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ObdTT entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ObdTT entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
