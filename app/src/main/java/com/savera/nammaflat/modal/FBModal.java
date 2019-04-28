package com.savera.nammaflat.modal;

import java.io.Serializable;
import java.util.Map;

abstract public class FBModal implements Serializable {
    abstract public void FillData(Map<String, Object> documentData);
}
