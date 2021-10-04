package binarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 *  二分查找算法
 *  数组一定是排好序的
 */

public class BinarySearch03 {
    public static void main(String[] args) {
        //创建数组
        int[] arr = {11, 23, 43, 100, 100, 100, 100, 1234,4244};
        List resultList = binarySearch(arr, 0, arr.length - 1, 100);
        System.out.println(resultList);
    }

    /**
     * 查找数据
     * @param arr
     * @param leftIndex
     * @param rightIndex
     * @param findVal
     * @return 返回List集合，包含查找的数据的索引
     */
    public static List<Integer> binarySearch(int[] arr, int leftIndex, int rightIndex, int findVal) {
        if (leftIndex > rightIndex) {
            return new ArrayList<>();
        }

        int midIndex = (leftIndex + rightIndex) / 2; //获取数组中间下标
        int midVal = arr[midIndex]; //获取数组中间的数据

        //判断要查找的数据与中间数据的大小
        if (findVal > midVal) { //大于则要查找的数在右边
            return binarySearch(arr, midIndex + 1, rightIndex, findVal);
        } else if (findVal < midVal) { //小于则要查找的数在左边
            return binarySearch(arr, leftIndex, rightIndex- 1, findVal);
        } else {
            List<Integer> resIndexList = new ArrayList<>();

            //向左查找
            int tempIndex = midIndex - 1;
            while (true) {
                if (tempIndex < 0 || arr[tempIndex] != findVal) { //当查找完了或与查找的数据不同就break
                    break;
                }
                resIndexList.add(tempIndex); //把符合的加进List集合
                tempIndex -= 1;
            }
            resIndexList.add(midIndex); //把查找到的数据索引加进List集合

            //向右查找
            tempIndex = midIndex + 1;
            while (true) {
                if (tempIndex > arr.length - 1 || arr[tempIndex] != findVal) {
                    break;
                }
                resIndexList.add(tempIndex);
                tempIndex++;
            }
            return resIndexList;
        }
    }
}
