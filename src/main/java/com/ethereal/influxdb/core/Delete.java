package com.ethereal.influxdb.core;

import com.ethereal.influxdb.core.model.DeleteModel;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author wxm
 * @version 1.0
 * @since 2021/6/17 8:45
 */
public class Delete extends Op {

    /**
     * 构造条件
     * <p>
     * 注意 where 条件中 map参数仅能是 tag
     * 这是由 influxdb 本身决定的
     *
     * @param model
     * @return
     */
    public static String build(DeleteModel model) {
        Objects.requireNonNull(model.getMeasurement(), "DeleteModel.Measurement");
        StringBuilder delete = new StringBuilder();
        delete.append("delete from ").append(model.getMeasurement());
        if (!ObjectUtils.isEmpty(model.getWhere())) {
            delete.append(" where ").append(model.getWhere());
        } else {
            throw new RuntimeException("where 条件缺失");
        }
        String sql = delete.toString();
        log.info(sql);
        return sql;
    }


    public static void main(String[] args) throws Exception {
        Map<String, Object> map = new TreeMap<>();
        map.put("device_id", "666");  //map中参数 仅能是tag
        DeleteModel model = new DeleteModel("ojbk");
        model.setMap(map);
        model.setStart(LocalDateTime.now().plusHours(-10L));
        model.setEnd(LocalDateTime.now());
        model.setWhere(Op.where(model));
        System.err.println(Delete.build(model));
    }

}
