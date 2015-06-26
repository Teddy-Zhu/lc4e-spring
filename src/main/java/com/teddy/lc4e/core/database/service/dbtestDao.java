package com.teddy.lc4e.core.database.service;

import com.teddy.lc4e.core.database.model.Dbtest;
import com.teddy.lc4e.core.database.repository.DbtestNewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * Created by teddy on 2015/6/25.
 */
@Service
public class dbtestDao {

    @Autowired
    private DbtestNewRepository testdbRepository;

    public void test(String ac) {

        Dbtest d = testdbRepository.findByName("username2");
        testdbRepository.push(d.getId(), "value", ac);

    }

    public void test3(String a) {

        Dbtest d = testdbRepository.findByName("username2");
        testdbRepository.addToSet(d.getId(), "tags", a);


    }

    public void test4() {
        Dbtest d = testdbRepository.findByName("username2");
        for (int i = 0; i < 20; i++) {
            testdbRepository.addToSet(d.getId(), "tags", random(5));
        }
    }

    public void test5(String value) {
        Dbtest d = testdbRepository.findByName("username2");
        testdbRepository.pull(d.getId(), "tags", value);
    }

    private String random(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            str = str + (char) (Math.random() * 26 + 'A');
        }
        return str;
    }

    public void test2() {
        testdbRepository.drop();
        for (int i = 1; i < 10; i++) {
            Dbtest person = new Dbtest();
            person.setName("username" + i);
            person.setValue("password" + i);
            person.setT("t" + i);
            person.setTags(new HashSet<String>());
            testdbRepository.insert(person);

        }
    }

}
