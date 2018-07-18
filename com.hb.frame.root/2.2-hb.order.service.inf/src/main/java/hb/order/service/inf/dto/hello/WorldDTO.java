package hb.order.service.inf.dto.hello;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 输入
 * Created by zd.yao on 2017/3/22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorldDTO implements Serializable {

    private String name;
    private String password;
}