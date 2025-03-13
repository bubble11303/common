package request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: 热心村民王富贵
 * @Date: 2025/03/13
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParams implements Serializable {

    private Integer page = 1;

    private Integer pageSize = 10;
}
