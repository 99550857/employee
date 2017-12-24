package com.managesystem.dao;

import com.managesystem.dao.impl.InformImpl;
import com.managesystem.model.Inform;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 99550 on 2017/12/22.
 */
public class InformDAOTest {


    InformDAO informDAO = new InformImpl();
    @Test
    public void getAllInform() throws Exception {
        List<Inform> list=informDAO.getAllInform();
        Collections.reverse(list);
        List<Inform> list1 = new ArrayList<>();
        for (Inform inform:list ) {
            if(list1.size()<5){
                list1.add(inform);
            }else {
                break;
            }
        }
        list1.forEach(inform -> System.out.println(inform));
    }
    @Test
    public void insertInform() throws Exception {
        for (int i = 0; i < 5; i++) {
            Inform inform = new Inform("1","111","上午adsasd门会议","请所有人准时到，地点：*******","赵玲",new Timestamp(System.currentTimeMillis()));
            System.out.println(informDAO.InsertInform(inform));
        }
    }
}