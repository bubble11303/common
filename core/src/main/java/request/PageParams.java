package request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: 你先别说话
 * @since: 2025/03/13
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParams implements Serializable {

    private Integer page = 1;

    private Integer pageSize = 10;
}
