package com.anggaari.learnspring.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.ArrayList;
import java.util.List;

public class DoubletonScope implements Scope {
    private Long counter = -1L;
    private List<Object> objects = new ArrayList<>(2);

    @Override
    public Object get(String s, ObjectFactory<?> objectFactory) {
        counter++;
        if (objects.size() == 2) {
            int index = (int) (counter % 2);
            return objects.get(index);
        }

        Object object = objectFactory.getObject();
        objects.add(object);

        return object;
    }

    @Override
    public Object remove(String s) {
        if (!objects.isEmpty()) {
            return objects.remove(0);
        }
        return null;
    }

    @Override
    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
