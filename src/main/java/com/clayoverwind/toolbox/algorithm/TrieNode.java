package com.clayoverwind.toolbox.algorithm;

/**
 * @author clayoverwind
 * @version 2017/4/11
 * @E-mail clayanddev@163.com
 */
public class TrieNode {
    public char val;
    public int count;
    public TrieNode[] nexts;
    public TrieNode(char val) {
        this.val = val;
        count = 1;
    }

    public void addWord(String word) {
        TrieNode current = this;
        int idx = 0;
        while (idx < word.length()) {
            if (current.nexts == null) {
                current.nexts = new TrieNode[26];
            }
            if (current.nexts[word.charAt(idx) - 'a'] == null) {
                current.nexts[word.charAt(idx) - 'a'] = new TrieNode(word.charAt(idx));
            } else {
                current.nexts[word.charAt(idx) - 'a'].count++;
            }
            current = current.nexts[word.charAt(idx) - 'a'];
            ++idx;
        }
    }

    public int queryPrefixCount(String prefix) {
        int idx = 0;
        TrieNode current = this;
        TrieNode targetNode = null;
        while (current != null && idx < prefix.length()) {
            targetNode = current.nexts == null ? null : current.nexts[prefix.charAt(idx) - 'a'];
            current = targetNode;
            ++idx;
        }
        return targetNode == null ? 0 : targetNode.count;
    }
}