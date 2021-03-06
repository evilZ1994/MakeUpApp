package com.example.lollipop.makeupapp.util;

import com.example.lollipop.makeupapp.bean.bmob.Schedule;
import com.example.lollipop.makeupapp.bean.bmob.User;
import com.example.lollipop.makeupapp.bean.realm.ScheduleRealm;

import java.util.Date;

import cn.bmob.v3.datatype.BmobDate;

/**
 * Created by R2D2 on 2017/9/8.
 */

public class BmobRealmTransUtil {

    public static ScheduleRealm schedule(Schedule schedule){
        ScheduleRealm scheduleRealm = new ScheduleRealm();
        scheduleRealm.setObjectId(schedule.getObjectId());
        scheduleRealm.setUserId(schedule.getUser().getObjectId());
        scheduleRealm.setTitle(schedule.getTitle());
        scheduleRealm.setClassification(schedule.getClassification());
        scheduleRealm.setStartTime(schedule.getStartTime());
        scheduleRealm.setEndTime(schedule.getEndTime());
        scheduleRealm.setRepeatMode(schedule.getRepeatMode());
        scheduleRealm.setRemindWay(schedule.getRemindWay());
        scheduleRealm.setOpen(schedule.isOpen());
        scheduleRealm.setRequestCode(schedule.getRequestCode());
        scheduleRealm.setCreateTime(DateFormatUtil.toDate(schedule.getCreateTime().getDate()));

        return scheduleRealm;
    }

    public static Schedule schedule(ScheduleRealm scheduleRealm){
        Schedule schedule = new Schedule();
        User user = new User();
        user.setObjectId(scheduleRealm.getUserId());
        schedule.setUser(user);
        schedule.setTitle(scheduleRealm.getTitle());
        schedule.setClassification(scheduleRealm.getClassification());
        schedule.setStartTime(scheduleRealm.getStartTime());
        schedule.setEndTime(scheduleRealm.getEndTime());
        schedule.setRepeatMode(scheduleRealm.getRepeatMode());
        schedule.setRemindWay(scheduleRealm.getRemindWay());
        schedule.setOpen(scheduleRealm.isOpen());
        schedule.setRequestCode(scheduleRealm.getRequestCode());
        schedule.setCreateTime(new BmobDate(scheduleRealm.getCreateTime()));
        return schedule;
    }
}
