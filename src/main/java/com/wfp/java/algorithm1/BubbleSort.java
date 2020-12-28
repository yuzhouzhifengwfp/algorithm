package com.wfp.java.algorithm1;


import java.util.Arrays;


public class BubbleSort {


    public static void main(String[] args) {
        int[] date = new int[]{1, 2, 3, 4, 5};
        bubbleSort(date);
        System.out.println(Arrays.toString(date));

        System.out.println();




    }

    public static  void bubbleSort(int[] list) {

        /*if(list ==null || list.length<=0)
        {
            throw new Exception("空数组");
        }*/
        for(int i=list.length-1;i>0;i--){
            boolean done=true;
            for(int j=0;j<i;j++) {
                if(list[j]>list[j+1]){
                    int tmp=list[j];
                    list[j]=list[j+1];
                    list[j+1]=tmp;
                    done=false;
                }
            }
            if(done){
                break;
            }
        }
    }
}
