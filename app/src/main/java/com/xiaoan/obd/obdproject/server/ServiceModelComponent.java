package com.xiaoan.obd.obdproject.server;

import com.xiaoan.obd.obdproject.module.car.CarListPresenter;
import com.xiaoan.obd.obdproject.module.car.add.CarAllInfoPresenter;
import com.xiaoan.obd.obdproject.module.car.choose.ChangePresenter;
import com.xiaoan.obd.obdproject.module.login.FindAccountPresenter;
import com.xiaoan.obd.obdproject.module.login.FindPwdPresenter;
import com.xiaoan.obd.obdproject.module.login.LoginPresenter;
import com.xiaoan.obd.obdproject.module.login.RegisterActivityPresenter;
import com.xiaoan.obd.obdproject.module.main.fragment.HomeFragmentPresenter;
import com.xiaoan.obd.obdproject.module.mine.ChangeNamePresenter;
import com.xiaoan.obd.obdproject.module.mine.ChangePhonePresenter;
import com.xiaoan.obd.obdproject.module.mine.ObdBoxPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhuchenxi on 16/1/25.
 */
@Singleton
@Component(modules = {ServiceAPIModule.class})
public interface ServiceModelComponent {

    void inject(LoginPresenter loginPresenter);

    void inject(FindAccountPresenter findAccountPresenter);

    void inject(FindPwdPresenter findPwdPresenter);

    void inject(RegisterActivityPresenter registerActivityPresenter);

    void inject(ChangeNamePresenter changeNamePresenter);

    void inject(ChangePhonePresenter changePhonePresenter);

    void inject(ObdBoxPresenter obdBoxPresenter);

    void inject(CarListPresenter carListPresenter);

    void inject(ChangePresenter changePresenter);

    void inject(CarAllInfoPresenter carAllInfoPresenter);

    void inject(HomeFragmentPresenter homeFragmentPresenter);
}