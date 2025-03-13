package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode<T> implements Serializable {

    private Integer id;

    private Integer level;

    private Integer pid;

    private T data;

    private List<TreeNode<T>> children;

}
