package com.ethereal.influxdb.core.model;


/**
 * @author wxm
 * @version 1.0
 * @since 2021/6/17 8:48
 */
public class DeleteModel extends BaseModel {

    public DeleteModel(){

    }

    public DeleteModel(String measurement){
        super(measurement);
    }

}
