package com.knowlegene.apirequest.hongkaitest.hongkaitest;

import com.nswt.osp.AbstractOspRequest;

import javax.xml.bind.annotation.*;

/**
 * Created by hongkai on 2016/12/24.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "hongkaitestAppiRequest")
public class HongkaitestAppiRequest extends AbstractOspRequest {

    @XmlAttribute
    private String travelPage;

    public String getTravelPage() {
        return travelPage;
    }

    public void setTravelPage(String travelPage) {
        this.travelPage = travelPage;
    }

}