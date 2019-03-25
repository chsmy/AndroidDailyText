package com.chs.androiddailytext.datastructure;

import java.util.ArrayList;
import java.util.Arrays;

public class QuickSort {

  // 快速排序，a是数组，n表示数组的大小
  public static void quickSort(int[] a, int n) {
    quickSortInternally(a, 0, n-1);
  }

  // 快速排序递归函数，p,r为下标
  private static void quickSortInternally(int[] a, int p, int r) {
    if (p >= r) return;

    int q = partition(a, p, r); // 获取分区点
    System.out.println("q=" + q);
    quickSortInternally(a, p, q-1);
    quickSortInternally(a, q+1, r);
  }

  private static int partition(int[] a, int p, int r) {
    int pivot = a[r];
    int i = p;
    for(int j = p; j < r; ++j) {
      if (a[j] < pivot) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        ++i;
      }
    }
    //把基准点插入中间位置
    int tmp = a[i];
    a[i] = a[r];
    a[r] = tmp;

    System.out.println("pivot=" + pivot);
    System.out.println("i=" + Arrays.toString(a));
    return i;
  }

  public ArrayList<Integer> BucketSort(ArrayList<Integer> array, int bucketSize) {
    if (array == null || array.size() < 2)
      return array;
    int max = array.get(0), min = array.get(0);
    // 找到最大值最小值
    for (int i = 0; i < array.size(); i++) {
      if (array.get(i) > max)
        max = array.get(i);
      if (array.get(i) < min)
        min = array.get(i);
    }
    int bucketCount = (max - min) / bucketSize + 1;
    ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
    ArrayList<Integer> resultArr = new ArrayList<>();
    for (int i = 0; i < bucketCount; i++) {
      bucketArr.add(new ArrayList<Integer>());
    }
    for (int i = 0; i < array.size(); i++) {
      bucketArr.get((array.get(i) - min) / bucketSize).add(array.get(i));
    }
    for (int i = 0; i < bucketCount; i++) {
      if (bucketCount == 1)
        bucketSize--;
      ArrayList<Integer> temp = BucketSort(bucketArr.get(i), bucketSize);
      for (int j = 0; j < temp.size(); j++)
        resultArr.add(temp.get(j));
    }
    return resultArr;
  }
}