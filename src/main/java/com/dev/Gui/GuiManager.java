package com.dev.Gui;

import com.dev.Gui.Elements.MElement;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.AbstractInt2IntMap;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GuiManager {

    public static final int padding = 6;



    private static GuiManager instance;


    public static void newInstance() {
        instance = new GuiManager();
    }

    public static GuiManager getInstance() {
        return instance;
    }

    private GuiManager() {

    }


    public Data<MElement> backgrounds;










    public static class Data<T> {
        private final HashMap<Integer, T> dataMap = Maps.newHashMap();
        private Integer lastId = 0;
        private T lastObject = null;

        public T get(int id) {
            return dataMap.getOrDefault(id, null);
        }

        public int getId(T t) {
            int id = -1;

            for (int id2 : dataMap.keySet()) {
                if (dataMap.get(id2).equals(t)) {
                    id = id2;
                    break;
                }
            }

            return id;
        }

        public void add(T t) {
            put(lastId, t);

            lastId++;
            lastObject = t;
        }

        public void put(int id, T t) {
            dataMap.put(id, t);
            if (lastId == id) lastObject = t;
        }

        public int getLastId() {
            return lastId;
        }

        public T getLastObject() {
            return lastObject;
        }

        public List<T> getList() {
            return new ArrayList<>(dataMap.values());
        }

        public void remove(int id) {
            dataMap.remove(id);
        }

        public void remove(T t) {
            this.remove(getId(t));
        }

        public void clear() {
            dataMap.clear();
        }
    }
}
