package com.savera.nammaflat.modal;

import java.util.ArrayList;
import java.util.List;

public class ServiceRequestEntries {
    private ArrayList<ServiceRequestModal> mServiceEntries;

    public ServiceRequestEntries() {
        mServiceEntries = new ArrayList<>();
    }
    public void Add(ServiceRequestModal serviceRequest) {
        mServiceEntries.add(serviceRequest);
    }
    public void Add(ArrayList<Object> serviceRequests) {
        mServiceEntries.clear();

        if(serviceRequests.isEmpty())
            return;

        if(!(serviceRequests.get(0) instanceof ServiceRequestModal))
            return;

        for (int ii = 0; ii<serviceRequests.size(); ++ii) {
            ServiceRequestModal srModal = (ServiceRequestModal) serviceRequests.get(ii);
            mServiceEntries.add(srModal);
        }
    }

    public void Remove(ServiceRequestModal serviceRequestModal) {
        if(mServiceEntries.isEmpty())
            return;

        mServiceEntries.remove(serviceRequestModal);
    }

    public void Reset() {
        mServiceEntries.clear();
    }

    public boolean IsEmpty() {
        return mServiceEntries.isEmpty();
    }

    public int GetCount() { return mServiceEntries.size(); }

    public ServiceRequestModal GetAt(int index) {
        if(mServiceEntries.isEmpty())
            return null;

        if(index < 0 || index >= mServiceEntries.size())
            return null;

        return mServiceEntries.get(index);
    }
}
