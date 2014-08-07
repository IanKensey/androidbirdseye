package com.twod.feralvillage;

public class GameComponentPool extends TObjectPool<GameComponent> {
    public Class<?> objectClass;
    
    public GameComponentPool(Class<?> type) {
        super();
        objectClass = type;
        fill();
    }
    
    public GameComponentPool(Class<?> type, int size) {
        super(size);
        objectClass = type;
        fill();
    }
    
    @Override
    protected void fill() {
        if (objectClass != null) {
            for (int x = 0; x < getSize(); x++) {
                try {
                    getAvailable().add(objectClass.newInstance());
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}

