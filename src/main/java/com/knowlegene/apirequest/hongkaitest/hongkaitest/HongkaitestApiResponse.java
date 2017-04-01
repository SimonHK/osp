package com.knowlegene.apirequest.hongkaitest.hongkaitest;

import com.knowlegene.apirequest.hongkaitest.hongkaitest.model.HongkaitestApi;
import com.knowlegene.apiservice.util.PageData;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hongkai on 2016/12/24.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "HongkaitestApiResponse")
public class HongkaitestApiResponse {

    @XmlElement
    private List<HongkaitestApi> fooList;

    public List<HongkaitestApi> getFooList() {
        return fooList;
    }

    public void setFooList(List<HongkaitestApi> fooList) {
        this.fooList = fooList;
    }
}