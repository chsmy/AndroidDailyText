package com.chs.androiddailytext.datastructure;

import java.util.Arrays;

/**
 * 作者：chs
 * 时间：2019-01-07 11:19
 * 描述：
 */
public class Text {

    public int main(){
       int a = 1;
       int res = 0;
       int ret = 0;
       ret = add(5,6);
       res = a + ret;
       System.out.println("res"+res);
       return  res;
    }

    public int add(int x ,int y){
        int sum = 0;
        sum = x + y;
        return sum;
    }

    public int[] bubbleSort(int[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++){
            //提前退出的标志
            boolean flag = false;
            for (int j = 0; j < array.length - 1 - i; j++){
                if (array[j + 1] < array[j]) {//交换
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    flag = true;//表示有了交换
                }
            }
                if(!flag) return array;//没交换了返回
        }
        return array;
    }

    public static int[] insertionSort(int[] array) {
        if (array.length == 0)
            return array;
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    // 插入排序，a 表示数组，n 表示数组大小
    public void insertionSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 1; i < n; ++i) {
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j+1] = a[j];  // 数据移动
                } else {
                    break;
                }
            }
            a[j+1] = value; // 插入数据
        }
    }
    //选择排序
    public int[] selectionSort(int[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) //找到最小的数
                    minIndex = j; //将最小数的索引保存
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public int[] MergeSort(int[] array) {
        if (array.length < 2) return array;
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(MergeSort(left), MergeSort(right));
    }

    //合并操作
    public int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length)//左边数组比较完了
                result[index] = right[j++];
            else if (j >= right.length)//右边数组比较完了
                result[index] = left[i++];
            else if (left[i] > right[j])//谁小就把谁拿来放到result中，指针加1
                result[index] = right[j++];
            else////谁小就把谁拿来放到result中，指针加1
                result[index] = left[i++];
        }
        return result;
    }

    public  int[] QuickSort(int[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end >= array.length || start > end) return null;
        int smallIndex = partition(array, start, end);
        if (smallIndex > start)
            QuickSort(array, start, smallIndex - 1);
        if (smallIndex < end)
            QuickSort(array, smallIndex + 1, end);
        return array;
    }
    public  int partition(int[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swap(array, pivot, end);
        for (int i = start; i <= end; i++)
            if (array[i] <= array[end]) {
                smallIndex++;
                if (i > smallIndex)
                    swap(array, i, smallIndex);
            }
        return smallIndex;
    }
    public  void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
   public static void main(String[] args){
        int[] arr = new int[]{4,5,6,3,2,1};
       QuickSort.quickSort(arr,6);
   }

    public double sqrt(double x, double precision) {
        if(x == 0 || x == 1){
            return x;
        }
        double low = 0;
        double high = x;
        double mid = low + (high - low)/2;
        while(Math.abs(high - low )> precision) {
            if (mid * mid > x ) {
                high = mid;
            } else if (mid * mid < x) {
                low = mid;
            } else {
                return mid;
            }
            mid = low + (high - low)/2;
        }
        return mid;
    }
}
