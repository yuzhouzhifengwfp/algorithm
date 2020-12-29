package com.wfp.java.algorithm1;

import java.util.*;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;
import sun.text.normalizer.UCharacter;

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
        
        
        
        
        //6
        public String longestPalindrome(String s) {
            String result = "";
            int slength = s.length();
            for (int i = 0; i < slength; i++) {
                String odd = extractPalindrome(s, i, i);
                String even = extractPalindrome(s, i, i + 1);

                if (odd.length() > result.length()) {
                    result = odd;
                }
                if (even.length() > result.length()) {
                    result = even;
                }
            }
            return result;
        }

    private String extractPalindrome(String s, int l, int r) {
        while(l>=0 && r<s.length() && s.charAt(l)==s.charAt(r))
        {
            l--;
            r++;
        }
        return s.substring(l+1,r);
    }

    @Test
    public void testSubstring(){
        System.out.println("a".substring(0, 1));

    }



    //7

    class IsValid {
        Stack<Character> st=new Stack<Character>();

        Map<Character,Character> map=new  HashMap<Character,Character>();
        {

            map.put('(',')');
            map.put('[',']');
            map.put('{','}');
        }

        public boolean isValid(String s) {



            int n=s.length();
            if(n%2==1) {
                return false;
            }

            for (int i = 0; i < n; i++) {
                if(!isValid(s.charAt(i))){
                    return false;
                }
            }

            return st.size()==0;


        }
        private Boolean isValid( char c){


            switch (c){
                case '(':
                case '[':
                case '{':
                    st.push(c);

                    return true;
                case ')':
                case ']':
                case '}':
                    if(st.size()<=0)
                    {
                        return false;
                    }
                    Character charprev=st.pop();
                    if(map.get(charprev)!=c)
                    {
                        return false;
                    }
                    return true;

            }

            return false;
        }
    }


    //8
    class FindKthLargest {
        public int findKthLargest(int[] nums, int k) {
shuffle(nums);
            k = nums.length - k;
            int lo = 0;
            int hi = nums.length-1;

            while (lo < hi) {
                int t = partition(nums, lo, hi);
                if (t > k) {
                    hi = t - 1;
                } else if (t < k) {
                    lo = t + 1;
                } else {
                    break;
                }
            }
            return nums[k];
        }

        private int partition(int[] nums, int lo, int hi) {
            int i=lo;
            int v=nums[hi];
            for (int j = i; j <hi ; j++) {
                if(nums[j]<v){
                    exch(nums,i,j);
                    i++;
                }
            }
            exch(nums,i,hi);
            return i;
        }

        private void exch(int[] nums, int i, int j) {
            int t=nums[i];
            nums[i]=nums[j];
            nums[j]=t;
        }

        private boolean less(int v, int w) {
            return v<w;
        }

        private void shuffle(int a[]) {

            final Random random = new Random();
            for(int ind = 1; ind < a.length; ind++) {
                final int r = random.nextInt(ind + 1);
                exch(a, ind, r);
            }
        }
    }

    //9
    class TrieNode {
        public boolean isWord;
        public TrieNode[] children = new TrieNode[26];
        public TrieNode() {}
    }

    public class Trie {
        private TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode ws = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(ws.children[c - 'a'] == null){
                    ws.children[c - 'a'] = new TrieNode();
                }
                ws = ws.children[c - 'a'];
            }
            ws.isWord = true;
        }

        public boolean search(String word) {
            TrieNode ws = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(ws.children[c - 'a'] == null) return false;
                ws = ws.children[c - 'a'];
            }
            return ws.isWord;
        }

        public boolean startsWith(String prefix) {
            TrieNode ws = root;
            for(int i = 0; i < prefix.length(); i++){
                char c = prefix.charAt(i);
                if(ws.children[c - 'a'] == null) return false;
                ws = ws.children[c - 'a'];
            }
            return true;
        }
    }


    //10
     class MinDistance {
        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();

            int[][] cost = new int[m + 1][n + 1];
            for(int i = 0; i <= m; i++)
                cost[i][0] = i;
            for(int i = 1; i <= n; i++)
                cost[0][i] = i;

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(word1.charAt(i) == word2.charAt(j))
                        cost[i + 1][j + 1] = cost[i][j];
                    else {
                        int replace = cost[i][j];
                        int delete = cost[i][j + 1];
                        int insert = cost[i + 1][j];
                        cost[i + 1][j + 1] = Math.min(replace, Math.min(delete, insert)) + 1;
                    }
                }
            }
            return cost[m][n];
        }
    }
}
