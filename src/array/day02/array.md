# 移动零,采用双指针交换

ij从零开始,发现一个不为零的数,就将他提到前面前面去,不用关心前面的数是什么

```java
public void moveZeroes(int[] nums) {
    int i , j = 0;
    int temp;
    for (i = 0; i < nums.length; i++) {
        if (nums[i] != 0) {
            temp = nums[j];
            nums[j++] = nums[i];
            nums[i] = temp;


        }
    }
    System.out.println(Arrays.toString(nums));
}
```
# 当数据很少地时候排序可以使用计数排序


```java
public void sortColors(int[] nums) {
    int i=0;
    int j = 0;
    for (int num : nums) {
        switch (num) {
            case 0:
                i++;
                break;
            case 1:
                j++;
                break;
            default:
                break;
        }
    }
    Arrays.fill(nums,0,i,0);
    Arrays.fill(nums,i,i+j,1);
    Arrays.fill(nums,i+j,nums.length,2);
}
```
# 位运算交换数据
```java
private void swap(int[] nums, int i, int j) {
    if (i != j) {
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
}
```
# 滑动窗口无序计数设置长度
 ```java
public String minWindow(String s, String t) {
        int[] freq = new int[127];
        int[] needs = new int[127];

        String temp = "";

        //t的长度,每次发现一个元素,就将窗口减小一次,代表执行了一次频率变化
        int len  =t.length();
        int left = 0;
        int right = 0;
        for (int i = 0; i < t.length(); i++) {
            freq[t.charAt(i)]++;
            needs[t.charAt(i)]++;
        }
        while (right<s.length()){
            while (len!=0 &&right<s.length()) {
                if(needs[s.charAt(right)]>=1){
                    //入窗口
                    len --;
                    needs[s.charAt(right)]--;
                }else if (freq[s.charAt(right)]>=1){
                    //对于多余数字插入
                    needs[s.charAt(right)]--;
                }
                right++;
            }
            while (len==0){
                if (freq[s.charAt(left)]>0){

                    //需求过多
                    if (needs[s.charAt(left)]<0) {
                        needs[s.charAt(left)]++;
                    }
                    //需求刚好用光
                    else if ((needs[s.charAt(left)]==0)){
                        len++;
                        needs[s.charAt(left)]++;
                    }
                }
                if (right-left<temp.length() || "".equals(temp)){
                    temp = s.substring(left,right);
                }
                left++;
            }
        }

        return temp;

    }
```