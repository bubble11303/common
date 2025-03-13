package utils;


import model.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtils<T> {

    private Map<Integer, TreeNode<T>> nodeMap;

    public TreeUtils() {
        this.nodeMap = new HashMap<>();
    }

    /**
     * 构建树形结构
     *
     * @param nodeList 对象列表
     */
    public void buildTree(List<TreeNode<T>> nodeList) {
        for (TreeNode<T> node : nodeList) {
            nodeMap.put(node.getData().hashCode(), node);
        }

        for (TreeNode<T> node : nodeList) {
            if (node.getPid() != 0) {
                TreeNode<T> parent = nodeMap.get(node.getPid());
                if (parent != null) {
                    parent.getChildren().add(node);
                }
            }
        }
    }

    /**
     * 根据节点ID获取节点
     *
     * @param nodeId 节点ID
     * @return 对应的节点对象，如果不存在则返回null
     */
    public TreeNode<T> getNodeById(int nodeId) {
        return nodeMap.get(nodeId);
    }

    /**
     * 获取指定节点的子节点列表
     *
     * @param nodeId 节点ID
     * @return 子节点列表，如果节点不存在或没有子节点则返回空列表
     */
    public List<TreeNode<T>> getChildren(int nodeId) {
        TreeNode<T> node = getNodeById(nodeId);
        if (node != null) {
            return node.getChildren();
        }
        return new ArrayList<>();
    }

    // 添加更多的工具方法...

    public static void main(String[] args) {

    }

}
