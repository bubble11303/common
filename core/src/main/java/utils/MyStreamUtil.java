package utils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author bubble
 * @since 2024/7/15
 */
public interface MyStreamUtil {

    /**
     * 对列表中的元素进行映射，将每个元素转换为另一种类型。
     */
    static <T, R> List<R> toList(Collection<T> list, Function<T, R> mapper) {
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 将列表中的元素转换为集合。
     */
    static <T, R> Set<R> toSet(List<T> list, Function<T, R> mapper) {
        return list.stream().map(mapper).collect(Collectors.toSet());
    }

    /**
     * 将列表中的元素分组，根据映射函数的结果进行分组。
     */
    static <T, R> Map<R, List<T>> grouping(List<T> list, Function<T, R> mapper) {
        return list.stream().collect(Collectors.groupingBy(mapper));
    }

    /**
     * 列表转换为map，根据映射函数的结果作为key，元素本身作为value。
     */
    static <T, R> Map<R, T> toMap(List<T> list, Function<T, R> mapper) {
        return list.stream().collect(Collectors.toMap(mapper, a -> a));
    }

    /**
     * 根据映射函数，计算列表中元素的和。
     */
    static <T, R> Double doubleSum(List<T> list, Function<T, R> mapper) {
        return list.stream().mapToDouble(a -> (Double) mapper.apply(a)).sum();
    }

    static <T, R> int intSum(List<T> list, Function<T, R> mapper) {
        return list.stream().mapToInt(a -> (Integer) mapper.apply(a)).sum();
    }

    /**
     * 去重
     */
    static <T, R> List<T> distinct(List<T> list, Function<T, R> mapper) {
        return list.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 排序
     */
    static <T, R> List<T> sort(List<T> list, Function<T, R> mapper) {
        return list.stream().sorted(Comparator.comparingInt(a -> (Integer) mapper.apply(a))).collect(Collectors.toList());
    }

    /**
     * 根据过滤条件获取列表中
     */
    static <T, R> List<T> filter(List<T> list, Function<T, R> mapper) {
        return list.stream().filter(a -> (Boolean) mapper.apply(a)).collect(Collectors.toList());
    }

    static <T, R> T filterOne(List<T> list, Function<T, R> mapper) {
        return list.stream().filter(a -> (Boolean) mapper.apply(a)).findFirst().orElse(null);
    }


    /**
     * 将列表中的元素转换为字符串，并用指定分隔符连接。
     *
     * @param list      要转换的列表
     * @param separator 用于连接元素的分隔符
     * @return 字符串表示形式
     */
    static String join(List<String> list, String separator) {
        return list.stream().collect(Collectors.joining(separator));
    }

    static <T> List<T> batchRemove(List<T> list, List<T> waitIngRemove) {
        return list.stream().filter(a -> !waitIngRemove.contains(a)).collect(Collectors.toList());
    }

}
