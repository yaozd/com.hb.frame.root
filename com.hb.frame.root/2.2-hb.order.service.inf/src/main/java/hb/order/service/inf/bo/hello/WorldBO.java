package hb.order.service.inf.bo.hello;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 输出
 * Created by zd.yao on 2017/3/23.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorldBO implements Serializable {
    private String value;
}
