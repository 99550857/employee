package com.managesystem.dao;

import com.managesystem.dao.impl.InformImpl;
import com.managesystem.model.Inform;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

/**
 * Created by 99550 on 2017/12/22.
 */
public class InformDAOTest {
    InformDAO informDAO = new InformImpl();
    @Test
    public void insertInform() throws Exception {
        for (int i = 0; i < 5; i++) {
            Inform inform = new Inform("1","111","上午adsasd门会议","请所有人准时到，地点：*******","赵玲",new Timestamp(System.currentTimeMillis()));
            System.out.println(informDAO.InsertInform(inform));
        }
    }
}