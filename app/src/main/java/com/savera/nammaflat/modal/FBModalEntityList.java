package com.savera.nammaflat.modal;

import java.util.ArrayList;

public class FBModalEntityList<T> {
    private ArrayList<T> mFBModalEntries;

    public FBModalEntityList() {
        mFBModalEntries = new ArrayList<>();
    }
    public void Add(T fbModelEntity) {
        mFBModalEntries.add(fbModelEntity);
    }

    public void Add(ArrayList<Object> fbModelEntities) {
        mFBModalEntries.clear();

        if(fbModelEntities.isEmpty())
            return;

        /*if(!(fbModelEntities.get(0) instanceof T))
            return;*/

        for (int ii = 0; ii<fbModelEntities.size(); ++ii) {
            T srModal = (T) fbModelEntities.get(ii);
            mFBModalEntries.add(srModal);
        }
    }

    public void Remove(T fbModelEntity) {
        if(mFBModalEntries.isEmpty())
            return;

        mFBModalEntries.remove(fbModelEntity);
    }

    public void Reset() {
        mFBModalEntries.clear();
    }

    public boolean IsEmpty() {
        return mFBModalEntries.isEmpty();
    }

    public int GetCount() { return mFBModalEntries.size(); }

    public T GetAt(int index) {
        if(mFBModalEntries.isEmpty())
            return null;

        if(index < 0 || index >= mFBModalEntries.size())
            return null;

        return mFBModalEntries.get(index);
    }
}
