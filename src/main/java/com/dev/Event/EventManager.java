package com.dev.Event;

import java.lang.reflect.Method;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventManager {
    private final CopyOnWriteArrayList<Object> listeners;

    public EventManager() {
        listeners = new CopyOnWriteArrayList<>();
    }

    // Регистрация объекта как слушателя
    public void register(Object listener) {
        listeners.add(listener);
    }

    // Вызов события
    public <T extends Event> void trigger(T event) {
        for (Object listener : listeners) {
            Class<?> clazz = listener.getClass();
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Subscribe.class) && method.getParameterCount() == 1) {
                    Class<?> eventType = method.getParameterTypes()[0];
                    if (eventType.isInstance(event)) { // Проверяем, подходит ли тип события

                        try {
                            method.invoke(listener, event); // Вызываем метод
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void unregister(Object listener) {
        listeners.remove(listener);
    }
}
