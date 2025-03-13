package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: 热心村民王富贵
 * @Date: 2025/03/13
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuntimeContext implements Serializable {

    private Map<String, String> headerMap;

    private String token;

    private String active;

}
