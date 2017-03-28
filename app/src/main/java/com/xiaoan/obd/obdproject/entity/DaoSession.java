package com.xiaoan.obd.obdproject.entity;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.xiaoan.obd.obdproject.entity.CarBean;
import com.xiaoan.obd.obdproject.entity.ObdRT;
import com.xiaoan.obd.obdproject.entity.ObdTT;
import com.xiaoan.obd.obdproject.entity.User;

import com.xiaoan.obd.obdproject.entity.CarBeanDao;
import com.xiaoan.obd.obdproject.entity.ObdRTDao;
import com.xiaoan.obd.obdproject.entity.ObdTTDao;
import com.xiaoan.obd.obdproject.entity.UserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig carBeanDaoConfig;
    private final DaoConfig obdRTDaoConfig;
    private final DaoConfig obdTTDaoConfig;
    private final DaoConfig userDaoConfig;

    private final CarBeanDao carBeanDao;
    private final ObdRTDao obdRTDao;
    private final ObdTTDao obdTTDao;
    private final UserDao userDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        carBeanDaoConfig = daoConfigMap.get(CarBeanDao.class).clone();
        carBeanDaoConfig.initIdentityScope(type);

        obdRTDaoConfig = daoConfigMap.get(ObdRTDao.class).clone();
        obdRTDaoConfig.initIdentityScope(type);

        obdTTDaoConfig = daoConfigMap.get(ObdTTDao.class).clone();
        obdTTDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        carBeanDao = new CarBeanDao(carBeanDaoConfig, this);
        obdRTDao = new ObdRTDao(obdRTDaoConfig, this);
        obdTTDao = new ObdTTDao(obdTTDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);

        registerDao(CarBean.class, carBeanDao);
        registerDao(ObdRT.class, obdRTDao);
        registerDao(ObdTT.class, obdTTDao);
        registerDao(User.class, userDao);
    }
    
    public void clear() {
        carBeanDaoConfig.getIdentityScope().clear();
        obdRTDaoConfig.getIdentityScope().clear();
        obdTTDaoConfig.getIdentityScope().clear();
        userDaoConfig.getIdentityScope().clear();
    }

    public CarBeanDao getCarBeanDao() {
        return carBeanDao;
    }

    public ObdRTDao getObdRTDao() {
        return obdRTDao;
    }

    public ObdTTDao getObdTTDao() {
        return obdTTDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

}
