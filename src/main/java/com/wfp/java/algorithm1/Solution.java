package com.wfp.java.algorithm1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wfp
 * @create 2020-12-28 18:47
 */
public class Solution {
//1

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] result = solution.twoSum(new int[]{1, 2}, 3);


    }

    //1
    public int[] twoSum(int[] data, int target) {
        int[] result = new int[2];
        if (data == null) {
            return result;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < data.length; i++) {
            if (map.containsKey(data[i])) {
                result[0] = map.get(data[i]);
                result[1] = i;
                return result;
            } else {
                int oTarget = target - data[i];
                map.put(oTarget, i);
            }
        }
        return result;
    }

    //2

    public int climbStairs(int n) {
        if (n < 1) {
            return 0;
        }
        if (n <= 2) {
            return n;
        }
        int[] result = new int[n];

        result[0] = 1;
        result[1] = 2;
        if (n > 2) {
            for (int i = 2; i < n; i++) {
                result[i] = result[i - 2] + result[i - 1];
            }
        }

        return result[n - 1];
    }



//3
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public TreeNode invertTree(TreeNode root) {
        if(root==null)
        {
            return null;
        }
        if( root.right!=null){
            root.right=invertTree(root.right);
        }
        if( root.left!=null){
            root.left=invertTree(root.left);
        }
        TreeNode tmp=root.left;
        root.left=root.right;
        root.right=tmp;

        return root;

    }


    //4
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
        public ListNode reverseList(ListNode head) {
            if (head == null) {
                return head;
            }
            ListNode current;
            ListNode prev = null;
            current = head;

            while (current != null) {
                ListNode tmp = current.next;
                current.next = prev;
                prev = current;
                current = tmp;
            }
            return prev;
        }



        //5
        class LRUCache {
            LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
            private int cap;

            public LRUCache(int capacity) {
                cap = capacity;
            }

            public int get(int key) {
                if (map.containsKey(key)) {
                    moveRecent(key);
                    return map.get(key);
                }
                return -1;
            }


            public void put(int key, int value) {
                if (map.containsKey(key)) {
                    map.remove(key);
                    map.put(key, value);
                } else {
                    while (map.size() >= cap) {
                        Iterator<Integer> iterator = map.keySet().iterator();
                        if (iterator.hasNext()) {
                            int delkey = iterator.next();
                            map.remove(delkey);
                        }
                    }
                    map.put(key, value);
                }
            }

            private void moveRecent(int key) {
                Integer value = map.get(key);
                map.remove(key);
                map.put(key, value);
            }
        }

}
