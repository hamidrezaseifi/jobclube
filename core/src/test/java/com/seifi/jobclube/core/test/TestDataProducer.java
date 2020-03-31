package com.seifi.jobclube.core.test;

import com.seifi.jobclube.core.entitys.AdvertiseEntity;
import com.seifi.jobclube.core.entitys.PostEntity;
import com.seifi.jobclube.core.entitys.ReviewEntity;

import java.util.Arrays;
import java.util.List;

public class TestDataProducer {

    protected AdvertiseEntity generaletTestAdvertiseEntity(int index){
        AdvertiseEntity model = new AdvertiseEntity();
        model.setDescription("desc:" + index);
        model.setId((long) index);
        model.setPhoto("photo:"+index);
        model.setStatus(1);
        model.setVersion(1);
        model.setTitle("title:"+index);

        return model;
    }

    protected List<AdvertiseEntity> generaletTestAdvertiseEntityList(){
        return Arrays.asList(generaletTestAdvertiseEntity(1),
                generaletTestAdvertiseEntity(2),
                generaletTestAdvertiseEntity(3));
    }

    protected PostEntity generaletTestPostEntity(int index){
        PostEntity model = new PostEntity();
        model.setDescription("desc:" + index);
        model.setId((long) index);
        model.setPhoto("photo:"+index);
        model.setTitle("title:"+index);

        return model;
    }

    protected List<PostEntity> generaletTestPostEntityList(){
        return Arrays.asList(generaletTestPostEntity(1),
                generaletTestPostEntity(2),
                generaletTestPostEntity(3));
    }



    protected ReviewEntity generaletTestReviewEntity(int index){
        ReviewEntity model = new ReviewEntity();
        model.setDescription("desc:" + index);
        model.setId((long) index);
        model.setUserId((long) index);
        model.setStatus(1);
        model.setVersion(1);

        return model;
    }

    protected List<ReviewEntity> generaletTestReviewEntityList(){
        return Arrays.asList(generaletTestReviewEntity(1),
                generaletTestReviewEntity(2),
                generaletTestReviewEntity(3));
    }
}
