package sort;

import java.util.LinkedHashMap;

/**
 * @author : Fr
 * @date : 2020/12/13 18:42
 */
public class Solution {
    public void insertSort(int[] nums){
        int temp;
        for (int i = 1; i < nums.length; i++) {
            temp = nums[i];
            for (int j = i-1; j >=0; j--) {
                //找到目标点
                if (temp >= nums[j] || j == 0){
                    //数组后退
                    if (i - j >= 0) {
                        System.arraycopy(nums, j, nums, j + 1, i - j);
                    }
                    //插入
                    if (j==0 && temp<nums[j]){
                        nums[j] = temp;
                    }else {
                        nums[j+1] = temp;
                    }

                    break;
                }

            }
        }

    }
    public void shellSort(int[] nums){
        int[] steps = {119,97,43,17,9,5,3};
        for (int step : steps) {
            for (int j = 0; j < step; j++) {
                hSort(nums,j,step);
            }
        }
        insertSort(nums);
    }
    public void bubbleSort(int[] nums){
        int flag = 0;
        for (int i = 0; i < nums.length; i++) {
            flag = 0;
            for (int j = i; j < nums.length - 1; j++) {
                if (nums[j]<nums[i]){
                    nums[i] ^=nums[j];
                    nums[j] ^=nums[i];
                    nums[i] ^=nums[j];
                    flag = 1;
                }
            }
            if (flag ==0){
                break;
            }
        }
    }

    /**
     * 递归
     * @param nums 目标
     * @param left 开始点
     * @param right 结束点
     */
    public void quickSort(int[] nums,int left,int right){
        if (left<right){
            int pivot = nums[left];
            int low = left;
            int high = right;

            while (low<high){
                while (pivot<=nums[high] && low<high){
                    high--;
                }
                nums[low] = nums[high];
                while (nums[low]<=pivot && low<high){
                    low++;
                }
                nums[high] = nums[low];
            }
            nums[low] = pivot;

            quickSort(nums,left,low-1);
            quickSort(nums,low+1,right);
        }

    }
    public void selectSort(int[] nums){

        for (int i = 0; i < nums.length-1; i++) {
            int min = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j]<min){
                    min ^= nums[j];
                    nums[j] ^= min;
                    min ^= nums[j];
                }
            }
            nums[i] = min;
        }
    }



    private void hSort(int[] nums, int pos, int step) {
        int temp;
        int j;
        for (int i = pos+step; i < nums.length; i++) {
            temp = nums[i];
            for (j = i-step; j >=0; j -=step) {
                //未找到找到目标点
                if (nums[j]>temp){
                    nums[j+step] = nums[j];
                }
                //找到找到目标点
                else {
                    break;
                }

            }
            nums[j+step] = temp;


        }

    }
}
