//based off https://leetcode.com/problems/validate-binary-search-tree/
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        //create a queue for the nodes
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        //create a map to let us know what depth each node is on
        HashMap<Node, Integer> dist = new HashMap<Node,Integer>();
        dist.put(root,1);
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        while(q.peek() != null){
            Node curr = q.remove();
            if (ret.size() < dist.get(curr)) {
                ret.add(new ArrayList<Integer>());
            }
            //get the list associated with this depth and add this number to the list
            ret.get(dist.get(curr)-1).add(curr.val);
            //enque all children
            for (Node n: curr.children) {
                q.add(n);
                dist.put(n,dist.get(curr)+1);
            }
        }
        return ret;
    }
}